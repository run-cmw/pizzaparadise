package io.swagger.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import java.util.ArrayList;
import java.util.List;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.validation.annotation.Validated;

/**
 *  Pizza class
 */
public class Pizza {

  @JsonProperty("PizzaSizeID")
  private String sizeID;
  @JsonProperty("gluten")
  private boolean gluten;
  @JsonProperty("toppingIDs")
  private List<String> toppingIDs;
  private int MAX_TOPPING = 4;
  private Double price;

  /**
   * Create new Pizza
   * @param sizeID pizzaSizeID given to this Pizza
   * @param gluten true if it is gluten, false if it is glutenFree
   */
  public Pizza(String sizeID, boolean gluten) {
    this.sizeID = sizeID;
    this.toppingIDs = new ArrayList<>();
    this.gluten = gluten;
    price = 0.00;
  }

  /**
   * Get pizzaSizeID
   * @return PizzaSizeID
   */
  public String getSizeID() {
    return this.sizeID;
  }

  /**
   * Get the maximum number of topping for this Pizza
   * @return the maximum number of topping for this Pizza
   */
  public int getMAX_TOPPING() {
    return this.MAX_TOPPING;
  }

  /**
   * Get the current number of toppings in this Pizza
   * @return the current number of toppings in this Pizza
   */
  public int getToppingCount() {
    return this.toppingIDs.size();
  }

  /**
   * Check if this pizza is gluten
   * @return true if it is gluten, false if it is a gluten free pizza
   */
  public boolean isGluten() {
    return this.gluten;
  }

  /**
   * Get all the toppingIDs in this Pizza
   * @return list of ToppingIDs in this Pizza
   */
  public List<String> getToppingIDs() {
    return this.toppingIDs;
  }

  public Double getPrice() {
    return this.price;
  }

  public void setPrice(Double price) {
    this.price = price;
  }

  /**
   * String representation of this Pizza.
   * @return a String representation of this Pizza
   */
  @Override
  public String toString() {
    return "Pizza{" + "sizeID= " + this.sizeID
        + ", gluten= " + this.gluten
        + ", toppingIDs= " + this.toppingIDs
        + ", price= " + this.price + "}";
  }

  /**
   * Check if two objects are equal.
   * @param obj object given for comparison
   * @return true if two objects are equal, false otherwise.
   */
  @Override
  public boolean equals(Object obj) {
    if(obj == this) { return true; }
    if(obj == null || getClass() != obj.getClass()) { return false; }
    Pizza pizza = (Pizza) obj;
    return this.sizeID.equals(pizza.getSizeID()) &&
        this.gluten == pizza.gluten &&
        this.toppingIDs.equals(pizza.getToppingIDs()) &&
        Double.compare(pizza.getPrice(), this.price) == 0 &&
        this.getToppingCount() == pizza.getToppingCount();
  }

}
