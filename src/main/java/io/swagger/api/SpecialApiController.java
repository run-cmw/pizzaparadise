package io.swagger.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.*;
import io.swagger.model.SpecialItem;
import io.swagger.service.SpecialService;
import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@javax.annotation.Generated(
    value = "io.swagger.codegen.v3.generators.java.SpringCodegen",
    date = "2019-09-26T03:54:46.062Z[GMT]")
@RestController
public class SpecialApiController implements SpecialApi {

  List<SpecialItem> specialItems = new ArrayList<>();
  // @Autowired private SpecialService specialService;

  @GetMapping("/special")
  @ApiOperation(
      value = "Get all SpecialItems",
      tags = {
        "special",
      })
  public ResponseEntity<List<SpecialItem>> getAllSpecials() {
    return new ResponseEntity<List<SpecialItem>>(specialItems, HttpStatus.OK);
  }

  @GetMapping("/special/{id}")
  @ApiOperation(
      value = "Get the special with specific id",
      tags = {
        "special",
      })
  public ResponseEntity<SpecialItem> getSpecialById(@PathVariable String id) {
    for (SpecialItem item : specialItems) {
      if (item.getId().equals(id)) {
        return new ResponseEntity<SpecialItem>(item, HttpStatus.OK);
      }
    }
    return new ResponseEntity<SpecialItem>(HttpStatus.NOT_FOUND);
  }

  @PostMapping("/special/add")
  @ApiOperation(
      value = "adds a Special item",
      tags = {
          "special",
      })
  public ResponseEntity<SpecialItem> addSpecial(
      @ApiParam(value = "Special item to add") @Valid @RequestBody SpecialItem newSpecial) {
    specialItems.add(newSpecial);
    return new ResponseEntity<SpecialItem>(newSpecial, HttpStatus.CREATED);
  }

  /*
  private static final Logger log = LoggerFactory.getLogger(SpecialApiController.class);

  private final ObjectMapper objectMapper;

  private final HttpServletRequest request;

  @org.springframework.beans.factory.annotation.Autowired
  public SpecialApiController(ObjectMapper objectMapper, HttpServletRequest request) {
    this.objectMapper = objectMapper;
    this.request = request;
  }
  */

}
