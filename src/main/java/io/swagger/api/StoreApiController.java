package io.swagger.api;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.model.StoreItem;
import io.swagger.service.StoreService;
import java.util.List;
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
   * HttpStatus.OK - if StoreItem is successfully found.
   */
  @GetMapping("/store")
  @ApiOperation(
      value = "Get all StoreItems",
      tags = {
          "store",
      })
  @ApiResponse(code=200, message = "OK")
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
  @ApiResponses(value = {
      @ApiResponse(code=200, message = "OK"),
      @ApiResponse(code=404, message = "NOT_FOUND")})
  public ResponseEntity<StoreItem> getStoreById(String id) {
    if(storeService.getStoreById(id) == null) {
      return new ResponseEntity<StoreItem>(HttpStatus.NOT_FOUND);
    }
    return new ResponseEntity<StoreItem>(storeService.getStoreById(id), HttpStatus.FOUND);
  }

  /**
   * {@inheritDoc}
   * HttpStatus.CREATED - if store is created.
   * HttpStatus.FORBIDDEN - if id exists in database.
   */
  @PostMapping("/store/add")
  @ApiOperation(
      value = "Add a StoreItem",
      tags = {
          "store",
      })
  @ApiResponses(value = {
      @ApiResponse(code=201, message = "CREATED"),
      @ApiResponse(code=403, message = "FORBIDDEN")})
  public ResponseEntity<StoreItem> addStore(String id, String streetNumAndName, String city,
      String state, String zipCode, boolean offersGlutenFree) {
    if (storeService.getStoreById(id) != null) {
      return new ResponseEntity<StoreItem>(HttpStatus.FORBIDDEN);
    }
    return new ResponseEntity<StoreItem>(
        storeService.addStore(id, streetNumAndName, city, state, zipCode, offersGlutenFree),
        HttpStatus.CREATED);
  }

  /**
   * {@inheritDoc}
   * HttpStatus.NOT_FOUND - if id is not found in database.
   * HttpStatus.NO_CONTENT - if store is successfully removed.
   */
  @DeleteMapping("/store/delete/{id}")
  @ApiOperation(
      value = "Delete a StoreItem using id",
      tags = {
          "store",
      })
  @ApiResponses(value = {
      @ApiResponse(code=204, message = "NO_CONTENT"),
      @ApiResponse(code=404, message = "NOT_FOUND")})
  public HttpStatus deleteStore(String id) {
    if (storeService.getStoreById(id) == null) {
      return HttpStatus.NOT_FOUND;
    }
    storeService.deleteStore(id);
    return HttpStatus.NO_CONTENT;
  }
}