package io.swagger.api;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.model.SideItem;
import io.swagger.service.SideService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@javax.annotation.Generated(
    value = "io.swagger.codegen.v3.generators.java.SpringCodegen",
    date = "2019-09-26T03:54:46.062Z[GMT]")
@RestController
public class SideApiController implements SideApi {
  @Autowired
  private SideService sideService;

  /**
   * {@inheritDoc}
   * HttpStatus.OK - if SideItem is successfully found.
   */
  @GetMapping("/side")
  @ApiOperation(
      value = "Get all SideItems",
      tags = {
          "side",
      })
  @ApiResponse(code=200, message = "OK")
  public ResponseEntity<List<SideItem>> getAllSides() {
    return new ResponseEntity<List<SideItem>>(sideService.getAllSides(), HttpStatus.OK);
  }

  /**
   * {@inheritDoc}
   * HttpStatus.NOT_FOUND - if id is not found in database.
   * HttpStatus.OK - if SideItem is successfully found.
   */
  @GetMapping("/side/{id}")
  @ApiOperation(
      value = "Get a specific SideItem using id",
      tags = {
          "side",
      })
  @ApiResponses(value = {
      @ApiResponse(code=200, message = "OK"),
      @ApiResponse(code=404, message = "NOT_FOUND")})
  public ResponseEntity<SideItem> getSideById(@PathVariable String id) {
    if(sideService.getSideById(id) == null) {
      return new ResponseEntity<SideItem>(HttpStatus.NOT_FOUND);
    }
    return new ResponseEntity<SideItem>(sideService.getSideById(id), HttpStatus.FOUND);
  }

  /**
   * {@inheritDoc}
   * HttpStatus.CREATED - if side is created.
   * HttpStatus.FORBIDDEN - if id exists in database.
   */
  @PostMapping("/side/add")
  @ApiOperation(
      value = "Add a SideItem",
      tags = {
          "side",
      })
  @ApiResponses(value = {
      @ApiResponse(code=201, message = "CREATED"),
      @ApiResponse(code=403, message = "FORBIDDEN")})
  public ResponseEntity<SideItem> addSide(String id, String name, Double price, String type) {
    if (sideService.getSideById(id) != null) {
      return new ResponseEntity<SideItem>(HttpStatus.FORBIDDEN);
    }
    return new ResponseEntity<SideItem>(
        sideService.addSide(id, name, price, type), HttpStatus.CREATED);
  }

  /**
   * {@inheritDoc}
   * HttpStatus.NOT_FOUND - if id is not found in database.
   * HttpStatus.NO_CONTENT - if store is successfully removed.
   */
  @DeleteMapping("/side/delete/{id}")
  @ApiOperation(
      value = "Delete a SideItem using id",
      tags = {
          "side",
      })
  @ApiResponses(value = {
      @ApiResponse(code=204, message = "NO_CONTENT"),
      @ApiResponse(code=404, message = "NOT_FOUND")})
  public HttpStatus deleteSide(String id) {
    if (sideService.getSideById(id) == null) {
      return HttpStatus.NOT_FOUND;
    }
    sideService.deleteSide(id);
    return HttpStatus.OK;
  }
}