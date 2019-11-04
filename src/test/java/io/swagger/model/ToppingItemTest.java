package io.swagger.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ToppingItemTest {
  private ToppingItem toppingItem1;
  private ToppingItem toppingItem2;
  private ToppingItem toppingItem3;

  @Before
  public void setUp() {
    toppingItem1 = new ToppingItem("pepperoni1", "pepperoni", "meat", 2.5, 2.75, 3.0, "gluten");
    toppingItem2 = new ToppingItem("sausage1", "sausage", "meat", 2.5, 2.75, 3.0, "gluten");
    toppingItem3 = new ToppingItem("onion1", "onion", "vegetable", 2.0, 2.25, 2.5, "non-gluten");

    // Test setters within setup method
    toppingItem1.setId("pepperoni1");
    toppingItem1.setToppingName("pepperoni");
    toppingItem1.setToppingSmallPrice(2.5);
  }

  @Test
  public void getIdTest() {
    assertEquals("pepperoni1", toppingItem1.getId());
  }

  @Test
  public void getNameTest() {
    assertEquals("pepperoni", toppingItem1.getToppingName());
  }

  @Test
  public void getPriceTest() {
    Double price;
    price = 2.5;
    assertEquals(price, toppingItem1.getToppingSmallPrice());
  }

  @Test
  public void equalsTest() {
    assertNotEquals(toppingItem2, toppingItem1);
    assertEquals(toppingItem2, toppingItem2);
    assertNotEquals(toppingItem3, toppingItem1);
    assertNotEquals(toppingItem2, 2);
    assertNotEquals(toppingItem2, null);
  }

  @Test
  public void hashCodeTest() {
    assertEquals(toppingItem1.hashCode(), toppingItem1.hashCode());
    assertNotEquals(toppingItem3.hashCode(), toppingItem1.hashCode());
  }

  @Test
  public void toStringTest() {
    final String TOPPING_ITEM_AS_STRING =
        "ToppingItem{"
            + "id='" + toppingItem1.getId() + '\''
            + ", topping name='" + toppingItem1.getToppingName() + '\''
            + ", topping type='" + toppingItem1.getToppingType() + '\''
            + ", topping small price='" + toppingItem1.getToppingSmallPrice() + '\''
            + ", topping medium price='" + toppingItem1.getToppingMediumPrice() + '\''
            + ", topping large price='" + toppingItem1.getToppingLargePrice() + '\''
            + ", topping gluten='" + toppingItem1.getToppingGluten() + '\''
        + '}';

    assertEquals(TOPPING_ITEM_AS_STRING, toppingItem1.toString());
  }
}