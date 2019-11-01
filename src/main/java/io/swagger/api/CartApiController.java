package io.swagger.api;

import io.swagger.annotations.ApiOperation;
import io.swagger.model.Cart;
import io.swagger.service.CartService;
import io.swagger.service.PizzaSizeService;
import io.swagger.service.SideService;
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


  @GetMapping("/cart/{id}")
  @ApiOperation(
      value = "Get all items in the cart with specific id",
      tags = {
          "cart",
      })
  public ResponseEntity<Cart> getCartItemsById(String id) {
    if (cartService.getCartItemsById(id) == null) {
      return new ResponseEntity<Cart>(HttpStatus.NOT_FOUND);
    }
    return new ResponseEntity<Cart>(cartService.getCartItemsById(id), HttpStatus.FOUND);
  }

  @GetMapping("/cart/{id}/price")
  @ApiOperation(
      value = "Get price of all items in the cart with specific id",
      tags = {
          "cart",
      })
  public ResponseEntity<Double> getPriceOfCartById(String id) {
    return new ResponseEntity<Double>(cartService.getTotalAmountInCart(id), HttpStatus.OK);
  }

  @PostMapping("/cart/{id}/add/pizza")
  @ApiOperation(
      value = "add pizza to the specific cart",
      tags = {
          "cart",
      })
  public ResponseEntity<Cart> addPizzaToCart(String id, String sizeId, boolean gluten,
      String topping1, String topping2, String topping3, String topping4) {
    if (sizeService.getPizzaSizeById(id) == null) {
      return new ResponseEntity<Cart>(
          cartService.addPizzaToCart(id, sizeId, gluten, topping1, topping2,
              topping3, topping4), HttpStatus.BAD_REQUEST);
    }

    return new ResponseEntity<Cart>(
        cartService.addPizzaToCart(id, sizeId, gluten, topping1, topping2,
            topping3, topping4), HttpStatus.CREATED);
  }

  @PutMapping("/cart/{id}/add/side")
  @ApiOperation(
      value = "add side to the specific cart",
      tags = {
          "cart",
      })
  public ResponseEntity<Cart> addSideToCart(String id, String sideID) {
    if (sideService.getSideById(id) == null) {
      return new ResponseEntity<Cart>(
          cartService.addSideToCart(id, sideID), HttpStatus.BAD_REQUEST);
    }

    return new ResponseEntity<Cart>(cartService.addSideToCart(id, sideID), HttpStatus.CREATED);

  }

  @DeleteMapping("/cart/{id}/delete")
  @ApiOperation(
      value = "delete a Cart with id",
      tags = {
          "cart",
      })
  public ResponseEntity<String> deleteCart(String id) {
    if (cartService.getCartItemsById(id) == null) {
      return new ResponseEntity<String>("id does not exist: " + id, HttpStatus.NOT_FOUND);
    }
    return new ResponseEntity<String>(cartService.deleteCart(id), HttpStatus.OK);

  }

}
