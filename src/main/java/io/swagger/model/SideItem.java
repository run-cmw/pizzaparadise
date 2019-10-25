package io.swagger.model;
import java.util.Objects;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.validation.annotation.Validated;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/** StoreItem */
@Validated
@javax.annotation.Generated(
    value = "io.swagger.codegen.v3.generators.java.SpringCodegen",
    date = "2019-09-26T03:54:46.062Z[GMT]")

@ApiModel
@Document(collection = "SideItem")
public class SideItem {
  @JsonProperty("id")
  @Id
  private String id;

  @JsonProperty("name")
  private String name;

  @JsonProperty("price")
  private Double price;

  /**
   * Construct a SideItem with the given id, name, and price.
   *
   * @param id side's identification number
   * @param name name of side item
   * @param price price of side item 
   */
  public SideItem(String id, String name, Double price) {
    super();
    this.id = id;
    this.name = name;
    this.price = price;
  }

  /**
   * Get side's id.
   * @return side's id.
   */
  @ApiModelProperty(example = "5dae8e058980e20b64e28174", required = true, value = "")
  @NotNull
  @Valid
  public String getId() {
    return id;
  }

  /**
   * Set side's id.
   * @param id side's id
   */
  public void setId(String id) {
    this.id = id;
  }

  /**
   * Get side's name.
   * @return side's name.
   */
  @ApiModelProperty(example = "Hot wings", required = true, value = "")
  @NotNull
  @Valid
  public String getName() {
    return name;
  }

  /**
   * Set side's name.
   * @param name side's name
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * Get side's price.
   * @return side's price.
   */
  @ApiModelProperty(example = "3.89", required = true, value = "")
  @NotNull
  @Valid
  public Double getPrice() {
    return price;
  }

  /**
   * Set side's price.
   * @param price side's price
   */
  public void setPrice(Double price) {
    this.price = price;
  }

  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SideItem sideItem = (SideItem) o;
    return Objects.equals(this.id, sideItem.id)
        && Objects.equals(this.name, sideItem.name)
        && Objects.equals(this.price, sideItem.price);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, price);
  }

  @Override
  public String toString() {
    return "SideItem{"
      + "id='" + id + '\''
      + ", name='" + name + '\''
      + ", price='" + price
      + '}';
  }
}