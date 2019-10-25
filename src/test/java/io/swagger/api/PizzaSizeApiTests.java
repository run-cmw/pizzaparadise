package io.swagger.api;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;
import static org.mockito.Mockito.when;

import io.swagger.model.PizzaSize;
import io.swagger.repository.PizzaSizeRepository;
import io.swagger.service.PizzaSizeService;
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
public class PizzaSizeApiTests {

  @Autowired
  private PizzaSizeService sizeService;

  @MockBean
  private PizzaSizeRepository sizeRepository;


  @Test
  public void getAllSizesTest() {
    when(sizeRepository.findAll()).thenReturn(Stream.of(
        new PizzaSize("5da6d984b7437ea5e054fe39", "Large", "16", 20.99),
        new PizzaSize("5da6d946b7437ea5e054fe38", "Medium", "13", 17.99),
        new PizzaSize("5da6d903b7437ea5e054fe37", "Small", "11", 13.99)
    ).collect(Collectors.toList()));

    Assert.assertEquals(3, sizeService.getAllPizzaSizes().size());
  }

  @Test
  public void getPizzaSizeByIdTest() {
    PizzaSize size1 = new PizzaSize("5da6d903b7437ea5e054fe37", "Small",
        "11", 13.99);

    Assert.assertEquals(
        sizeService.getPizzaSizeById("5da6d903b7437ea5e054fe37").toString(), size1.toString()
    );

    PizzaSize size2 = new PizzaSize("5da6d946b7437ea5e054fe38", "Medium",
        "13", 17.99);

    Assert.assertEquals(
        sizeService.getPizzaSizeById("5da6d946b7437ea5e054fe38").toString(), size2.toString()
    );

    PizzaSize size3 = new PizzaSize("5da6d984b7437ea5e054fe39", "Large",
        "16", 20.99);

    Assert.assertEquals(
        sizeService.getPizzaSizeById("5da6d984b7437ea5e054fe39"), size3);

    Assert.assertTrue(sizeService.getPizzaSizeById("5da6d984b7437ea5e054fe39").equals(size3));

    Assert.assertFalse(size3.equals(null));

    Assert.assertEquals(
        sizeService.getPizzaSizeById("5d"), null);

  }

  @Test
  public void httpStatusTest() {
    int statusCode = given().get("https://pizza-paradise.herokuapp.com/size").statusCode();
    Assert.assertEquals(statusCode, 200);

    int statusCode1 = given().get("https://pizza-paradise.herokuapp.com/sizes").statusCode();
    Assert.assertEquals(statusCode1, 404);

    int statusCode2 = given().get("https://pizza-paradise.herokuapp.com/size/5da6d903b7437ea5e054fe37").statusCode();
    Assert.assertEquals(statusCode2, 302);

    int statusCode3 = given().get("https://pizza-paradise.herokuapp.com/size/5da4fe37").statusCode();
    Assert.assertEquals(statusCode3, 404);



  }

  @Test
  public void verifySizeName() {
    given().when().get("https://pizza-paradise.herokuapp.com/size").then()
        .body(containsString("Medium"));
  }




}
