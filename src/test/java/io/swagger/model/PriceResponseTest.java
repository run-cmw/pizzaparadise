package io.swagger.model;

import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class PriceResponseTest {
  public PriceResponse response1;
  public PriceResponse response2;
  public PriceResponse response3;
  public PriceResponse response4;

  @Before
  public void setUp() {
    response1 = new PriceResponse(true, 5.00, "USD");
    response2 = new PriceResponse(false, null, null);
    response3 = new PriceResponse(true, 50.00, "USD");
    response4 = new PriceResponse(true, 5.00, "EURO");
  }

  @Test
  public void isSuccessTest() {
    Assert.assertEquals(response1.isSuccess(), true);
    Assert.assertEquals(response2.isSuccess(), false);
    Assert.assertNotEquals(response1.isSuccess(), false);
  }

  @Test
  public void getPriceTest() {
    Assert.assertEquals(response1.getPrice(), (Double) 5.00);
    Assert.assertEquals(response2.getPrice(), null);
    Assert.assertNotEquals(response1.getPrice(), (Double) 50.00);
    Assert.assertEquals(response3.getPrice(), (Double) 50.00);
  }

  @Test
  public void getCurrenctTest() {
    Assert.assertEquals(response1.getCurrency(), "USD");
    Assert.assertEquals(response2.getCurrency(), null);
    Assert.assertNotEquals(response1.getCurrency(), null);
    Assert.assertNotEquals(response1.getCurrency(), "EURO");
    Assert.assertEquals(response4.getCurrency(), "EURO");
  }

}
