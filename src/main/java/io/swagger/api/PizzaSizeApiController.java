package io.swagger.api;

import io.swagger.annotations.ApiOperation;
import io.swagger.model.PizzaSize;
import io.swagger.service.PizzaSizeService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * PizzaSizeApiController
 */
@javax.annotation.Generated(
    value = "io.swagger.codegen.v3.generators.java.SpringCodegen",
    date = "2019-09-26T03:54:46.062Z[GMT]")
@RestController
public class PizzaSizeApiController implements PizzaSizeApi {

  @Autowired
  private PizzaSizeService sizeService;

  @GetMapping("/size")
  @ApiOperation(
      value = "Get all PizzaSizes",
      tags = {
          "pizza size",
      })
  public ResponseEntity<List<PizzaSize>> getAllPizzaSizes() {
    return new ResponseEntity<List<PizzaSize>>(sizeService.getAllPizzaSizes(), HttpStatus.OK);
  }

  @GetMapping("/size/{id}")
  @ApiOperation(
      value = "Get specific PizzaSize with id",
      tags = {
          "pizza size",
      })
  public ResponseEntity<PizzaSize> getPizzaSizeById(@PathVariable String id) {
    if(sizeService.getPizzaSizeById(id) == null) {
      return new ResponseEntity<PizzaSize>(HttpStatus.NOT_FOUND);
    }
    return new ResponseEntity<PizzaSize>(sizeService.getPizzaSizeById(id), HttpStatus.FOUND);
  }
}
