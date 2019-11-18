package io.swagger.service;


import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import io.swagger.model.Pizza;
import io.swagger.model.PizzaSize;
import io.swagger.model.ToppingItem;
import io.swagger.repository.PizzaSizeRepository;
import io.swagger.repository.ToppingItemRepository;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@TestPropertySource(locations = "classpath:/application-test.properties")
public class PizzaServiceTest {

  @Autowired
  public PizzaService pizzaService;

  @Autowired
  private ToppingItemRepository toppingRepo;

  @Autowired
  private PizzaSizeRepository sizeRepo;


  @Before
  public void setUp() {
    toppingRepo.deleteAll();
    sizeRepo.deleteAll();
  }

  String SMALL_SIZE = "small";
  String MEDIUM_SIZE = "medium";
  String TOPPING_NOT_FOUND = "TOPPING_NOT_FOUND";
  String TOO_MANY_PIZZA_TOPPINGS = "TOO_MANY_PIZZA_TOPPINGS";

  String BACON = "bacon1";
  String BROCCOLI = "broccoli1";

  private ToppingItem setupBacon() {
    ToppingItem bacon = new ToppingItem("bacon1", "bacon", "meat", 2.50, 2.75, 3.00, "gluten");
    toppingRepo.insert(bacon);
    return bacon;
  }

  private ToppingItem setupBroccoli() {
    ToppingItem broccoli = new ToppingItem("broccoli1", "broccoli", "vegetable", 2.00, 2.25, 2.50, "non-gluten");
    toppingRepo.insert(broccoli);
    return broccoli;
  }

  private Pizza setUpPizza(String size, boolean gluten) {
    Pizza pizza = new Pizza(size, gluten);
    return pizza;
  }

  private PizzaSize setUpSmallSize() {
    PizzaSize pizzaSize = new PizzaSize("small", "Small", "6",9.99);
    sizeRepo.insert(pizzaSize);
    return pizzaSize;
  }

  private PizzaSize setUpMediumSize() {
    PizzaSize pizzaSize = new PizzaSize("medium", "Medium", "9",12.99);
    sizeRepo.insert(pizzaSize);
    return pizzaSize;
  }

  @Test
  public void TestGetPizzaPrice() throws Exception {
    setUpSmallSize();
    Pizza pizza = setUpPizza(SMALL_SIZE, true);
    ToppingItem bacon = setupBacon();
    ToppingItem broccoli = setupBroccoli();
    pizza.getToppingIDs().add(BACON);
    pizza.getToppingIDs().add(BROCCOLI);

    Double price = pizzaService.getPizzaPrice(pizza);
    assertEquals((Double) 14.49, price);
  }

  @Test
  public void TestGetPizzaToppingPrice() throws Exception {
    setUpMediumSize();
    ToppingItem bacon = setupBacon();
    ToppingItem broccoli = setupBroccoli();
    List<String> toppingIDs1 = new ArrayList<>();
    toppingIDs1.add(bacon.getId());
    toppingIDs1.add(broccoli.getId());

    Double price = pizzaService.getPizzaToppingPrice(MEDIUM_SIZE, toppingIDs1);
    assertEquals((Double) 5.00, price);

    List<String> toppingIDs2 = new ArrayList<>();
    toppingIDs2.add("noTopping");
    try {
      pizzaService.getPizzaToppingPrice(MEDIUM_SIZE, toppingIDs2);
      fail();
    } catch (IOException err) {
      assertThat(err.getMessage(), is(TOPPING_NOT_FOUND));

    }
  }

}
