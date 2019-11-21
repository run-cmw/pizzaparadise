package io.swagger.api;

import static org.mockito.Mockito.when;

import io.swagger.model.PizzaSize;
import io.swagger.repository.PizzaSizeRepository;
import io.swagger.service.PizzaSizeService;
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
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class PizzaSizeApiTests {

  @Autowired private PizzaSizeService sizeService;

  @MockBean private PizzaSizeRepository sizeRepository;

  final String TEST_ID = "large";
  final PizzaSize TEST_PIZZA_SIZE = new PizzaSize(TEST_ID, "Large", "16", 20.99);

  @Before
  public void setUp() {
    when(sizeRepository.findAll())
        .thenReturn(
            Stream.of(
                    TEST_PIZZA_SIZE,
                    new PizzaSize("medium", "Medium", "13", 17.99),
                    new PizzaSize("small", "Small", "11", 13.99))
                .collect(Collectors.toList()));

    when(sizeRepository.findById(TEST_ID)).thenReturn(Optional.of(TEST_PIZZA_SIZE));
  }

  @Test
  public void getAllSizesTest() {
    Assert.assertEquals(3, sizeService.getAllPizzaSizes().size());
    Assert.assertNotEquals(sizeService.getAllPizzaSizes(), null);
  }

  @Test
  public void getPizzaSizeByIdTest() {
    Assert.assertEquals(TEST_PIZZA_SIZE, sizeService.getPizzaSizeById(TEST_ID));
  }
}
