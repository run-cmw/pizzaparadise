package io.swagger.api;

import static com.jayway.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.when;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import io.swagger.model.StoreItem;
import io.swagger.repository.StoreItemRepository;
import io.swagger.service.StoreService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class StoreApiTest {
  @Autowired
  private StoreService storeService;

  @MockBean
  private StoreItemRepository storeItemRepository;
  private StoreItem testStore;
  private StoreItem testStore2;

  @Before
  public void setUp() {
    testStore = new StoreItem("5dae8e058980e20b64e28179", "999 Moonglow Ave", "Emeryville", "California", "70802");
    testStore2 = new StoreItem("5dae8e058980e20b64e28177", "777 Plank Rd", "Baton Rouge", "Louisiana", "98105");
  }

  @Test
  public void getAllStoresTest() {
    when(storeItemRepository.findAll()).thenReturn(Stream.of(
        new StoreItem("5dae8e058980e20b64e28179", "999 Moonglow Ave", "Emeryville", "California", "70802"),
        new StoreItem("5dae8e058980e20b64e28177", "777 Plank Rd", "Baton Rouge", "Louisiana", "98105")
    ).collect(Collectors.toList()));

    // Test size of list of stores
    assertEquals(2, storeService.getAllStores().size());
  }

  @Test
  public void getStoreByIdTest() {
    when(storeItemRepository.findAll()).thenReturn(Stream.of(
        new StoreItem("5dae8e058980e20b64e28179", "999 Moonglow Ave", "Emeryville", "California", "70802"),
        new StoreItem("5dae8e058980e20b64e28177", "777 Plank Rd", "Baton Rouge", "Louisiana", "98105")
    ).collect(Collectors.toList()));

    final String TEST_STORE_ID = "5dae8e058980e20b64e28179";

    assertEquals((storeService.getStoreById(TEST_STORE_ID)), testStore);
    assertEquals((storeService.getStoreById(TEST_STORE_ID).toString()), testStore.toString());
  }

  @Test
  public void getStoreByIncorrectIdTest() {
    final String NONEXISTENT_ID = "666";

    assertNull(storeService.getStoreById(NONEXISTENT_ID));
  }

  @Test
  public void equalityTest() {
    assertEquals(testStore, testStore);
    assertNotEquals(testStore, testStore2);
    assertNotEquals(testStore, null);
  }

  @Test
  public void statusCodeTest() {
    int statusCodeOk = given().get("https://fierce-hamlet-19207.herokuapp.com/store").statusCode();
    int statusCodeNotFound = given().get("https://fierce-hamlet-19207.herokuapp.com/stores").statusCode();

    assertEquals(statusCodeOk, 200);
    assertEquals(statusCodeNotFound, 404);
  }
}