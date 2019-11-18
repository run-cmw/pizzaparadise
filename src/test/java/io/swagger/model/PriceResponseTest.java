package io.swagger.model;

import org.junit.Assert;
import org.junit.Test;

public class PriceResponseTest {

  @Test
  public void isSuccessTest() {
    PriceResponse response1 = new PriceResponse(true, 5.00, "USD");
    PriceResponse response2 = new PriceResponse(false, null, null);
    Assert.assertEquals(response1.isSuccess(), true);
    Assert.assertEquals(response2.isSuccess(), false);
    Assert.assertNotEquals(response1.isSuccess(), false);
  }

  @Test
  public void getPriceTest() {
    PriceResponse response1 = new PriceResponse(true, 5.00, "USD");
    PriceResponse response2 = new PriceResponse(false, null, null);
    PriceResponse response3 = new PriceResponse(true, 50.00, "USD");
    Assert.assertEquals(response1.getPrice(), (Double) 5.00);
    Assert.assertEquals(response2.getPrice(), null);
    Assert.assertNotEquals(response1.getPrice(), (Double) 50.00);
    Assert.assertEquals(response3.getPrice(), (Double) 50.00);
  }

  @Test
  public void getCurrenctTest() {
    PriceResponse response1 = new PriceResponse(true, 5.00, "USD");
    PriceResponse response2 = new PriceResponse(false, null, null);
    PriceResponse response4 = new PriceResponse(true, 5.00, "EURO");
    Assert.assertEquals(response1.getCurrency(), "USD");
    Assert.assertEquals(response2.getCurrency(), null);
    Assert.assertNotEquals(response1.getCurrency(), null);
    Assert.assertNotEquals(response1.getCurrency(), "EURO");
    Assert.assertEquals(response4.getCurrency(), "EURO");
  }

}
