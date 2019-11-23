package io.swagger.api;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

import io.swagger.model.ToppingItem;
import io.swagger.repository.ToppingItemRepository;
import io.swagger.service.ToppingItemService;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.Assert;
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
public class ToppingApiTests {

  @MockBean private ToppingItemRepository toppingRepository;

  @Autowired private ToppingItemService toppingItemService;

  @Test
  public void getAllToppingTest() {
    when(toppingRepository.findAll())
        .thenReturn(
            Stream.of(
                    new ToppingItem("pepperoni1", "pepperoni", "meat", 2.5, 2.75, 3.0, "gluten"),
                    new ToppingItem("sausage1", "sausage", "meat", 2.5, 2.75, 3.0, "gluten"),
                    new ToppingItem("onion1", "onion", "vegetable", 2.0, 2.25, 2.5, "non-gluten"),
                    new ToppingItem(
                        "greenPeppers1",
                        "green peppers",
                        "vegetable",
                        2.0,
                        2.25,
                        2.5,
                        "non-gluten"),
                    new ToppingItem(
                        "mushroom1", "mushroom", "vegetable", 2.0, 2.25, 2.5, "non-gluten"),
                    new ToppingItem(
                        "blackOlives1", "black olives", "vegetable", 2.0, 2.25, 2.5, "non-gluten"))
                .collect(Collectors.toList()));

    Assert.assertEquals(6, toppingItemService.getAllTopping().size());
  }

  @Test
  public void getToppingByIdTest() {
    ToppingItem topping1 =
        new ToppingItem("pepperoni1", "pepperoni", "meat", 2.5, 2.75, 3.0, "gluten");

    when(toppingRepository.findById(topping1.getId())).thenReturn(Optional.of(topping1));
    Assert.assertEquals(topping1, toppingItemService.getToppingById("pepperoni1"));
  }

  @Test
  public void getNonexistentToppingTest() {
    when(toppingRepository.findById(any())).thenReturn(Optional.empty());
    Assert.assertNull(toppingItemService.getToppingById("errorId"));
  }
}
