package io.swagger.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import io.swagger.model.ApplySpecialResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

@javax.annotation.Generated(
    value = "io.swagger.codegen.v3.generators.java.SpringCodegen",
    date = "2019-09-26T03:54:46.062Z[GMT]")
@Api(value = "applySpecial", description = "Apply Special API")

/** Interface for the Apply Special API */
public interface ApplySpecialApi {
  /**
   * Apply the specified special to the given store and cart.
   *
   * @param specialId id of the special being requested
   * @param cartId id of the cart receiving the special
   * @param storeId id of the store that the cart belongs to
   * @return Success message that pizza was added to cart or error message if special is not valid
   *     for cart.
   */
  ResponseEntity<ApplySpecialResponse> applySpecial(
      @ApiParam(value = "Special to add") @RequestParam(required = true) String specialId,
      @ApiParam(value = "Store that contains cart") @RequestParam(required = true) String storeId,
      @ApiParam(value = "Cart to update") @RequestParam(required = true) String cartId)
      throws Exception;
}
