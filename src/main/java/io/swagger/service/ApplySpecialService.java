package io.swagger.service;

import io.swagger.Message;
import io.swagger.exceptions.ToppingNotFoundException;
import io.swagger.model.ApplySpecialResponse;
import io.swagger.model.Cart;
import io.swagger.model.Pizza;
import io.swagger.model.SideItem;
import io.swagger.repository.CartRepository;
import io.swagger.repository.SpecialItemRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/** Service for the Store API */
@Service
public class ApplySpecialService {
  private static final String DISCOUNT_30_PERCENT = "buy2LargePizzaNoTopping";
  private static final String FREE_PIZZA = "buy1Get1Free";
  private static final String FREE_SODA = "buy1PizzaGetSodaFree";

  @Autowired private CartRepository cartRepository;

  @Autowired private SpecialItemRepository specialItemRepository;

  @Autowired private CartService cartService;

  @Autowired private SideService sideService;

  @Autowired private PizzaService pizzaService;

  /**
   * Apply the specified special to the given store and cart.
   *
   * @param specialId id of the special being requested
   * @param storeId id of the store that the cart belongs to
   * @param cartId id of the cart receiving the special
   * @throws Exception through applyBuy1Get1Special()'s getPizzaPrice() if invalid topping
   */
  public ApplySpecialResponse applySpecial(String specialId, String storeId, String cartId) {
    Cart cart = cartService.getCartItemsById(storeId, cartId);

    // Check if valid special, if cart is at store, and if special has been applied to cart.
    if (!checkSpecial(specialId)) {
      return null;
    }

    if (!checkCartAtStore(cartId, storeId)) {
      return null;
    }

    if (cart.isSpecialApplied()) {
      return null;
    }

    switch (specialId) {
      case FREE_PIZZA:
        return applyBuy1Get1Special(storeId, cartId);
      case DISCOUNT_30_PERCENT:
        return apply30PercentOffSpecial(storeId, cartId);
      case FREE_SODA:
        return applyFreeSodaSpecial(storeId, cartId);
      default:
        return null;
    }
  }

  /**
   * Helper to validate the given special id.
   *
   * @param specialId id of the special being requested
   * @return {@code true} if the id is a valid special and {@code false} otherwise.
   */
  public boolean checkSpecial(String specialId) {
    return specialItemRepository.existsById(specialId);
  }

  /**
   * Helper to validate the given cart id exists at the given store id.
   *
   * @param cartId id of the cart receiving the special
   * @param storeId id of the store that the cart belongs to
   * @return {@code true} if the cart exists at the particular store and {@code false} otherwise.
   */
  public boolean checkCartAtStore(String cartId, String storeId) {
    for (Cart cart : cartRepository.findAll()) {
      if (cart.getId().equals(cartId) && cart.getStoreID().equals(storeId)) {
        return true;
      }
    }
    return false;
  }

  /**
   * Makes the price of the second highest cost pizza free.
   *
   * @param storeId id of the store that the cart belongs to
   * @param cartId id of the cart receiving the special
   * @throws Exception through getPizzaPrice(Pizza pizza) if invalid topping.
   */
  public ApplySpecialResponse applyBuy1Get1Special(String storeId, String cartId) {
    Cart cart = cartService.getCartItemsById(storeId, cartId);
    List<Pizza> pizzas = cart.getPizzas();
    double highestCostPizzaPrice = 0.00;
    double secondHighestCostPizzaPrice = 0.00;

    // Verify there are at least two pizzas in cart.
    if (pizzas.size() < 2) {
      return null;
    }

    // Find highest cost pizza.
    for (Pizza pizza : pizzas) {
      final Double pizzaPrice;
      try {
        pizzaPrice = pizzaService.getPizzaPrice(pizza);
      } catch (ToppingNotFoundException e) {
        // Should be unreachable if the cart is valid
        throw new RuntimeException(e);
      }

      if (pizzaPrice > highestCostPizzaPrice) {
        secondHighestCostPizzaPrice = highestCostPizzaPrice;
        highestCostPizzaPrice = pizzaPrice;
      } else if (pizzaPrice > secondHighestCostPizzaPrice) {
        secondHighestCostPizzaPrice = pizzaPrice;
      }
    }

    // Reduce cart price by the price of the second highest cost pizza (which will now be free).
    Double savings = secondHighestCostPizzaPrice;
    cart.setTotalAmount(cart.getTotalAmount() - savings);
    cart.setSpecialApplied(true);
    cartRepository.save(cart);

    return new ApplySpecialResponse(FREE_PIZZA, savings);
  }

  /**
   * Applies 30% off cart price if cart has 2 large pizzas with no toppings.
   *
   * @param cartId Id of cart being checked for special
   */
  public ApplySpecialResponse apply30PercentOffSpecial(String storeId, String cartId) {
    Cart cart = cartService.getCartItemsById(storeId, cartId);
    List<Pizza> pizzas = cart.getPizzas();
    List<Pizza> largePizzas = new ArrayList<>();
    double oldCartPrice = cart.getTotalAmount();
    double newCartPrice;
    final double thirtyPercentOff = 0.70;
    final String largePizza = "large";

    // Put large toppingless pizzas in list.
    for (Pizza pizza : pizzas) {
      if (pizza.getSizeID().equals(largePizza) && pizza.getToppingIDs().size() == 0) {
        largePizzas.add(pizza);
      }
    }

    // Verify there are at least two large pizzas with no toppings.
    if (largePizzas.size() < 2) {
      return null;
    }

    // Reduce price of cart by 30%.
    newCartPrice = oldCartPrice * thirtyPercentOff;
    cart.setTotalAmount(newCartPrice);
    cart.setSpecialApplied(true);
    cartRepository.save(cart);

    return new ApplySpecialResponse(DISCOUNT_30_PERCENT, oldCartPrice - newCartPrice);
  }

  /**
   * Helper to determine if a list of SideItems contains a drink.
   *
   * @param sides list of side items
   * @return {@code true} if the list contains a drink and {@code false} otherwise.
   */
  private boolean hasDrink(List<String> sides) {
    for (String sideId : sides) {
      if (sideService.getSideById(sideId).getType().equals(SideItem.TYPE_DRINK)) {
        return true;
      }
    }
    return false;
  }

  /**
   * Makes the price of the highest cost drink free.
   *
   * @param cartId Id of cart being checked for special
   */
  public ApplySpecialResponse applyFreeSodaSpecial(String storeId, String cartId) {
    Cart cart = cartService.getCartItemsById(storeId, cartId);
    List<Pizza> pizzas = cart.getPizzas();
    List<String> sides = cart.getSides();
    List<String> drinks = new ArrayList<>();
    double oldCartPrice = cart.getTotalAmount();
    double highestCostDrinkPrice = 0.00;

    // Verify that the cart contains at least one pizza and one drink.
    if (pizzas.size() < 1 || !hasDrink(sides)) {
      return null;
    }

    // Add drinks to a list.
    for (String sideId : sides) {
      if (sideService.getSideById(sideId).getType().equals(SideItem.TYPE_DRINK)) drinks.add(sideId);
    }

    // Find highest cost drink.
    for (String drinkId : drinks) {
      // Find highest cost drink price
      Double price = sideService.getSideById(drinkId).getPrice();
      if (price > highestCostDrinkPrice) {
        highestCostDrinkPrice = price;
      }
    }
    // Reduce cart price by the price of the highest cost drink (which will now be free).
    double newCartPrice = oldCartPrice - highestCostDrinkPrice;
    cart.setTotalAmount(newCartPrice);
    cart.setSpecialApplied(true);
    cartRepository.save(cart);

    return new ApplySpecialResponse(FREE_SODA, highestCostDrinkPrice);
  }
}
