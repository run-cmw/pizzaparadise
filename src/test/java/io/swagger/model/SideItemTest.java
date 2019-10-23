package io.swagger.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class SideItemTest {
  SideItem sideItem;
  SideItem same;
  SideItem different;

  @Before
  public void setUp() throws Exception {
    sideItem = new SideItem("2dae8e058980e20b64e28174", "Cheesesticks", 6.99);
    same = new SideItem("5dae8e058980e20b64e28170", "Hot wings", 7.99);
    different = new SideItem("2eae8e058980e20b64e28175", "2 liter Peach Crush", 2.99);
    
    // Test setters within setup method
    sideItem.setId("5dae8e058980e20b64e28170");
    sideItem.setName("Hot wings");
    sideItem.setPrice(7.99);
  }

  @Test
  public void getIdTest() {
    assertEquals("5dae8e058980e20b64e28170", sideItem.getId());
  }

  @Test
  public void getNameTest() {
    assertEquals("Hot wings", sideItem.getName());
  }

  @Test
  public void getPriceTest() {
    Double price = 7.99;
    assertEquals(price, sideItem.getPrice());
  }

  @Test
  public void equalsTest() {
    assertTrue(same.equals(sideItem));
    assertTrue(same.equals(same));
    assertFalse(different.equals(sideItem));
    assertFalse(same.equals(2));
    assertFalse(same.equals(null));
  }

  @Test
  public void hashCodeTest() {
    assertEquals(same.hashCode(), sideItem.hashCode());
    assertNotEquals(different.hashCode(), sideItem.hashCode());
  }

  @Test
  public void toStringTest() {
    final String SIDE_ITEM_AS_STRING =
      "SideItem{"
      + "id='" + sideItem.getId() + '\''
      + ", name='" + sideItem.getName()+ '\''
      + ", price='" + sideItem.getPrice()
      + '}';

    assertEquals(SIDE_ITEM_AS_STRING, sideItem.toString());
  }
}