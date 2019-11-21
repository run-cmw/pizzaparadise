package io.swagger.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Price Response
 */
public class PriceResponse {

  @JsonProperty("Success")
  private boolean success;
  @JsonProperty("Price")
  private Double price;
  @JsonProperty("Currency")
  private String currency;

  /**
   * Construct a new PriceResponse
   *
   * @param success true if successfully got the price, false otherwise.
   * @param price price that was found.
   * @param currency currency that was found.
   */
  public PriceResponse(boolean success, Double price, String currency) {
    this.success = success;
    this.price = price;
    this.currency = currency;
  }

  /**
   * Get the price.
   *
   * @return Double price.
   */
  public Double getPrice() {
    return price;
  }

  /**
   * Get the currency unit of this price.
   *
   * @return String if it is in US dollar, return "USD"
   */
  public String getCurrency() {
    return currency;
  }

  /**
   * Check if the PriceResponse was successful.
   *
   * @return true if price was found, false otherwise.
   */
  public boolean isSuccess() {
    return success;
  }
}
