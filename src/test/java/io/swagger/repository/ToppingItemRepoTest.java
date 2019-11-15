package io.swagger.repository;

import static org.junit.Assert.assertEquals;

import io.swagger.repository.ToppingItemRepository;
import io.swagger.model.ToppingItem;
import java.util.Optional;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@TestPropertySource(locations = "classpath:/application-test.properties")
public class ToppingItemRepoTest {

  @Autowired
  private ToppingItemRepository toppingItemRepository;
  private ToppingItem toppingItem4;
  private ToppingItem toppingItem5;

  @Before
  public void setup() {
    toppingItemRepository.deleteAll();
    ToppingItem toppingItem1 = new ToppingItem("bacon1", "bacon", "meat", 2.50, 2.75, 3.00, "gluten");
    ToppingItem toppingItem2 = new ToppingItem("broccoli1", "broccoli", "vegetable", 2.00, 2.25,
        2.50, "non-gluten");
    ToppingItem toppingItem3 = new ToppingItem("ham1", "ham", "meat", 2.50, 2.75, 3.00,
        "gluten");

    toppingItem4 = new ToppingItem("lettuce1", "lettuce", "vegie", 2.50, 2.75, 3.00, "non-gluten");
    toppingItem5 = new ToppingItem("mushroom1", "mushroom", "vegetable", 2.00, 2.25,
        2.50, "non-gluten");
    toppingItemRepository.insert(toppingItem1);
    toppingItemRepository.insert(toppingItem2);
    toppingItemRepository.insert(toppingItem3);
  }

  @Test
  public void getIDTest() {
    ToppingItem item = toppingItemRepository.findById("ham1").get();
    Assert.assertEquals(item.getId(), "ham1");
  }


  @Test
  public void getAllToppingTest() {
    assertEquals(3, toppingItemRepository.count());
  }

  @Test
  public void getToppingById() {

    Assert.assertEquals(toppingItemRepository.findById("ham1").isPresent(), true);
  }


  @Test
  public void addDeleteToppingToRepository() {

    assertEquals(3, toppingItemRepository.count());
    toppingItemRepository.save(toppingItem4);
    assertEquals(4, toppingItemRepository.count());
    toppingItemRepository.save(toppingItem5);
    assertEquals(5, toppingItemRepository.count());
    toppingItemRepository.delete(toppingItem4);
    assertEquals(4, toppingItemRepository.count());
    toppingItemRepository.delete(toppingItem5);
    assertEquals(3, toppingItemRepository.count());

  }

}