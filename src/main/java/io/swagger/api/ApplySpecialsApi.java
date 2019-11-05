package io.swagger.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@javax.annotation.Generated(
    value = "io.swagger.codegen.v3.generators.java.SpringCodegen",
    date = "2019-09-26T03:54:46.062Z[GMT]")
@Api(value = "applySpecials", description = "Apply Specials API")

/**
 * Interface for the Apply Specials API
 */
public interface ApplySpecialsApi {
  /**
   * Adds a pizza for free if new pizza is of equal or lesser value to highest cost pizza in cart.
   * Adds the pizza at regular price if new pizza is of higher value than all pizzas in cart.
   *
   * @param cartId Id of cart being checked for special
   * @return Success message that pizza was added to cart or error message if special is not valid
   * for cart.
   */
  ResponseEntity<String> applyBuy1Get1Special(
      @ApiParam(value = "CartID") @PathVariable("cartId") String cartId,
      @ApiParam(value = "StoreID") @PathVariable("storeId") String storeId,
      @ApiParam(value = "PizzaSizeID") @RequestParam(required = true) String sizeId,
      @ApiParam(value = "Gluten") @RequestParam(required = true) boolean gluten,
      @ApiParam(value = "ToppingID") @RequestParam(required = false) String topping1,
      @ApiParam(value = "ToppingID") @RequestParam(required = false) String topping2,
      @ApiParam(value = "ToppingID") @RequestParam(required = false) String topping3,
      @ApiParam(value = "ToppingID") @RequestParam(required = false) String topping4);

  /**
   * Applies 30% off cart price if cart contains 2 large pizzas with no toppings.
   *
   * @param cartId Id of cart being checked for special
   * @return Success message with new cart total and savings or error message if special is not
   * valid for cart.
   */
  ResponseEntity<String> apply30PercentOffSpecial(
      @ApiParam(value = "CartID") @PathVariable("cartId") String cartId,
      @ApiParam(value = "StoreID") @PathVariable("storeId") String storeId);

  /**
   * Adds a soda to cart for free if at least one pizza is in cart.
   *
   * @param cartId Id of cart being checked for special
   * @return Success message if soda was added to cart or error message if special is not valid for
   * cart.
   */
  ResponseEntity<String> applyFreeSodaSpecial(
      @ApiParam(value = "CartID") @PathVariable("cartId") String cartId,
      @ApiParam(value = "StoreID") @PathVariable("storeId") String storeId,
      @ApiParam(value = "SideID") @RequestParam(required = true) String drinkId);
}