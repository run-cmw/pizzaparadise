package io.swagger.api;

import io.swagger.annotations.*;
import io.swagger.model.SpecialItem;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * SpecialAPI interface
 */
@javax.annotation.Generated(
    value = "io.swagger.codegen.v3.generators.java.SpringCodegen",
    date = "2019-09-26T03:54:46.062Z[GMT]")
@Api(value = "special", description = "the special API")
public interface SpecialApi {

  /**
   * Get all special items
   * @return List of all SpecialItem from database
   */
  ResponseEntity<List<SpecialItem>> getAllSpecials();


  /**
   * Get a specific specialItem by specialId
   * @param id
   * @return specialItem found by Id
   */
  ResponseEntity<SpecialItem> getSpecialById(@PathVariable String id);


  /**
   * Add Special to the Mongo database
   * @param specialId specialId given to add to the database
   * @param name name given to add to the database
   * @param description description given to add to the database
   * @return Special Item that is added
   */
  ResponseEntity<SpecialItem> addSpecial(
      @ApiParam(value = "SpecialId") @RequestParam(required = true) String specialId,
      @ApiParam(value = "Name of special") @RequestParam(required = true) String name,
      @ApiParam(value = "description of special") @RequestParam(required = true) String description);

  /**
   * Delete a special Item found by id from database
   * @param id id given for search
   * @return HttpStatus.NO_CONTENT if successfully removed, HttpStatus.NOT_FOUND if id wasn't found.
   */
  HttpStatus deleteSpecial(@PathVariable String id);
}
