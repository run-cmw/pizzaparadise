package io.swagger.api;

import io.swagger.annotations.ApiOperation;
import io.swagger.model.ApplySpecialResponse;
import io.swagger.service.ApplySpecialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@javax.annotation.Generated(
    value = "io.swagger.codegen.v3.generators.java.SpringCodegen",
    date = "2019-09-26T03:54:46.062Z[GMT]")
@RestController
public class ApplySpecialApiController implements ApplySpecialApi {
  @Autowired
  private ApplySpecialService applySpecialService;

  /**
   * {@inheritDoc}
   * HttpStatus.OK - if special was successfully attempted.
   * HttpStatus.BAD_REQUEST - if the the special cannot be applied bc a special has already been
   *                          applied to the cart or bc the cart's contents do not match the
   *                          special's requirements
   */
  @PutMapping("/applySpecial")
  @ApiOperation(
      value = "Update the price of the cart depending on the special being applied.",
      tags = {
          "apply special",
      })
  public ResponseEntity<ApplySpecialResponse> applySpecial(String specialId, String storeId, String cartId) {
    try {
      if(applySpecialService.applySpecial(specialId,storeId, cartId).getSuccess() == false) {
        return new ResponseEntity<ApplySpecialResponse>
            (applySpecialService.applySpecial(specialId, storeId, cartId),
                HttpStatus.BAD_REQUEST);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    try {
      return new ResponseEntity<ApplySpecialResponse>
          (applySpecialService.applySpecial(specialId, storeId, cartId), HttpStatus.OK);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }
}