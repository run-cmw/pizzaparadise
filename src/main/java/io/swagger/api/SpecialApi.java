package io.swagger.api;

import io.swagger.annotations.*;
import io.swagger.model.SpecialItem;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@javax.annotation.Generated(
    value = "io.swagger.codegen.v3.generators.java.SpringCodegen",
    date = "2019-09-26T03:54:46.062Z[GMT]")
@Api(value = "special", description = "the special API")
public interface SpecialApi {

  ResponseEntity<List<SpecialItem>> getAllSpecials();

  ResponseEntity<SpecialItem> getSpecialById(@PathVariable String id);

  ResponseEntity<SpecialItem> addSpecial(
      @ApiParam(value = "Special item to add") @Valid @RequestBody SpecialItem body);
}
