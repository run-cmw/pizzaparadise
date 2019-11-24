package io.swagger.service;

import java.util.Calendar;
import java.util.GregorianCalendar;
import org.springframework.stereotype.Service;
import io.swagger.model.Card;
import io.swagger.model.Cart;
import io.swagger.model.Receipt;

@Service
public class PurchaseService {

  public Receipt makeReceipt(Cart cart, Card card) {
    if (validateExpDate(card.getExpMonth(), card.getExpYear())) {
      return new Receipt(cart, card);
    }
    return null;
  }

  public boolean validateExpDate(Integer month, Integer year) {
    GregorianCalendar now = new GregorianCalendar();
    int daysInMonth = Calendar.getInstance().getActualMaximum(month);
    GregorianCalendar expDate = new GregorianCalendar(year, month, daysInMonth, 23, 59, 59);
    return now.compareTo(expDate) <= 0;
  }
}