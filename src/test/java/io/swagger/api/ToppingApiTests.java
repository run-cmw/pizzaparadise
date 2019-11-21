package io.swagger.api;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;
import static org.mockito.Mockito.when;


import io.swagger.model.ToppingItem;
import io.swagger.repository.ToppingItemRepository;
import io.swagger.service.ToppingItemService;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.Assert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ToppingApiTests {


  @MockBean
  private ToppingItemRepository toppingRepository;

  @Autowired
  private ToppingItemService toppingItemService;


  @Test
  public void getAllToppingTest() {
    when(toppingRepository.findAll()).thenReturn(Stream.of(
        new ToppingItem("pepperoni1", "pepperoni", "meat", 2.5, 2.75, 3.0, "gluten"),
        new ToppingItem("sausage1", "sausage", "meat", 2.5, 2.75, 3.0, "gluten"),
        new ToppingItem("onion1", "onion", "vegetable", 2.0, 2.25, 2.5,
            "non-gluten"),
        new ToppingItem("greenPeppers1", "green peppers", "vegetable", 2.0, 2.25, 2.5,
            "non-gluten"),
        new ToppingItem("mushroom1", "mushroom", "vegetable", 2.0, 2.25, 2.5,
            "non-gluten"),
        new ToppingItem("blackOlives1", "black olives", "vegetable", 2.0, 2.25, 2.5,
            "non-gluten")
    ).collect(Collectors.toList()));

    Assert.assertEquals(6, toppingItemService.getAllTopping().size());

    ToppingItem topping1 = new ToppingItem("pepperoni1", "pepperoni", "meat", 2.5,
        2.75, 3.0, "gluten");

    Assert.assertEquals(toppingItemService.getToppingById("pepperoni1").toString(),
        topping1.toString());

    ToppingItem topping2 = new ToppingItem("onion1", "onion", "vegetable", 2.0,
        2.25, 2.5, "non-gluten");

    Assert.assertEquals(toppingItemService.getToppingById("onion1").toString(),
        topping2.toString());

    ToppingItem topping3 = new ToppingItem("mushroom1", "mushroom", "vegetable", 2.0,
        2.25, 2.5, "non-gluten");

    Assert.assertEquals(toppingItemService.getToppingById("mushroom1").toString(),
        topping3.toString());

    Assert.assertFalse(topping3.equals(null));

    Assert.assertEquals(toppingItemService.getToppingById("errorId"), null);

  }


  @Test
  public void statusCodeTest() {

    int statusCodeOk = given().get("https://pizza-paradise.herokuapp.com/topping")
        .statusCode();
    assertEquals(200,statusCodeOk);

    int statusCodeNotFound = given().get("https://hidden-beyond-92673.herokuapp.com/toppings")
        .statusCode();
    assertEquals(404,statusCodeNotFound);


  }

  @Test
  public void verifyNameTest() {
    given().when().get("https://hidden-beyond-92673.herokuapp.com/topping").then()
        .body(containsString("pepperoni"));
  }

  @Test
  public void verifyTypeTest() {
    given().when().get("https://hidden-beyond-92673.herokuapp.com/topping").then()
        .body(containsString("meat"));
  }

  @Test
  public void verifyGlutenTest() {
    given().when().get("https://hidden-beyond-92673.herokuapp.com/topping").then()
        .body(containsString("non-gluten"));
  }

}