package io.swagger.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum CardProvider {
  @JsonProperty("CardProvider")
  VISA,
  MASTER,
  AMERICANEXPRESS,
  DISCOVER;
}
