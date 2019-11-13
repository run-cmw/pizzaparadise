package io.swagger.api;

import io.swagger.annotations.ApiOperation;
import io.swagger.model.Cart;
import io.swagger.model.CartAddResponse;
import io.swagger.model.PriceResponse;
import io.swagger.service.CartService;
import io.swagger.service.PizzaSizeService;
import io.swagger.service.SideService;
import io.swagger.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@javax.annotation.Generated(
    value = "io.swagger.codegen.v3.generators.java.SpringCodegen",
    date = "2019-09-26T03:54:46.062Z[GMT]")
@RestController
public class CartApiController implements CartApi {

  @Autowired
  private CartService cartService;
  @Autowired
  private PizzaSizeService sizeService;
  @Autowired
  private SideService sideService;
  @Autowired
  private StoreService storeService;

  /**
   * {@inheritDoc}
   * HttpStatus.NOT_FOUND - if storeId and cartId are not matching.
   * HttpStatus.OK - if Cart was successfully found.
   */
  @GetMapping("store/{storeId}/cart/{cartId}")
  @ApiOperation(
      value = "Get all items in the cart with specific id.",
      tags = {
          "cart",
      })
  public ResponseEntity<Cart> getCartItemsById(String storeId, String cartId) {
    if (cartService.getCartItemsById(storeId, cartId) == null) {
      return new ResponseEntity<Cart>(HttpStatus.NOT_FOUND);
    }
    return new ResponseEntity<Cart>(cartService.getCartItemsById(storeId, cartId), HttpStatus.OK);
  }

  /**
   * {@inheritDoc}
   * HttpStatus.NOT_FOUND - if storeId and cartId are not matching.
   * HttpStatus.OK - if price of the Cart was successfully found.
   */
  @GetMapping("/store/{storeId}/cart/{cartId}/price")
  @ApiOperation(
      value = "Get price of all items in the cart with specific id.",
      tags = {
          "cart",
      })
  public ResponseEntity<PriceResponse> getPriceOfCartById(String storeId, String cartId) {
    if (cartService.getCartItemsById(storeId, cartId) == null) {
      PriceResponse response = new PriceResponse(false, null, null);
      return new ResponseEntity<PriceResponse>(response, HttpStatus.NOT_FOUND);
    }

    return new ResponseEntity<PriceResponse>(cartService.getTotalAmountInCart(cartId),
        HttpStatus.OK);
  }

  /**
   * {@inheritDoc}
   * HttpStatus.NOT_FOUND - if the storeId is not found.
   * HttpStatus.NOT_FOUND - if the pizzaSizeId is not found.
   * HttpStatus.NOT_FOUND - if the store's gluten setting does not match with given gluten.
   * HttpStatus.CREATED - if pizza was successfully added to Cart.
   */
  @PostMapping("/store/{storeId}/cart/{cartId}/add/pizza")
  @ApiOperation(
      value = "add pizza to the specific cart.",
      tags = {
          "cart",
      })
  public ResponseEntity<CartAddResponse> addPizzaToCart(String storeId, String cartId,
      String sizeId, boolean gluten,
      String topping1, String topping2, String topping3, String topping4) {
    CartAddResponse response;
    String message;
    if (storeService.getStoreById(storeId) == null) {
      message = "This storeID is not found.";
      response = new CartAddResponse(false, null, null, null, message);
      return new ResponseEntity<CartAddResponse>(response, HttpStatus.NOT_FOUND);
    }
    if (sizeService.getPizzaSizeById(sizeId) == null) {
      message = "This pizza size is not found.";
      response = new CartAddResponse(false, null, null, null, message);
      return new ResponseEntity<CartAddResponse>(response, HttpStatus.NOT_FOUND);
    }

    response = cartService.addPizzaToCart(storeId, cartId, sizeId, gluten, topping1, topping2,
        topping3, topping4);
    if (response.success == false) {
      return new ResponseEntity<CartAddResponse>(response, HttpStatus.NOT_FOUND);
    } else {
      return new ResponseEntity<CartAddResponse>(response, HttpStatus.CREATED);
    }

  }

  /**
   * {@inheritDoc}
   * HttpStatus.NOT_FOUND - if the storeId is not found.
   * HttpStatus.NOT_FOUND - if the sideId is not found.
   * HttpStatus.CREATED - if side was successfully added to Cart.
   */
  @PostMapping("/store/{storeId}/cart/{cartId}/add/side")
  @ApiOperation(
      value = "add side to the specific cart.",
      tags = {
          "cart",
      })
  public ResponseEntity<CartAddResponse> addSideToCart(String storeId, String cartId,
      String sideId) {
    CartAddResponse response;
    String message;
    if (storeService.getStoreById(storeId) == null) {
      message = "This storeID is not found.";
      response = new CartAddResponse(false, null, null, null, message);
      return new ResponseEntity<CartAddResponse>(response, HttpStatus.NOT_FOUND);
    }
    if (sideService.getSideById(sideId) == null) {
      message = "This SideID is not found.";
      response = new CartAddResponse(false, null, null, null, message);
      return new ResponseEntity<CartAddResponse>(response, HttpStatus.NOT_FOUND);
    }
    response = cartService.addSideToCart(storeId, cartId, sideId);
    return new ResponseEntity<CartAddResponse>(response, HttpStatus.CREATED);

  }

  /**
   * {@inheritDoc}
   * HttpStatus.NOT_FOUND - if storeId and cartId are not matching.
   * HttpStatus.NO_CONTENT - if cartId is successfully removed.
   */
  @DeleteMapping("store/{storeId}/cart/{cartId}/delete")
  @ApiOperation(
      value = "Delete a Cart with id.",
      tags = {
          "cart",
      })
  public HttpStatus deleteCart(String storeId, String cartId) {
    if (cartService.getCartItemsById(storeId, cartId) == null) {
      return HttpStatus.NOT_FOUND;
    }
    return cartService.deleteCart(cartId);
  }

  /**
   * {@inheritDoc}
   * HttpStatus.NOT_FOUND - if storeId and cartId are not matching.
   * HttpStatus.NOT_FOUND - if the sideId is not found.
   * HttpStatus.NO_CONTENT - if side is successfully removed from Cart.
   */
  @DeleteMapping("store/{storeId}/cart/{cartId}/delete/side")
  @ApiOperation(
      value = "Delete a sideItem from a Cart with id.",
      tags = {
          "cart",
      })
  public HttpStatus deleteSideFromCart(String storeId, String cartId, String sideId) {
    if (cartService.getCartItemsById(storeId, cartId) == null) {
      return HttpStatus.NOT_FOUND;
    }
    if (sideService.getSideById(sideId) == null) {
      return HttpStatus.NOT_FOUND;
    }
    return cartService.deleteSideFromCart(cartId, sideId);

  }

  /**
   * {@inheritDoc}
   * HttpStatus.NOT_FOUND - if storeId and cartId are not matching.
   * HttpStatus.BAD_REQUEST - if given pizzaIndex is less than 0.
   * HttpStatus.NO_CONTENT - if side is successfully removed from Cart.
   */
  @DeleteMapping("store/{storeId}/cart/{cartId}/delete/pizza")
  @ApiOperation(
      value = "Delete a pizza from a Cart with index number(starting zero).",
      tags = {
          "cart",
      })
  public HttpStatus deletePizzaFromCart(String storeId, String cartId, Integer pizzaIndex) {
    if (cartService.getCartItemsById(storeId, cartId) == null) {
      return HttpStatus.NOT_FOUND;
    }
    if (pizzaIndex < 0) {
      return HttpStatus.BAD_REQUEST;
    }
    return cartService.deletePizzaFromCart(cartId, pizzaIndex);
  }

}
