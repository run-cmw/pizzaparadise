package io.swagger.api;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import io.swagger.PizzaParadiseApplication;
import io.swagger.model.StoreItem;
import io.swagger.repository.StoreItemRepository;
import io.swagger.service.StoreService;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class StoreApiTest {
  @Autowired
  private StoreService storeService;

  @MockBean
  private StoreItemRepository storeItemRepository;

  @Before
  public void setUp() throws Exception {
    StoreItem testStore = new StoreItem("5dae8e058980e20b64e28175", "555 Sunshine Blvd", "Clam Gulch", "Alaska", "94608");
    StoreItem testStore2 = new StoreItem("5dae8e058980e20b64e28179", "999 Moonglow Ave", "Emeryville", "California", "70802");
    StoreItem testStore3 = new StoreItem("5dae8e058980e20b64e28177", "777 Plank Rd", "Baton Rouge", "Louisiana", "98105");
  }

  @Test
  public void getAllStoresTest() {
    when(storeItemRepository.findAll()).thenReturn(Stream.of(
        new StoreItem("5dae8e058980e20b64e28175", "555 Sunshine Blvd", "Clam Gulch", "Alaska", "94608"),
        new StoreItem("5dae8e058980e20b64e28179", "999 Moonglow Ave", "Emeryville", "California", "70802"),
        new StoreItem("5dae8e058980e20b64e28177", "777 Plank Rd", "Baton Rouge", "Louisiana", "98105")
    ).collect(Collectors.toList()));

    // Test size of list
    assertEquals(3, storeService.getAllStores().size());
  }

  @Test
  public void getStoreByIdTest() {
    // Test id String values
    assertEquals(storeService.getStoreById("5dae8e058980e20b64e28175").toString(), testStore.toString());
    assertEquals(storeService.getStoreById("5dae8e058980e20b64e28179").toString(), testStore2.toString());
    assertEquals(storeService.getStoreById("5dae8e058980e20b64e28177").toString(), testStore3.toString());

    // Test id attribute
    assertTrue(storeService.getStoreById("5dae8e058980e20b64e28175").equals(testStore));
  }

  @Test
  public void getStoreByIncorrectIdTest() {
    final String NONEXISTENT_ID = "666";
    final String ERROR_MSG = "id does not exist: " + NONEXISTENT_ID;

    // Test nonexistent id
    assertEquals(storeService.getStoreById(NONEXISTENT_ID), ERROR_MSG);
  }

  @Test
  public void equalityTest() {
    // Test incorrect equalities
    assertFalse(testStore.equals(null));
    assertFalse(testStore.equals(testStore2));
  }
}