package io.swagger.api;

import static org.junit.Assert.assertEquals;

import io.swagger.DBPizzaSizes;
import io.swagger.DBSideItems;
import io.swagger.DBStoreItems;
import io.swagger.model.Cart;
import io.swagger.model.CartAddResponse;
import io.swagger.model.Pizza;
import io.swagger.model.PizzaSize;
import io.swagger.model.PriceResponse;
import io.swagger.repository.CartRepository;
import io.swagger.service.CartService;
import io.swagger.service.PizzaSizeService;
import org.bson.types.ObjectId;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner.class)
@TestPropertySource("classpath:/application-test.properties")
@EnableAutoConfiguration(exclude = {MongoAutoConfiguration.class, MongoDataAutoConfiguration.class})
@SpringBootTest(classes = {CartApiController.class, CartService.class})

public class CartApiControllerTest {

  @MockBean
  private CartRepository cartRepository;

  @Autowired
  private CartService cartService;

  @Autowired
  private PizzaSizeService pizzaSizeService;

  @Autowired
  private CartApi cartApi;

  @Before
  public void setUp() {
    cartRepository.deleteAll();
  }

  private Pizza setUpSmallPizza() {
    PizzaSize smallPizza = DBPizzaSizes.SMALL;
    pizzaSizeService.addPizzaSize(smallPizza);
    return new Pizza(smallPizza.getId(), true);
  }

  private Pizza setUpMediumPizza() {
    PizzaSize mediumPizza = DBPizzaSizes.MEDIUM;
    pizzaSizeService.addPizzaSize(mediumPizza);
    return new Pizza(mediumPizza.getId(), true);
  }

  private Pizza setUpLargePizza() {
    PizzaSize largePizza = DBPizzaSizes.MEDIUM;
    pizzaSizeService.addPizzaSize(largePizza);
    return new Pizza(largePizza.getId(), false);
  }

  private Cart setUpCart1() throws Exception {
    ObjectId cartId1 = new ObjectId();
    Cart cart1 = new Cart(DBStoreItems.BROOKLYN_STORE.getId(), cartId1);
    cartRepository.insert(cart1);
    Pizza smallPizza = setUpSmallPizza();
    cartService.addPizzaToCart(cart1, smallPizza);
    cartService.addSideToCart(cart1, DBSideItems.BROWNIE.getId());
    return cart1;
  }

  private Cart setUpCart2() throws Exception {
    ObjectId cartId2 = new ObjectId();
    Cart cart2 = new Cart(DBStoreItems.EASTLAKE_STORE.getId(), cartId2);
    cartRepository.insert(cart2);
    Pizza mediumPizza = setUpMediumPizza();
    cartService.addPizzaToCart(cart2, mediumPizza);
    return cart2;
  }

  private Cart setUpCart3() throws Exception {
    ObjectId cartId3 = new ObjectId();
    Cart cart3 = new Cart(DBStoreItems.STONE_WAY_STORE.getId(), cartId3);
    cartRepository.insert(cart3);
    Pizza largePizza = setUpLargePizza();
    cartService.addPizzaToCart(cart3, largePizza);
    return cart3;
  }

  @Test
  public void cartAtStoreCheck() throws Exception {
    setUpCart1();
    setUpCart2();
    setUpCart3();
    assertEquals("brooklyn", setUpCart1().getStoreID());
    assertEquals("eastlake", setUpCart2().getStoreID());
    assertEquals("stoneWay", setUpCart3().getStoreID());
  }


  @Test
  public void testGetCartItemsById() throws Exception {
    setUpCart1();
    final ResponseEntity<Cart> response = cartApi.getCartItemsById("brooklyn", "cartId1");
    assertEquals(HttpStatus.OK, response.getStatusCode());
  }

  @Test
  public void testGetPriceOfCartById() throws Exception {
    setUpCart1();
    final ResponseEntity<PriceResponse> cartPrice = cartApi
        .getPriceOfCartById("brooklyn", "cartId1");
    assertEquals(HttpStatus.OK, cartPrice);
  }

  @Test
  public void testAddPizzaToCart() throws Exception {
    setUpCart1();
    final ResponseEntity<CartAddResponse> cartResponse = cartApi
        .addPizzaToCart("brooklyn", "cartId1", setUpLargePizza());
    assertEquals(HttpStatus.OK, cartResponse);
  }

}

