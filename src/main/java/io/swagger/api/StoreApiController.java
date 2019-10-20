package io.swagger.api;

import io.swagger.annotations.*;
import io.swagger.model.SpecialItem;
import io.swagger.repository.SpecialItemRepository;
import io.swagger.service.SpecialItemService;

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

@javax.annotation.Generated(
    value = "io.swagger.codegen.v3.generators.java.SpringCodegen",
    date = "2019-09-26T03:54:46.062Z[GMT]")
@Controller
public class StoreApiController implements StoreApi {
  @GetMapping("/stores")
  @ApiOperation(
      value = "Get all stores",
      tags = {
          "store",
      })
  public ResponseEntity<List<StoreItem>> getAllStores() {
    return new ResponseEntity<List<StoreItem>>(storeItems, HttpStatus.OK);
  }

  @GetMapping("/stores/{id}")
  @ApiOperation(
      value = "Get a specific StoreItem by id",
      tags = {
          "store",
      })
  public ResponseEntity<StoreItem> getStoreById(@PathVariable String id) {
    for (StoreItem item : storeItems) {
      if (item.getId().equals(id)) {
        return new ResponseEntity<StoreItem>(item, HttpStatus.OK);
      }
    }
    return new ResponseEntity<StoreItem>(HttpStatus.NOT_FOUND);
  }

  @PostMapping("/stores/add")
  @ApiOperation(
      value = "Add a StoreItem",
      tags = {
          "store",
      })
  public ResponseEntity<StoreItem> addStore(
      @ApiParam(value = "StoreItem to add") @Valid @RequestBody StorelItem newStore) {
    storeItems.add(newStore);

    return new ResponseEntity<StoreItem>(newStore, HttpStatus.CREATED);
  }

  @DeleteMapping("/stores/delete/{id}")
  @ApiOperation(
      value = "Delete a StoreItem by id",
      tags = {
          "store",
      })
  public ResponseEntity<String> deleteStore(@PathVariable String id) {
    for (StoreItem item : storeItems) {
      if (item.getId().equals(id)) {
        return new ResponseEntity<StoreItem>(HttpStatus.OK);
      }
    }
    return new ResponseEntity<String>("id does not exist: " + id, HttpStatus.NOT_FOUND);

  }
}