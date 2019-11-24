package io.swagger.api;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import io.swagger.model.Card;
import io.swagger.model.PurchaseResponse;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2019-09-26T03:54:46.062Z[GMT]")
@Api(value = "purchase", description = "Purchase API")
/** Interface for Purchase API */
public interface PurchaseApi {

  public ResponseEntity<PurchaseResponse> makePurchase(
      @ApiParam(value = "CartID") @PathVariable("cartId") String cartId,
      @ApiParam(value = "StoreID") @PathVariable("storeId") String storeId,
      @ApiParam(value = "Card information to purchase") @Valid @RequestBody Card card);

}
