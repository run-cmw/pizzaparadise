package io.swagger.model;

import io.swagger.model.StoreItem;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class StoreItemTest {
  StoreItem storeItem;

  @Before
  public void setUp() throws Exception {
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
  public void getStreetNumAndNameest() {
    assertEquals("555 Sunshine Blvd", storeItem.getZipCode());
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
}