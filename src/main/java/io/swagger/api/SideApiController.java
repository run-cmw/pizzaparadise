package io.swagger.api;

import io.swagger.annotations.*;
import io.swagger.model.SideItem;
import io.swagger.service.SideService;

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
public class SideApiController implements SideApi {
  @Autowired
  private SideService sideService;

  @GetMapping("/side")
  @ApiOperation(
      value = "Get all SideItems",
      tags = {
          "side",
      })
  public ResponseEntity<List<SideItem>> getAllSides() {
    return new ResponseEntity<List<SideItem>>(sideService.getAllSides(), HttpStatus.OK);
  }

  @GetMapping("/side/{id}")
  @ApiOperation(
      value = "Get a specific SideItem using id",
      tags = {
          "side",
      })
  public ResponseEntity<SideItem> getSideById(@PathVariable String id) {
    if(sideService.getSideById(id) == null) {
      return new ResponseEntity<SideItem>(HttpStatus.NOT_FOUND);
    }
    return new ResponseEntity<SideItem>(sideService.getSideById(id), HttpStatus.FOUND);
  }

  @PostMapping("/side/add")
  @ApiOperation(
      value = "Add a SideItem",
      tags = {
          "side",
      })
  public ResponseEntity<SideItem> addSide(
      @ApiParam(value = "SideItem to add") @Valid @RequestBody SideItem newSide) {
    return new ResponseEntity<SideItem>(sideService.addSide(newSide), HttpStatus.CREATED);
  }

  @DeleteMapping("/side/delete/{id}")
  @ApiOperation(
      value = "Delete a SideItem using id",
      tags = {
          "side",
      })
  public ResponseEntity<String> deleteSide(@PathVariable String id) {
    if (sideService.getSideById(id) == null) {
      return new ResponseEntity<String>("id does not exist: " + id, HttpStatus.NOT_FOUND);
    }
    return new ResponseEntity<String>(sideService.deleteSide(id), HttpStatus.OK);
  }
}