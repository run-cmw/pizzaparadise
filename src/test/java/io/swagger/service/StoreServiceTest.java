package io.swagger.service;


import static org.junit.Assert.assertEquals;
import io.swagger.model.StoreItem;
import io.swagger.repository.StoreItemRepository;
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
public class StoreServiceTest {
  @Autowired
  public StoreService storeService;

  @Autowired
  public StoreItemRepository storeRepo;


  @Test
  public void getAllSizeTest() {
    storeRepo.deleteAll();
    StoreItem store1 = new StoreItem("moonglow", "999 Moonglow Ave", "Emeryville", "California", "70802", true);
    StoreItem store2 = new StoreItem("plank", "777 Plank Rd", "Baton Rouge", "Louisiana", "98105", false);
    StoreItem store3 = new StoreItem("house", "4060 9th ave", "Seattle", "Washington", "98105", false);
    StoreItem store4 = new StoreItem("second", "4115 Roosevelt way NE", "Seattle", "Washington", "98105", true);

    assertEquals(0, storeRepo.count());
    storeRepo.insert(store1);
    assertEquals(1, storeRepo.count());
    storeRepo.insert(store2);
    assertEquals(2, storeRepo.count());
    storeRepo.insert(store3);

    List<StoreItem> list =storeService.getAllStores();
    assertEquals(3, storeRepo.count());
    Assert.assertEquals(3, list.size());
    Assert.assertEquals(true, list.contains(store1));
    Assert.assertEquals(true, list.contains(store2));
    Assert.assertEquals(true, list.contains(store3));
    Assert.assertEquals(false, list.contains(store4));
  }

  @Test
  public void getSpecialByIdTest() {
    storeRepo.deleteAll();
    StoreItem store1 = new StoreItem("moonglow", "999 Moonglow Ave", "Emeryville", "California", "70802", true);
    StoreItem store2 = new StoreItem("plank", "777 Plank Rd", "Baton Rouge", "Louisiana", "98105", false);
    StoreItem store3 = new StoreItem("house", "4060 9th ave", "Seattle", "Washington", "98105", false);

    assertEquals(0, storeRepo.count());
    storeRepo.insert(store1);

    Assert.assertEquals(storeService.getStoreById("moonglow"), store1);

    Assert.assertEquals(storeService.getStoreById("house"), null);
    storeRepo.insert(store2);
    storeRepo.insert(store3);
    Assert.assertEquals(storeService.getStoreById("plank"), store2);
    Assert.assertEquals(storeService.getStoreById("plank").getId(), "plank");
    Assert.assertEquals(storeService.getStoreById("plank").getOffersGlutenFree(), false);
    Assert.assertEquals(storeService.getStoreById("plank").getStreetNumAndName(), "777 Plank Rd");
    Assert.assertEquals(storeService.getStoreById("plank").getCity(), "Baton Rouge");
    Assert.assertEquals(storeService.getStoreById("plank").getZipCode(), "98105");
    Assert.assertEquals(storeService.getStoreById("plank").getState(), "Louisiana");

    Assert.assertNotEquals(storeService.getStoreById("moonglow"), store2);
    Assert.assertEquals(storeService.getStoreById("moonglow").getId(), "moonglow");
    Assert.assertEquals(storeService.getStoreById("moonglow").getOffersGlutenFree(), true);
    Assert.assertEquals(storeService.getStoreById("moonglow").getStreetNumAndName(), "999 Moonglow Ave");
    Assert.assertEquals(storeService.getStoreById("moonglow").getCity(), "Emeryville");
    Assert.assertEquals(storeService.getStoreById("moonglow").getZipCode(), "70802");
    Assert.assertEquals(storeService.getStoreById("moonglow").getState(), "California");

    Assert.assertEquals(storeService.getStoreById("house"), store3);
    Assert.assertEquals(storeService.getStoreById("house").getId(), "house");
    Assert.assertEquals(storeService.getStoreById("house").getOffersGlutenFree(), false);
    Assert.assertEquals(storeService.getStoreById("house").getStreetNumAndName(), "4060 9th ave");
    Assert.assertEquals(storeService.getStoreById("house").getCity(), "Seattle");
    Assert.assertEquals(storeService.getStoreById("house").getZipCode(), "98105");
    Assert.assertEquals(storeService.getStoreById("house").getState(), "Washington");
  }

  @Test
  public void addSpecialTest() {

  }

  @Test
  public void deleteSpecialTest() {
    storeRepo.deleteAll();
    StoreItem store1 = new StoreItem("plank", "777 Plank Rd", "Baton Rouge", "Louisiana", "98105", false);
    StoreItem store2 = new StoreItem("house", "4060 9th ave", "Seattle", "Washington", "98105", false);
    StoreItem store3 = new StoreItem("second", "4115 Roosevelt way NE", "Seattle", "Washington", "98105", true);

    assertEquals(0, storeRepo.count());
    storeRepo.insert(store1);
    storeRepo.insert(store2);
    assertEquals(2, storeRepo.count());
    Assert.assertEquals(storeService.getStoreById("house"), store2);

    storeService.deleteStore("house");
    assertEquals(1, storeRepo.count());
    Assert.assertEquals(storeService.getStoreById("house"), null);
    Assert.assertEquals(storeService.getStoreById("plank"), store1);
    Assert.assertEquals(storeService.getStoreById("second"), null);
    storeRepo.insert(store3);
    assertEquals(2, storeRepo.count());
    storeService.deleteStore("second");
    Assert.assertEquals(storeService.getStoreById("second"), null);
    assertEquals(1, storeRepo.count());
  }

}
