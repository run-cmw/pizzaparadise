package io.swagger.api;



import io.swagger.model.SpecialItem;
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
@Api(value = "special", description = "the special API")
public interface SpecialApi {


  void addSpecial(@ApiParam(value = "Special item to add"  )  @Valid @RequestBody SpecialItem body);


  List<SpecialItem> getAllSpecials();


  ResponseEntity<SpecialItem> getSpecialById(@PathVariable("id") String id);


}
