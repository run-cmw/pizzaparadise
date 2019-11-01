package io.swagger.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import io.swagger.model.Cart;
import io.swagger.model.PizzaSize;
import io.swagger.model.SideItem;
import io.swagger.model.ToppingItem;
import javax.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@javax.annotation.Generated(
    value = "io.swagger.codegen.v3.generators.java.SpringCodegen",
    date = "2019-09-26T03:54:46.062Z[GMT]")
@Api(value = "cart", description = "Cart API")
public interface CartApi {

  // get all items from specific Cart found by cartID
  ResponseEntity<Cart> getCartItemsById(@ApiParam(value = "CartID") @PathVariable String id);

  // get total amount of specific Cart found by cartID
  ResponseEntity<Double> getPriceOfCartById(@ApiParam(value = "CartID") @PathVariable String id);

  // add pizza to specific Cart found by cartID, with pizzaSizeID, gluten, toppingIDs
  ResponseEntity<Cart> addPizzaToCart(
      @ApiParam(value = "CartID") @PathVariable String id,
      @ApiParam(value = "PizzaSizeID") @RequestParam(required = true) String sizeId,
      @ApiParam(value = "Gluten") @RequestParam(required = true) boolean gluten,
      @ApiParam(value = "ToppingID") @RequestParam(required = false) String topping1,
      @ApiParam(value = "ToppingID") @RequestParam(required = false) String topping2,
      @ApiParam(value = "ToppingID") @RequestParam(required = false) String topping3,
      @ApiParam(value = "ToppingID") @RequestParam(required = false) String topping4);

  // add side to specific Cart found by cartID, with sideID
  ResponseEntity<Cart> addSideToCart(
      @ApiParam(value = "CartID") @PathVariable String id,
      @ApiParam(value = "SideID") @RequestParam(required = true) String sideID);

  ResponseEntity<String> deleteCart(@ApiParam(value = "CartID") @PathVariable String id);

}
