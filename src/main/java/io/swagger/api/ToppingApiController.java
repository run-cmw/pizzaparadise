package io.swagger.api;

import io.swagger.annotations.*;
import io.swagger.model.ToppingItem;
import io.swagger.repository.ToppingItemRepository;

import io.swagger.service.ToppingItemService;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


/**
 * ToppingApiController
 */

@javax.annotation.Generated(
    value = "io.swagger.codegen.v3.generators.java.SpringCodegen",
    date = "2019-09-26T03:54:46.062Z[GMT]")
@RestController
public class ToppingApiController implements ToppingApi {

  @Autowired
  private ToppingItemService toppingItemService;

  @GetMapping("/topping")
  @ApiOperation(
      value = "Get all ToppingItem",
      tags = {
          "topping",
      })
  public ResponseEntity<List<ToppingItem>> getAllTopping() {
    return new ResponseEntity<List<ToppingItem>>(toppingItemService.getAllTopping(), HttpStatus.OK);
  }

  @GetMapping("/topping/{id}")
  @ApiOperation(
      value = "Get ToppingItem with specific id",
      tags = {
          "topping",
      })
  public ResponseEntity<ToppingItem> getToppingById(@PathVariable String id) {
    if (toppingItemService.getToppingById(id) == null) {
      return new ResponseEntity<ToppingItem>(HttpStatus.NOT_FOUND);
    }
    return new ResponseEntity<ToppingItem>(toppingItemService.getToppingById(id), HttpStatus.FOUND);
  }

  @PostMapping("/topping/add")
  @ApiOperation(
      value = "add a ToppingItem",
      tags = {
          "topping",
      })
  public ResponseEntity<ToppingItem> addTopping(
      @ApiParam(value = "Topping item to add") @Valid @RequestBody ToppingItem newTopping) {

    return new ResponseEntity<ToppingItem>(toppingItemService.addTopping(newTopping),
        HttpStatus.CREATED);
  }

  @DeleteMapping("/topping/delete/{id}")
  @ApiOperation(
      value = "delete a ToppingItem with id",
      tags = {
          "topping",
      })
  public ResponseEntity<String> deleteTopping(@PathVariable String id) {

    if (toppingItemService.getToppingById(id) == null) {
      return new ResponseEntity<String>("id does not exist: " + id, HttpStatus.NOT_FOUND);
    }
    return new ResponseEntity<String>(toppingItemService.deleteTopping(id), HttpStatus.OK);

  }

}