package io.swagger.api;

import io.swagger.annotations.*;
import io.swagger.model.SpecialItem;
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
@RestController
public class SpecialApiController implements SpecialApi {

  @Autowired
  private SpecialItemService specialItemService;

  @GetMapping("/special")
  @ApiOperation(
      value = "Get all SpecialItems",
      tags = {
        "special",
      })
  public ResponseEntity<List<SpecialItem>> getAllSpecials() {
    return new ResponseEntity<List<SpecialItem>>(specialItemService.getAllSpecials(), HttpStatus.OK);
  }

  @GetMapping("/special/{id}")
  @ApiOperation(
      value = "Get SpecialItem with specific id",
      tags = {
        "special",
      })
  public ResponseEntity<SpecialItem> getSpecialById(@PathVariable String id) {
    if(specialItemService.getSpecialById(id) == null) {
      return new ResponseEntity<SpecialItem>(HttpStatus.NOT_FOUND);
    }
    return new ResponseEntity<SpecialItem>(specialItemService.getSpecialById(id), HttpStatus.FOUND);
  }

  @PostMapping("/special/add")
  @ApiOperation(
      value = "add a SpecialItem",
      tags = {
          "special",
      })
  public ResponseEntity<SpecialItem> addSpecial(
      @ApiParam(value = "Special item to add") @Valid @RequestBody SpecialItem newSpecial) {

    return new ResponseEntity<SpecialItem>(specialItemService.addSpecial(newSpecial), HttpStatus.CREATED);
  }

  @DeleteMapping("/special/delete/{id}")
  @ApiOperation(
      value = "delete a SpecialItem with id",
      tags = {
          "special",
      })
  public ResponseEntity<String> deleteSpecial(@PathVariable String id) {

    if (specialItemService.getSpecialById(id) == null) {
      return new ResponseEntity<String>("id does not exist: " + id, HttpStatus.NOT_FOUND);
    }
    return new ResponseEntity<String>(specialItemService.deleteSpecial(id), HttpStatus.OK);

  }

}
