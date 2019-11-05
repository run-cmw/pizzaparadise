package io.swagger.api;

import io.swagger.annotations.ApiOperation;
import io.swagger.service.ApplySpecialsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@javax.annotation.Generated(
    value = "io.swagger.codegen.v3.generators.java.SpringCodegen",
    date = "2019-09-26T03:54:46.062Z[GMT]")
@RestController
public class ApplySpecialsApiController implements ApplySpecialsApi {
  @Autowired
  private ApplySpecialsService applySpecialsService;

  @GetMapping("/store/{storeId}/cart/{cartId}/applySpecials/buy1get1")
  @ApiOperation(
      value = "Add a pizza for free if new pizza is of equal or lesser value to highest cost pizza "
          + "in cart. Add the pizza at regular price if new pizza is of higher value than all "
          + "pizzas in cart.",
      tags = {
          "apply specials",
      })
  public ResponseEntity<String> applyBuy1Get1Special(String cartId, String storeId, String sizeId,
      boolean gluten, String topping1, String topping2, String topping3, String topping4) {
    if(applySpecialsService.applyBuy1Get1Special(cartId, storeId, sizeId, gluten, topping1,
        topping2, topping3, topping4) == null) {
      return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
    }
    return new ResponseEntity<String>(applySpecialsService.applyBuy1Get1Special(cartId, storeId,
        sizeId, gluten, topping1, topping2, topping3, topping4), HttpStatus.FOUND);
  }

  @GetMapping("/store/{storeId}/cart/{cartId}/applySpecials/30PercentOff")
  @ApiOperation(
      value = "Apply 30% off cart price if cart contains 2 large pizzas with no toppings",
      tags = {
          "apply specials",
      })
  public ResponseEntity<String> apply30PercentOffSpecial(String cartId, String storeId) {
    if(applySpecialsService.apply30PercentOffSpecial(cartId, storeId) == null) {
      return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
    }
    return new ResponseEntity<String>(applySpecialsService.apply30PercentOffSpecial(cartId,
        storeId), HttpStatus.FOUND);
  }

  @GetMapping("/store/{storeId}/cart/{cartId}/applySpecials/freeSoda")
  @ApiOperation(
      value = "Add a soda to cart for free if at least one pizza is in cart",
      tags = {
          "apply specials",
      })
  public ResponseEntity<String> applyFreeSodaSpecial(String cartId, String storeId, String drinkId)
  {
    if(applySpecialsService.applyFreeSodaSpecial(cartId, storeId, drinkId) == null) {
      return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
    }
    return new ResponseEntity<String>(applySpecialsService.applyFreeSodaSpecial(cartId, storeId,
        drinkId), HttpStatus.FOUND);
  }
}