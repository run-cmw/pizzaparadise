package io.swagger.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.*;
import io.swagger.model.StoreItem;
import io.swagger.service.StoreService;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@javax.annotation.Generated(
    value = "io.swagger.codegen.v3.generators.java.SpringCodegen",
    date = "2019-09-26T03:54:46.062Z[GMT]")
@Controller
public class StoreApiController implements StoreApi {
  @Autowired private StoreService storeService = new StoreService();

  @GetMapping("/stores")
  @ApiOperation(
      value = "Get all stores",
      tags = {
          "store",
      })
  public ResponseEntity<List<StoreItem>> getAllStores() {
    return new ResponseEntity<List<StoreItem>>(storeService.getAllStoreItems(), HttpStatus.OK);
  }

  @GetMapping("/stores/{id}")
  @ApiOperation(
      value = "Get a specific StoreItem by id",
      tags = {
          "store",
      })
  public ResponseEntity<StoreItem> getStoreById(@PathVariable String id) {
    StoreItem store = storeService.getStoreById(id);

    if (store = null) {
      return new ResponseEntity<StoreItem>(HttpStatus.NOT_FOUND);
    }
    return new ResponseEntity<StoreItem>(item, HttpStatus.OK);
  }

  @PostMapping("/stores/add")
  @ResponseStatus(HttpStatus.CREATED)
  @ApiOperation(
      value = "Add a StoreItem",
      tags = {
          "store",
      })
  public void addStore(
      @ApiParam(value = "StoreItem to add") @Valid @RequestBody (Address address String hourOpen String minuteOpen String hourClosed String minuteClosed) {
    storeService.addStoreItem(new StoreItem(address, hourOpen, minuteOpen, hourClosed, minuteClosed));
  }

//  @DeleteMapping("/stores/delete/{id}")
//  @ApiOperation(
//      value = "Delete a StoreItem by id",
//      tags = {
//          "store",
//      })
//  public ResponseEntity<String> deleteStore(@PathVariable String id) {
//    for (StoreItem item : storeItems) {
//      if (item.getId().equals(id)) {
//        return new ResponseEntity<StoreItem>(HttpStatus.OK);
//      }
//    }
//    return new ResponseEntity<String>("id does not exist: " + id, HttpStatus.NOT_FOUND);
//  }
}