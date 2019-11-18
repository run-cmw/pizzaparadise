package io.swagger.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;


public class PizzaTest {
  public Pizza pizza1;
  public Pizza pizza2;

  @Before
  public void setUp() {
    pizza1 = new Pizza("small", true);
    pizza2 = new Pizza("medium", false);
  }


  @Test
  public void getSizeIdTest() {
    assertEquals("small", pizza1.getSizeID());
  }

  @Test
  public void getGlutenTest() {
    assertTrue(pizza1.isGluten());
  }

  @Test
  public void getToppingCountTest() {
   assertEquals(0, pizza1.getToppingCount());
  }

  @Test
  public void getToppingIdsTest() {
    pizza1.getToppingIDs().add("pepperoni");
    pizza1.getToppingIDs().add("sausage");

    assertTrue(pizza1.getToppingIDs().contains("pepperoni"));
    assertTrue(pizza1.getToppingIDs().contains("sausage"));
  }

  @Test
  public void getPriceTest() {
    assertEquals((Double) 0.00, pizza1.getPrice());
  }

  @Test
  public void setPriceTest() {
    pizza1.setPrice(5.00);
    assertEquals((Double) 5.00, pizza1.getPrice());
  }

  @Test
  public void equalTest() {
    Pizza sameAsPizza1 = new Pizza("small", true);
    assertEquals(pizza1, sameAsPizza1);
    assertEquals(pizza1, pizza1);
    assertNotEquals(pizza1, pizza2);
  }

  @Test
  public void toStringTest() {
    assertEquals("Pizza{sizeID=small, gluten=true, toppingIDs=[], price=0.0}", pizza1.toString());
  }
}
