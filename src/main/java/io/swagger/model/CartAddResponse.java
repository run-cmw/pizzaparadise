package io.swagger.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

/**
 * Cart Add Response
 */
public class CartAddResponse {

  @JsonProperty("Success")
  private boolean success;
  @JsonProperty("Items")
  private List<String> items;
  @JsonProperty("CartID")
  private String cartID;
  @JsonProperty("StoreID")
  private String storeID;
  @JsonProperty("Message")
  private String message;

  /**
   * Construct a new CartAddResponse.
   *
   * @param success true if adding was successful, false otherwise.
   * @param items list of items that are added to Cart.
   * @param cartID cartId to show where it was saved.
   * @param storeID storeId to show where it was saved.
   * @param message message to show the if it was success or fail.
   */
  public CartAddResponse(boolean success, List<String> items, String cartID, String storeID,
      String message) {
    this.success = success;
    this.items = items;
    this.cartID = cartID;
    this.storeID = storeID;
    this.message = message;

  }

  /**
   * Check if the CartAddResponse was successful.
   * @return true if it was successful, false otherwise.
   */
  public boolean getSuccess() {
    return this.success;
  }

  /**
   * Get list of items added to cart.
   * @return list of items that are added by this query.
   */
  public List<String> getItems() {
    return this.items;
  }

  /**
   * Get CartId of this CartAddResponse.
   * @return CartId the destination where items are saved.
   */
  public String getCartID() {
    return this.cartID;
  }

  /**
   * Get StoreId of this CartAddResponse.
   * @return storeId the destination where items are saved.
   */
  public String getStoreID() {
    return this.storeID;
  }

  /**
   * Get the message of this CartAddResponse.
   * @return message to show if it was successful or failed.
   */
  public String getMessage() {
    return this.message;
  }

  /**
   * String representation of this CartAddResponse.
   * @return a String representation of this CartAddResponse
   */
  @Override
  public String toString() {
    return "CartAddResponse{" +"success= " + this.success +
        ",cartId= " + this.cartID + ", storeID= " + this.storeID +
        ", items= " + this.items +
        ", message= " + this.message + "}";
  }

  /**
   * Check if two objects are equal.
   * @param obj object given for comparison
   * @return true if two objects are equal, false otherwise.
   */
  @Override
  public boolean equals(Object obj) {
    if(this == obj) { return true;}
    if(obj == null || getClass() != obj.getClass()) { return false; }
    CartAddResponse object = (CartAddResponse) obj;
    return this.success == object.getSuccess() &&
        this.cartID.equals(object.getCartID()) &&
        this.items.equals(object.getItems()) &&
        this.storeID.equals(object.getStoreID()) &&
        this.message.equals(object.getMessage());
  }

}
