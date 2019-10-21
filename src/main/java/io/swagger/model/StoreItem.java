package io.swagger.model;
import io.swagger.model.Address;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Objects;
import java.util.UUID;
import javax.validation.Valid;
import javax.validation.constraints.*;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/** StoreItem */
@Validated
@javax.annotation.Generated(
    value = "io.swagger.codegen.v3.generators.java.SpringCodegen",
    date = "2019-09-26T03:54:46.062Z[GMT]")

@ApiModel
@RequestMapping("/stores")
public class StoreItem {
  @JsonProperty("id")
  private String id = null;

  @JsonProperty("address")
  private Address address = null;

  @JsonProperty("hourOpen")
  private String hourOpen = null;

  @JsonProperty("minuteOpen")
  private String minuteOpen = null;

  @JsonProperty("timeOpen")
  private String timeOpen = null;

  @JsonProperty("hourClosed")
  private String hourClosed = null;

  @JsonProperty("minuteClosed")
  private String minuteClosed = null;

  @JsonProperty("timeClosed")
  private String timeClosed = null;

  /**
   * Construct a StoreItem with the given address, hour open, minute open, hour open, and hour closed.
   *
   * @param address store's address, in the form: street city state zip code
   * @param hourOpen hour value of the store's open time, between 00 and 23
   * @param minuteOpen minute value of the store's open time, between 00 and 59
   * @param hourClosed hour value of the store's close time, between 00 and 23
   * @param minuteClosed minute value of the store's close time, between 00 and 59
   */
  public StoreItem(Address address, String hourOpen, String minuteOpen, String hourClosed, String minuteClosed) {
    super();
    this.id = generateUUID();
    this.address = address;
    this.hourOpen = hourOpen;
    this.minuteOpen = minuteOpen;
    this.timeOpen = generateTimeOpen();
    this.hourClosed = hourClosed;
    this.minuteClosed = minuteClosed;
    this.timeClosed = generateTimeClosed();
  }

  /**
   * Get store's unversally unique id.
   *
   * @return store's unversally unique id.
   */
  @ApiModelProperty(example = "d290f1ee-6c54-4b01-90e6-d701748f0851", required = true, value = "")
  @NotNull
  @GetMapping("/{storeId}")
  public String getId() {
    return id;
  }

  /**
   * Helper to generate a random UUID (universally unique identifier).
   *
   * @return random UUID String.
   */
  private String generateUUID() {
    UUID id = UUID.randomUUID();
    randomUUIDString = id.toString;
    return randomUUIDString;
  }

  /**
   * Set store's id.
   *
   * @param id store's id
   */
  public void setId(UUID id) {
    this.id = id;
  }

  /**
   * Get store's address.
   *
   * @return store's address.
   */
  @ApiModelProperty(example = "123 Main St, Coastal, State 77777", required = true, value = "")
  @NotNull
  @Valid
  public Address getAddress() {
    return address.getStreet() + ", " + address.getCity() + ", " + address.getState() + " " + address.getZipCode();
  }

  /**
   * Set store's address.
   *
   * @param address store's address
   */
  public void setAddress(Address address) {
    this.address = address;
  }

  /**
   * Get store's hour open.
   *
   * @return store's hour open.
   */
  @ApiModelProperty(example = "09", required = true, value = "")
  @NotNull
  @Valid
  public String getHourOpen() {
    return hourOpen;
  }

  /**
   * Set store's hour open.
   *
   * @param hourOpen hour value for the store's open time
   */
  public String setHourOpen() {
    this.hourOpen = hourOpen;
  }

  /**
   * Get store's minute open.
   *
   * @return store's minute open.
   */
  @ApiModelProperty(example = "12", required = true, value = "")
  @NotNull
  @Valid
  public String getMinuteOpen() {
    return minuteOpen;
  }

  /**
   * Set store's minute open.
   *
   * @param minuteOpen minute value for store's open time
   */
  public String setMinuteOpen() {
    this.minuteOpen = minuteOpen;
  }

  /**
   * Helper that generates the store's time open using the provided hour open and minute open.
   *
   * @return store's time open.
   */
  private String generateTimeOpen() {
    return hourOpen + ":" + minuteOpen;
  }

  /**
   * Get store's time open.
   *
   * @return store's time open.
   */
  @ApiModelProperty(example = "09:12", required = true, value = "")
  @NotNull
  @Valid
  public String getTimeOpen() {
    return timeOpen;
  }

  /**
   * Set store's time open using the provided hour open and minute open.
   *
   */
  public void setTimeOpen() {
    this.timeOpen = generateTimeOpen();
  }

  /**
   * Get store's hour closed.
   *
   * @return store's hour closed.
   */
  @ApiModelProperty(example = "09", required = true, value = "")
  @NotNull
  @Valid
  public String getHourClosed() {
    return hourClosed;
  }

  /**
   * Set store's hour closed.
   *
   * @param hourClosed hour value for the store's close time
   */
  public String setHourClosed() {
    this.hourClosed = hourClosed;
  }

  /**
   * Get store's minute closed.
   *
   * @return store's minute closed.
   */
  @ApiModelProperty(example = "12", required = true, value = "")
  @NotNull
  @Valid
  public String getMinuteClosed() {
    return minuteClosed;
  }

  /**
   * Set store's minute closed.
   *
   * @param minuteClosed minute value for the store's close time
   */
  public String setMinuteClosed() {
    this.minuteOpen = minuteOpen;
  }

  /**
   * Helper that generates the store's time closed using provided hour closed and minute closed.
   *
   * @return store's time closed.
   */
  private String generateTimeClosed() {
    return hourClosed + ":" + minuteClosed;
  }

  /**
   * Get store's time closed.
   *
   * @return store's time closed.
   */
  @ApiModelProperty(example = "09:12", required = true, value = "")
  @NotNull
  @Valid
  public String getTimeClosed() {
    return timeClosed;
  }

  /**
   * Set store's time closed.
   *
   */
  public void setTimeClosed() {
    this.timeClosed = generateTimeClosed();
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
