package io.swagger.model;

import org.junit.Assert;
import org.junit.Test;


public class PizzaSizeTest {


  @Test
  public void getIdTest() {
    PizzaSize size1 = new PizzaSize("1", "small", "11", 13.00);
    Assert.assertEquals(size1.getId(), "1");
    PizzaSize size3 = new PizzaSize("2", "medium", "13", 15.00);
    Assert.assertEquals(size3.getId(), "2");
  }

  @Test
  public void getSizeNameTest() {
    PizzaSize size1 = new PizzaSize("1", "small", "11", 13.00);
    Assert.assertEquals(size1.getSizeName(), "small");
    PizzaSize test = new PizzaSize("2", "medium", "13", 15.00);
    Assert.assertEquals(test.getSizeName(), "medium");

  }

  @Test
  public void getSizeInchTest() {
    PizzaSize size1 = new PizzaSize("1", "small", "11", 13.00);
    Assert.assertEquals(size1.getSizeInch(), "11");
    PizzaSize size3 = new PizzaSize("2", "medium", "13", 15.00);
    Assert.assertEquals(size3.getSizeInch(), "13");
  }

  @Test
  public void getPriceTest() {
    PizzaSize size1 = new PizzaSize("1", "small", "11", 13.00);
    Assert.assertEquals(size1.getPrice(), (Double) 13.00);
    PizzaSize size3 = new PizzaSize("2", "medium", "13", 15.00);
    Assert.assertEquals(size3.getPrice(), (Double) 15.00);
  }

  @Test
  public void setIdTest() {
    PizzaSize test = new PizzaSize("2", "medium", "13", 15.00);
    test.setId("4");
    Assert.assertEquals(test.getId(), "4");
  }

  @Test
  public void setSizeNameTest() {
    PizzaSize test = new PizzaSize("2", "medium", "13", 15.00);
    test.setSizeName("extra large");
    Assert.assertNotEquals(test.getSizeName(), "large");
  }

  @Test
  public void setSizeInchTest() {
    PizzaSize test = new PizzaSize("2", "medium", "13", 15.00);
    test.setSizeInch("18");
    Assert.assertEquals(test.getSizeInch(), "18");
  }

  @Test
  public void setPriceTest() {
    PizzaSize test = new PizzaSize("2", "medium", "13", 15.00);
    test.setPrice(25.00);
    Assert.assertEquals(test.getPrice(), (Double) 25.00);
  }

  @Test
  public void equalTest() {
    PizzaSize size1 = new PizzaSize("1", "small", "11", 13.00);
    PizzaSize size2 = new PizzaSize("1", "small", "11", 13.00);
    PizzaSize size3 = new PizzaSize("2", "medium", "13", 15.00);

    Assert.assertTrue(size1.equals(size2));
    Assert.assertFalse(size1.equals(size3));
    Assert.assertFalse(size1.equals(null));
  }

  @Test
  public void toStringTest() {
    PizzaSize size1 = new PizzaSize("1", "small", "11", 13.00);
    Assert.assertEquals(size1.toString(),
        "PizzaSize{id='1', sizeName='small', sizeInch='11', price='13.0}");

  }
}

