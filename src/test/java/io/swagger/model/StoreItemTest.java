package io.swagger.model;

import static org.junit.Assert.*;

import io.swagger.model.StoreItem;
import java.util.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringJUnit4ClassRunner.class)
//@RunWith(SpringRunner.class)
@SpringBootTest
public class StoreItemTest {
  StoreItem storeItem;
//  String id;
//  String streetNameAndNum;
//  String city;
//  String state;
//  String zipCode;

  @Before
  public void setUp() throws Exception {
    storeItem.id = "5dae8e058980e20b64e28179";
    storeItem.streetNumAndName = "555 Sunshine Blvd";
    storeItem.city = "Clam Gulch";
    storeItem.state = "Alaska";
    storeItem.zipCode = "94608";
  }

  @Test
  public void getIdTest() {
    storeItem.setId("7dae8e058980e20b64e28179");
    assertEquals("7dae8e058980e20b64e28179", storeItem.getId());
    assertFalse("5dae8e058980e20b64e28179", storeItem.getId());
  }

  @Test
  public void getStreetNumAndNameest() {
    storeItem.setStreetNumAndName("777 Sunshine Rd");
    assertEquals("777 Sunshine Rd", storeItem.getZipCode());
    assertFalse("555 Sunshine Blvd", storeItem.getZipCode());
  }

  @Test
  public void getCityTest() {
    storeItem.setCity("Berkeley");
    assertTrue("Berkeley", storeItem.getCity());
    assertFalse("Clam Gulch", storeItem.getCity());
  }

  @Test
  public void getStateTest() {
    storeItem.setState("Louisiana");
    assertTrue("Louisiana", storeItem.getState());
    assertFalse("Alaska", storeItem.getState());
  }

  @Test
  public void getZipCodeTest() {
    storeItem.zipCode = "98105";
    assertTrue("98105", storeItem.getZipCode());
    assertFalse("94608", storeItem.getZipCode());
  }
}