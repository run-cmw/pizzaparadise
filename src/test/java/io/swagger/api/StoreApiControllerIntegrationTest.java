package io.swagger.api;

import io.swagger.model.StoreItem;

import java.util.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StoreApiControllerIntegrationTest {

  @Autowired
  private StoreApi api;

  @Test
  public void addStoreTest() throws Exception {
    StoreItem body = new StoreItem();
    ResponseEntity<Void> responseEntity = api.addStore(body);
    assertEquals(HttpStatus.NOT_IMPLEMENTED, responseEntity.getStatusCode());
  }

  @Test
  public void searchStoreTest() throws Exception {
    String searchString = "searchString_example";
    Integer skip = 56;
    Integer limit = 56;
    ResponseEntity<List<StoreItem>> responseEntity = api.searchStore(searchString, skip, limit);
    assertEquals(HttpStatus.NOT_IMPLEMENTED, responseEntity.getStatusCode());
  }

}
