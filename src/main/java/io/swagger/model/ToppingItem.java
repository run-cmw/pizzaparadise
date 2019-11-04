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

/**
 * ToppingItem
 */
@Validated
@javax.annotation.Generated(
    value = "io.swagger.codegen.v3.generators.java.SpringCodegen",
    date = "2019-09-26T03:54:46.062Z[GMT]")
@ApiModel
@Document(collection = "ToppingItem")
public class ToppingItem {

  @Id
  @JsonProperty("id")
  private String id;

  @JsonProperty("toppingName")
  private String toppingName;

  @JsonProperty("toppingType")
  private String toppingType;

  @JsonProperty("toppingSmallPrice")
  private Double toppingSmallPrice;

  @JsonProperty("toppingMediumPrice")
  private Double toppingMediumPrice;

  @JsonProperty("toppingLargePrice")
  private Double toppingLargePrice;

  @JsonProperty("toppingGluten")
  private String toppingGluten;

  /**
   * Creating new ToppingItem
   * @param id given to this ToppingItem
   * @param toppingName given to this ToppingItem
   * @param toppingType given to this ToppingItem
   * @param toppingSmallPrice given to this ToppingItem
   * @param toppingMediumPrice given to this ToppingItem
   * @param toppingLargePrice given to this ToppingItem
   * @param toppingGluten given to this ToppingItem
   */

  public ToppingItem(String id, String toppingName, String toppingType,
      Double toppingSmallPrice, Double toppingMediumPrice, Double toppingLargePrice,
      String toppingGluten) {
    this.id = id;
    this.toppingName = toppingName;
    this.toppingType = toppingType;
    this.toppingSmallPrice = toppingSmallPrice;
    this.toppingMediumPrice = toppingMediumPrice;
    this.toppingLargePrice = toppingLargePrice;
    this.toppingGluten = toppingGluten;
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
   * Get toppingName
   * @return toppingName
   **/
  @ApiModelProperty(example = "Sausage", value = "")
  @NotNull
  @GetMapping("/{toppingName}")

  public String getToppingName() {
    return toppingName;
  }

  /**
   * Set toppingName
   */
  public void setToppingName(String toppingName) {
    this.toppingName = toppingName;
  }


  /**
   * Get toppingType
   * @return toppingType
   **/
  @ApiModelProperty(example = "meat", value = "")
  @NotNull
  @Valid

  public String getToppingType() {
    return toppingType;
  }

  /**
   * Set topping type
   */
  public void setToppingType(String toppingType) {
    this.toppingType = toppingType;
  }


  /**
   * Get toppingSmallPrice
   * @return toppingSmallPrice
   **/
  @ApiModelProperty(example = "2.5", value = "")
  @NotNull
  @Valid

  public Double getToppingSmallPrice() {
    return toppingSmallPrice;
  }

  /**
   * Set topping small price
   */
  public void setToppingSmallPrice(Double toppingSmallPrice) {
    this.toppingSmallPrice = toppingSmallPrice;
  }

  /**
   * Get toppingMediumPrice
   * @return toppingMediumPrice
   **/
  @ApiModelProperty(example = "2.75", value = "")
  @NotNull
  @Valid

  public Double getToppingMediumPrice() {
    return toppingMediumPrice;
  }

  /**
   * Set topping medium price
   */
  public void setToppingMediumPrice(Double toppingMediumPrice) {
    this.toppingMediumPrice = toppingMediumPrice;
  }

  /**
   * Get toppingLargePrice
   * @return toppingLargePrice
   **/
  @ApiModelProperty(example = "3.0", value = "")
  @NotNull
  @Valid

  public Double getToppingLargePrice() {
    return toppingLargePrice;
  }

  /**
   * Set topping large price
   */
  public void setToppingLargePrice(Double toppingLargePrice) {
    this.toppingLargePrice = toppingLargePrice;
  }

  /**
   * Get toppingGluten
   * @return toppingGluten
   **/
  @ApiModelProperty(example = "gluten", value = "")
  @NotNull
  @Valid

  public String getToppingGluten() {
    return toppingGluten;
  }

  /**
   * Set topping gluten or non-gluten
   */
  public void setToppingGluten(String toppingGluten) {
    this.toppingGluten = toppingGluten;
  }

  /*
   * Spring representation of a Topping Item
   */
  @Override
  public String toString() {
    return "ToppingItem{" +
        "id='" + id + '\'' +
        ", topping name='" + toppingName + '\'' +
        ", topping type='" + toppingType + '\'' +
        ", topping small price='" + toppingSmallPrice + '\'' +
        ", topping medium price='" + toppingMediumPrice + '\'' +
        ", topping large price='" + toppingLargePrice + '\'' +
        ", topping gluten='" + toppingGluten + '\'' +
        '}';
  }


}