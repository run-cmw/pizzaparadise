package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.UUID;
import org.threeten.bp.OffsetDateTime;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;
import java.time.LocalTime;

/**
 * Address represents information for a store's location.
 */
public class Address {
  private String street;
  private String city;
  private String state;
  private String zipCode;

  /**
   * Construct an Address object with the given street, city, state, and zip code.
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
   * @return store's street.
   */
  public String getStreet() {
    return street;
  }

  /**
   * Get store's city.
   * @return store's city.
   */
  public String getCity() {
    return city;
  }

  /**
   * Get store's state.
   * @return store's state.
   */
  public String getState() {
    return state;
  }

  /**
   * Get store's zip code.
   * @return store's zip code.
   */
  public String getZipCode() {
    return zipCode;
  }

  /**
   * Set store's street.
   * @param street store's street.
   */
  public void setStreet(String street) {
    this.street = street;
  }

  /**
   * Set store's city.
   * @param city store's city.
   */
  public void setCity(String city) {
    this.city = city;
  }

  /**
   * Set store's state.
   * @param state store's state.
   */
  public void setState(String state) {
    this.state = state;
  }

  /**
   * Set stores's zip code.
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
    return Objects.equals(this.street, address.street) &&
        Objects.equals(this.city, address.city) &&
        Objects.equals(this.state, address.state) &&
        Objects.equals(this.zipCode, address.zipCode);
  }

  @Override
  public int hashCode() {
    return Objects.hash(street, city,state, zipCode);
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
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}
