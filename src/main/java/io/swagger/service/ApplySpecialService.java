package io.swagger.service;

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

  private static final String ERROR_DISCOUNT_30_PERCENT =
      "ERROR_CART_MUST_CONTAIN_TWO_LARGE_TOPPINGLESS_PIZZAS";
  private static final String ERROR_FREE_PIZZA = "ERROR_CART_MUST_CONTAIN_TWO_OR_MORE_PIZZAS";
  private static final String ERROR_FREE_SODA =
      "ERROR_CART_MUST_CONTAIN_ONE_OR_MORE_PIZZAS_AND_DRINKS";
  private static final String ERROR_INVALID_SPECIAL = "ERROR_INVALID_SPECIAL";
  private static final String ERROR_NO_CART = "ERROR_CART_NOT_AT_STORE";
  private static final String ERROR_ONE_SPECIAL = "ERROR_ONLY_ONE_SPECIAL_PER_CART";
  private static final String ERROR_UNIMPLEMENTED_SPECIAL = "ERROR_SPECIAL_NOT_IMPLEMENTED";

  @Autowired
  private CartRepository cartRepository;

  @Autowired
  private SpecialItemRepository specialItemRepository;

  @Autowired
  private CartService cartService;

  @Autowired
  private SideService sideService;

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

    if (cart.isSpecialApplied()) {
      throw new Exception(ERROR_ONE_SPECIAL);
    }

    if (!checkCartAtStore(cartId, storeId)) {
      throw new Exception(ERROR_NO_CART);
    }

    if (checkSpecial(specialId)) {
      switch (specialId) {
      case FREE_PIZZA:
        return applyBuy1Get1Special(storeId, cartId);
      case DISCOUNT_30_PERCENT:
        return apply30PercentOffSpecial(storeId, cartId);
      case FREE_SODA:
        return applyFreeSodaSpecial(storeId, cartId);
      default:
        throw new Exception(ERROR_UNIMPLEMENTED_SPECIAL);
      }
    } else {
      throw new Exception(ERROR_INVALID_SPECIAL);
    }
  }

  /**
   * Helper to validate the given special id.
   * 
   * @param specialId id of the special being requested
   * @return {@code true} if the id is a valid special and {@code false}
   *         otherwise.
   */
  protected boolean checkSpecial(String specialId) {
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
  protected boolean checkCartAtStore(String cartId, String storeId) {
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
  protected ApplySpecialResponse applyBuy1Get1Special(String storeId, String cartId) throws Exception {
    ApplySpecialResponse applySpecialResponse = new ApplySpecialResponse(FREE_PIZZA);
    Cart cart = cartService.getCartItemsById(storeId, cartId);
    List<Pizza> pizzas = cart.getPizzas();
    Pizza highestCostPizza = null;
    Pizza secondHighestCostPizza = null;
    double highestCostPizzaPrice = 0.00;
    double secondHighestCostPizzaPrice = 0.00;

    if (pizzas.size() > 1) {
      // Find highest cost pizza.
      for (Pizza pizza : pizzas) {
        if (pizza.getPrice() > highestCostPizzaPrice) {
          highestCostPizza = pizza;
          highestCostPizzaPrice = highestCostPizza.getPrice();
        }
      }

      // Find second highest cost pizza.
      for (Pizza pizza : pizzas) {
        if (!pizza.equals(highestCostPizza) && pizza.getPrice() > secondHighestCostPizzaPrice) {
          secondHighestCostPizza = pizza;
          secondHighestCostPizzaPrice = secondHighestCostPizza.getPrice();
        }
      }
      // Make price of pizza of equal or lesser value free.
      applySpecialResponse.setSavings(secondHighestCostPizza.getPrice());
      secondHighestCostPizza.setPrice(0.00);
      cart.setSpecialApplied(true);
      cartRepository.save(cart);
      applySpecialResponse.setSuccess(true);
    } else {
      throw new Exception(ERROR_FREE_PIZZA);
    }
    return applySpecialResponse;
  }

  /**
   * Applies 30% off cart price if cart has 2 large pizzas with no toppings.
   *
   * @param cartId Id of cart being checked for special
   */
  protected ApplySpecialResponse apply30PercentOffSpecial(String storeId, String cartId) throws Exception {
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
      if (pizza.getSizeID().equals(LARGE_PIZZA) && pizza.getToppingCount() == 0) {
        largePizzas.add(pizza);
      }
    }

    // Verify at least two large pizzas with no toppings.
    if (largePizzas.size() >= 2) {
      // Reduce price of cart by 30%.
      newCartPrice = oldCartPrice * THIRTY_PERCENT_OFF;
      cart.setTotalAmount(newCartPrice);
      applySpecialResponse.setSavings(oldCartPrice - newCartPrice);
      cart.setSpecialApplied(true);
      cartRepository.save(cart);
      applySpecialResponse.setSuccess(true);
    } else {
      throw new Exception(ERROR_DISCOUNT_30_PERCENT);
    }
    return applySpecialResponse;
  }

  /**
   * Helper to determine if a list of SideItems contains a drink.
   *
   * @param sides list of side items
   * @return {@code true} if the list contains a drink and {@code false} otherwise.
   */
  private boolean hasDrink(List<SideItem> sides) {
    for(SideItem side : sides) {
      if (side.getType() == SideItem.TYPE_DRINK) {
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
  protected ApplySpecialResponse applyFreeSodaSpecial(String storeId, String cartId) throws Exception {
    ApplySpecialResponse applySpecialResponse = new ApplySpecialResponse(FREE_SODA);

    Cart cart = cartService.getCartItemsById(storeId, cartId);
    List<Pizza> pizzas = cart.getPizzas();
    List<SideItem> sides = new ArrayList<>();
    double oldCartPrice = cart.getTotalAmount();
    double highestCostDrinkPrice = 0.00;

    for (String sideId : cart.getSides()) {
      sides.add(sideService.getSideById(sideId));
    }

    if (pizzas.size() == 0 || !hasDrink(sides)) {
      throw new Exception(ERROR_FREE_SODA);
    }

    for (SideItem side : sides) {
      // Find highest cost drink price
      Double price = side.getPrice();
      if (price > highestCostDrinkPrice) {
        highestCostDrinkPrice = price;
      }
    }
    // Make price of drink free. Must subtract price of drink from cart total bc cart design does
    // not have side prices. We want to change the price of the cart's side, not change the price
    // of the actual SideItem in the database with setPrice().
    applySpecialResponse.setSavings(highestCostDrinkPrice);
    double newCartPrice = oldCartPrice - highestCostDrinkPrice;
    cart.setTotalAmount(newCartPrice);
    cart.setSpecialApplied(true);
    cartRepository.save(cart);
    applySpecialResponse.setSuccess(true);

    return applySpecialResponse;
  }
}