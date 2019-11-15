package io.swagger.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import org.junit.Before;
import org.junit.Test;


public class SideItemTest {
  private SideItem sideItem;
  private SideItem same;
  private SideItem different;

  @Before
  public void setUp() {
    sideItem = new SideItem("cheeseSticks", "Cheesesticks", 6.99, "appetizer");
    same = new SideItem("hotWings", "Hot wings", 7.99, "appetizer");
    different = new SideItem("2LiterPeachCrush", "2 liter Peach Crush", 2.99, "drink");
    
    // Test setters within setup method
    sideItem.setId("hotWings");
    sideItem.setName("Hot wings");
    sideItem.setPrice(7.99);
    sideItem.setType("appetizer");
  }

  @Test
  public void getIdTest() {
    assertEquals("hotWings", sideItem.getId());
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
    assertEquals(same, sideItem);
    assertEquals(same, same);
    assertNotEquals(different, sideItem);
    assertNotEquals(same, 2);
    assertNotEquals(same, null);
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
      + ", price='" + sideItem.getPrice()+ '\''
      + ", type='" + sideItem.getType()+ '\''
      + '}';

    assertEquals(SIDE_ITEM_AS_STRING, sideItem.toString());
  }
}