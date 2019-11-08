package io.swagger.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import io.swagger.model.Cart;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@javax.annotation.Generated(
    value = "io.swagger.codegen.v3.generators.java.SpringCodegen",
    date = "2019-09-26T03:54:46.062Z[GMT]")
@Api(value = "cart", description = "Cart API")
public interface CartApi {

  // get all items from specific Cart from a store by using storeID, cartID
  ResponseEntity<Cart> getCartItemsById(
      @ApiParam(value = "StoreID") @PathVariable("storeId") String storeId,
      @ApiParam(value = "CartID") @PathVariable("cartId") String cartId);

  // get total amount of specific Cart from a store by using storeID, cartID
  ResponseEntity<Double> getPriceOfCartById(
      @ApiParam(value = "StoreID") @PathVariable("storeId") String storeId,
      @ApiParam(value = "CartID") @PathVariable("cartId") String cartId);

  // add pizza to specific Cart to store by using storeID, cartID, with pizzaSizeID, gluten, toppingIDs
  ResponseEntity<Cart> addPizzaToCart(
      @ApiParam(value = "StoreID") @PathVariable("storeId") String storeId,
      @ApiParam(value = "CartID") @PathVariable("cartId") String cartId,
      @ApiParam(value = "PizzaSizeID") @RequestParam(required = true) String sizeId,
      @ApiParam(value = "Gluten") @RequestParam(required = true) boolean gluten,
      @ApiParam(value = "ToppingID") @RequestParam(required = false) String topping1,
      @ApiParam(value = "ToppingID") @RequestParam(required = false) String topping2,
      @ApiParam(value = "ToppingID") @RequestParam(required = false) String topping3,
      @ApiParam(value = "ToppingID") @RequestParam(required = false) String topping4);

  // add side to specific Cart found from store by using storeID, cartID, with sideID
  ResponseEntity<Cart> addSideToCart(
      @ApiParam(value = "StoreID")@PathVariable("storeId") String storeId,
      @ApiParam(value = "CartID") @PathVariable("cartId") String cartId,
      @ApiParam(value = "SideID") @RequestParam(required = true) String sideID);

  // delete a cart from a store by using by using storeID, cartID
  ResponseEntity deleteCart(
      @ApiParam(value = "StoreID") @PathVariable("storeId") String storeId,
      @ApiParam(value = "CartID") @PathVariable("cartId") String cartId);

  // delete a side item in cart by using by using storeID, cartID, sideID
  ResponseEntity<String> deleteSideFromCart(
      @ApiParam(value = "StoreID") @PathVariable("storeId") String storeId,
      @ApiParam(value = "CartID") @PathVariable("cartId") String cartId,
      @ApiParam(value = "SideID") @RequestParam(required = true) String sideId);

  // delete a pizza in cart by using by using storeID, cartID, an index from pizza array
  ResponseEntity<String> deletePizzaFromCart(
      @ApiParam(value = "StoreID") @PathVariable("storeId") String storeId,
      @ApiParam(value = "CartID") @PathVariable("cartId") String cartId,
      @ApiParam(value = "index of Pizza Array") @RequestParam(required = true) Integer pizzaIndex);


}
