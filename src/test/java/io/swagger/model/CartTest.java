package io.swagger.model;

import static org.junit.Assert.assertEquals;

import java.util.List;
import org.bson.types.ObjectId;
import org.junit.Assert;
import org.junit.Test;

public class CartTest {


  @Test
  public void getIdTest() {
    ObjectId id1 = new ObjectId();
    Cart cart1 = new Cart("brooklyn", id1);
    Assert.assertNotEquals(cart1.getId(), " rdfd");
    Assert.assertEquals(cart1.getId(), id1.toHexString());
  }

  @Test
  public void getStoreIDTest() {
    ObjectId id1 = new ObjectId();
    ObjectId id2 = new ObjectId();
    Cart cart1 = new Cart("brooklyn", id1);
    Cart cart3 = new Cart("stoneWay", id2);
    Assert.assertEquals(cart1.getStoreID(), "brooklyn");
    Assert.assertTrue(cart1.getStoreID().equals(cart1.getStoreID()));
    Assert.assertFalse(cart1.getStoreID().equals(cart3.getStoreID()));
  }


  @Test
  public void getPriceTest() {
    ObjectId id1 = new ObjectId();
    Cart cart1 = new Cart("brooklyn", id1);
    Assert.assertEquals(cart1.getTotalAmount(), (Double) 0.00);
  }


  @Test
  public void getSideTest() {
    ObjectId id1 = new ObjectId();
    Cart cart2 = new Cart("stoneWay", id1);
    List<String> sideId = cart2.getSides();
    Assert.assertEquals(sideId.size(), 0);
    cart2.getSides().add("pepperoni");
    Assert.assertEquals(sideId.size(), 1);
    Assert.assertEquals(sideId.contains("pepperoni"), true);
  }

  @Test
  public void getPizzaTest() {
    ObjectId id1 = new ObjectId();
    Cart cart2 = new Cart("brooklyn", id1);
    List<Pizza> pizzas = cart2.getPizzas();
    Assert.assertEquals(pizzas.size(), 0);
    Pizza pizza1 = new Pizza("small", false);
    pizzas.add(pizza1);
    Assert.assertEquals(pizzas.size(), 1);
  }


  @Test
  public void toStringTest() {
    ObjectId id1 = new ObjectId();
    Cart cart1 = new Cart("eastlake", id1);
    String test = "Cart{cartId= " + id1 +", storeId= eastlake, listOfPizza= [], listOfSide= [], totalPrice= 0.0, specialApplied= false}";
    Assert.assertEquals(cart1.toString(), test);

    ObjectId cartId2 = new ObjectId();
    String store2 = "brooklyn";
    Cart cart2 = new Cart(store2, cartId2);
    cart2.setSpecialApplied(true);
    cart2.setTotalAmount(8.55);
    cart2.getSides().add("16OzWater");
    String test2 = "Cart{cartId= " + cartId2.toString() +", storeId= brooklyn, listOfPizza= [], listOfSide= [16OzWater], totalPrice= 8.55, specialApplied= true}";
    Assert.assertEquals(cart2.toString(), test2);


  }

  @Test
  public void isSpecialAppliedTest() {
    ObjectId id1 = new ObjectId();
    Cart cart1 = new Cart("brooklyn", id1);
    Assert.assertEquals(cart1.isSpecialApplied(), false);
    cart1.setSpecialApplied(true);
    Assert.assertEquals(cart1.isSpecialApplied(), true);
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

  @Test
  public void equalTest() {
    ObjectId id1 = new ObjectId();
    Cart cart1 = new Cart("brooklyn", id1);
    Cart cart2 = new Cart("brooklyn", id1);

    Assert.assertFalse(cart1.equals(null));
    Assert.assertTrue(cart1.equals(cart2));

    ObjectId testId = new ObjectId();
    Cart test1 = new Cart("brooklyn", testId);
    Assert.assertTrue(test1.equals(test1));
    test1.setTotalAmount(5.00);
    Cart test2 = new Cart("brooklyn", testId);
    test1.setTotalAmount(3.00);
    Assert.assertFalse(test1.equals(test2));
    ObjectId testId2 = new ObjectId();
    Cart test3 = new Cart("eastlake", testId2);
    test3.setSpecialApplied(true);
    Cart test4 = new Cart("eastlake", testId2);

    Cart test5 = new Cart("stoneWay", testId2);

    Assert.assertFalse(test1.equals(test4));
    Assert.assertFalse(test3.equals(test4));
    Assert.assertFalse(test3.equals(test5));
    Assert.assertFalse(test4.equals(test5));

    ObjectId testId3 = new ObjectId();
    Cart test6 = new Cart("stoneWay", testId3);
    test6.getSides().add("pepperoni");

    Cart test7 = new Cart("stoneWay", testId3);
    test7.getSides().add("onion");
    Assert.assertFalse(test6.equals(test7));

    Pizza pizza1 = new Pizza("small", true);
    Pizza pizza2 = new Pizza("medium", false);

    Cart test8 = new Cart("stoneWay", testId3);
    test8.getPizzas().add(pizza1);
    test8.getPizzas().add(pizza2);
    Cart test9 = new Cart("stoneWay", testId3);
    Assert.assertFalse(test8.equals(test9));

    ObjectId testId4 = new ObjectId();
    Cart test10 = new Cart("eastlake", testId4);
    test10.getPizzas().add(pizza1);
    test10.getSides().add("pepperoni");
    test10.setTotalAmount(5.00);
    test10.setSpecialApplied(true);
    Cart test11 = new Cart("eastlake", testId4);
    test11.getPizzas().add(pizza1);
    test11.getSides().add("pepperoni");
    test11.setTotalAmount(5.00);
    test11.setSpecialApplied(true);
    Assert.assertEquals(test10.toString(), test11.toString());

    ObjectId testId5 = new ObjectId();
    Cart test12 = new Cart("eastlake", testId5);
    Cart test13 = new Cart("eastlake", testId5);
    test13.setSpecialApplied(true);
    Assert.assertEquals(test12.equals(test13), false);

  }
}