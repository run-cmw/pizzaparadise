package io.swagger.model;


import io.swagger.model.SpecialItem;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class SpecialItemTest {

  private SpecialItem special1;
  private SpecialItem special2;
  private SpecialItem special3;
  private SpecialItem test;

  @Before
  public void setUp() {
    special1 = new SpecialItem("1", "Buy1Get1Free", "BuyOneGetOne description");
    special2 = new SpecialItem("1", "Buy1Get1Free", "BuyOneGetOne description");
    special3 = new SpecialItem("3", "Bye1Get1Free", "ByeOneGetOne description");
    test = new SpecialItem("2", "1SodaFree", "you get 1 soda description");
  }

  @Test
  public void getIdTest() {
    Assert.assertEquals(special1.getId(), "1");
  }

  @Test
  public void getNameTest() {
    Assert.assertEquals(special1.getName(), "Buy1Get1Free");
  }

  @Test
  public void getDescriptionTest() {
    Assert.assertEquals(special1.getDescription(), "BuyOneGetOne description");
  }

  @Test
  public void setIdTest() {
    test.setId("4");
    Assert.assertEquals(test.getId(), "4");
  }

  @Test
  public void setNameTest() {
    test.setName("2SodaFree");
    Assert.assertNotEquals(test.getName(), "1SodaFree");
  }

  @Test
  public void setDescriptionTest() {
    test.setDescription("you get 2 soda description");
    Assert.assertEquals(test.getDescription(), "you get 2 soda description");
  }

  @Test
  public void equalTest() {
    Assert.assertTrue(special1.equals(special2));
    Assert.assertFalse(special1.equals(special3));
  }

  @Test
  public void toStringTest() {
    Assert.assertEquals(special1.toString(), "SpecialItem{id='1', name='Buy1Get1Free', description='BuyOneGetOne description}");

  }





}
