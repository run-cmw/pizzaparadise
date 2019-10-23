package io.swagger.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Objects;
import javax.validation.Valid;
import javax.validation.constraints.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/** SpecialItem */
@Validated
@javax.annotation.Generated(
    value = "io.swagger.codegen.v3.generators.java.SpringCodegen",
    date = "2019-09-26T03:54:46.062Z[GMT]")
@ApiModel
@Document(collection = "SpecialItem")
public class SpecialItem {


  @JsonProperty("id")
  @Id
  private String id;

  @JsonProperty("name")
  private String name;

  @JsonProperty("description")
  private String description;


  public SpecialItem(String id, String name, String description) {
    this.id = id;
    this.name = name;
    this.description = description;
  }



  /**
   * Get id
   * @return id
   */
  @ApiModelProperty(example = "1", required = true, value = "")
  @NotNull
  @Valid
  public String getId() {
    return id;
  }

  /**
   * Set id
   */
  public void setId(String id) {
    this.id = id;
  }

  /**
   * Get name
   * @return name
   */
  @ApiModelProperty(example = "Buy1Get1Free", required = true, value = "")
  @NotNull
  public String getName() {
    return name;
  }

  /**
   * Set name
   */
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

  /**
   * Set description
   */
  public void setDescription(String description) {
    this.description = description;
  }

  /*
   * Spring representation of a Special Item
   */
  @Override
  public String toString() {
    return "SpecialItem{" +
        "id='" + id + '\'' +
        ", name='" + name + '\'' +
        ", description='" + description +
        '}';
  }

  @Override
  public boolean equals(Object obj) {
    if(obj == null) { return false; }
    SpecialItem specialItem = (SpecialItem) obj;
    return specialItem.getId().equals(this.id) &&
        specialItem.getName().equals(this.name) &&
        specialItem.getDescription().equals(this.description);
  }


}
