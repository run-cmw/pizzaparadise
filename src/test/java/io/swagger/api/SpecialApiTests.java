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
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class SpecialApiTests {

  @Autowired
  private SpecialItemService specialService;

  @MockBean
  private SpecialItemRepository specialRepository;

  @Before
  public void Setup() {
    when(specialRepository.findAll()).thenReturn(Stream.of(
        new SpecialItem("buy1get1free", "Buy1Get1Free", "Only one special at a time. If you buy 1 pizza, you get 1 free pizza that is equal or less value."),
        new SpecialItem("buy1PizzaGetSodaFree", "Buy1PizzaGetSodaFree", "If you purchase 1 pizza then you get 1 soda for free"),
        new SpecialItem("buy2LargePizzaNoTopping", "Buy2LargePizzaNoTopping30%OFF", "if you buy 2 large pizza that has no toppings, then get 30% off")
    ).collect(Collectors.toList()));
  }


  @Test
  public void getAllSpecialTest() {
    Assert.assertEquals(3, specialService.getAllSpecials().size());
    Assert.assertNotEquals(specialService.getAllSpecials(), null);
  }

  @Test
  public void getSpecialByIdTest() {
    SpecialItem special1 = new SpecialItem("buy1get1free", "Buy1Get1Free", "Only one special at a time. If you buy 1 pizza, you get 1 free pizza that is equal or less value.");

    Assert.assertEquals(
        specialService.getSpecialById("buy1get1free").toString(), special1.toString());

    Assert.assertTrue(specialService.getSpecialById("buy1get1free").equals(special1));

    SpecialItem special2 = new SpecialItem("buy1PizzaGetSodaFree", "Buy1PizzaGetSodaFree", "If you purchase 1 pizza then you get 1 soda for free");


    Assert.assertEquals(
        specialService.getSpecialById("buy1PizzaGetSodaFree").toString(), special2.toString()
    );


    SpecialItem special3 = new SpecialItem("buy2LargePizzaNoTopping", "Buy2LargePizzaNoTopping30%OFF", "if you buy 2 large pizza that has no toppings, then get 30% off");

    Assert.assertEquals(
        specialService.getSpecialById("buy2LargePizzaNoTopping"), special3);


    Assert.assertTrue(specialService.getSpecialById("buy2LargePizzaNoTopping").equals(special3));

    Assert.assertFalse(special3.equals(null));

    Assert.assertEquals(
        specialService.getSpecialById("5d"), null);

  }

  @Test
  public void httpStatusCodeTest() {
    int statusCode = given().get("https://pizza-paradise.herokuapp.com/special").statusCode();
    assertEquals(statusCode, 200);

    int statusCode1 = given().get("https://pizza-paradise.herokuapp.com/specials").statusCode();
    assertEquals(statusCode1, 404);

    int statusCode2 = given().get("https://pizza-paradise.herokuapp.com/special/buy2LargePizzaNoTopping").statusCode();
    Assert.assertEquals(statusCode2, 302);

    int statusCode3 = given().get("https://pizza-paradise.herokuapp.com/special/456787654").statusCode();
    assertEquals(statusCode3, 404);

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
