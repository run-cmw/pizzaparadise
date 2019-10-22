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

/** StoreItem */
@Validated
@javax.annotation.Generated(
    value = "io.swagger.codegen.v3.generators.java.SpringCodegen",
    date = "2019-09-26T03:54:46.062Z[GMT]")

@ApiModel
@Document(collection = "StoreItem")
public class StoreItem {
  @JsonProperty("id")
  @Id
  private String id;

  @JsonProperty("streetNumAndName")
  private String streetNumAndName;

  @JsonProperty("city")
  private String city;

  @JsonProperty("state")
  private String state;

  @JsonProperty("zipCode")
  private String zipCode;

  /**
   * Get store's id.
   * @return store's id.
   */
  @ApiModelProperty(example = "5dae8e058980e20b64e28174", required = true, value = "")
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
        && Objects.equals(this.zipCode, storeItem.zipCode);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, streetNumAndName, city, state, zipCode);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class StoreItem {\n");

    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    streetNumAndName: ").append(toIndentedString(streetNumAndName)).append("\n");
    sb.append("    city: ").append(toIndentedString(city)).append("\n");
    sb.append("    state: ").append(toIndentedString(state)).append("\n");
    sb.append("    zipCode: ").append(toIndentedString(zipCode)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}
