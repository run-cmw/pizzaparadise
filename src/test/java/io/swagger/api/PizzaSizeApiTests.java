package io.swagger.api;

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
        new PizzaSize("5da6d984b7437ea5e054fe39", "Large", "20", 20.99),
        new PizzaSize("5da6d946b7437ea5e054fe38", "Medium", "17", 17.99),
        new PizzaSize("5da6d903b7437ea5e054fe37", "Small", "13", 13.99)
    ).collect(Collectors.toList()));
    Assert.assertEquals(3, sizeService.getAllPizzaSizes().size());

    PizzaSize size1 = new PizzaSize("5da6d903b7437ea5e054fe37", "Small",
        "13", 13.99);

    Assert.assertEquals(
        sizeService.getPizzaSizeById("5da6d903b7437ea5e054fe37").toString(), size1.toString()
    );

    PizzaSize size2 = new PizzaSize("5da6d946b7437ea5e054fe38", "Medium",
        "17", 17.99);

    Assert.assertEquals(
        sizeService.getPizzaSizeById("5da6d946b7437ea5e054fe38").toString(), size2.toString()
    );

    PizzaSize size3 = new PizzaSize("5da6d984b7437ea5e054fe39", "Large",
        "20", 20.99);

    Assert.assertEquals(
        sizeService.getPizzaSizeById("5da6d984b7437ea5e054fe39").toString(), size3.toString()
    );

    Assert.assertTrue(sizeService.getPizzaSizeById("5da6d984b7437ea5e054fe39").equals(size3));

    Assert.assertFalse(size3.equals(null));

  }




}
