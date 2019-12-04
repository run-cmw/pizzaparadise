package io.swagger.api;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import io.swagger.DBPizzaSizes;
import io.swagger.DBStoreItems;
import io.swagger.model.Card;
import io.swagger.model.CardProvider;
import io.swagger.model.Cart;
import io.swagger.model.Pizza;
import io.swagger.model.PizzaSize;
import io.swagger.model.PurchaseResponse;
import io.swagger.model.Receipt;
import io.swagger.repository.CartRepository;
import io.swagger.service.CartService;
import io.swagger.service.PizzaSizeService;
import io.swagger.service.PurchaseService;
import org.bson.types.ObjectId;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner.class)
@TestPropertySource("classpath:/application-test.properties")
@EnableAutoConfiguration
@SpringBootTest
public class PurchaseApiControllerTest {

  @Autowired
  private PurchaseApiController purchaseApi;
  @Autowired
  private CartRepository cartRepository;
  @Autowired
  private CartService cartService;
  @Autowired
  private PizzaSizeService pizzaSizeService;
  @Autowired
  private PurchaseService purchaseService;


  @Before
  public void setUp() {
    cartRepository.deleteAll();
  }

  @Test
  public void testMakePurchaseOk() throws Exception {
    ObjectId cartId = new ObjectId();
    Cart cart1 = new Cart(DBStoreItems.BROOKLYN_STORE.getId(), cartId);
    cartRepository.save(cart1);

    PizzaSize largeSize = DBPizzaSizes.SMALL;
    pizzaSizeService.addPizzaSize(largeSize);

    Pizza pizza = new Pizza(largeSize.getId(), true);
    cartService.addPizzaToCart(cart1, pizza);
    cartService.addPizzaToCart(cart1, pizza);

    Card card1 = new Card("Mary", "Smith", "4400616718352235", 12, 2030, CardProvider.VISA);
    Receipt receipt1 = purchaseService.makeReceipt(cart1, card1);

    final ResponseEntity<PurchaseResponse> response = purchaseApi
        .makePurchase(cart1.getId(), cart1.getStoreID(), card1);
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertNotNull(receipt1);
  }

  @Test
  public void testMakePurchaseExpCard() throws Exception {
    ObjectId cartId = new ObjectId();
    Cart cart2 = new Cart(DBStoreItems.BROOKLYN_STORE.getId(), cartId);
    cartRepository.save(cart2);

    PizzaSize largeSize = DBPizzaSizes.SMALL;
    pizzaSizeService.addPizzaSize(largeSize);

    Pizza pizza = new Pizza(largeSize.getId(), true);
    cartService.addPizzaToCart(cart2, pizza);
    cartService.addPizzaToCart(cart2, pizza);

    Card card2 = new Card("Bob", "Smith", "4400616718352235", 12, 2000, CardProvider.VISA);

    final ResponseEntity<PurchaseResponse> response = purchaseApi
        .makePurchase(cart2.getId(), cart2.getStoreID(), card2);
    assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
  }

  @Test
  public void testMakePurchaseInvalidCardMonth() throws Exception {
    ObjectId cartId = new ObjectId();
    Cart cart3 = new Cart(DBStoreItems.BROOKLYN_STORE.getId(), cartId);
    cartRepository.save(cart3);

    PizzaSize largeSize = DBPizzaSizes.SMALL;
    pizzaSizeService.addPizzaSize(largeSize);

    Pizza pizza = new Pizza(largeSize.getId(), true);
    cartService.addPizzaToCart(cart3, pizza);
    cartService.addPizzaToCart(cart3, pizza);

    Card card2 = new Card("Bob", "Smith", "4400616718352235", 13, 2020, CardProvider.VISA);

    final ResponseEntity<PurchaseResponse> response = purchaseApi
        .makePurchase(cart3.getId(), cart3.getStoreID(), card2);
    assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
  }
}