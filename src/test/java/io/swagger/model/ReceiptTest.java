package io.swagger.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import org.bson.types.ObjectId;
import org.junit.Before;
import org.junit.Test;

public class ReceiptTest {

  private Receipt receipt;
  private Cart cart;
  private Card card;

  @Before
  public void setUp() {
    cart = makeCart();
    card = makeCard();
    receipt = new Receipt(cart, card);
  }

  public Card makeCard() {
    return new Card("YeJee", "Lee", "4400616718352235", 1, 2021, CardProvider.VISA);
  }

  public Cart makeCart() {
    ObjectId id = new ObjectId();
    return new Cart("brooklyn", id);
  }

  @Test
  public void getReceiptId() {
    assertNotNull(receipt.getReceiptId());

  }
}