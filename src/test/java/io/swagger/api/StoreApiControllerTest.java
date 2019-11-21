package io.swagger.api;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.when;

import io.swagger.model.StoreItem;
import io.swagger.repository.StoreItemRepository;
import io.swagger.service.StoreService;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class StoreApiControllerTest {
  final String TEST_STORE_ID = "moonglow";
  private final StoreItem TEST_STORE_1 =
      new StoreItem(TEST_STORE_ID, "999 Moonglow Ave", "Emeryville", "California", "70802", true);
  private final StoreItem TEST_STORE_2 =
      new StoreItem("plank", "777 Plank Rd", "Baton Rouge", "Louisiana", "98105", false);

  @Autowired private StoreService storeService;

  @MockBean private StoreItemRepository storeItemRepository;

  @Before
  public void setUp() {
    when(storeItemRepository.findAll())
        .thenReturn(Stream.of(TEST_STORE_1, TEST_STORE_2).collect(Collectors.toList()));

    when(storeItemRepository.findById(TEST_STORE_ID)).thenReturn(Optional.of(TEST_STORE_1));
  }

  @Test
  public void getAllStoresTest() {
    // Test size of list of stores
    assertEquals(2, storeService.getAllStores().size());
  }

  @Test
  public void getStoreByIdTest() {

    final StoreItem actualStore = storeService.getStoreById(TEST_STORE_ID).get();

    assertEquals(TEST_STORE_1, actualStore);
  }

  @Test
  public void getStoreByIncorrectIdTest() {
    final String NONEXISTENT_ID = "666";

    assertNull(storeService.getStoreById(NONEXISTENT_ID));
  }

  @Test
  public void equalityTest() {
    assertEquals(TEST_STORE_1, TEST_STORE_1);
    assertNotEquals(TEST_STORE_1, TEST_STORE_2);
    assertNotEquals(TEST_STORE_1, null);
  }
}
