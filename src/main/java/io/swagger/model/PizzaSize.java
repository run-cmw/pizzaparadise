package io.swagger.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.ObjectOutputStream;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.validation.annotation.Validated;

@Validated
@javax.annotation.Generated(
    value = "io.swagger.codegen.v3.generators.java.SpringCodegen",
    date = "2019-09-26T03:54:46.062Z[GMT]")
@ApiModel
@Document(collection = "PizzaSize")
public class PizzaSize {

  @Id
  private String id;
  private String sizeName;
  private String sizeInch;
  private Double price;

  public PizzaSize(String id, String sizeName, String sizeInch, Double price) {
    this.id = id;
    this.sizeName = sizeName;
    this.sizeInch = sizeInch;
    this.price = price;
  }

  public String getId() {
    return this.id;
  }

  @ApiModelProperty(example = "1", required = true, value = "")
  @NotNull
  @Valid
  public void setId(String id) {
    this.id = id;
  }

  public String getSizeName() {
    return this.sizeName;
  }

  @ApiModelProperty(example = "Small", required = true, value = "")
  @NotNull
  public void setSizeName(String sizeName) {
    this.sizeName = sizeName;
  }

  public String getSizeInch() {
    return this.sizeInch;
  }

  @ApiModelProperty(example = "11", required = true, value = "")
  @NotNull
  public void setSizeInch(String sizeInch) {
    this.sizeInch = sizeInch;
  }

  public Double getPrice() {
    return this.price;
  }

  @ApiModelProperty(example = "13.99", required = true, value = "")
  @NotNull
  public void setPrice(Double price) {
    this.price = price;
  }

  /*
   * Spring representation of Pizza Size
   */
  @Override
  public String toString() {
    return "PizzaSize{" +
        "id='" + id + '\'' +
        ", sizeName='" + sizeName + '\'' +
        ", sizeInch='" + sizeInch + '\'' +
        ", price='" + price +
        '}';
  }

  @Override
  public boolean equals(Object obj) {
    if(obj == null) { return false; }
    PizzaSize size = (PizzaSize) obj;
    return size.getId().equals(this.id) &&
        size.getPrice().equals(this.price) &&
        size.getSizeName().equals(this.sizeName) &&
        size.getSizeInch().equals(this.sizeInch);
  }

}
