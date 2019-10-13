package io.swagger.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.*;
import io.swagger.model.SpecialItem;
import io.swagger.service.SpecialService;
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
public class SpecialApiController implements SpecialApi {

  @Autowired private SpecialService specialService;

  @GetMapping("/specials")
  @ApiOperation(
      value = "Get all Specials",
      tags = {
        "special",
      })
  public List<SpecialItem> getAllSpecials() {
    return specialService.getAllSpecialItems();
  }

  @GetMapping("/special/{id}")
  @ApiOperation(
      value = "Get the special with specific id",
      tags = {
        "special",
      })
  public ResponseEntity<SpecialItem> getSpecialById(@PathVariable("id") String id) {
    SpecialItem special = specialService.getSpecialById(id);
    if (special == null) {
      return new ResponseEntity<SpecialItem>(HttpStatus.NOT_FOUND);
    }
    return new ResponseEntity<SpecialItem>(special, HttpStatus.OK);
  }

  private static final Logger log = LoggerFactory.getLogger(SpecialApiController.class);

  private final ObjectMapper objectMapper;

  private final HttpServletRequest request;

  @org.springframework.beans.factory.annotation.Autowired
  public SpecialApiController(ObjectMapper objectMapper, HttpServletRequest request) {
    this.objectMapper = objectMapper;
    this.request = request;
  }

  @PostMapping("/special/add")
  @ResponseStatus(HttpStatus.CREATED)
  @ApiOperation(
      value = "adds a Special item",
      nickname = "addSpecial",
      notes = "Adds a Special to the system",
      tags = {
        "special",
      })
  public void addSpecial(
      @ApiParam(value = "Special item to add") @Valid @RequestBody SpecialItem newSpecial) {
    specialService.addSpecialItem(newSpecial);
  }
}
