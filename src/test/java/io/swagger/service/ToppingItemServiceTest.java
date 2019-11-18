package io.swagger.service;

import static org.junit.Assert.assertEquals;
import io.swagger.model.ToppingItem;
import io.swagger.repository.ToppingItemRepository;
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
public class ToppingItemServiceTest {

  @Autowired
  private ToppingItemRepository toppingRepo;
  @Autowired
  private ToppingItemService toppingService;

  @Test
  public void getAllToppingsTest() {
    toppingRepo.deleteAll();
    ToppingItem toppingItem1 = new ToppingItem("bacon1", "bacon", "meat", 2.50, 2.75, 3.00, "gluten");
    ToppingItem toppingItem2 = new ToppingItem("broccoli1", "broccoli", "vegetable", 2.00, 2.25,
        2.50, "non-gluten");
    ToppingItem toppingItem3 = new ToppingItem("ham1", "ham", "meat", 2.50, 2.75, 3.00,
        "gluten");
    ToppingItem test = new ToppingItem("carrot", "carrot", "vegetable", 2.00, 2.40, 3.00, "non-gluten");


    assertEquals(0, toppingRepo.count());
    toppingRepo.insert(toppingItem1);
    toppingRepo.insert(toppingItem2);
    toppingRepo.insert(toppingItem3);
    List<ToppingItem> list = toppingService.getAllTopping();
    assertEquals(3, toppingRepo.count());
    Assert.assertEquals(3, list.size());

    // These will work once you add Equal method to ToppingItem object.
    //Assert.assertEquals(true, list.contains(toppingItem1));
    //Assert.assertEquals(true, list.contains(toppingItem2));
    //Assert.assertEquals(true, list.contains(toppingItem3));
    //Assert.assertEquals(false, list.contains(test));

  }

  @Test
  public void getToppingByIdTest() {
    toppingRepo.deleteAll();
    ToppingItem toppingItem1 = new ToppingItem("bacon1", "bacon", "meat", 2.50, 2.75, 3.00, "gluten");
    ToppingItem toppingItem2 = new ToppingItem("broccoli1", "broccoli", "vegetable", 2.00, 2.25,
        2.50, "non-gluten");
    ToppingItem toppingItem3 = new ToppingItem("ham1", "ham", "meat", 2.50, 2.75, 3.00,
        "gluten");
    ToppingItem test = new ToppingItem("carrot", "carrot", "vegetable", 2.00, 2.40, 3.00, "non-gluten");
    assertEquals(0, toppingRepo.count());
    toppingRepo.insert(toppingItem1);
    Assert.assertEquals(toppingService.getToppingById("bacon1").toString(),
        toppingItem1.toString());
    Assert.assertEquals(toppingService.getToppingById("broccoli1"),
        null);
    toppingRepo.insert(toppingItem2);
    toppingRepo.insert(toppingItem3);

    Assert.assertEquals(toppingService.getToppingById("broccoli1").toString(),
        toppingItem2.toString());

    Assert.assertEquals(toppingService.getToppingById("ham1").toString(),
        toppingItem3.toString());


    Assert.assertEquals(toppingService.getToppingById("noToppingId"), null);
  }

  @Test
  public void addToppingTest() {

  }

  @Test
  public void deleteToppingTest() {
    toppingRepo.deleteAll();
    ToppingItem toppingItem1 = new ToppingItem("bacon1", "bacon", "meat", 2.50, 2.75, 3.00, "gluten");
    ToppingItem toppingItem2 = new ToppingItem("broccoli1", "broccoli", "vegetable", 2.00, 2.25,
        2.50, "non-gluten");
    ToppingItem toppingItem3 = new ToppingItem("ham1", "ham", "meat", 2.50, 2.75, 3.00,
        "gluten");

    assertEquals(0, toppingRepo.count());
    toppingRepo.insert(toppingItem1);
    toppingRepo.insert(toppingItem2);
    assertEquals(2, toppingRepo.count());
    toppingRepo.insert(toppingItem3);
    assertEquals(3, toppingRepo.count());


    Assert.assertEquals(toppingService.getToppingById("broccoli1").toString(),
        toppingItem2.toString());

    Assert.assertEquals(toppingService.getToppingById("ham1").toString(),
        toppingItem3.toString());

    toppingService.deleteTopping("bacon1");
    assertEquals(2, toppingRepo.count());
    Assert.assertEquals(toppingService.getToppingById("bacon1"), null);
  }
}
