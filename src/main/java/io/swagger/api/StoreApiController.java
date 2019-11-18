package io.swagger.api;

import io.swagger.annotations.ApiOperation;
import io.swagger.model.StoreItem;
import io.swagger.service.StoreService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@javax.annotation.Generated(
    value = "io.swagger.codegen.v3.generators.java.SpringCodegen",
    date = "2019-09-26T03:54:46.062Z[GMT]")
@RestController
public class StoreApiController implements StoreApi {
  @Autowired
  private StoreService storeService;

  /**
   * {@inheritDoc}
   * HttpStatus.OK - if StoreItems are successfully found.
   */
  @GetMapping("/store")
  @ApiOperation(
      value = "Get all StoreItems",
      tags = {
          "store",
      })
  public ResponseEntity<List<StoreItem>> getAllStores() {
    return new ResponseEntity<List<StoreItem>>(storeService.getAllStores(), HttpStatus.OK);
  }

  /**
   * {@inheritDoc}
   * HttpStatus.NOT_FOUND - if id is not found in database.
   * HttpStatus.OK - if StoreItem is successfully found.
   */
  @GetMapping("/store/{id}")
  @ApiOperation(
      value = "Get a specific StoreItem using id",
      tags = {
          "store",
      })
  public ResponseEntity<Optional<StoreItem>> getStoreById(String id) {
    if(storeService.getStoreById(id) == null) {
      return new ResponseEntity<Optional<StoreItem>>(HttpStatus.NOT_FOUND);
    }
    return new ResponseEntity<Optional<StoreItem>>(storeService.getStoreById(id), HttpStatus.OK);
  }

  /**
   * {@inheritDoc}
   * HttpStatus.OK - if StoreItem is successfully added or updated.
   */
  @PostMapping("/store/add")
  @ApiOperation(
      value = "Add or update a StoreItem",
      tags = {
          "store",
      })
  public ResponseEntity<StoreItem> addStore(StoreItem newStore) {
    return new ResponseEntity<StoreItem>(
        storeService.addStore(newStore), HttpStatus.OK);
  }

  /**
   * {@inheritDoc}
   * HttpStatus.NOT_FOUND - if id is not found in database.
   * HttpStatus.OK - if StoreItem is successfully removed.
   */
  @DeleteMapping("/store/delete/{id}")
  @ApiOperation(
      value = "Delete a StoreItem using id",
      tags = {
          "store",
      })
  public HttpStatus deleteStore(String id) {
    if (storeService.getStoreById(id) == null) {
      return HttpStatus.NOT_FOUND;
    }
    storeService.deleteStore(id);
    return HttpStatus.OK;
  }
}