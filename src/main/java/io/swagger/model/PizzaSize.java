package io.swagger.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.validation.annotation.Validated;

/**
 * PizzaSize
 */
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

  /**
   * Creating new PizzaSize
   * @param id id given to this PizzaSize
   * @param sizeName sizeName given to this PizzaSize
   * @param sizeInch sizeInch given to this PizzaSize
   * @param price price given to this PizzaSize
   */
  public PizzaSize(String id, String sizeName, String sizeInch, Double price) {
    this.id = id;
    this.sizeName = sizeName;
    this.sizeInch = sizeInch;
    this.price = price;
  }

  /**
   * Get this id.
   * @return id.
   */
  public String getId() {
    return this.id;
  }

  /**
   * Set id.
   * @param id new id given to this PizzaSize.
   */
  @ApiModelProperty(example = "small")
  public void setId(String id) {
    this.id = id;
  }

  /**
   * Get this sizeName.
   * @return sizeName.
   */
  public String getSizeName() {
    return this.sizeName;
  }

  /**
   * Set sizeName for this PizzaSize.
   * @param sizeName new sizeName given to this PizzaSize.
   */
  @ApiModelProperty(example = "Small")
  public void setSizeName(String sizeName) {
    this.sizeName = sizeName;
  }

  /**
   * Get this sizeInch.
   * @return sizeInch.
   */
  public String getSizeInch() {
    return this.sizeInch;
  }

  /**
   * Set sizeInch for this PizzaSize.
   * @param sizeInch new sizeInch given to this PizzaSize.
   */
  @ApiModelProperty(example = "11")
  public void setSizeInch(String sizeInch) {
    this.sizeInch = sizeInch;
  }

  /**
   * Get this price.
   * @return price.
   */
  public Double getPrice() {
    return this.price;
  }

  /**
   * Set price for this PizzaSize.
   * @param price new price given to this PizzaSize.
   */
  @ApiModelProperty(example = "13.99")
  public void setPrice(Double price) {
    this.price = price;
  }

  /**
   * String representation of PizzaSize
   * @return String representation of PizzaSize
   */
  @Override
  public String toString() {
    return "PizzaSize{" +
        "id=" + id +
        ", sizeName=" + sizeName +
        ", sizeInch=" + sizeInch +
        ", price=" + price +
        "}";
  }

  /**
   * Check if two objects are equal.
   * @param obj obj given to compare
   * @return true if two objects are equal, false otherwise.
   */
  @Override
  public boolean equals(Object obj) {
    if(this == obj) return true;
    if(obj == null || getClass() != obj.getClass()) { return false; }
    PizzaSize size = (PizzaSize) obj;
    return size.getId().equals(this.id) &&
        size.getPrice().equals(this.price) &&
        size.getSizeName().equals(this.sizeName) &&
        size.getSizeInch().equals(this.sizeInch);
  }

}
