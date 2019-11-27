package io.swagger.service;

import io.swagger.repository.ReceiptRepository;
import java.util.Calendar;
import java.util.GregorianCalendar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import io.swagger.model.Card;
import io.swagger.model.Cart;
import io.swagger.model.Receipt;

@Service
public class PurchaseService {

  @Autowired
  private ReceiptRepository receiptRepository;

  /**
   * Create Receipt using the Cart and Card
   * @param cart cart given to show items
   * @param card card given to purchase
   * @return receipt if card is valid, null if the card is expired
   */
  public Receipt makeReceipt(Cart cart, Card card) {
    if (validateExpDate(card.getExpMonth(), card.getExpYear())) {
      Receipt receipt = new Receipt(cart, card);
      receiptRepository.save(receipt);
      return receipt;
    }
    return null;
  }

  /**
   * Validate the expiration date of the Card
   * @param month month given to check
   * @param year year given to check
   * @return true if the month/year is not expired. false if it is expired.
   */
  public boolean validateExpDate(Integer month, Integer year) {
    GregorianCalendar now = new GregorianCalendar();
    int daysInMonth = Calendar.getInstance().getActualMaximum(month);
    GregorianCalendar expDate = new GregorianCalendar(year, month, daysInMonth, 23, 59, 59);
    return now.compareTo(expDate) <= 0;
  }
}