package io.swagger.service;

import io.swagger.Message;
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

/**
 * Service for the Store API
 */
@Service
public class ApplySpecialService {
  private static final String DISCOUNT_30_PERCENT = "buy2LargePizzaNoTopping";
  private static final String FREE_PIZZA = "buy1get1free";
  private static final String FREE_SODA = "buy1PizzaGetSodaFree";

  @Autowired
  private CartRepository cartRepository;

  @Autowired
  private SpecialItemRepository specialItemRepository;

  @Autowired
  private CartService cartService;

  @Autowired
  private SideService sideService;

  @Autowired
  private PizzaService pizzaService;

  /**
   * Apply the specified special to the given store and cart.
   *
   * @param specialId id of the special being requested
   * @param storeId id of the store that the cart belongs to
   * @param cartId  id of the cart receiving the special
   */
  public ApplySpecialResponse applySpecial(String specialId, String storeId, String cartId)
      throws Exception {
    Cart cart = cartService.getCartItemsById(storeId, cartId);

    // Check if valid special, if cart is at store, and if special has been applied to cart.
    if(!checkSpecial(specialId)) {
      throw new Exception(Message.ERROR_INVALID_SPECIAL); // return nul?????
    }

    if (!checkCartAtStore(cartId, storeId)) {
      throw new Exception(Message.ERROR_NO_CART);
    }

    if (cart.isSpecialApplied()) {
      throw new Exception(Message.ERROR_ONE_SPECIAL);
    }

    switch (specialId) {
    case FREE_PIZZA:
      return applyBuy1Get1Special(storeId, cartId);
    case DISCOUNT_30_PERCENT:
      return apply30PercentOffSpecial(storeId, cartId);
    case FREE_SODA:
      return applyFreeSodaSpecial(storeId, cartId);
    default:
      throw new Exception(Message.ERROR_UNIMPLEMENTED_SPECIAL);
    }
  }

  /**
   * Helper to validate the given special id.
   * 
   * @param specialId id of the special being requested
   * @return {@code true} if the id is a valid special and {@code false}
   *         otherwise.
   */
  private boolean checkSpecial(String specialId) {
    return specialItemRepository.existsById(specialId);
  }

  /**
   * Helper to validate the given cart id exists at the given store id.
   * 
   * @param cartId  id of the cart receiving the special
   * @param storeId id of the store that the cart belongs to
   * @return {@code true} if the cart exists at the particular store and
   *         {@code false} otherwise.
   */
  private boolean checkCartAtStore(String cartId, String storeId) {
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
   * @param cartId  id of the cart receiving the special
   */
  private ApplySpecialResponse applyBuy1Get1Special(String storeId, String cartId)
      throws Exception { // !!! CHANGE TO IOException and remove execption in method!!!
    ApplySpecialResponse applySpecialResponse = new ApplySpecialResponse(FREE_PIZZA);
    Cart cart = cartService.getCartItemsById(storeId, cartId);
    List<Pizza> pizzas = cart.getPizzas();
    Pizza highestCostPizza = null;
    Pizza secondHighestCostPizza = null;
    double highestCostPizzaPrice = 0.00;
    double secondHighestCostPizzaPrice = 0.00;

    // Check if at least two pizzas are in cart.
    if(pizzas.size() < 2) {
      throw new Exception(Message.ERROR_FREE_PIZZA); // !!!! return null??
    }


    // Find highest cost pizza.
    for (Pizza pizza : pizzas) {
      if (pizzaService.getPizzaPrice(pizza) > highestCostPizzaPrice) {
        highestCostPizza = pizza;
        highestCostPizzaPrice = pizzaService.getPizzaPrice(highestCostPizza);
      }
    }

    // Find second highest cost pizza.
    for (Pizza pizza : pizzas) {
      if (!pizza.equals(highestCostPizza) && pizzaService.getPizzaPrice(pizza)
          > secondHighestCostPizzaPrice) {
        secondHighestCostPizza = pizza;
        secondHighestCostPizzaPrice = pizzaService.getPizzaPrice(secondHighestCostPizza);
      }
    }
    // Reduce cart price by the price of the second highest cost pizza (which will now be free).
    Double savings = pizzaService.getPizzaPrice(secondHighestCostPizza);
    applySpecialResponse.setSavings(savings);
    cart.setTotalAmount(cart.getTotalAmount() - savings);
    cart.setSpecialApplied(true);
    cartRepository.save(cart);
    applySpecialResponse.setSuccess(true);

    return applySpecialResponse;
  }

  /**
   * Applies 30% off cart price if cart has 2 large pizzas with no toppings.
   *
   * @param cartId Id of cart being checked for special
   */
  private ApplySpecialResponse apply30PercentOffSpecial(String storeId, String cartId)
      throws Exception {
    ApplySpecialResponse applySpecialResponse = new ApplySpecialResponse(DISCOUNT_30_PERCENT);
    Cart cart = cartService.getCartItemsById(storeId, cartId);
    List<Pizza> pizzas = cart.getPizzas();
    List<Pizza> largePizzas = new ArrayList<>();
    double oldCartPrice = cart.getTotalAmount();
    double newCartPrice;
    final double THIRTY_PERCENT_OFF = 0.70;
    final String LARGE_PIZZA = "large";

    // Put large toppingless pizzas in list.
    for (Pizza pizza : pizzas) {
      if (pizza.getSizeID().equals(LARGE_PIZZA) && pizza.getToppingIDs().size() == 0) {
        largePizzas.add(pizza);
      }
    }

    // Verify there are at least two large pizzas with no toppings.
    if(largePizzas.size() < 2) {
      throw new Exception(Message.ERROR_DISCOUNT_30_PERCENT); // return nul????????
    }

    // Reduce price of cart by 30%.
    newCartPrice = oldCartPrice * THIRTY_PERCENT_OFF;
    cart.setTotalAmount(newCartPrice);
    applySpecialResponse.setSavings(oldCartPrice - newCartPrice);
    cart.setSpecialApplied(true);
    cartRepository.save(cart);
    applySpecialResponse.setSuccess(true);

    return applySpecialResponse;
  }

  /**
   * Helper to determine if a list of SideItems contains a drink.
   *
   * @param sides list of side items
   * @return {@code true} if the list contains a drink and {@code false} otherwise.
   */
  private boolean hasDrink(List<String> sides) {
    for(String sideId : sides) {
      if (sideService.getSideById(sideId).get().getType() == SideItem.TYPE_DRINK) {
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
  private ApplySpecialResponse applyFreeSodaSpecial(String storeId, String cartId)
      throws Exception {
    ApplySpecialResponse applySpecialResponse = new ApplySpecialResponse(FREE_SODA);
    Cart cart = cartService.getCartItemsById(storeId, cartId);
    List<Pizza> pizzas = cart.getPizzas();
    List<String> sides = cart.getSides();
    List<String> drinks = new ArrayList<>();
    double oldCartPrice = cart.getTotalAmount();
    double highestCostDrinkPrice = 0.00;

    // Check that the cart contains at least one pizza and one drink.
    if (pizzas.size() < 1 || !hasDrink(sides)) {
      throw new Exception(Message.ERROR_FREE_SODA); // return nul??
    }

    // Add drinks to a list.
    for (String sideId : sides) {
      if(sideService.getSideById(sideId).get().getType() == SideItem.TYPE_DRINK)
      drinks.add(sideId);
    }

    // Find highest cost drink.
    for (String drinkId : drinks) {
      // Find highest cost drink price
      Double price = sideService.getSideById(drinkId).get().getPrice();
      if (price > highestCostDrinkPrice) {
        highestCostDrinkPrice = price;
      }
    }
    // Reduce cart price by the price of the highest cost drink (which will now be free).
    applySpecialResponse.setSavings(highestCostDrinkPrice);
    double newCartPrice = oldCartPrice - highestCostDrinkPrice;
    cart.setTotalAmount(newCartPrice);
    cart.setSpecialApplied(true);
    cartRepository.save(cart);
    applySpecialResponse.setSuccess(true);

    return applySpecialResponse;
  }
}