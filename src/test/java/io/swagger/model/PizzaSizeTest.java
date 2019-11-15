package io.swagger.model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class PizzaSizeTest {

  private PizzaSize size1;
  private PizzaSize size2;
  private PizzaSize size3;
  private PizzaSize test;

  @Before
  public void setUp() {
    size1 = new PizzaSize("1", "small", "11", 13.00);
    size2 = new PizzaSize("1", "small", "11", 13.00);
    size3 = new PizzaSize("2", "medium", "13", 15.00);
    test = new PizzaSize("2", "medium", "13", 15.00);
  }

  @Test
  public void getIdTest() {
    Assert.assertEquals(size1.getId(), "1");
  }

  @Test
  public void getSizeNameTest() {
    Assert.assertEquals(size1.getSizeName(), "small");
  }

  @Test
  public void getSizeInchTest() {
    Assert.assertEquals(size1.getSizeInch(), "11");
  }

  @Test
  public void getPriceTest() {
    Assert.assertEquals(size1.getPrice(), (Double) 13.00);
  }

  @Test
  public void setIdTest() {
    test.setId("4");
    Assert.assertEquals(test.getId(), "4");
  }

  @Test
  public void setSizeNameTest() {
    test.setSizeName("extra large");
    Assert.assertNotEquals(test.getSizeName(), "large");
  }

  @Test
  public void setSizeInchTest() {
    test.setSizeInch("18");
    Assert.assertEquals(test.getSizeInch(), "18");
  }

  @Test
  public void setPriceTest() {
    test.setPrice(25.00);
    Assert.assertEquals(test.getPrice(), (Double) 25.00);
  }

  @Test
  public void equalTest() {
    Assert.assertTrue(size1.equals(size2));
    Assert.assertFalse(size1.equals(size3));
    Assert.assertFalse(size1.equals(null));
  }

  @Test
  public void toStringTest() {
    Assert.assertEquals(size1.toString(),
        "PizzaSize{id='1', sizeName='small', sizeInch='11', price='13.0}");

  }
}

