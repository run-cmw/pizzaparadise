package io.swagger.service;

import static junit.framework.TestCase.fail;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import io.swagger.model.ToppingItem;
import io.swagger.repository.ToppingItemRepository;
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
public class ToppingItemServiceTest {

  @Autowired private ToppingItemRepository toppingRepo;
  @Autowired private ToppingItemService toppingService;

  @Before
  public void setUp() {
    toppingRepo.deleteAll();
  }

  private ToppingItem setupBacon() {
    ToppingItem bacon = new ToppingItem("bacon1", "bacon", "meat", 2.50, 2.75, 3.00, "gluten");
    toppingRepo.insert(bacon);
    return bacon;
  }

  private ToppingItem setupBroccoli() {
    ToppingItem broccoli =
        new ToppingItem("broccoli1", "broccoli", "vegetable", 2.00, 2.25, 2.50, "non-gluten");
    toppingRepo.insert(broccoli);
    return broccoli;
  }

  @Test
  public void getAllToppingsTest() {
    ToppingItem topping1 = setupBacon();
    ToppingItem topping2 = setupBroccoli();

    List<ToppingItem> allToppings = toppingService.getAllTopping();
    assertEquals(2, toppingRepo.count());
    assertTrue(allToppings.contains(topping1));
    assertTrue(allToppings.contains(topping2));
  }

  @Test
  public void getToppingByIdTest() {
    ToppingItem topping = setupBacon();
    ToppingItem toppingFromDB = toppingService.getToppingById(topping.getId());
    assertNull(toppingService.getToppingById("noTopping"));
    assertEquals(topping, toppingFromDB);
  }

  @Test
  public void addToppingTest() {
    ToppingItem broccoli =
        new ToppingItem("broccoli1", "broccoli", "vegetable", 2.00, 2.25, 2.50, "non-gluten");

    ToppingItem toppingFromServer = toppingService.addTopping(broccoli);
    assertEquals(broccoli, toppingFromServer);
  }

  @Test
  public void deleteToppingTest() {
    ToppingItem broccoli = setupBroccoli();
    try {
      toppingService.deleteTopping(broccoli.getId());
      assertEquals(0, toppingRepo.count());
    } catch (Exception err) {
      fail(err.getMessage());
    }
  }
}
