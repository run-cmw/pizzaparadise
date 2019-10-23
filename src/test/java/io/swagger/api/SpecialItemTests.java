package io.swagger.api;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;
import static org.mockito.Mockito.when;


import io.swagger.model.SpecialItem;
import io.swagger.repository.SpecialItemRepository;
import io.swagger.service.SpecialItemService;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class SpecialItemTests {

  @Autowired
  private SpecialItemService specialService;

  @MockBean
  private SpecialItemRepository specialRepository;


  @Test
  public void getAllSpecialTest() {
    when(specialRepository.findAll()).thenReturn(Stream.of(
        new SpecialItem("5da6b2e86d9aec817c99d5d9", "Buy1Get1Free", "Only one special at a time. If you buy 1 pizza, you get 1 free pizza that is equal or less value."),
        new SpecialItem("2", "Buy1PizzaGetSodaFree", "If you purchase 1 pizza then you get 1 soda for free"),
        new SpecialItem("3", "Buy2LargePizzaNoTopping30%OFF", "if you buy 2 large pizza that has no toppings, then get 30% off")
    ).collect(Collectors.toList()));

    Assert.assertEquals(3, specialService.getAllSpecials().size());

    SpecialItem special1 = new SpecialItem("5da6b2e86d9aec817c99d5d9", "Buy1Get1Free", "Only one special at a time. If you buy 1 pizza, you get 1 free pizza that is equal or less value.");

    Assert.assertEquals(
        specialService.getSpecialById("5da6b2e86d9aec817c99d5d9").toString(), special1.toString());

    Assert.assertTrue(specialService.getSpecialById("5da6b2e86d9aec817c99d5d9").equals(special1));

    SpecialItem special2 = new SpecialItem("2", "Buy1PizzaGetSodaFree", "If you purchase 1 pizza then you get 1 soda for free");


    Assert.assertEquals(
        specialService.getSpecialById("2").toString(), special2.toString()
    );


    SpecialItem special3 = new SpecialItem("3", "Buy2LargePizzaNoTopping30%OFF", "if you buy 2 large pizza that has no toppings, then get 30% off");

    Assert.assertEquals(
        specialService.getSpecialById("3"), special3);


    Assert.assertTrue(specialService.getSpecialById("3").equals(special3));

    Assert.assertFalse(special3.equals(null));

    Assert.assertEquals(
        specialService.getSpecialById("5d"), null);

  }

  @Test
  public void basicPingTest() {
    int statusCode = given().get("https://pizza-paradise.herokuapp.com/special").statusCode();
    Assert.assertEquals(statusCode, 200);

    int statusCode1 = given().get("https://pizza-paradise.herokuapp.com/specials").statusCode();
    Assert.assertEquals(statusCode1, 404);


  }

  @Test
  public void verifyNameTest() {
    given().when().get("https://pizza-paradise.herokuapp.com/special").then()
        .body(containsString("Buy1PizzaGetSodaFree"));
  }

  @Test
  public void verifyDescriptionTest() {
    given().when().get("https://pizza-paradise.herokuapp.com/special").then()
        .body(containsString("If you purchase 1 pizza then you get 1 soda for free."));
  }

}
