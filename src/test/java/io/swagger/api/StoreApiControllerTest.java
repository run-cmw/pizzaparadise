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
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import io.swagger.model.StoreItem;
import io.swagger.repository.StoreItemRepository;
import io.swagger.service.StoreService;

@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@TestPropertySource(locations = "classpath:/application-test.properties")
public class StoreApiControllerTest {
  @Autowired
  private StoreService storeService;

  @MockBean
  private StoreItemRepository storeItemRepository;
  private StoreItem testStore;
  private StoreItem testStore2;

  @Before
  public void setUp() {
    testStore = new StoreItem("moonglow", "999 Moonglow Ave", "Emeryville", "California", "70802", true);
    testStore2 = new StoreItem("plank", "777 Plank Rd", "Baton Rouge", "Louisiana", "98105", false);

    when(storeItemRepository.findAll()).thenReturn(Stream.of(
        testStore,
        testStore2
    ).collect(Collectors.toList()));
  }

  @Test
  public void getAllStoresTest() {
    // Test size of list of stores
    assertEquals(2, storeService.getAllStores().size());
  }

  @Test
  public void getStoreByIdTest() {
    final String TEST_STORE_ID = "moonglow";
    final String TEST_STORE2_ID = "plank";

    assertEquals((storeService.getStoreById(TEST_STORE_ID)), testStore);
    assertEquals((storeService.getStoreById(TEST_STORE2_ID)), testStore2);
    assertEquals((storeService.getStoreById(TEST_STORE_ID).toString()), testStore.toString());
    assertEquals((storeService.getStoreById(TEST_STORE2_ID).toString()), testStore2.toString());
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