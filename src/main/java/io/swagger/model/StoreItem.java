package io.swagger.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import java.time.LocalTime;
import java.util.Objects;
import java.util.UUID;
import javax.validation.Valid;
import javax.validation.constraints.*;
import org.springframework.validation.annotation.Validated;

/** StoreItem */
@Validated
@javax.annotation.Generated(
    value = "io.swagger.codegen.v3.generators.java.SpringCodegen",
    date = "2019-09-26T03:54:46.062Z[GMT]")
public class StoreItem {
  @JsonProperty("id")
  private UUID id = null;

  @JsonProperty("address")
  private Address address = null;

  @JsonProperty("timeOpen")
  private LocalTime timeOpen = null;

  @JsonProperty("timeClosed")
  private LocalTime timeClosed = null;

  /**
   * Construct a StoreItem id with the given id.
   *
   * @param id store's id.
   * @return
   */
  public StoreItem id(UUID id) {
    this.id = id;
    return this;
  }

  /**
   * Get store's id.
   *
   * @return store's id.
   */
  @ApiModelProperty(example = "d290f1ee-6c54-4b01-90e6-d701748f0851", required = true, value = "")
  @NotNull
  @Valid
  public UUID getId() {
    return id;
  }

  /**
   * Set store's id.
   *
   * @param id store's id.
   */
  public void setId(UUID id) {
    this.id = id;
  }

  /**
   * Construct a StoreItem address with the given address information.
   *
   * @param name
   * @return
   */
  public StoreItem address(Address address) {
    this.address = address;
    return this;
  }

  /**
   * Get store's address.
   *
   * @return store's address.
   */
  @ApiModelProperty(example = "123 Main St Coastal State 77777", required = true, value = "")
  @NotNull
  public Address getAddress() {
    return address;
  }

  /**
   * Set store's address.
   *
   * @param address
   */
  public void setAddress(Address address) {
    this.address = address;
  }

  /**
   * Construct a StoreItem timeOpen with the given opening time information.
   *
   * @param timeOpen
   * @return
   */
  public StoreItem timeOpen(LocalTime timeOpen) {
    this.timeOpen = timeOpen;
    return this;
  }

  /**
   * Get store's time open.
   *
   * @return store's time open.
   */
  @ApiModelProperty(example = "09:12:33", required = true, value = "")
  @NotNull
  @Valid
  public LocalTime getTimeOpen() {
    return timeOpen;
  }

  /**
   * Set store's time open.
   *
   * @param timeOpen
   */
  public void setTimeOpen(LocalTime timeOpen) {
    this.timeOpen = timeOpen;
  }

  /**
   * Construct a StoreItem timeClosed with the given closing time information.
   *
   * @param timeClosed
   * @return
   */
  public StoreItem timeClosed(LocalTime timeClosed) {
    this.timeClosed = timeClosed;
    return this;
  }

  /**
   * Get store's time closed.
   *
   * @return store's time closed.
   */
  @ApiModelProperty(example = "09:12:33", required = true, value = "")
  @NotNull
  @Valid
  public LocalTime getTimeClosed() {
    return timeClosed;
  }

  /**
   * Set store's time closed.
   *
   * @param timeClosed
   */
  public void setTimeClosed(LocalTime timeClosed) {
    this.timeClosed = timeClosed;
  }

  /**
   * Helper to check if store is currently open.
   *
   * @return {@true} if store is open and {@false} otherwise.
   */
  public boolean isOpenCheck() {
    // What if the user is in a different time zone?
    // What if the server is in a different time zone?
    LocalTime currentTime = LocalTime.now();
    return (currentTime.isAfter(timeOpen) && currentTime.isBefore(timeClosed));
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
        && Objects.equals(this.address, storeItem.address)
        && Objects.equals(this.timeOpen, storeItem.timeOpen)
        && Objects.equals(this.timeClosed, storeItem.timeClosed);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, address, timeOpen, timeClosed);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class StoreItem {\n");

    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    address: ").append(toIndentedString(address)).append("\n");
    sb.append("    timeOpen: ").append(toIndentedString(timeOpen)).append("\n");
    sb.append("    timeClosed: ").append(toIndentedString(timeClosed)).append("\n");
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
