package io.swagger.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ToppingItemTest {
  private ToppingItem toppingItem1;
  private ToppingItem toppingItem2;
  private ToppingItem toppingItem3;

  @Before
  public void setUp() {
    toppingItem1 = new ToppingItem("pepperoni1", "pepperoni", "meat", 2.5, 2.75, 3.0, "gluten");
    toppingItem2 = new ToppingItem("sausage1", "sausage", "meat", 2.5, 2.75, 3.0, "gluten");
    toppingItem3 = new ToppingItem("onion1", "onion", "vegetable", 2.0, 2.25, 2.5, "non-gluten");

    // Test setters within setup method
    toppingItem1.setId("pepperoni1");
    toppingItem1.setToppingName("pepperoni");
    toppingItem1.setToppingSmallPrice(2.5);
  }

  @Test
  public void getIdTest() {
    assertEquals("pepperoni1", toppingItem1.getId());
  }

  @Test
  public void getNameTest() {
    assertEquals("pepperoni", toppingItem1.getToppingName());
  }

  @Test
  public void getPriceTest() {
    Double price;
    price = 2.5;
    assertEquals(price, toppingItem1.getToppingSmallPrice());
  }

  @Test
  public void equalsTest() {
    assertNotEquals(toppingItem2, toppingItem1);
    assertEquals(toppingItem2, toppingItem2);
    assertNotEquals(toppingItem3, toppingItem1);
    assertNotEquals(toppingItem2, 2);
    assertNotEquals(toppingItem2, null);
  }

  @Test
  public void hashCodeTest() {
    assertEquals(toppingItem1.hashCode(), toppingItem1.hashCode());
    assertNotEquals(toppingItem3.hashCode(), toppingItem1.hashCode());
  }

  @Test
  public void toStringTest() {
    final String TOPPING_ITEM_AS_STRING =
        "ToppingItem{"
            + "id='" + toppingItem1.getId() + '\''
            + ", topping name='" + toppingItem1.getToppingName() + '\''
            + ", topping type='" + toppingItem1.getToppingType() + '\''
            + ", topping small price='" + toppingItem1.getToppingSmallPrice() + '\''
            + ", topping medium price='" + toppingItem1.getToppingMediumPrice() + '\''
            + ", topping large price='" + toppingItem1.getToppingLargePrice() + '\''
            + ", topping gluten='" + toppingItem1.getToppingGluten() + '\''
        + '}';

    assertEquals(TOPPING_ITEM_AS_STRING, toppingItem1.toString());
  }


  @Test
  public void setToppingTypeTest() {
    ToppingItem topping1 = new ToppingItem("pepperoni1", "pepperoni", "meat", 2.5, 2.75, 3.0, "gluten");
    Assert.assertEquals(topping1.getToppingType(), "meat");
    topping1.setToppingType("vegetable");
    Assert.assertEquals(topping1.getToppingType(), "vegetable");

  }

  @Test
  public void setToppingMedPriceTest() {
    ToppingItem topping1 = new ToppingItem("pepperoni1", "pepperoni", "meat", 2.5, 2.75, 3.0, "gluten");
    Assert.assertEquals(topping1.getToppingMediumPrice(), (Double) 2.75);
    topping1.setToppingMediumPrice(3.00);
    Assert.assertEquals(topping1.getToppingMediumPrice(), (Double) 3.00);
  }

  @Test
  public void setToppingLargePriceTest() {
    ToppingItem topping1 = new ToppingItem("onion1", "onion", "vegetable", 2.0, 2.25, 2.5, "non-gluten");
    Assert.assertEquals(topping1.getToppingLargePrice(), (Double) 2.5);
    topping1.setToppingLargePrice(3.00);
    Assert.assertEquals(topping1.getToppingLargePrice(), (Double) 3.00);
  }

  @Test
  public void setGlutenTest() {
    ToppingItem topping1 = new ToppingItem("onion1", "onion", "vegetable", 2.0, 2.25, 2.5, "non-gluten");
    Assert.assertEquals(topping1.getToppingGluten(), "non-gluten");
    topping1.setToppingGluten("gluten");
    Assert.assertEquals(topping1.getToppingGluten(), "gluten");
  }
}