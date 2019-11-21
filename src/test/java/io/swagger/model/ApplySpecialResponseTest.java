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
public class ApplySpecialResponseTest {
  private ApplySpecialResponse applySpecialResponse;
  private ApplySpecialResponse sameObject;
  private ApplySpecialResponse differentObject;

  @Before
  public void setUp() {
    applySpecialResponse = new ApplySpecialResponse("buy1PizzaGetSodaFree");
    sameObject = new ApplySpecialResponse("buy1PizzaGetSodaFree");
    differentObject = new ApplySpecialResponse("buy1Get1Free");
  }

  @Test
  public void testGetSpecialId() {
    assertEquals("buy1PizzaGetSodaFree", applySpecialResponse.getSpecialId());
  }

  @Test
  public void testSetSpecialId() {
    applySpecialResponse.setSpecialId("buy2LargePizzaNoTopping");
    assertEquals("buy2LargePizzaNoTopping", applySpecialResponse.getSpecialId());
  }

  @Test
  public void testGetSuccess() {
    assertEquals(false, applySpecialResponse.getSuccess());
  }

  @Test
  public void testSetSuccess() {
    applySpecialResponse.setSuccess(true);
    assertEquals(true, applySpecialResponse.getSuccess());
  }

  @Test
  public void testGetMessage() {
    assertEquals(null, applySpecialResponse.getMessage());
  }

  @Test
  public void testSetMessage() {
    applySpecialResponse.setMessage("ERROR_ONLY_ONE_SPECIAL_PER_CART");
    assertEquals("ERROR_ONLY_ONE_SPECIAL_PER_CART", applySpecialResponse.getMessage());
  }

  @Test
  public void testGetSavings() {
    assert(0.00 == applySpecialResponse.getSavings());
  }

  @Test
  public void testSetSavings() {
    applySpecialResponse.setSavings(2.49);
    assert(2.49 == applySpecialResponse.getSavings());
  }
  
  @Test
  public void testEquals() {
    assertEquals(sameObject, applySpecialResponse);
    assertNotEquals(differentObject, applySpecialResponse);
    assertNotEquals(2, applySpecialResponse);
    assertNotEquals(null, applySpecialResponse);
  }

  @Test
  public void testHashCode() {
    assertEquals(sameObject.hashCode(), applySpecialResponse.hashCode());
    assertNotEquals(differentObject.hashCode(), applySpecialResponse.hashCode());
  }

  @Test
  public void testToString() {
    final String APPLY_SPECIAL_AS_STRING =
        "ApplySpecialResponse{"
            + "specialId='" + applySpecialResponse.getSpecialId() + '\''
            + ", success='" + applySpecialResponse.getSuccess() + '\''
            + ", message='" + applySpecialResponse.getMessage() + '\''
            + ", savings='" + applySpecialResponse.getSavings() + '\''
            + '}';

    assertEquals(APPLY_SPECIAL_AS_STRING, applySpecialResponse.toString());
  }
}