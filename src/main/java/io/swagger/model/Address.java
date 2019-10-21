package io.swagger.model;

import java.util.Objects;
import javax.validation.Valid;
import javax.validation.constraints.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/** Address represents location information for a store. */
@ApiModel
public class Address {
  @JsonProperty("street")
  private String street = null;

  @JsonProperty("city")
  private String city = null;

  @JsonProperty("state")
  private String state = null;

  @JsonProperty("zipCode")
  private String zipCode = null;

  /**
   * Construct an Address object with the given street, city, state, and zip code.
   *
   * @param street street number and name of store.
   * @param city city of store.
   * @param state state of store.
   * @param zipCode zip code of store.
   */
  public Address(String street, String city, String state, String zipCode) {
    this.street = street;
    this.city = city;
    this.state = state;
    this.zipCode = zipCode;
  }

  /**
   * Get store's street.
   *
   * @return store's street.
   */
  @ApiModelProperty(example = "123 Main St", required = true, value = "")
  @NotNull
  @Valid
  public String getStreet() {
    return street;
  }

  /**
   * Set store's street.
   *
   * @param street store's street.
   */
  public void setStreet(String street) {
    this.street = street;
  }

  /**
   * Get store's city.
   *
   * @return store's city.
   */
  @ApiModelProperty(example = "Coastal", required = true, value = "")
  @NotNull
  @Valid
  public String getCity() {
    return city;
  }

  /**
   * Set store's city.
   *
   * @param city store's city.
   */
  public void setCity(String city) {
    this.city = city;
  }

  /**
   * Get store's state.
   *
   * @return store's state.
   */
  @ApiModelProperty(example = "State", required = true, value = "")
  @NotNull
  @Valid
  public String getState() {
    return state;
  }

  /**
   * Set store's state.
   *
   * @param state store's state.
   */
  public void setState(String state) {
    this.state = state;
  }

  /**
   * Get store's zip code.
   *
   * @return store's zip code.
   */
  @ApiModelProperty(example = "77777", required = true, value = "")
  @NotNull
  @Valid
  public String getZipCode() {
    return zipCode;
  }

  /**
   * Set stores's zip code.
   *
   * @param zipCode store's zip code.
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
    Address address = (Address) o;
    return Objects.equals(this.street, address.street)
        && Objects.equals(this.city, address.city)
        && Objects.equals(this.state, address.state)
        && Objects.equals(this.zipCode, address.zipCode);
  }

  @Override
  public int hashCode() {
    return Objects.hash(street, city, state, zipCode);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Address {\n");

    sb.append("    street: ").append(toIndentedString(street)).append("\n");
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
