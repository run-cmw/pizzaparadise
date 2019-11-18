package io.swagger.service;

import static org.junit.Assert.assertEquals;
import io.swagger.model.PizzaSize;
import io.swagger.repository.PizzaSizeRepository;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@TestPropertySource(locations = "classpath:/application-test.properties")
public class PizzaSizeServiceTest {


  @Autowired
  public PizzaSizeService sizeService;

  @Autowired
  public PizzaSizeRepository pizzaSizeRepository;


  @Test
  public void getAllSizeTest() {
    pizzaSizeRepository.deleteAll();
    PizzaSize pizzaSize1 = new PizzaSize("newSize1", "newSize1 Name", "6",9.99);
    PizzaSize pizzaSize2 = new PizzaSize("newSize2", "newSize2 Name", "9",12.99);
    PizzaSize pizzaSize3 = new PizzaSize("newSize3", "newSize3 Name", "12",14.99);

    assertEquals(0, pizzaSizeRepository.count());
    pizzaSizeRepository.insert(pizzaSize1);
    pizzaSizeRepository.insert(pizzaSize2);
    pizzaSizeRepository.insert(pizzaSize3);

    List<PizzaSize> list = sizeService.getAllPizzaSizes();
    Assert.assertEquals(3, list.size());
    Assert.assertEquals(true, list.contains(pizzaSize1));
    Assert.assertEquals(true, list.contains(pizzaSize2));
    Assert.assertEquals(true, list.contains(pizzaSize3));
  }

  @Test
  public void getPizzaSizeByIdTest() {
    pizzaSizeRepository.deleteAll();
    PizzaSize pizzaSize1 = new PizzaSize("small", "Small", "6",9.99);
    PizzaSize pizzaSize2 = new PizzaSize("medium", "Medium", "9",12.99);
    PizzaSize pizzaSize3 = new PizzaSize("large", "Large", "12",14.99);

    assertEquals(0, pizzaSizeRepository.count());
    pizzaSizeRepository.insert(pizzaSize1);
    pizzaSizeRepository.insert(pizzaSize2);
    pizzaSizeRepository.insert(pizzaSize3);

    Assert.assertEquals(sizeService.getPizzaSizeById("small"), pizzaSize1);
    Assert.assertEquals(sizeService.getPizzaSizeById("small").getPrice(), (Double) 9.99);
    Assert.assertEquals(sizeService.getPizzaSizeById("small").getSizeInch(), "6");

    Assert.assertEquals(sizeService.getPizzaSizeById("medium"), pizzaSize2);
    Assert.assertEquals(sizeService.getPizzaSizeById("medium").getPrice(), (Double) 12.99);
    Assert.assertEquals(sizeService.getPizzaSizeById("medium").getSizeInch(), "9");

    Assert.assertEquals(sizeService.getPizzaSizeById("large"), pizzaSize3);

    Assert.assertFalse(sizeService.getPizzaSizeById("small").equals(null));
    Assert.assertEquals(sizeService.getPizzaSizeById("errorId"), null);
  }

}
