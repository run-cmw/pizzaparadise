package io.swagger.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import org.junit.Test;


public class SideItemTest {

  @Test
  public void getIdTest() {
    SideItem sideItem = new SideItem("cheeseSticks", "Cheesesticks", 6.99, "appetizer");
    assertEquals("cheeseSticks", sideItem.getId());
  }

  @Test
  public void setIdTest() {
    SideItem sideItem = new SideItem("cheeseSticks", "Cheesesticks", 6.99, "appetizer");
    sideItem.setId("hotWings");
    assertEquals("hotWings", sideItem.getId());
  }

  @Test
  public void getNameTest() {
    SideItem sideItem = new SideItem("cheeseSticks", "Cheesesticks", 6.99, "appetizer");
    assertEquals("Cheesesticks", sideItem.getName());
  }

  @Test
  public void setNameTest() {
    SideItem sideItem = new SideItem("cheeseSticks", "Cheesesticks", 6.99, "appetizer");
    sideItem.setName("Hot wings");
    assertEquals("Hot wings", sideItem.getName());
  }

  @Test
  public void getPriceTest() {
    SideItem sideItem = new SideItem("cheeseSticks", "Cheesesticks", 6.99, "appetizer");
    Double price = 6.99;
    assertEquals(price, sideItem.getPrice());
  }

  @Test
  public void setPriceTest() {
    SideItem sideItem = new SideItem("cheeseSticks", "Cheesesticks", 6.99, "appetizer");
    Double price = 10.99;
    sideItem.setPrice(price);
    assertEquals(price, sideItem.getPrice());
  }

  @Test
  public void getTypeTest() {
    SideItem sideItem = new SideItem("cheeseSticks", "Cheesesticks", 6.99, "appetizer");
    assertEquals("appetizer", sideItem.getType());
  }

  @Test
  public void setTypeTest() {
    SideItem sideItem = new SideItem("cheeseSticks", "Cheesesticks", 6.99, "appetizer");
    sideItem.setType("dessert");
    assertEquals("dessert", sideItem.getType());
  }

  @Test
  public void equalsTest() {

    SideItem sideItem = new SideItem("cheeseSticks", "Cheesesticks", 6.99, "appetizer");
    SideItem sideItem2 = new SideItem("cheeseSticks", "Cheesesticks", 6.99, "appetizer");
    SideItem same = new SideItem("hotWings", "Hot wings", 7.99, "appetizer");
    SideItem different = new SideItem("2LiterPeachCrush", "2 liter Peach Crush", 2.99, "drink");

    assertEquals(sideItem2, sideItem);
    assertEquals(same, same);
    assertNotEquals(different, sideItem);
    assertNotEquals(same, 2);
    assertNotEquals(same, null);
  }

  @Test
  public void hashCodeTest() {
    SideItem sideItem = new SideItem("cheeseSticks", "Cheesesticks", 6.99, "appetizer");
    SideItem sideItem2 = new SideItem("cheeseSticks", "Cheesesticks", 6.99, "appetizer");
    SideItem same = new SideItem("hotWings", "Hot wings", 7.99, "appetizer");
    SideItem different = new SideItem("2LiterPeachCrush", "2 liter Peach Crush", 2.99, "drink");

    assertEquals(sideItem2.hashCode(), sideItem.hashCode());
    assertNotEquals(different.hashCode(), sideItem.hashCode());
  }

  @Test
  public void toStringTest() {
    SideItem sideItem = new SideItem("cheeseSticks", "Cheesesticks", 6.99, "appetizer");

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