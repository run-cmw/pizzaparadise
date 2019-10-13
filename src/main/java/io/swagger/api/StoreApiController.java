package io.swagger.api;



import io.swagger.model.StoreItem;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.*;
import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.Map;
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2019-09-26T03:54:46.062Z[GMT]")
@Controller
public class StoreApiController implements StoreApi {

  private static final Logger log = LoggerFactory.getLogger(StoreApiController.class);

  private final ObjectMapper objectMapper;

  private final HttpServletRequest request;

  @org.springframework.beans.factory.annotation.Autowired
  public StoreApiController(ObjectMapper objectMapper, HttpServletRequest request) {
    this.objectMapper = objectMapper;
    this.request = request;
  }

  public ResponseEntity<Void> addStore(@ApiParam(value = "Store item to add"  )  @Valid @RequestBody StoreItem body) {
    String accept = request.getHeader("Accept");
    return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
  }

  public ResponseEntity<List<StoreItem>> searchStore(@ApiParam(value = "pass an optional search string for looking up stores") @Valid @RequestParam(value = "searchString", required = false) String searchString,@Min(0)@ApiParam(value = "number of records to skip for pagination", allowableValues = "") @Valid @RequestParam(value = "skip", required = false) Integer skip,@Min(0) @Max(50) @ApiParam(value = "maximum number of records to return", allowableValues = "") @Valid @RequestParam(value = "limit", required = false) Integer limit) {
    String accept = request.getHeader("Accept");
    if (accept != null && accept.contains("application/json")) {
      try {
        return new ResponseEntity<List<StoreItem>>(objectMapper.readValue("[ {\n  \"isOpen\" : \"true\",\n  \"timeClosed\" : \"09:12:33\",\n  \"timeOpen\" : \"09:12:33\",\n  \"address\" : \"123 Main St Coastal State 77777\",\n  \"id\" : \"d290f1ee-6c54-4b01-90e6-d701748f0851\"\n}, {\n  \"releaseDate\" : \"2016-08-29T09:12:33.001Z\",\n  \"name\" : \"Widget Adapter\",\n  \"id\" : \"d290f1ee-6c54-4b01-90e6-d701748f0851\"\n} ]", List.class), HttpStatus.NOT_IMPLEMENTED);
      } catch (IOException e) {
        log.error("Couldn't serialize response for content type application/json", e);
        return new ResponseEntity<List<StoreItem>>(HttpStatus.INTERNAL_SERVER_ERROR);
      }
    }

    return new ResponseEntity<List<StoreItem>>(HttpStatus.NOT_IMPLEMENTED);
  }

}