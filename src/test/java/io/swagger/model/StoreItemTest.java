package io.swagger.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import io.swagger.DBStoreItems;
import org.junit.Test;

public class StoreItemTest {
  private StoreItem storeItem = DBStoreItems.BROOKLYN_STORE;
  private StoreItem sameObject = DBStoreItems.BROOKLYN_STORE;
  private StoreItem differentObject = DBStoreItems.EASTLAKE_STORE;

  @Test
  public void testGetId() {
    assertEquals("brooklyn", storeItem.getId());
  }

  @Test
  public void testSetId() {
    storeItem.setId("sunshine");
    assertEquals("sunshine", storeItem.getId());
  }

  @Test
  public void testGetStreetNumAndName() {
    assertEquals("555 Sunshine Ave", storeItem.getStreetNumAndName());
  }

  @Test
  public void testSetStreetNumAndName() {
    storeItem.setStreetNumAndName("555 Sunshine Ave");
    assertEquals("555 Sunshine Ave", storeItem.getStreetNumAndName());
  }

  @Test
  public void testGetCity() {
    assertEquals("Seattle", storeItem.getCity());
  }

  @Test
  public void testSetCity() {
    storeItem.setCity("Clam Gulch");
    assertEquals("Clam Gulch", storeItem.getCity());
  }

  @Test
  public void testGetState() {
    assertEquals("Washington", storeItem.getState());
  }

  @Test
  public void testSetState() {
    storeItem.setState("California");
    assertEquals("California", storeItem.getState());
  }

  @Test
  public void testGetZipCode() {
    assertEquals("98105", storeItem.getZipCode());
  }

  @Test
  public void testSetZipCode() {
    storeItem.setZipCode("70806");
    assertEquals("70806", storeItem.getZipCode());
  }

  @Test
  public void testGetOffersGlutenFree() {
    assertFalse(storeItem.getOffersGlutenFree());
  }

  @Test
  public void testSetOffersGlutenFree() {
    storeItem.setOffersGlutenFree(true);
    assertTrue(storeItem.getOffersGlutenFree());
  }

  @Test
  public void testEquals() {
    assertEquals(sameObject, storeItem);
    assertNotEquals(differentObject, storeItem);
    assertNotEquals(2, storeItem);
    assertNotEquals(null, storeItem);
  }

  @Test
  public void testHashCode() {
    assertEquals(sameObject.hashCode(), storeItem.hashCode());
    assertNotEquals(differentObject.hashCode(), storeItem.hashCode());
  }

  @Test
  public void testToString() {

    final String STORE_ITEM_AS_STRING =
        "StoreItem{"
            + "id='"
            + "sunshine"
            + '\''
            + ", streetNumAndName='"
            + "555 Sunshine Ave"
            + '\''
            + ", city='"
            + "Seattle"
            + '\''
            + ", state='"
            + "Washington"
            + '\''
            + ", zipCode='"
            + "98105"
            + '\''
            + ", offersGlutenFree='"
            + false
            + '\''
            + '}';

    assertEquals(STORE_ITEM_AS_STRING, storeItem.toString());
  }
}
