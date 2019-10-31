package io.swagger.model;

import java.util.Objects;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonProperty;
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
@Document(collection = "StoreItem")
public class StoreItem {
  @JsonProperty("id")
  private String id;

  @JsonProperty("streetNumAndName")
  private String streetNumAndName;

  @JsonProperty("city")
  private String city;

  @JsonProperty("state")
  private String state;

  @JsonProperty("zipCode")
  private String zipCode;

  @JsonProperty("offersGlutenFree")
  private boolean offersGlutenFree;

  /**
   * Construct a StoreItem with the given id, street, city, state, and zip code.
   *
   * @param id store's identification number
   * @param streetNumAndName street portion of store's address
   * @param city city portion of store's address
   * @param state state portion of store's address
   * @param zip code zip code portion of store's address
   * @param offersGlutenFree whether store offers gluten free pizza
   */
  public StoreItem(String id, String streetNumAndName, String city, String state, String zipCode, boolean offersGlutenFree) {
    super();
    this.id = id;
    this.streetNumAndName = streetNumAndName;
    this.city = city;
    this.state = state;
    this.zipCode = zipCode;
    this.offersGlutenFree = offersGlutenFree;
  }

  /**
   * Get store's id.
   * @return store's id.
   */
  @ApiModelProperty(example = "Pike", required = true, value = "")
  @NotNull
  @Valid
  public String getId() {
    return id;
  }

  /**
   * Set store's id.
   * @param id store's id
   */
  public void setId(String id) {
    this.id = id;
  }

  /**
   * Get store's street number and name.
   * @return store's street number and name.
   */
  @ApiModelProperty(example = "123 Main St", required = true, value = "")
  @NotNull
  @Valid
  public String getStreetNumAndName() {
    return streetNumAndName;
  }

  /**
   * Set store's street number and name.
   * @param streetNumAndName store's street number and name
   */
  public void setStreetNumAndName(String streetNumAndName) {
    this.streetNumAndName = streetNumAndName;
  }

  /**
   * Get store's city.
   * @return store's city.
   */
  @ApiModelProperty(example = "Oakland", required = true, value = "")
  @NotNull
  @Valid
  public String getCity() {
    return city;
  }

  /**
   * Set store's city.
   * @param city city for the store's address
   */
  public void setCity(String city) {
    this.city = city;
  }

  /**
   * Get store's state.
   * @return store's state.
   */
  @ApiModelProperty(example = "California", required = true, value = "")
  @NotNull
  @Valid
  public String getState() {
    return state;
  }

  /**
   * Set store's state.
   * @param state state for the store's address
   */
  public void setState(String state) {
    this.state = state;
  }

  /**
   * Get store's zip code.
   * @return store's zip code.
   */
  @ApiModelProperty(example = "94608", required = true, value = "")
  @NotNull
  @Valid
  public String getZipCode() {
    return zipCode;
  }

  /**
   * Set store's zip code.
   * @param zipCode zip code for the store's address
   */
  public void setZipCode(String zipCode) {
    this.zipCode = zipCode;
  }

  /**
   * Get store's offersGlutenFree value.
   * @return {@code true} if store offers gluten free pizza and {@code false} otherwwise.
   */
  @ApiModelProperty(example = "false", required = true, value = "")
  @NotNull
  @Valid
  public boolean getOffersGlutenFree() {
    return offersGlutenFree;
  }

  /**
   * Set whether store offers gluten free pizza.
   * @param offersGlutenFree store's value for offering gluten free pizza
   */
  public void setOffersGlutenFree(boolean offersGlutenFree) {
    this.offersGlutenFree = offersGlutenFree;
  }

  /**
   * Indicates whether some other object is "equal to" this one.
   *
   * @param obj - the reference object with which to compare
   * @return {@code true} if this object is the same as the obj argument and {@code false} otherwise.
   */
  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    StoreItem storeItem = (StoreItem) o;
    return Objects.equals(this.id, storeItem.id)
        && Objects.equals(this.streetNumAndName, storeItem.streetNumAndName)
        && Objects.equals(this.city, storeItem.city)
        && Objects.equals(this.state, storeItem.state)
        && Objects.equals(this.zipCode, storeItem.zipCode)
        && Objects.equals(this.offersGlutenFree, storeItem.offersGlutenFree);
  }

  /**
   * Returns a hash code value for a StoreItem.
   *
   * @return hash code value for a StoreItem.
   */
  @Override
  public int hashCode() {
    return Objects.hash(id, streetNumAndName, city, state, zipCode, offersGlutenFree);
  }

  /**
   * Returns a string representation of a Store.
   * Format - class StoreItem {
   *              id: storeItemId
   *              streetNumAndName: storeItemStreetNumAndName
   *              city: storeItemCity
   *              state: storeItemState
   *              zipCode: storeItemZipCode
   *              offersGlutenFree: trueOrFalse
   *          }
   *
   * @return string representation of the object.
   */
  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class StoreItem {\n");
    sb.append("    id: ").append(id).append("\n");
    sb.append("    streetNumAndName: ").append(streetNumAndName).append("\n");
    sb.append("    city: ").append(city).append("\n");
    sb.append("    state: ").append(state).append("\n");
    sb.append("    zipCode: ").append(zipCode).append("\n");
    sb.append("    offersGlutenFree: ").append(offersGlutenFree).append("\n");
    sb.append("}");
    return sb.toString();
  }
}