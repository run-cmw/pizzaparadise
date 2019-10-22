package io.swagger.api;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
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
  public void setUp() throws Exception {
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
    final String TEST_STORE_ID = "5dae8e058980e20b64e28179";

    assertTrue((storeService.getStoreById(TEST_STORE_ID)).equals(testStore));
    assertEquals(storeService.getStoreById(TEST_STORE_ID).toString(), testStore.toString());
  }

  @Test
  public void getStoreByIncorrectIdTest() {
    final String NONEXISTENT_ID = "666";

    assertEquals(storeService.getStoreById(NONEXISTENT_ID), null);
  }

  @Test
  public void equalityTest() {
    assertTrue(testStore.equals(testStore));
    assertFalse(testStore.equals(testStore2));
    assertFalse(testStore.equals(null));
  }
}