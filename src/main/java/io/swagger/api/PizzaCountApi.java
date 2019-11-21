package io.swagger.api;

import io.swagger.annotations.Api;

@javax.annotation.Generated(
    value = "io.swagger.codegen.v3.generators.java.SpringCodegen",
    date = "2019-09-26T03:54:46.062Z[GMT]")
@Api(value = "pizzaCount", description = "PizzaCount API")
public interface PizzaCountApi {

  // get suggested pizza order count
  ResponseEntity<String> getNumOfPizzaByInput(
      @ApiParam(value = "Adult") @PathVariable("adult") Integer adult,
      @ApiParam(value = "Kid") @PathVariable("kid") Integer kid);
}
