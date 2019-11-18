package io.swagger.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

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
    CartAddResponse response1 = new CartAddResponse(item1, "cart1", "brooklyn");
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
    CartAddResponse response1 = new CartAddResponse(item1, "cart1","brooklyn");
    CartAddResponse response2 = new CartAddResponse("failed");
    Assert.assertEquals(response1.getSuccess(), true);
    Assert.assertEquals(response2.getSuccess(), false);
  }

  @Test
  public void getCartIDTest() {
    List<String> item1 = new ArrayList<>();
    item1.add("medium");
    item1.add("onion");
    CartAddResponse response1 = new CartAddResponse(item1, "cart1","brooklyn");
    CartAddResponse response3 = new CartAddResponse(item1, "cart2","eastlake");

    Assert.assertEquals(response1.getCartID(), "cart1");
    Assert.assertNotEquals(response1.getCartID(), "cart2");
    Assert.assertEquals(response3.getCartID(), "cart2");
  }

  @Test
  public void getStoreIDTest() {
    List<String> item1 = new ArrayList<>();
    item1.add("medium");
    item1.add("onion");
    CartAddResponse response1 = new CartAddResponse(item1, "cart1","brooklyn");
    CartAddResponse response3 = new CartAddResponse(item1, "cart2","eastlake");

    Assert.assertEquals("brooklyn", response1.getStoreID());
    Assert.assertEquals(response3.getStoreID(), "eastlake");
  }

  @Test
  public void getMessageTest() {
    List<String> item1 = new ArrayList<>();
    item1.add("small");
    item1.add("gluten");
    CartAddResponse response1 = new CartAddResponse("This response failed");
    CartAddResponse response3 = new CartAddResponse(item1, "cart2", "eastlake");

    assertEquals(response1.getMessage(), "This response failed");

    // Successful responses have a null error message
    assertNull(response3.getMessage());
  }

  @Test
  public void toStringTest() {
    List<String> item1 = new ArrayList<>();
    item1.add("small");
    item1.add("gluten");
    CartAddResponse response1 = new CartAddResponse(item1, "cart1","brooklyn");
    Assert.assertEquals("CartAddResponse{success=true, cartId=cart1, storeID=brooklyn, items=[small, gluten], message=null}", response1.toString());
  }

  @Test
  public void equalTest() {
    List<String> item1 = new ArrayList<>();
    item1.add("small");
    item1.add("gluten");
    item1.add("pepperoni");
    CartAddResponse response1 = new CartAddResponse(item1, "cart1", "brooklyn");
    CartAddResponse sameAsResponse1 = new CartAddResponse(item1, "cart1", "brooklyn");

    CartAddResponse response2 = new CartAddResponse(item1, "cart1", "eastside");

    assertEquals(response1, response1);
    assertEquals(response1, sameAsResponse1);
    assertNotEquals(response1, response2);
    assertNotNull(response1);
  }

}
