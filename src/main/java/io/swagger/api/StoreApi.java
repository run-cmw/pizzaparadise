package io.swagger.api;

import io.swagger.annotations.*;
import io.swagger.model.StoreItem;
import java.util.List;
import java.util.UUID;
import javax.validation.Valid;
import javax.validation.constraints.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@javax.annotation.Generated(
    value = "io.swagger.codegen.v3.generators.java.SpringCodegen",
    date = "2019-09-26T03:54:46.062Z[GMT]")
@Api(value = "stores", description = "the store API")
public interface StoreApi {

  // Get all stores
  ResponseEntity<List<StoreItem>> getAllStores();

  // Get a specific StoreItem by id
  ResponseEntity<StoreItem> getStoreById(@PathVariable UUID id);

  // Add a StoreItem
  ResponseEntity<StoreItem> addStore(
      @ApiParam(value = "StoreItem to add") @Valid @RequestBody StoreItem newStore);

//  // Delete a StoreItem by id
//  ResponseEntity<String> deleteStore(@PathVariable String id);
}