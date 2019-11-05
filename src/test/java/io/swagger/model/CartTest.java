package io.swagger.model;

import java.util.List;
import org.bson.types.ObjectId;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CartTest {
  private Cart cart1;
  private Cart cart2;
  private Cart cart3;
  private ObjectId id1;



  @Before
  public void setUp() {
    id1 = new ObjectId();
    ObjectId id2 = new ObjectId();
    cart1 = new Cart("brooklyn", id1);
    cart2 = new Cart("brooklyn", id1);
    cart3 = new Cart("stoneWay", id2);
  }

  @Test
  public void getIdTest() {
    Assert.assertNotEquals(cart1.getId(), " rdfd");
    Assert.assertEquals(cart1.getId(), id1.toHexString());
  }

  @Test
  public void getStoreIDTest() {
    Assert.assertEquals(cart1.getStoreID(), "brooklyn");
    Assert.assertTrue(cart1.getStoreID().equals(cart1.getStoreID()));
    Assert.assertFalse(cart1.getStoreID().equals(cart3.getStoreID()));
  }


  @Test
  public void getPriceTest() {
    Assert.assertEquals(cart1.getTotalAmount(), (Double) 0.00);
  }


  @Test
  public void getSideTest() {
    List<String> sideId = cart2.getSides();
    Assert.assertEquals(sideId.size(), 0);
  }

  @Test
  public void getPizzaTest() {
    List<Pizza> pizzas = cart2.getPizzas();
    Assert.assertEquals(pizzas.size(), 0);
    Pizza pizza1 = new Pizza("small", false);
    pizzas.add(pizza1);
    Assert.assertEquals(pizzas.size(), 1);
  }


  @Test
  public void equalTest() {
    Assert.assertFalse(cart1.equals(null));
    Assert.assertFalse(cart1.equals(cart2));
    ObjectId testId = new ObjectId();
    Cart test1 = new Cart("brooklyn", testId);
    Assert.assertTrue(test1.equals(test1));
  }

  @Test
  public void toStringTest() {
    Assert.assertEquals(cart1.toString(), "Cart{id='" + id1 +
        "', list of pizza= []'," + " list of side= []', total price= 0.0}");


  }

  @Test
  public void SettingNewCartTest() {
    ObjectId id2 = new ObjectId();
    Cart test2 = new Cart("eastlake", id2);
    test2.setStoreID("brooklyn");
    test2.setTotalAmount(5.00);
    ObjectId id3 = new ObjectId();
    test2.setId(id3);
    Assert.assertEquals(test2.getTotalAmount(), (Double) 5.00);

    Assert.assertEquals(test2.getId(), id3.toHexString());


  }
}