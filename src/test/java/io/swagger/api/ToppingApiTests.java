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
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@TestPropertySource(locations = "classpath:/application-test.properties")
public class ToppingApiTests {

  @Autowired
  private ToppingItemService toppingService;

  @MockBean
  private ToppingItemRepository toppingRepository;


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

    Assert.assertEquals(6, toppingService.getAllTopping().size());

    ToppingItem topping1 = new ToppingItem("pepperoni1", "pepperoni", "meat", 2.5,
        2.75, 3.0, "gluten");

    Assert.assertEquals(toppingService.getToppingById("pepperoni1").toString(),
        topping1.toString());

    ToppingItem topping2 = new ToppingItem("onion1", "onion", "vegetable", 2.0,
        2.25, 2.5, "non-gluten");

    Assert.assertEquals(toppingService.getToppingById("onion1").toString(),
        topping2.toString());

    ToppingItem topping3 = new ToppingItem("mushroom1", "mushroom", "vegetable", 2.0,
        2.25, 2.5, "non-gluten");

    Assert.assertEquals(toppingService.getToppingById("mushroom1").toString(),
        topping3.toString());

    Assert.assertFalse(topping3.equals(null));

    Assert.assertEquals(toppingService.getToppingById("errorId"), null);

  }

  @Test
  public void statusCodeTest() {
    int statusCodeOk = given().get("https://hidden-beyond-92673.herokuapp.com/topping")
        .statusCode();
    Assert.assertEquals(statusCodeOk, 200);

    int statusCodeNotFound = given().get("https://hidden-beyond-92673.herokuapp.com/toppings")
        .statusCode();
    Assert.assertEquals(statusCodeNotFound, 404);


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