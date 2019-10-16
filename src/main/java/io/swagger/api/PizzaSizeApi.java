package io.swagger.api;

import io.swagger.annotations.Api;
import io.swagger.model.PizzaSize;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

@javax.annotation.Generated(
    value = "io.swagger.codegen.v3.generators.java.SpringCodegen",
    date = "2019-09-26T03:54:46.062Z[GMT]")
@Api(value = "pizzaSize", description = "Pizza Size API")
public interface PizzaSizeApi {

  // Get all pizza sizes
  ResponseEntity<List<PizzaSize>> getAllPizzaSizes();

  // Get a specific pizza size with id
  ResponseEntity<PizzaSize> getPizzaSizeById(@PathVariable String id);
}
