package io.swagger.model;

import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CartAddResponseTest {
  public CartAddResponse response1;
  public CartAddResponse sameAsResponse1;
  public CartAddResponse response2;
  public CartAddResponse response3;
  public CartAddResponse response4;

  @Before
  public void setUp() {
    List<String> item1 = new ArrayList<>();
    item1.add("medium");
    item1.add("onion");
    response1 = new CartAddResponse(true, item1, "cart1","brooklyn", "successful");
    sameAsResponse1 = new CartAddResponse(true, item1, "cart1","brooklyn", "successful");

    response2 = new CartAddResponse(false, item1, "cart1","brooklyn", "successful");
    response3 = new CartAddResponse(true, item1, "cart2","eastlake", "successful");
    response4 = new CartAddResponse(true, item1, "cart2","eastlake", "not successful");
  }

  @Test
  public void getItemsTest() {
    List<String> itemTest1 = new ArrayList<>();
    itemTest1.add("medium");
    itemTest1.add("onion");
    Assert.assertEquals(response1.getItems(), itemTest1);
    List<String> itemTest2 = new ArrayList<>();
    itemTest2.add("large");
    itemTest2.add("onion");
    Assert.assertNotEquals(response1.getItems(), itemTest2);
  }

  @Test
  public void isSuccessTest() {
    Assert.assertEquals(response1.getSuccess(), true);
    Assert.assertEquals(response2.getSuccess(), false);
  }

  @Test
  public void getCartIDTest() {
    Assert.assertEquals(response1.getCartID(), "cart1");
    Assert.assertNotEquals(response1.getCartID(), "cart2");
    Assert.assertEquals(response3.getCartID(), "cart2");
  }

  @Test
  public void getStoreIDTest() {
    Assert.assertEquals(response1.getStoreID(), "brooklyn");
    Assert.assertNotEquals(response1.getStoreID(), "eastlake");
    Assert.assertEquals(response3.getStoreID(), "eastlake");
  }

  @Test
  public void getMessageTest() {
    Assert.assertEquals(response1.getMessage(), "successful");
    Assert.assertNotEquals(response1.getMessage(), "not successful");
    Assert.assertEquals(response3.getMessage(), "successful");
    Assert.assertEquals(response4.getMessage(), "not successful");
    Assert.assertNotEquals(response4.getMessage(), "nott successful");
  }

}
