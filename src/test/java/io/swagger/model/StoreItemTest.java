package io.swagger.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Before;
import org.junit.Test;

public class StoreItemTest {

  private StoreItem storeItem;
  private StoreItem sameObject;
  private StoreItem differentObject;

  @Before
  public void setUp() {
    storeItem = new StoreItem("gulf", "555 Gulf Blvd", "Clam Gulch", "Alaska", "94608", false);
    sameObject = new StoreItem("gulf", "555 Gulf Blvd", "Clam Gulch", "Alaska", "94608", false);
    differentObject =
        new StoreItem("louisiana", "555 Louisiana Blvd", "Clam Gulch", "Alaska", "94608", true);
  }

  @Test
  public void testGetId() {
    assertEquals("gulf", storeItem.getId());
  }

  @Test
  public void testSetId() {
    storeItem.setId("sunshine");
    assertEquals("sunshine", storeItem.getId());
  }

  @Test
  public void testGetStreetNumAndName() {
    assertEquals("555 Gulf Blvd", storeItem.getStreetNumAndName());
  }

  @Test
  public void testSetStreetNumAndName() {
    storeItem.setStreetNumAndName("555 Sunshine Ave");
    assertEquals("555 Sunshine Ave", storeItem.getStreetNumAndName());
  }

  @Test
  public void testGetCity() {
    assertEquals("Clam Gulch", storeItem.getCity());
  }

  @Test
  public void testSetCity() {
    storeItem.setCity("Seattle");
    assertEquals("Seattle", storeItem.getCity());
  }

  @Test
  public void testGetState() {
    assertEquals("Alaska", storeItem.getState());
  }

  @Test
  public void testSetState() {
    storeItem.setState("California");
    assertEquals("California", storeItem.getState());
  }

  @Test
  public void testGetZipCode() {
    assertEquals("94608", storeItem.getZipCode());
  }

  @Test
  public void testSetZipCode() {
    storeItem.setZipCode("70806");
    assertEquals("70806", storeItem.getZipCode());
  }

  @Test
  public void testGetOffersGlutenFree() {
    assertEquals(false, storeItem.getOffersGlutenFree());
  }

  @Test
  public void testSetOffersGlutenFree() {
    storeItem.setOffersGlutenFree(true);
    assertEquals(true, storeItem.getOffersGlutenFree());
  }

  @Test
  public void equalsTest() {
    assertEquals(sameObject, storeItem);
    assertNotEquals(differentObject, storeItem);
    assertNotEquals(2, storeItem);
    assertNotEquals(null, storeItem);
  }

  @Test
  public void hashCodeTest() {
    assertEquals(sameObject.hashCode(), storeItem.hashCode());
    assertNotEquals(differentObject.hashCode(), storeItem.hashCode());
  }

  @Test
  public void toStringTest() {
    StoreItem storeItem =
        new StoreItem("sunshine", "555 Sunshine Ave", "Seattle", "California", "70806", true);

    final String STORE_ITEM_AS_STRING =
        "StoreItem{"
            + "id='"
            + storeItem.getId()
            + '\''
            + ", streetNumAndName='"
            + storeItem.getStreetNumAndName()
            + '\''
            + ", city='"
            + storeItem.getCity()
            + '\''
            + ", state='"
            + storeItem.getState()
            + '\''
            + ", zipCode='"
            + storeItem.getZipCode()
            + '\''
            + ", offersGlutenFree='"
            + storeItem.getOffersGlutenFree()
            + '\''
            + '}';

    assertEquals(STORE_ITEM_AS_STRING, storeItem.toString());
  }
}
