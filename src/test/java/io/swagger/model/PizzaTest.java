package io.swagger.model;

import org.junit.Assert;
import org.junit.Test;


public class PizzaTest {


  @Test
  public void getSizeIdTest() {
    Pizza pizza1 = new Pizza("small", true);
    Pizza pizzaTest = new Pizza("small", true);
    Pizza pizza2 = new Pizza("medium", false);
    Pizza pizza3 = new Pizza("large", false);
    Assert.assertEquals(pizza1.getSizeID(), "small");
    Assert.assertEquals(pizza2.getSizeID(), "medium");
    Assert.assertEquals(pizza3.getSizeID(), "large");
    Assert.assertFalse(pizza1.getSizeID().equals(pizza2.getSizeID()));
  }

  @Test
  public void getGlutenTest() {
    Pizza pizza1 = new Pizza("small", true);
    Pizza pizza2 = new Pizza("medium", false);
    Pizza pizza3 = new Pizza("large", false);

    Assert.assertEquals(pizza1.isGluten(), true);
    Assert.assertEquals(pizza2.isGluten(), false);
    Assert.assertEquals(pizza3.isGluten(), false);
  }

  @Test
  public void getToppingCountTest() {
    Pizza pizzaTest1 = new Pizza("small", false);
    pizzaTest1.getToppingIDs().add("pepperoni");
    pizzaTest1.getToppingIDs().add("sausage");
    pizzaTest1.getToppingIDs().add("onion");

    Assert.assertEquals(pizzaTest1.getToppingCount(), 3);
    Assert.assertEquals(pizzaTest1.getMAX_TOPPING(), 4);
    Assert.assertEquals(pizzaTest1.getToppingCount() <pizzaTest1.getMAX_TOPPING(), true);
  }

  @Test
  public void getToppingIdsTest() {
    Pizza pizzaTest1 = new Pizza("small", false);
    pizzaTest1.getToppingIDs().add("pepperoni");
    pizzaTest1.getToppingIDs().add("sausage");
    pizzaTest1.getToppingIDs().add("onion");

    Assert.assertEquals(pizzaTest1.getToppingIDs().contains("pepperoni"), true);
    Assert.assertEquals(pizzaTest1.getToppingIDs().contains("sausage"), true);
    Assert.assertEquals(pizzaTest1.getToppingIDs().contains("onion"), true);
    Assert.assertEquals(pizzaTest1.getToppingIDs().contains("onionsss"), false);

  }

  @Test
  public void getPriceTest() {
    Pizza pizzaTest1 = new Pizza("large", false);
    pizzaTest1.getToppingIDs().add("pepperoni");
    Assert.assertEquals(pizzaTest1.getPrice(), (Double) 0.0);
    pizzaTest1.setPrice(23.99);
    Assert.assertEquals((Double) 23.99, pizzaTest1.getPrice());

  }

  @Test
  public void isEqualTest() {
    Pizza pizzaTest1 = new Pizza("large", false);
    Pizza pizzaTest2 = new Pizza("large", false);
    Assert.assertEquals(pizzaTest1.equals(pizzaTest2), true);
    Assert.assertEquals(pizzaTest1.equals(null), false);
    Pizza pizzaTest3 = new Pizza("small", false);
    Assert.assertEquals(pizzaTest1.equals(pizzaTest3), false);

    pizzaTest2.getToppingIDs().add("pepperoni");
    Assert.assertEquals(pizzaTest1.equals(pizzaTest2), false);

    Pizza pizzaTest4= new Pizza("small", true);
    Assert.assertEquals(pizzaTest4.equals(pizzaTest3), false);

    Pizza pizzaTest5 = new Pizza("large", false);
    pizzaTest5.setPrice(5.00);
    Pizza pizzaTest6 = new Pizza("large", false);
    pizzaTest6.setPrice(6.00);
    Assert.assertEquals(pizzaTest5.equals(pizzaTest6), false);
    pizzaTest6.setPrice(5.00);
    Assert.assertEquals(pizzaTest5.equals(pizzaTest6), true);



  }

  @Test
  public void toStringTest() {
    Pizza pizzaTest1 = new Pizza("large", false);
    Assert.assertEquals(pizzaTest1.toString(), "Pizza{sizeID= large, gluten= false, toppingIDs= [], price= 0.0}");

    Pizza pizzaTest2 = new Pizza("small", true);
    pizzaTest2.getToppingIDs().add("pepperoni");
    pizzaTest2.getToppingIDs().add("onion");
    pizzaTest2.getToppingIDs().add("sausage");
    Assert.assertEquals(pizzaTest2.toString(), "Pizza{sizeID= small, gluten= true, toppingIDs= [pepperoni, onion, sausage], price= 0.0}");
    Assert.assertNotEquals(pizzaTest2.toString(), "Pizza{sizeID= small, gluten= true, toppingIDs= [onion, sausage, pepperoni], price= 0.0}");

  }




}
