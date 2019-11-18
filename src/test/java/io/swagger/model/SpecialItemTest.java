package io.swagger.model;


import org.junit.Assert;
import org.junit.Test;


public class SpecialItemTest {


  @Test
  public void getIdTest() {
    SpecialItem special1 = new SpecialItem("1", "Buy1Get1Free", "BuyOneGetOne description");
    Assert.assertEquals(special1.getId(), "1");
  }

  @Test
  public void getNameTest() {
    SpecialItem special1 = new SpecialItem("1", "Buy1Get1Free", "BuyOneGetOne description");
    Assert.assertEquals(special1.getName(), "Buy1Get1Free");
  }

  @Test
  public void getDescriptionTest() {
    SpecialItem special1 = new SpecialItem("1", "Buy1Get1Free", "BuyOneGetOne description");
    Assert.assertEquals(special1.getDescription(), "BuyOneGetOne description");
  }

  @Test
  public void setIdTest() {
    SpecialItem test = new SpecialItem("2", "1SodaFree", "you get 1 soda description");
    test.setId("4");
    Assert.assertEquals(test.getId(), "4");
  }

  @Test
  public void setNameTest() {
    SpecialItem test = new SpecialItem("2", "1SodaFree", "you get 1 soda description");
    test.setName("2SodaFree");
    Assert.assertNotEquals(test.getName(), "1SodaFree");
  }

  @Test
  public void setDescriptionTest() {
    SpecialItem test = new SpecialItem("2", "1SodaFree", "you get 1 soda description");
    test.setDescription("you get 2 soda description");
    Assert.assertEquals(test.getDescription(), "you get 2 soda description");
  }

  @Test
  public void equalTest() {
    SpecialItem special1 = new SpecialItem("1", "Buy1Get1Free", "BuyOneGetOne description");
    SpecialItem special2 = new SpecialItem("1", "Buy1Get1Free", "BuyOneGetOne description");
    SpecialItem special3 = new SpecialItem("3", "Bye1Get1Free", "ByeOneGetOne description");
    Assert.assertTrue(special1.equals(special2));
    Assert.assertTrue(special1.equals(special1));
    Assert.assertFalse(special1.equals(special3));
    Assert.assertFalse(special1.equals(null));
  }

  @Test
  public void toStringTest() {
    SpecialItem special1 = new SpecialItem("1", "Buy1Get1Free", "BuyOneGetOne description");
    Assert.assertEquals(special1.toString(), "SpecialItem{id='1', name='Buy1Get1Free', description='BuyOneGetOne description}");

  }





}
