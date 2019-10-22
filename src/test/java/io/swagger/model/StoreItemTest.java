package io.swagger.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class StoreItemTest {
  StoreItem storeItem;
  StoreItem same;
  StoreItem different;

  @Before
  public void setUp() throws Exception {
    storeItem = new StoreItem("5dae8e058980e20b64e28175", "555 Sunshine Ave", "Seattle", "California", "70806");
    same = new StoreItem("5dae8e058980e20b64e28179", "555 Sunshine Blvd", "Clam Gulch", "Alaska", "94608");
    different = new StoreItem("5dae8e058980e20b64e28199", "555 Sunshine Blvd", "Clam Gulch", "Alaska", "94608");
    
    // Test setters within setup method
    storeItem.setId("5dae8e058980e20b64e28179");
    storeItem.setStreetNumAndName("555 Sunshine Blvd");
    storeItem.setCity("Clam Gulch");
    storeItem.setState("Alaska");
    storeItem.setZipCode("94608");
  }

  @Test
  public void getIdTest() {
    assertEquals("5dae8e058980e20b64e28179", storeItem.getId());
  }

  @Test
  public void getStreetNumAndNameTest() {
    assertEquals("555 Sunshine Blvd", storeItem.getStreetNumAndName());
  }

  @Test
  public void getCityTest() {
    assertEquals("Clam Gulch", storeItem.getCity());
  }

  @Test
  public void getStateTest() {
    assertEquals("Alaska", storeItem.getState());
  }

  @Test
  public void getZipCodeTest() {
    assertEquals("94608", storeItem.getZipCode());
  }

  @Test
  public void equalsTest() {
    assertTrue(same.equals(storeItem));
    assertTrue(same.equals(same));
    assertFalse(different.equals(storeItem));
    assertFalse(same.equals(2));
    assertFalse(same.equals(null));
  }

  @Test
  public void hashCodeTest() {
    assertEquals(same.hashCode(), storeItem.hashCode());
    assertNotEquals(different.hashCode(), storeItem.hashCode());
  }

  @Test
  public void toStringTest() {
    final String STORE_ITEM_AS_STRING =
      "class StoreItem {\n"
      + "    id: " + storeItem.getId() + "\n"
      + "    streetNumAndName: " + storeItem.getStreetNumAndName() + "\n"
      + "    city: " + storeItem.getCity() + "\n" 
      + "    state: " + storeItem.getState() + "\n"
      + "    zipCode: " + storeItem.getZipCode() + "\n"
      + "}";

    assertEquals(STORE_ITEM_AS_STRING, storeItem.toString());
  }
}