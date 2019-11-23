package io.swagger.api;

import static org.mockito.Mockito.when;

import io.swagger.model.SpecialItem;
import io.swagger.repository.SpecialItemRepository;
import io.swagger.service.SpecialItemService;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@TestPropertySource(locations = "classpath:/application-test.properties")
public class SpecialApiTests {

  @Autowired private SpecialItemService specialService;

  @MockBean private SpecialItemRepository specialRepository;

  private List<SpecialItem> testSpecials() {
    return Stream.of(
            new SpecialItem(
                "buy1get1free",
                "Buy1Get1Free",
                "Only one special at a time. If you buy 1 pizza, you get 1 free pizza that is equal or less value."),
            new SpecialItem(
                "buy1PizzaGetSodaFree",
                "Buy1PizzaGetSodaFree",
                "If you purchase 1 pizza then you get 1 soda for free"),
            new SpecialItem(
                "buy2LargePizzaNoTopping",
                "Buy2LargePizzaNoTopping30%OFF",
                "if you buy 2 large pizza that has no toppings, then get 30% off"))
        .collect(Collectors.toList());
  }

  @Before
  public void Setup() {
    List<SpecialItem> testSpecials = testSpecials();
    when(specialRepository.findAll()).thenReturn(testSpecials);
    for (SpecialItem item : testSpecials) {
      when(specialRepository.findById(item.getId())).thenReturn(Optional.of(item));
    }
  }

  @Test
  public void getAllSpecialTest() {
    Assert.assertEquals(3, specialService.getAllSpecials().size());
    Assert.assertNotEquals(specialService.getAllSpecials(), null);
  }

  @Test
  public void getSpecialByIdTest() {
    SpecialItem special1 =
        new SpecialItem(
            "buy1get1free",
            "Buy1Get1Free",
            "Only one special at a time. If you buy 1 pizza, you get 1 free pizza that is equal or less value.");

    Assert.assertEquals(special1, specialService.getSpecialById("buy1get1free"));

    Assert.assertTrue(specialService.getSpecialById("buy1get1free").equals(special1));

    SpecialItem special2 =
        new SpecialItem(
            "buy1PizzaGetSodaFree",
            "Buy1PizzaGetSodaFree",
            "If you purchase 1 pizza then you get 1 soda for free");

    Assert.assertEquals(
        specialService.getSpecialById("buy1PizzaGetSodaFree").toString(), special2.toString());

    SpecialItem special3 =
        new SpecialItem(
            "buy2LargePizzaNoTopping",
            "Buy2LargePizzaNoTopping30%OFF",
            "if you buy 2 large pizza that has no toppings, then get 30% off");

    Assert.assertEquals(specialService.getSpecialById("buy2LargePizzaNoTopping"), special3);

    Assert.assertTrue(specialService.getSpecialById("buy2LargePizzaNoTopping").equals(special3));

    Assert.assertFalse(special3.equals(null));

    Assert.assertEquals(specialService.getSpecialById("5d"), null);
  }
}
