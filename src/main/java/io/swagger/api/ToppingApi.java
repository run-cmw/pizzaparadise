package io.swagger.api;

import io.swagger.annotations.*;
import io.swagger.model.ToppingItem;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@javax.annotation.Generated(
    value = "io.swagger.codegen.v3.generators.java.SpringCodegen",
    date = "2019-09-26T03:54:46.062Z[GMT]")
@Api(value = "topping", description = "the topping API")
public interface ToppingApi {

  // Get all topping items
  ResponseEntity<List<ToppingItem>> getAllTopping();

  // Get a specific Topping item with id
  ResponseEntity<ToppingItem> getToppingById(@PathVariable String id);

  // Add Topping item to the database
  ResponseEntity<ToppingItem> addTopping(
      @ApiParam(value = "Topping item to add") @Valid @RequestBody ToppingItem newTopping);

  // Delete a topping item found by id from database
  ResponseEntity<String> deleteTopping(@PathVariable String id);
}