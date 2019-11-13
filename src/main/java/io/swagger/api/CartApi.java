package io.swagger.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import io.swagger.model.Cart;
import io.swagger.model.CartAddResponse;
import io.swagger.model.PriceResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@javax.annotation.Generated(
    value = "io.swagger.codegen.v3.generators.java.SpringCodegen",
    date = "2019-09-26T03:54:46.062Z[GMT]")
@Api(value = "cart", description = "Cart API")
public interface CartApi {

  /**
   * get all items from specific Cart from a store by using storeID, cartID.
   * @param storeId storeId given to this API.
   * @param cartId cartId given to this API.
   * @return Cart shows all information of this Cart.
   */
  ResponseEntity<Cart> getCartItemsById(
      @ApiParam(value = "StoreID") @PathVariable("storeId") String storeId,
      @ApiParam(value = "CartID") @PathVariable("cartId") String cartId);

  /**
   * get total amount of specific Cart from a store by using storeID, cartID.
   * @param storeId storeId given to this API.
   * @param cartId cartId given to this API.
   * @return PriceResponse contains information about "Success", "Total Price", and "Currency" of Cart
   */
  ResponseEntity<PriceResponse> getPriceOfCartById(
      @ApiParam(value = "StoreID") @PathVariable("storeId") String storeId,
      @ApiParam(value = "CartID") @PathVariable("cartId") String cartId);


  /**
   * add pizza to specific Cart to store by using storeID, cartID, with pizzaSizeID, gluten, toppingIDs
   * @param storeId storeId given to this API.
   * @param cartId cartId given to this API.
   * @param sizeId sizeId given to this API.
   * @param gluten gluten given to this API.(true = gluten / false = gluten free);
   * @param topping1 ToppingId given to this API.
   * @param topping2 ToppingId given to this API.
   * @param topping3 ToppingId given to this API.
   * @param topping4 ToppingId given to this API.
   * @return CartAddResponse contains all information about pizza that is added.
   */
  ResponseEntity<CartAddResponse> addPizzaToCart(
      @ApiParam(value = "StoreID") @PathVariable("storeId") String storeId,
      @ApiParam(value = "CartID") @PathVariable("cartId") String cartId,
      @ApiParam(value = "PizzaSizeID") @RequestParam(required = true) String sizeId,
      @ApiParam(value = "Gluten") @RequestParam(required = true) boolean gluten,
      @ApiParam(value = "ToppingID") @RequestParam(required = false) String topping1,
      @ApiParam(value = "ToppingID") @RequestParam(required = false) String topping2,
      @ApiParam(value = "ToppingID") @RequestParam(required = false) String topping3,
      @ApiParam(value = "ToppingID") @RequestParam(required = false) String topping4);


  /**
   * add side to specific Cart found from store by using storeID, cartID, with sideID.
   * @param storeId storeId given to this API.
   * @param cartId cartId given to this API.
   * @param sideId sideId given to this API.
   * @return CartAddResponse contains all information about side that is added.
   */
  ResponseEntity<CartAddResponse> addSideToCart(
      @ApiParam(value = "StoreID")@PathVariable("storeId") String storeId,
      @ApiParam(value = "CartID") @PathVariable("cartId") String cartId,
      @ApiParam(value = "SideID") @RequestParam(required = true) String sideId);

  /**
   * delete a cart from a store by using by using storeID, cartID.
   * @param storeId storeId given to this API.
   * @param cartId cartId given to this API.
   * @return HttpStatus correspond to this action.
   */
  HttpStatus deleteCart(
      @ApiParam(value = "StoreID") @PathVariable("storeId") String storeId,
      @ApiParam(value = "CartID") @PathVariable("cartId") String cartId);

  /**
   * delete a side item in cart by using by using storeID, cartID, sideID.
   * @param storeId storeId given to this API.
   * @param cartId cartId given to this API.
   * @param sideId  sideId given to this API.
   * @return HttpStatus correspond to this action.
   */
  HttpStatus deleteSideFromCart(
      @ApiParam(value = "StoreID") @PathVariable("storeId") String storeId,
      @ApiParam(value = "CartID") @PathVariable("cartId") String cartId,
      @ApiParam(value = "SideID") @RequestParam(required = true) String sideId);

  /**
   * delete a pizza in cart by using by using storeID, cartID, an index from pizza List.
   * @param storeId storeId given to this API.
   * @param cartId cartId given to this API.
   * @param pizzaIndex  pizzaIndex given to this API.
   * @return HttpStatus correspond to this action.
   */
  HttpStatus deletePizzaFromCart(
      @ApiParam(value = "StoreID") @PathVariable("storeId") String storeId,
      @ApiParam(value = "CartID") @PathVariable("cartId") String cartId,
      @ApiParam(value = "index of Pizza Array") @RequestParam(required = true) Integer pizzaIndex);


}
