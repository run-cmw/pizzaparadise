package io.swagger.api;

import io.swagger.annotations.ApiOperation;
import io.swagger.model.Cart;
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
import org.springframework.web.bind.annotation.PutMapping;
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

  @GetMapping("store/{storeId}/cart/{cartId}")
  @ApiOperation(
      value = "Get all items in the cart with specific id",
      tags = {
          "cart",
      })
  public ResponseEntity<Cart> getCartItemsById(String storeId, String cartId) {
    if (cartService.getCartItemsById(storeId, cartId) == null) {
      return new ResponseEntity<Cart>(HttpStatus.NOT_FOUND);
    }
    return new ResponseEntity<Cart>(cartService.getCartItemsById(storeId, cartId), HttpStatus.FOUND);
  }

  @GetMapping("/store/{storeId}/cart/{cartId}/price")
  @ApiOperation(
      value = "Get price of all items in the cart with specific id",
      tags = {
          "cart",
      })
  public ResponseEntity<Double> getPriceOfCartById(String storeId, String cartId) {
    if(cartService.getCartItemsById(storeId, cartId) == null) {
      return new ResponseEntity<Double>(HttpStatus.NOT_FOUND);
    }
    return new ResponseEntity<Double>(cartService.getTotalAmountInCart(storeId, cartId), HttpStatus.OK);
  }

  @PostMapping("/store/{storeId}/cart/{cartId}/add/pizza")
  @ApiOperation(
      value = "add pizza to the specific cart",
      tags = {
          "cart",
      })
  public ResponseEntity<Cart> addPizzaToCart(String storeId, String cartId, String sizeId, boolean gluten,
      String topping1, String topping2, String topping3, String topping4) {
    if(storeService.getStoreById(storeId) == null) {
      return new ResponseEntity<Cart>(HttpStatus.NOT_FOUND);
    }
    if (sizeService.getPizzaSizeById(sizeId) == null) {
      return new ResponseEntity<Cart>( HttpStatus.NOT_FOUND);
    }

    Cart cart = cartService.addPizzaToCart(storeId, cartId, sizeId, gluten, topping1, topping2,
        topping3, topping4);
    if(cart == null) {
      return new ResponseEntity<Cart>(HttpStatus.BAD_REQUEST);
    } else {
      return new ResponseEntity<Cart>(cart, HttpStatus.CREATED);
    }

  }

  @PutMapping("/store/{storeId}/cart/{cartId}/add/side")
  @ApiOperation(
      value = "add side to the specific cart",
      tags = {
          "cart",
      })
  public ResponseEntity<Cart> addSideToCart(String storeId, String cartId, String sideID) {
    if(storeService.getStoreById(storeId) == null) {
      return new ResponseEntity<Cart>(HttpStatus.NOT_FOUND);
    }
    if (sideService.getSideById(sideID) == null) {
      return new ResponseEntity<Cart>(HttpStatus.NOT_FOUND);
    }

    return new ResponseEntity<Cart>(cartService.addSideToCart(storeId, cartId, sideID), HttpStatus.CREATED);

  }

  @DeleteMapping("store/{storeId}/cart/{cartId}/delete")
  @ApiOperation(
      value = "delete a Cart with id",
      tags = {
          "cart",
      })
  public ResponseEntity deleteCart(String storeId, String cartId) {
    if(storeService.getStoreById(storeId) == null) {
      return new ResponseEntity(HttpStatus.NOT_FOUND);
    }
    if (cartService.getCartItemsById(storeId, cartId) == null) {
      return new ResponseEntity(HttpStatus.NOT_FOUND);
    }
    return new ResponseEntity(cartService.deleteCart(cartId), HttpStatus.NO_CONTENT);
  }

  @DeleteMapping("store/{storeId}/cart/{cartId}/delete/side")
  @ApiOperation(
      value = "delete a sideItem from a Cart with id",
      tags = {
          "cart",
      })
  public ResponseEntity<String> deleteSideFromCart(String storeId, String cartId, String sideId) {
    if(storeService.getStoreById(storeId) == null) {
      return new ResponseEntity<String>("StoreID not found.", HttpStatus.NOT_FOUND);
    }
    if (cartService.getCartItemsById(storeId, cartId) == null) {
      return new ResponseEntity<String>("CartID does not exist: " + cartId, HttpStatus.NOT_FOUND);
    }
    if (sideService.getSideById(sideId) == null) {
      return new ResponseEntity<String>("SideID does not exist", HttpStatus.NOT_FOUND);
    }
    return new ResponseEntity<String>(cartService.deleteSideFromCart(storeId, cartId, sideId), HttpStatus.OK);

  }

  @DeleteMapping("store/{storeId}/cart/{cartId}/delete/pizza")
  @ApiOperation(
      value = "delete a pizza from a Cart with index number",
      tags = {
          "cart",
      })
  public ResponseEntity<String> deletePizzaFromCart(String storeId, String cartId, Integer pizzaIndex) {
    if(storeService.getStoreById(storeId) == null) {
      return new ResponseEntity<String>("StoreID not found.", HttpStatus.NOT_FOUND);
    }
    if (cartService.getCartItemsById(storeId, cartId) == null) {
      return new ResponseEntity<String>("CartID does not exist: " + cartId, HttpStatus.NOT_FOUND);
    }
    return new ResponseEntity<String>(cartService.deletePizzaFromCart(storeId, cartId, pizzaIndex), HttpStatus.OK);
  }

}
