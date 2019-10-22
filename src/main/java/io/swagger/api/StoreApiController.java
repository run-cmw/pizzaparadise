package io.swagger.api;

import io.swagger.annotations.*;
import io.swagger.model.StoreItem;
import io.swagger.repository.StoreItemRepository;
import io.swagger.service.StoreService;

import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@javax.annotation.Generated(
    value = "io.swagger.codegen.v3.generators.java.SpringCodegen",
    date = "2019-09-26T03:54:46.062Z[GMT]")
@RestController
public class StoreApiController implements StoreApi {
  @Autowired
  private StoreService storeService;

  @GetMapping("/store")
  @ApiOperation(
      value = "Get all stores",
      tags = {
          "store",
      })
  public ResponseEntity<List<StoreItem>> getAllStores() {
    return new ResponseEntity<List<StoreItem>>(storeService.getAllStoreItems(), HttpStatus.OK);
  }

  @GetMapping("/store/{id}")
  @ApiOperation(
      value = "Get a specific StoreItem by id",
      tags = {
          "store",
      })
  public ResponseEntity<StoreItem> getStoreById(@PathVariable String id) {
    if(storeService.getStoreById(id) == null) {
      return new ResponseEntity<StoreItem>(HttpStatus.NOT_FOUND);
    }
    return new ResponseEntity<StoreItem><storeService.getStoreById(id), HttpStatus.FOUND);
  }

  @PostMapping("/store/add")
  @ApiOperation(
      value = "Add a StoreItem",
      tags = {
          "store",
      })
  public ResponseEntity<StoreItem> addStore(
      @ApiParam(value = "StoreItem to add") @Valid @RequestBody StoreItem newStore) {

    return new ResponseEnttity<StoreItem>(storeService.addStore(newStore), HttpStatus.CREATED);
  }

  @DeleteMapping("/store/delete/{id}")
  @ApiOperation(
      value = "Delete a StoreItem by id",
      tags = {
          "store",
      })
  public ResponseEntity<String> deleteStore(@PathVariable String id) {
    if (storeService.getStoreById(id) == null) {
      return new ResponseEntity<String>("id does not exist: " + id, HttpStatus.NOT_FOUND);
    }
    return new ResponseEntity<String>(storeService.deleteStore(id), HttpStatus.OK);
  }
}