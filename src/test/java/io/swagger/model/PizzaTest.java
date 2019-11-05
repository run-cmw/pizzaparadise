package io.swagger.model;

import java.util.List;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@TestPropertySource("classpath:/application-test.properties")
@SpringBootTest
public class PizzaTest {

  public Pizza pizza1;
  public Pizza pizza2;
  public Pizza pizza3;
  public Pizza pizzaTest;

  @Before
  public void setUp() {
    pizza1 = new Pizza("small", true);
    pizzaTest = new Pizza("small", true);
    pizza2 = new Pizza("medium", false);
    pizza3 = new Pizza("large", false);
  }

  @Test
  public void getSizeIdTest() {
    Assert.assertEquals(pizza1.getSizeID(), "small");
    Assert.assertEquals(pizza2.getSizeID(), "medium");
    Assert.assertEquals(pizza3.getSizeID(), "large");
    Assert.assertFalse(pizza1.getSizeID().equals(pizza2.getSizeID()));
  }

  @Test
  public void getGlutenTest() {
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



}
