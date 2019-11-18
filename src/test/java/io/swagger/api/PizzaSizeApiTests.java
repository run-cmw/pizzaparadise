package io.swagger.api;

import static com.jayway.restassured.RestAssured.given;
import static org.assertj.core.internal.bytebuddy.matcher.ElementMatchers.is;
import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import io.swagger.model.PizzaSize;
import io.swagger.repository.PizzaSizeRepository;
import io.swagger.service.PizzaSizeService;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.JsonPathResultMatchers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest(PizzaSizeApiController.class)
@TestPropertySource(locations = "classpath:/application-test.properties")
public class PizzaSizeApiTests {

  @Autowired
  private MockMvc mvc;

  @MockBean
  private PizzaSizeApiController sizeController;
  @MockBean
  private PizzaSizeService sizeService;


  private PizzaSizeRepository sizeRepository;


  @Test
  public void getAllSizesTest() throws Exception {

    PizzaSize size1 = new PizzaSize("large", "Large", "16", 20.99);
    mvc.perform( MockMvcRequestBuilders
        .get("/size")
        .contentType(MediaType.APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$[0]").value(size1.toString()));

  }

  @Test
  public void getPizzaSizeByIdTest() {
    PizzaSize size1 = new PizzaSize("small", "Small",
        "11", 13.99);

    Assert.assertEquals(
        sizeService.getPizzaSizeById("small").toString(), size1.toString());

    PizzaSize size2 = new PizzaSize("medium", "Medium",
        "13", 17.99);

    Assert.assertEquals(
        sizeService.getPizzaSizeById("medium").toString(), size2.toString()
    );

    PizzaSize size3 = new PizzaSize("large", "Large",
        "16", 20.99);

    Assert.assertEquals(
        sizeService.getPizzaSizeById("large"), size3);

    Assert.assertTrue(sizeService.getPizzaSizeById("large").equals(size3));

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

    int statusCode2 = given().get("https://pizza-paradise.herokuapp.com/size/small").statusCode();
    Assert.assertEquals(statusCode2, 302);

    int statusCode3 = given().get("https://pizza-paradise.herokuapp.com/size/5da4fe37").statusCode();
    Assert.assertEquals(statusCode3, 404);
  }

  @Test
  public void verifySizeName() {
    given().when().get("https://pizza-paradise.herokuapp.com/size").then()
        .body(containsString("Medium"));

     given().when().get("https://pizza-paradise.herokuapp.com/size/small").then()
        .body(containsString("Small"));
  }




}
