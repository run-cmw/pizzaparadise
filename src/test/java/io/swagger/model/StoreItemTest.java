package io.swagger.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


public class StoreItemTest {
  private StoreItem storeItem;
  private StoreItem same;
  private StoreItem different;

  @Before
  public void setUp() {
    storeItem = new StoreItem("sunshine", "555 Sunshine Ave", "Seattle", "California", "70806", true);
    same = new StoreItem("gulf", "555 Gulf Blvd", "Clam Gulch", "Alaska", "94608", false);
    different = new StoreItem("louisiana", "555 Louisiana Blvd", "Clam Gulch", "Alaska", "94608", true);
    
    // Test setters within setup method
    storeItem.setId("gulf");
    storeItem.setStreetNumAndName("555 Gulf Blvd");
    storeItem.setCity("Clam Gulch");
    storeItem.setState("Alaska");
    storeItem.setZipCode("94608");
    storeItem.setOffersGlutenFree(false);
  }

  @Test
  public void getIdTest() {
    assertEquals("gulf", storeItem.getId());
  }

  @Test
  public void getStreetNumAndNameTest() {
    assertEquals("555 Gulf Blvd", storeItem.getStreetNumAndName());
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
  public void getOffersGlutenFree() {assertEquals(false, storeItem.getOffersGlutenFree()); }

  @Test
  public void equalsTest() {
    assertEquals(same, storeItem);
    assertEquals(same, same);
    assertNotEquals(different, storeItem);
    assertNotEquals(same, 2);
    assertNotEquals(same, null);
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
      + "    offersGlutenFree: " + storeItem.getOffersGlutenFree() + "\n"
      + "}";

    assertEquals(STORE_ITEM_AS_STRING, storeItem.toString());
  }
}