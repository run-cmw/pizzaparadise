package io.swagger.model;

import io.swagger.annotations.ApiModel;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.validation.annotation.Validated;

/**
 * Cart class
 */
@Validated
@javax.annotation.Generated(
    value = "io.swagger.codegen.v3.generators.java.SpringCodegen",
    date = "2019-09-26T03:54:46.062Z[GMT]")
@ApiModel
@Document(collection = "Cart")
public class Cart {
  @Id
  private ObjectId id;
  private List<Pizza> pizzas;
  private List<String> sides;
  private String storeID;
  private Double totalAmount;

  /**
   * Create new Cart
   * @param id cartID given to this new Cart
   */
  public Cart(String storeID, ObjectId id) {
    this.storeID = storeID;
    this.id = id;
    this.totalAmount = 0.0;
    this.pizzas = new ArrayList<>();
    this.sides = new ArrayList<>();
  }

  /**
   * Get cartID
   * @return cartID
   */
  public String getId() {
    return this.id.toHexString();
  }

  /**
   * Set cartID
   * @param id new cartID given to this Cart
   */
  public void setId(ObjectId id) {
    this.id = id;
  }

  /**
   * Get storeID
   * @return storeID
   */
  public String getStoreID() { return this.storeID; }

  /**
   * Set storeID
   * @param id new storeID given to this Cart
   */
  public void setStoreID(String id) { this.storeID = storeID; }

  /**
   * Get the totalAmount in this Cart
   * @return totalAmount in this Cart
   */
  public Double getTotalAmount() {
    return this.totalAmount;
  }

  /**
   * Set the totalAmount in this Cart
   * @param p new totalAmount given to this Cart
   */
  public void setTotalAmount(Double totalAmount) {
    this.totalAmount = totalAmount;
  }

  /**
   * Get list of pizzas in this Cart
   * @return list of pizzas in this Cart
   */
  public List<Pizza> getPizzas() {
    return this.pizzas;
  }

  /**
   * Get list of sideID in this Cart
   * @return list of sideID in this Cart
   */
  public List<String> getSides() {
    return this.sides;
  }


  /**
   * String representation of this Cart
   * @return a String representation of this Cart
   */
  @Override
  public String toString() {
    return "Cart{"
        + "id='" + id + '\''
        + ", list of pizza= " + pizzas.toString() + '\''
        + ", list of side= " + sides.toString() + '\''
        + ", total price= " + totalAmount
        + '}';
  }

  @Override
  public boolean equals(Object obj) {
    if(this == obj) return true;
    if(obj == null) { return false; }
    Cart cart = (Cart) obj;
    return cart.getSides().equals(this.sides) && cart.getStoreID().equals(this.storeID)
        && cart.getPizzas().equals(this.pizzas) && cart.getId().equals(this.id)
        && cart.getTotalAmount().equals(this.totalAmount);
  }

}
