package io.swagger.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


public class StoreItemTest {

  @Test
  public void setIdTest() {
    StoreItem storeItem = new StoreItem("sunshine", "555 Sunshine Ave", "Seattle", "California", "70806", true);

    storeItem.setId("gulf");
    assertEquals("gulf", storeItem.getId());
  }

  @Test
  public void getIdTest() {
    StoreItem storeItem = new StoreItem("sunshine", "555 Sunshine Ave", "Seattle", "California", "70806", true);
    assertEquals("sunshine", storeItem.getId());
  }

  @Test
  public void getStreetNumAndNameTest() {
    StoreItem storeItem = new StoreItem("sunshine", "555 Sunshine Ave", "Seattle", "California", "70806", true);

    assertEquals("555 Sunshine Ave", storeItem.getStreetNumAndName());
  }

  @Test
  public void setStreetNumAndNameTest() {
    StoreItem storeItem = new StoreItem("sunshine", "555 Sunshine Ave", "Seattle", "California", "70806", true);
    storeItem.setStreetNumAndName("555 Gulf Blvd");
    assertEquals("555 Gulf Blvd", storeItem.getStreetNumAndName());
  }

  @Test
  public void getCityTest() {
    StoreItem storeItem = new StoreItem("sunshine", "555 Sunshine Ave", "Seattle", "California", "70806", true);
    assertEquals("Seattle", storeItem.getCity());
  }

  @Test
  public void setCityTest() {
    StoreItem storeItem = new StoreItem("sunshine", "555 Sunshine Ave", "Seattle", "California", "70806", true);
    storeItem.setCity("Clam Gulch");
    assertEquals("Clam Gulch", storeItem.getCity());
  }

  @Test
  public void getStateTest() {
    StoreItem storeItem = new StoreItem("sunshine", "555 Sunshine Ave", "Seattle", "California", "70806", true);
    assertEquals("California", storeItem.getState());
  }

  @Test
  public void setStateTest() {
    StoreItem storeItem = new StoreItem("sunshine", "555 Sunshine Ave", "Seattle", "California", "70806", true);

    storeItem.setState("Alaska");
    assertEquals("Alaska", storeItem.getState());
  }

  @Test
  public void getZipCodeTest() {
    StoreItem storeItem = new StoreItem("sunshine", "555 Sunshine Ave", "Seattle", "California", "70806", true);

    assertEquals("70806", storeItem.getZipCode());
  }

  @Test
  public void setZipCodeTest() {
    StoreItem storeItem = new StoreItem("sunshine", "555 Sunshine Ave", "Seattle", "California", "70806", true);
    storeItem.setZipCode("94608");
    assertEquals("94608", storeItem.getZipCode());
  }

  @Test
  public void getOffersGlutenFree() {
    StoreItem storeItem = new StoreItem("sunshine", "555 Sunshine Ave", "Seattle", "California", "70806", true);
    assertEquals(true, storeItem.getOffersGlutenFree()); }

  @Test
  public void setOffersGlutenFree() {
    StoreItem storeItem = new StoreItem("sunshine", "555 Sunshine Ave", "Seattle", "California", "70806", true);
    storeItem.setOffersGlutenFree(false);
    assertEquals(false, storeItem.getOffersGlutenFree()); }

  @Test
  public void equalsTest() {
    StoreItem storeItem = new StoreItem("gulf", "555 Gulf Blvd", "Clam Gulch", "Alaska", "94608", false);
    StoreItem same = new StoreItem("gulf", "555 Gulf Blvd", "Clam Gulch", "Alaska", "94608", false);
    StoreItem different = new StoreItem("louisiana", "555 Louisiana Blvd", "Clam Gulch", "Alaska", "94608", true);

    assertEquals(same, storeItem);
    assertEquals(same, same);
    assertNotEquals(different, storeItem);
    assertNotEquals(same, 2);
    assertNotEquals(same, null);
  }

  @Test
  public void hashCodeTest() {
    StoreItem storeItem = new StoreItem("gulf", "555 Gulf Blvd", "Clam Gulch", "Alaska", "94608", false);
    StoreItem same = new StoreItem("gulf", "555 Gulf Blvd", "Clam Gulch", "Alaska", "94608", false);
    StoreItem different = new StoreItem("louisiana", "555 Louisiana Blvd", "Clam Gulch", "Alaska", "94608", true);

    assertEquals(same.hashCode(), storeItem.hashCode());
    assertNotEquals(different.hashCode(), storeItem.hashCode());
  }

  @Test
  public void toStringTest() {
    StoreItem storeItem = new StoreItem("sunshine", "555 Sunshine Ave", "Seattle", "California", "70806", true);

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