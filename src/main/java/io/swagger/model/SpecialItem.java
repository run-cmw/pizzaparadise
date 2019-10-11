package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.UUID;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.threeten.bp.OffsetDateTime;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * SpecialItem
 */

@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2019-09-26T03:54:46.062Z[GMT]")
@ApiModel
@RequestMapping("/special")
public class SpecialItem {
  @JsonProperty("id")
  private String id = null;

  @JsonProperty("name")
  private String name = null;

  @JsonProperty("description")
  private String description = null;

  public SpecialItem(String id, String name, String description) {
    super();
    this.id = id;
    this.name = name;
    this.description = description;
  }

  /**
   * Get id
   * @return id
   **/
  @ApiModelProperty(example = "1", required = true, value = "")
  @NotNull

  @Valid
  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  /**
   * Get name
   * @return name
   **/
  @ApiModelProperty(example = "Buy1Get1Free", required = true, value = "")
  @NotNull
  @GetMapping("/{specialName}")
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  /**
   * Get description
   * @return description
   **/
  @ApiModelProperty(example = "Only one special at a time. If you buy 1 pizza, you get 1 free pizza that is equal or less value.", required = true, value = "")
  @NotNull

  @Valid
  public String getDescription() {
    return description;
  }

  public void setDescription(String releaseDate) {
    this.description = description;
  }

  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SpecialItem specialItem = (SpecialItem) o;
    return Objects.equals(this.id, specialItem.id) &&
        Objects.equals(this.name, specialItem.name) &&
        Objects.equals(this.description, specialItem.description);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, description);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class SpecialItem {\n");

    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
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
