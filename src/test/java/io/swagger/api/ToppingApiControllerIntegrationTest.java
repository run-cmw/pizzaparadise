package io.swagger.api;

import io.swagger.model.ToppingItem;

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
public class ToppingApiControllerIntegrationTest {

    @Autowired
    private ToppingApi api;

    @Test
    public void addToppingTest() throws Exception {
        ToppingItem body = new ToppingItem();
        ResponseEntity<Void> responseEntity = api.addTopping(body);
        assertEquals(HttpStatus.NOT_IMPLEMENTED, responseEntity.getStatusCode());
    }

    @Test
    public void searchToppingTest() throws Exception {
        String searchString = "searchString_example";
        Integer skip = 56;
        Integer limit = 56;
        ResponseEntity<List<ToppingItem>> responseEntity = api.searchTopping(searchString, skip, limit);
        assertEquals(HttpStatus.NOT_IMPLEMENTED, responseEntity.getStatusCode());
    }

}
