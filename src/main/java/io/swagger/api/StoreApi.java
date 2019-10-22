package io.swagger.api;

import io.swagger.annotations.*;
import io.swagger.model.StoreItem;
import java.util.List;
import javax.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

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
   * @return a list of StoreItems
   */
  ResponseEntity<List<StoreItem>> getAllStores();

  /**
   * Get a specific StoreItem by id
   * @param id id of requested StoreItem
   * @return specified StoreItem
   */
  ResponseEntity<StoreItem> getStoreById(@PathVariable String id);

  /**
   * Add a StoreItem
   * @param newStore new StoreItem to add
   */
  ResponseEntity<StoreItem> addStore(
      @ApiParam(value = "StoreItem to add") @Valid @RequestBody StoreItem newStore);

  /**
   * Delete a StoreItem by id
   * @param id id of StoreItem to delete
   * @return String confirming deletion of StoreItem with specified id
   */
  ResponseEntity<String> deleteStore(@PathVariable String id);
}