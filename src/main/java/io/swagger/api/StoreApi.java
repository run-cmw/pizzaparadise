package io.swagger.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import io.swagger.model.StoreItem;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@javax.annotation.Generated(
    value = "io.swagger.codegen.v3.generators.java.SpringCodegen",
    date = "2019-09-26T03:54:46.062Z[GMT]")
@Api(value = "stores", description = "the store API")

/**
 * Interface for the Store API
 */
public interface StoreApi {
  /**
   * Get all stores.
   * @return a list of all StoreItems.
   */
  ResponseEntity<List<StoreItem>> getAllStores();

  /**
   * Get a specific StoreItem by id
   * @param id id of requested StoreItem
   * @return specified StoreItem
   */
  ResponseEntity<StoreItem> getStoreById(@PathVariable String id);

  /**
   * Add a StoreItem.
   * @param id store's unique identifier
   * @param streetNumAndName street portion of store's address
   * @param city city portion of store's address
   * @param state state portion of store's address
   * @param zipCode zip code portion of store's address
   * @param offersGlutenFree whether store offers gluten free pizza
   * @return StoreItem added to database.
   */
  ResponseEntity<StoreItem> addStore(
      @ApiParam(value = "new store's id") @RequestParam(required=true) String id,
      @ApiParam(value = "new store's street name and number") @RequestParam(required=true)
          String streeNameAndNum,
      @ApiParam(value = "new store's city") @RequestParam(required=true) String city,
      @ApiParam(value = "new store's state") @RequestParam(required=true) String state,
      @ApiParam(value = "new store's zip code") @RequestParam(required=true) String zipCode,
      @ApiParam(value = "whether new store offers gluten free") @RequestParam(required=true)
          boolean offersGlutenFree);

  /**
   * Delete a StoreItem by id.
   * @param id id of StoreItem to delete
   * @return {@code HttpStatus.NO_CONTENT} if store successfully removed and
   * {@code HttpStatus.NOT_FOUND} if id wasn't found.
   */
  HttpStatus deleteStore(@PathVariable String id);
}