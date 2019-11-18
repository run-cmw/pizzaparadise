package io.swagger.service;


import static org.junit.Assert.assertEquals;


import io.swagger.model.SideItem;
import io.swagger.repository.SideItemRepository;

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
public class SideServiceTest {
  @Autowired
  public SideService sideService;

  @Autowired
  public SideItemRepository sideRepo;


  @Test
  public void getAllSideTest() {
    sideRepo.deleteAll();

    SideItem side1 = new SideItem("cheeseSticks", "Cheesesticks", 7.99, "appetizer");
    SideItem side2 = new SideItem("chocolateChipCookie", "Chocolate chip cookie", 1.99, "dessert");
    SideItem side3 = new SideItem("brownie", "Brownie", 2.49, "dessert");
    SideItem side4 = new SideItem("16OzWater", "16 oz water", 1.49, "drink");

    assertEquals(0, sideRepo.count());
    sideRepo.insert(side1);
    assertEquals(1, sideRepo.count());
    sideRepo.insert(side2);
    assertEquals(2, sideRepo.count());
    sideRepo.insert(side3);

    List<SideItem> list = sideService.getAllSides();
    Assert.assertEquals(3, list.size());
    Assert.assertEquals(true, list.contains(side1));
    Assert.assertEquals(true, list.contains(side2));
    Assert.assertEquals(true, list.contains(side3));
    Assert.assertEquals(false, list.contains(side4));

  }

  @Test
  public void getSideByIdTest() {
    sideRepo.deleteAll();
    SideItem side1 = new SideItem("cheeseSticks", "Cheesesticks", 7.99, "appetizer");
    SideItem side2 = new SideItem("chocolateChipCookie", "Chocolate chip cookie", 1.99, "dessert");
    SideItem side3 = new SideItem("brownie", "Brownie", 2.49, "dessert");


    assertEquals(0, sideRepo.count());
    sideRepo.insert(side1);

    Assert.assertEquals(sideService.getSideById("cheeseSticks"), side1);
    Assert.assertEquals(sideService.getSideById("cheeseSticks").getPrice(), (Double) 7.99);
    Assert.assertEquals(sideService.getSideById("cheeseSticks").getType(), "appetizer");
    Assert.assertEquals(sideService.getSideById("cheeseSticks").getId(), "cheeseSticks");
    Assert.assertEquals(sideService.getSideById("cheeseSticks").getName(), "Cheesesticks");

    Assert.assertEquals(sideService.getSideById("brownie"), null);
    sideRepo.insert(side2);
    sideRepo.insert(side3);
    assertEquals(3, sideRepo.count());
    Assert.assertEquals(sideService.getSideById("chocolateChipCookie"), side2);
    Assert.assertEquals(sideService.getSideById("chocolateChipCookie").getPrice(), (Double) 1.99);
    Assert.assertEquals(sideService.getSideById("chocolateChipCookie").getType(), "dessert");
    Assert.assertEquals(sideService.getSideById("chocolateChipCookie").getId(), "chocolateChipCookie");
    Assert.assertEquals(sideService.getSideById("chocolateChipCookie").getName(), "Chocolate chip cookie");

    Assert.assertNotEquals(sideService.getSideById("chocolateChipCookie"), side3);
    Assert.assertNotEquals(sideService.getSideById("chocolateChipCookie"), side1);
  }

  @Test
  public void addSideTest() {

  }

  @Test
  public void deleteSideTest() {
    sideRepo.deleteAll();
    SideItem side1 = new SideItem("snack", "Snack", 5.99, "dessert");
    SideItem side2 = new SideItem("16OzWater", "16 oz water", 1.49, "drink");

    assertEquals(0, sideRepo.count());
    sideRepo.insert(side1);
    sideRepo.insert(side2);

    assertEquals(2, sideRepo.count());
    Assert.assertEquals(sideService.getSideById("16OzWater"), side2);

    sideService.deleteSide("16OzWater");
    assertEquals(1, sideRepo.count());
    Assert.assertEquals(sideService.getSideById("16OzWater"), null);
    Assert.assertEquals(sideService.getSideById("snack"), side1);
    SideItem side3 = new SideItem("brownie", "Brownie", 2.49, "dessert");
    sideRepo.insert(side3);
    sideService.deleteSide("brownie");
    Assert.assertEquals(sideService.getSideById("brownie"), null);
  }

}
