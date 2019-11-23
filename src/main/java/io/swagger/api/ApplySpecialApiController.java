package io.swagger.api;

import io.swagger.Message;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.model.ApplySpecialResponse;
import io.swagger.model.Cart;
import io.swagger.service.ApplySpecialService;
import io.swagger.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@javax.annotation.Generated(
    value = "io.swagger.codegen.v3.generators.java.SpringCodegen",
    date = "2019-09-26T03:54:46.062Z[GMT]")
@RestController
public class ApplySpecialApiController implements ApplySpecialApi {
  @Autowired
  private ApplySpecialService applySpecialService;
  @Autowired
  private CartService cartService;

  /**
   * {@inheritDoc}
   * HttpStatus.OK - if special was successfully attempted.
   * HttpStatus.BAD_REQUEST - if the the special cannot be applied bc a special has already been
   *                          applied to the cart or bc the cart's contents do not match the
   *                          special's requirements
   */
  @PostMapping("/applySpecial")
  @ApiOperation(
      value = "Update the price of the cart depending on the special being applied.",
      tags = {
          "apply special",
      })
  @ApiResponses(value = {
      @ApiResponse(code=400, message = "BAD_REQUEST")})
  public ResponseEntity<ApplySpecialResponse> applySpecial
  (String specialId, String storeId, String cartId) {
    ApplySpecialResponse response;
    Cart cart = cartService.getCartItemsById(storeId, cartId);

    if(!applySpecialService.checkSpecial(specialId)) {
      response = new ApplySpecialResponse(specialId);
      response.setMessage(Message.ERROR_INVALID_SPECIAL);
      return new ResponseEntity<ApplySpecialResponse>(response, HttpStatus.BAD_REQUEST);
    }
    if(!applySpecialService.checkCartAtStore(cartId, storeId)) {
      response = new ApplySpecialResponse(specialId);
      response.setMessage(Message.ERROR_NO_CART);
      return new ResponseEntity<ApplySpecialResponse>(response, HttpStatus.BAD_REQUEST);
    }
    if(cart.isSpecialApplied() == true) {
      response = new ApplySpecialResponse(specialId);
      response.setMessage(Message.ERROR_ONE_SPECIAL);
      return new ResponseEntity<ApplySpecialResponse>(response, HttpStatus.BAD_REQUEST);
    }

    if(applySpecialService.applySpecial(specialId, storeId, cartId) == null) {
      switch(specialId) {
        case "buy1Get1Free":
          response = new ApplySpecialResponse(specialId);
          response.setMessage(Message.ERROR_FREE_PIZZA);
          return new ResponseEntity<ApplySpecialResponse>(response, HttpStatus.BAD_REQUEST);
        case "buy2LargePizzaNoTopping":
          response = new ApplySpecialResponse(specialId);
          response.setMessage(Message.ERROR_DISCOUNT_30_PERCENT);
          return new ResponseEntity<ApplySpecialResponse>(response, HttpStatus.BAD_REQUEST);
        case "buy1PizzaGetSodaFree":
          response = new ApplySpecialResponse(specialId);
          response.setMessage(Message.ERROR_FREE_SODA);
          return new ResponseEntity<ApplySpecialResponse>(response, HttpStatus.BAD_REQUEST);
//        default:
//          response = new ApplySpecialResponse(specialId);
//          response.setMessage((Message.ERROR_UNIMPLEMENTED_SPECIAL));
//          return new ResponseEntity<ApplySpecialResponse>(response, HttpStatus.BAD_REQUEST);
      }
    }

    return new ResponseEntity<ApplySpecialResponse>
        (applySpecialService.applySpecial(specialId, storeId, cartId), HttpStatus.OK);
  }
}