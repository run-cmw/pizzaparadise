package io.swagger.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Objects;
import javax.validation.Valid;
import javax.validation.constraints.*;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/** SpecialItem */
@Validated
@javax.annotation.Generated(
    value = "io.swagger.codegen.v3.generators.java.SpringCodegen",
    date = "2019-09-26T03:54:46.062Z[GMT]")
@ApiModel

public class SpecialItem {

  @JsonProperty("id")
  private String id;

  @JsonProperty("name")
  private String name;

  @JsonProperty("description")
  private String description;

  /**
   * Get id
   *
   * @return id
   */
  @ApiModelProperty(example = "1", required = true, value = "")
  @NotNull
  @Valid
  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  /**
   * Get name
   *
   * @return name
   */
  @ApiModelProperty(example = "Buy1Get1Free", required = true, value = "")
  @NotNull
  @GetMapping("/{specialName}")
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  /**
   * Get description
   * @return description
   */
  @ApiModelProperty(
      example =
          "Only one special at a time. If you buy 1 pizza, you get 1 free pizza that is equal or less value.",
      required = true,
      value = "")
  @NotNull
  @Valid
  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }
}
