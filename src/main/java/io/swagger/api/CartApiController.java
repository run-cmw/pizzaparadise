package io.swagger.api;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.model.Cart;
import io.swagger.model.CartAddResponse;
import io.swagger.model.Pizza;
import io.swagger.model.PriceResponse;
import io.swagger.model.StoreItem;
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
  @ApiResponses(value = {
      @ApiResponse(code=200, message = "OK"), @ApiResponse(code=404, message = "NOT_FOUND")})
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
  @ApiResponses(value = {
      @ApiResponse(code=201, message = "CREATED"), @ApiResponse(code=404, message = "NOT_FOUND")})
  public ResponseEntity<CartAddResponse> addPizzaToCart(String storeId, String cartId,
      String sizeId, boolean gluten,
      String [] toppings) {
    final CartAddResponse response;
    final String message;
    if (toppings.length > Pizza.MAXIMUM_TOPPING_COUNT) {
      message = "TOO_MANY_PIZZA_TOPPINGS";
      return new ResponseEntity<CartAddResponse>(new CartAddResponse(message), HttpStatus.BAD_REQUEST);
    }
    if (storeService.getStoreById(storeId) == null) {
      message = "STORE_NOT_FOUND";
      return new ResponseEntity<CartAddResponse>(new CartAddResponse(message), HttpStatus.NOT_FOUND);
    }
    if (sizeService.getPizzaSizeById(sizeId) == null) {
      message = "PIZZA_SIZE_NOT_FOUND";
      return new ResponseEntity<CartAddResponse>(new CartAddResponse(message), HttpStatus.NOT_FOUND);
    }
    if (!gluten && !storeService.storeCanServeGlutenFree(storeId)) {
      message = "STORE_CANNOT_SERVE_GLUTEN_FREE";
      return new ResponseEntity<CartAddResponse>(new CartAddResponse(message), HttpStatus.BAD_REQUEST);
    }

    // I would expect this to be...
    // Cart cart = cartService.getOrCreateCart(storeId, cartId);
    // Pizza pizza = pizzaService.createPizza(sizeId, gluten, toppings);
    // cartService.addPizzaToCart(cart, pizza);
    try {
      Pizza pizza = cartService.addPizzaToCart(storeId, cartId, sizeId, gluten, toppings);
      // TODO: I would expect CartAddResponse to take a pizza/side as the first parameter
      response = new CartAddResponse(pizza.getToppingIDs(), cartId, storeId);
      return new ResponseEntity<CartAddResponse>(response, HttpStatus.CREATED);
    } catch (RuntimeException exception) {
      // TODO: THIS MUST BE A CHECKED EXCEPTION TYPE NOT RUNTIMEEXCEPTION :)
      return new ResponseEntity<CartAddResponse>(new CartAddResponse(exception.getMessage()), HttpStatus.BAD_REQUEST);
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
  @ApiResponses(value = {
      @ApiResponse(code=201, message = "CREATED"), @ApiResponse(code=404, message = "NOT_FOUND")})
  public ResponseEntity<CartAddResponse> addSideToCart(String storeId, String cartId,
      String sideId) {
    final CartAddResponse response;
    final String message;
    if (storeService.getStoreById(storeId) == null) {
      message = "This storeID is not found.";
      response = new CartAddResponse(message);
      return new ResponseEntity<CartAddResponse>(response, HttpStatus.NOT_FOUND);
    }
    if (sideService.getSideById(sideId) == null) {
      message = "This SideID is not found.";
      response = new CartAddResponse(message);
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
  @ApiResponses(value = {
      @ApiResponse(code=204, message = "NO_CONTENT"),
      @ApiResponse(code=404, message = "NOT_FOUND")})
  public HttpStatus deleteCart(String storeId, String cartId) {
    if (cartService.getCartItemsById(storeId, cartId) == null) {
      return HttpStatus.NOT_FOUND;
    }
    cartService.deleteCart(cartId);
    return HttpStatus.NO_CONTENT;
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
  @ApiResponses(value = {
      @ApiResponse(code=204, message = "NO_CONTENT"),
      @ApiResponse(code=404, message = "NOT_FOUND")})
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
  @ApiResponses(value = {
      @ApiResponse(code=204, message = "NO_CONTENT"),
      @ApiResponse(code=400, message = "BAD_REQUEST"),
      @ApiResponse(code=404, message = "NOT_FOUND")})
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
