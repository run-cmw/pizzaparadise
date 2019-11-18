package io.swagger.service;

import static org.junit.Assert.assertEquals;

import io.swagger.model.SpecialItem;
import io.swagger.repository.SpecialItemRepository;
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
public class SpecialServiceTest {

  @Autowired
  public SpecialItemService specialService;

  @Autowired
  public SpecialItemRepository specialRepo;


  @Test
  public void getAllSizeTest() {
    specialRepo.deleteAll();
    SpecialItem special1 = new SpecialItem("buy1get1Free", "Buy1Get1", "description1");
    SpecialItem special2 = new SpecialItem("freeSoda", "freeSoda", "description2");
    SpecialItem special3 = new SpecialItem("freeTopping", "freeTopping", "description3");
    SpecialItem special4 = new SpecialItem("deliveryfeeFree", "DeliveryfeeFree", "description1");

    assertEquals(0, specialRepo.count());
    specialRepo.insert(special1);
    assertEquals(1, specialRepo.count());
    specialRepo.insert(special2);
    assertEquals(2, specialRepo.count());
    specialRepo.insert(special3);

    List<SpecialItem> lst = specialService.getAllSpecials();
    Assert.assertEquals(3, lst.size());
    Assert.assertEquals(true, lst.contains(special1));
    Assert.assertEquals(true, lst.contains(special2));
    Assert.assertEquals(true, lst.contains(special3));
    Assert.assertEquals(false, lst.contains(special4));
  }

  @Test
  public void getSpecialByIdTest() {
    specialRepo.deleteAll();
    SpecialItem special1 = new SpecialItem("deliveryfeeFree", "DeliveryfeeFree", "description1");
    SpecialItem special2 = new SpecialItem("free16OzSoda", "Free16OzSoda", "description2");
    SpecialItem special3 = new SpecialItem("free2LiterSoda", "Free2LiterSoda", "description3");

    assertEquals(0, specialRepo.count());
    specialRepo.insert(special1);
    specialRepo.insert(special2);
    specialRepo.insert(special3);

    Assert.assertEquals(specialService.getSpecialById("deliveryfeeFree"), special1);
    Assert.assertEquals(specialService.getSpecialById("deliveryFree"), null);

    Assert.assertEquals(specialService.getSpecialById("free16OzSoda"), special2);

    Assert.assertNotEquals(specialService.getSpecialById("free16OzSoda"), special3);
  }

  @Test
  public void addSpecialTest() {
    specialRepo.deleteAll();
    SpecialItem special1 = new SpecialItem("deliveryfeeFree", "DeliveryfeeFree", "description1");
    SpecialItem special2 = new SpecialItem("free16OzSoda", "Free16OzSoda", "description2");
    SpecialItem special3 = new SpecialItem("free2LiterSoda", "Free2LiterSoda", "description3");

    SpecialItem special4 = new SpecialItem("freeSauce", "freeSauce", "description4");
    assertEquals(0, specialRepo.count());
    specialRepo.insert(special1);
    specialRepo.insert(special2);
    specialRepo.insert(special3);
    assertEquals(3, specialRepo.count());
    Assert.assertEquals(specialService.getSpecialById("freeSauce"), null);

    SpecialItem special5 = specialService.addSpecial("freeSauce", "freeSauce", "description5");

    Assert.assertNotEquals(special5, special4);
    Assert.assertEquals(specialService.getSpecialById("freeSauce"), special5);
    Assert.assertNotEquals(specialService.getSpecialById("freeSauce"), special4);
  }

  @Test
  public void deleteSpecialTest() {
    specialRepo.deleteAll();
    SpecialItem special1 = new SpecialItem("deliveryfeeFree", "DeliveryfeeFree", "description1");
    SpecialItem special2 = new SpecialItem("free16OzSoda", "Free16OzSoda", "description2");

    assertEquals(0, specialRepo.count());
    specialRepo.insert(special1);
    specialRepo.insert(special2);
    assertEquals(2, specialRepo.count());
    Assert.assertEquals(specialService.getSpecialById("free16OzSoda"), special2);

    specialService.deleteSpecial("free16OzSoda");
    assertEquals(1, specialRepo.count());
    Assert.assertEquals(specialService.getSpecialById("free16OzSoda"), null);
  }

}
