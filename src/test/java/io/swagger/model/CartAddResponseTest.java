package io.swagger.model;

import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;

public class CartAddResponseTest {

  @Test
  public void getItemsTest() {
    List<String> item1 = new ArrayList<>();
    item1.add("medium");
    item1.add("onion");
    CartAddResponse response1 = new CartAddResponse(true, item1, "cart1","brooklyn", "successful");
    List<String> itemTest1 = new ArrayList<>();
    itemTest1.add("medium");
    itemTest1.add("onion");
    Assert.assertEquals(response1.getItems(), itemTest1);
    List<String> itemTest2 = new ArrayList<>();
    itemTest2.add("large");
    itemTest2.add("gluten");
    itemTest2.add("onion");
    Assert.assertNotEquals(response1.getItems(), itemTest2);
  }

  @Test
  public void isSuccessTest() {
    List<String> item1 = new ArrayList<>();
    item1.add("medium");
    item1.add("gluten");
    CartAddResponse response1 = new CartAddResponse(true, item1, "cart1","brooklyn", "successful");
    CartAddResponse response2 = new CartAddResponse(false, item1, "cart1","brooklyn", "successful");
    Assert.assertEquals(response1.getSuccess(), true);
    Assert.assertEquals(response2.getSuccess(), false);
  }

  @Test
  public void getCartIDTest() {
    List<String> item1 = new ArrayList<>();
    item1.add("medium");
    item1.add("onion");
    CartAddResponse response1 = new CartAddResponse(true, item1, "cart1","brooklyn", "successful");
    CartAddResponse response3 = new CartAddResponse(true, item1, "cart2","eastlake", "successful");

    Assert.assertEquals(response1.getCartID(), "cart1");
    Assert.assertNotEquals(response1.getCartID(), "cart2");
    Assert.assertEquals(response3.getCartID(), "cart2");
  }

  @Test
  public void getStoreIDTest() {
    List<String> item1 = new ArrayList<>();
    item1.add("medium");
    item1.add("onion");
    CartAddResponse response1 = new CartAddResponse(true, item1, "cart1","brooklyn", "successful");
    CartAddResponse response3 = new CartAddResponse(true, item1, "cart2","eastlake", "successful");

    Assert.assertEquals(response1.getStoreID(), "brooklyn");
    Assert.assertNotEquals(response1.getStoreID(), "eastlake");
    Assert.assertEquals(response3.getStoreID(), "eastlake");
  }

  @Test
  public void getMessageTest() {
    List<String> item1 = new ArrayList<>();
    item1.add("small");
    item1.add("gluten");
    CartAddResponse response1 = new CartAddResponse(true, item1, "cart1","brooklyn", "successful");
    CartAddResponse response3 = new CartAddResponse(true, item1, "cart2","eastlake", "successful");
    CartAddResponse response4 = new CartAddResponse(true, item1, "cart2","eastlake", "not successful");

    Assert.assertEquals(response1.getMessage(), "successful");
    Assert.assertNotEquals(response1.getMessage(), "not successful");
    Assert.assertEquals(response3.getMessage(), "successful");
    Assert.assertEquals(response4.getMessage(), "not successful");
    Assert.assertNotEquals(response4.getMessage(), "nott successful");
  }

  @Test
  public void toStringTest() {
    List<String> item1 = new ArrayList<>();
    item1.add("small");
    item1.add("gluten");
    CartAddResponse response1 = new CartAddResponse(true, item1, "cart1","brooklyn", "successful");
    Assert.assertEquals(response1.toString(), "CartAddResponse{success= true,cartId= cart1, storeID= brooklyn, items= [small, gluten], message= successful}");

  }

  @Test
  public void equalTest() {
    List<String> item1 = new ArrayList<>();
    item1.add("small");
    item1.add("gluten");
    item1.add("pepperoni");
    CartAddResponse response1 = new CartAddResponse(true, item1, "cart1","brooklyn", "successful");
    CartAddResponse sameAsResponse1 = new CartAddResponse(true, item1, "cart1","brooklyn", "successful");

    CartAddResponse response2 = new CartAddResponse(false, item1, "cart1","brooklyn", "successful");
    CartAddResponse response3 = new CartAddResponse(true, item1, "cart2","eastlake", "successful");
    CartAddResponse response4 = new CartAddResponse(true, item1, "cart1","brooklyn", "not successful");

    Assert.assertTrue(response1.equals(response1));
    Assert.assertTrue(response1.equals(sameAsResponse1));

    Assert.assertFalse(response1.equals(response2));
    Assert.assertFalse(response1.equals(response3));
    Assert.assertFalse(response1.equals(null));
    Assert.assertFalse(response1.equals(response4));

  }

}
