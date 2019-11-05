package io.swagger.service;

import io.swagger.model.Cart;
import io.swagger.model.Pizza;
import io.swagger.model.SideItem;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
/**
 * Service for the Store API
 */
public class ApplySpecialsService {
  private Cart cart;
  private CartService cartService;
  SideService sideService;
  private String errorMessage = "Special does not apply: ";
  private String successMessage = "Success! ";

  String largePizza = "large";

  /**
   * Adds a pizza for free if new pizza is of equal or lesser value to highest cost pizza in cart.
   * Adds the pizza at regular price if new pizza is of higher value than all pizzas in cart.
   *
   * @param cartId Id of cart being checked for special
   * @return Success message that pizza was added to cart or error message if special is not valid
   * for cart.
   */
  public String applyBuy1Get1Special(String cartId, String storeId, String sizeId, boolean gluten,
      String topping1, String topping2, String topping3, String topping4) {
    // Add requested pizza to cart.
    cartService.addPizzaToCart(storeId, cartId, sizeId, gluten, topping1, topping2, topping3,
        topping4);
    cart = cartService.getCartItemsById(storeId, cartId);
    List<Pizza> pizzas = cart.getPizzas();
    // Assign last (most recently added) pizza to a variable.
    Pizza newPizza = pizzas.get(pizzas.size()-1);
    Double newPizzaInitialPrice = newPizza.getPrice();

    if (pizzas.size() == 1) { // No pizzas were in cart before new one was added
      return errorMessage + "Cart must contain a pizza to receive an additional pizza for free.";
    } else if (pizzas.size() > 1) {
      for (Pizza pizza : pizzas) {
        if(pizza.getPrice() >= newPizzaInitialPrice) {
          newPizza.setPrice(0.00);
        }
      }
    }
    return successMessage + "Pizza added to cart.";
  }

  /**
   * Applies 30% off cart price if cart contains 2 large pizzas with no toppings.
   *
   * @param cartId Id of cart being checked for special
   * @return Success message with new cart total and savings or error message if special is not
   * valid for cart.
   */
  public String apply30PercentOffSpecial(String cartId, String storeId) {
    cart = cartService.getCartItemsById(storeId, cartId);
    List<Pizza> pizzas = cart.getPizzas();
    List<Pizza> largePizzas = new ArrayList<>();
    double oldCartPrice = cart.getTotalAmount();
    double newCartPrice;
    double priceDifference;
    double thirtyPercentOff = 0.70;

    for (Pizza pizza : pizzas) {
      if (pizza.getSizeID() == largePizza && pizza.getToppingCount() == 0) {
        largePizzas.add(pizza);
      }
    }

    if (largePizzas.size() >= 2) {
      newCartPrice = oldCartPrice * thirtyPercentOff;
      cart.setTotalAmount(newCartPrice);
      priceDifference = oldCartPrice - newCartPrice;
      return successMessage
          + "New total is" + cart.getTotalAmount() + ". You saved " + priceDifference + "!";
    } else {
      return errorMessage
          + "Cart must contain at least 2 large pizzas with no toppings for 30% off.";
    }
  }

  /**
   * Adds a soda to cart for free if at least one pizza is in cart.
   *
   * @param cartId Id of cart being checked for special
   * @return Success message if soda was added to cart or error message if special is not valid for
   * cart.
   */
  public String applyFreeSodaSpecial(String cartId, String storeId, String drinkId) {
    cart = cartService.getCartItemsById(storeId, cartId);
    List<Pizza> pizzas = cart.getPizzas();
    List<String> sides = cart.getSides();

    if (pizzas != null) {
      cartService.addSideToCart(storeId, cartId, drinkId);
      String newSideId = sides.get(sides.size()-1);
      SideItem newSide = sideService.getSideById(newSideId);
      newSide.setPrice(0.00);
      return successMessage + "Drink added to cart.";
    } else {
      return errorMessage + "Cart must contain at least one pizza to receive a drink for free.";
    }
  }
}