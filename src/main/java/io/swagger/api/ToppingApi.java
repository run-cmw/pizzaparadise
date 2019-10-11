package io.swagger.api;

import io.swagger.model.ToppingItem;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.List;
import java.util.Map;
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2019-09-26T03:54:46.062Z[GMT]")
@Api(value = "topping", description = "the topping API")
public interface ToppingApi {

  @ApiOperation(value = "adds a Topping item", nickname = "addTopping", notes = "Adds an topping to the system", tags={ "topping", })
  @ApiResponses(value = {
      @ApiResponse(code = 201, message = "topping created"),
      @ApiResponse(code = 400, message = "invalid input, object invalid"),
      @ApiResponse(code = 409, message = "an existing item already exists") })
  @RequestMapping(value = "/topping",
      consumes = { "application/json" },
      method = RequestMethod.POST)
  ResponseEntity<Void> addTopping(@ApiParam(value = "Topping item to add"  )  @Valid @RequestBody ToppingItem body);

  @ApiOperation(value = "searchs for topping", nickname = "searchTopping", notes = "By passing in the appropriate options, you can search for available toppings in the system ", response = ToppingItem.class, responseContainer = "List", tags={ "topping", })
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "search results matching criteria", response = ToppingItem.class, responseContainer = "List"),
      @ApiResponse(code = 400, message = "bad input parameter") })
  @RequestMapping(value = "/topping",
      produces = { "application/json" },
      method = RequestMethod.GET)
  ResponseEntity<List<ToppingItem>> searchTopping(@ApiParam(value = "pass an optional search string for looking up toppings") @Valid @RequestParam(value = "searchString", required = false) String searchString,@Min(0)@ApiParam(value = "number of records to skip for pagination", allowableValues = "") @Valid @RequestParam(value = "skip", required = false) Integer skip,@Min(0) @Max(50) @ApiParam(value = "maximum number of records to return", allowableValues = "") @Valid @RequestParam(value = "limit", required = false) Integer limit);

}
