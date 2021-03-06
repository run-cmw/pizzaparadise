package io.swagger.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Before;
import org.junit.Test;

public class CardTest {

  private Card card;

  @Before
  public void setUp() {
    card = new Card("YeJee", "Lee", "4400616718352235", 1, 2021, CardProvider.VISA);
  }

  @Test
  public void getFirstNameTest() {
    assertEquals("YeJee", card.getFirstName());
  }

  @Test
  public void getLastNameTest() {
    assertEquals("Lee", card.getLastName());
  }

  @Test
  public void getCardNumberTest() {
    assertEquals("4400616718352235", card.getCardNumber());
  }

  @Test
  public void getExpMonthTest() {
    assertEquals((Integer) 1, card.getExpMonth());
  }

  @Test
  public void getExpYearTest() {
    assertEquals((Integer) 2021, card.getExpYear());
  }

  @Test
  public void getCardProviderTest() {
    assertEquals(CardProvider.VISA, card.getCardProvider());
  }

  @Test
  public void toStringTest() {
    String test =
        "Card{Name="
            + card.getFirstName()
            + " "
            + card.getLastName()
            + ", "
            + "CardNumber="
            + card.getCardNumber()
            + ", "
            + "ExpirationDate="
            + card.getExpMonth()
            + "/"
            + card.getExpYear()
            + ", "
            + "CardProvider="
            + card.getCardProvider()
            + "}";
    assertEquals(test, card.toString());
  }

  @Test
  public void equalsTest() {
    assertEquals(card, card);
    assertNotEquals(card, null);
    SpecialItem sameAsSpecial1 = new SpecialItem("1", "Buy1Get1Free", "BuyOneGetOne description");
    assertNotEquals(card, sameAsSpecial1);
    Card card1 = new Card("Jenny", "Lee", "4400616718352235", 1, 2021, CardProvider.VISA);
    Card card2 = new Card("YeJee", "Chung", "4400616718352235", 1, 2021, CardProvider.VISA);
    Card card3 = new Card("YeJee", "Lee", "4400616718352466", 1, 2021, CardProvider.VISA);
    Card card4 = new Card("YeJee", "Lee", "4400616718352235", 2, 2021, CardProvider.VISA);
    Card card5 = new Card("YeJee", "Lee", "4400616718352235", 1, 2022, CardProvider.VISA);
    Card card6 = new Card("YeJee", "Lee", "4400616718352235", 1, 2021, CardProvider.MASTER);
    Card card7 = new Card("YeJee", "Lee", "4400616718352235", 1, 2021, CardProvider.VISA);
    assertNotEquals(card1, card);
    assertNotEquals(card2, card);
    assertNotEquals(card3, card);
    assertNotEquals(card4, card);
    assertNotEquals(card5, card);
    assertNotEquals(card6, card);
    assertEquals(card7, card);
  }
}
