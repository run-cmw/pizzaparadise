package io.swagger.api;

import static org.junit.Assert.assertEquals;

import io.swagger.DBPizzaSizes;
import io.swagger.DBSideItems;
import io.swagger.DBStoreItems;
import io.swagger.DBToppingItems;
import io.swagger.exceptions.ToppingNotFoundException;
import io.swagger.model.Cart;
import io.swagger.model.CartAddResponse;
import io.swagger.model.Pizza;
import io.swagger.model.PizzaSize;
import io.swagger.model.PriceResponse;
import io.swagger.model.SideItem;
import io.swagger.model.ToppingItem;
import io.swagger.repository.CartRepository;
import io.swagger.service.CartService;
import io.swagger.service.PizzaSizeService;
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
public class CartApiControllerTest {

  @Autowired
  private CartApi cartApi;
  @Autowired
  private CartRepository cartRepository;
  @Autowired
  private CartService cartService;
  @Autowired
  private PizzaSizeService pizzaSizeService;

  @Before
  public void setUp() {
    cartRepository.deleteAll();
  }

  @Test
  public void testGetCartItemsById() throws Exception {
    ObjectId cartId = new ObjectId();
    Cart cart = new Cart(DBStoreItems.BROOKLYN_STORE.getId(), cartId);
    cartRepository.save(cart);

    PizzaSize largeSize = DBPizzaSizes.SMALL;
    pizzaSizeService.addPizzaSize(largeSize);

    Pizza pizza = new Pizza(largeSize.getId(), false);
    cartService.addPizzaToCart(cart, pizza);
    cartService.addPizzaToCart(cart, pizza);

    final ResponseEntity<Cart> response = cartApi
        .getCartItemsById(DBStoreItems.BROOKLYN_STORE.getId(), cart.getId());
    assertEquals(HttpStatus.OK, response.getStatusCode());

    Cart cart2 = new Cart(DBStoreItems.EASTLAKE_STORE.getId(), cartId);

    final ResponseEntity<Cart> response2 = cartApi
        .getCartItemsById(DBStoreItems.EASTLAKE_STORE.getId(), cart2.getId());
    assertEquals(HttpStatus.NOT_FOUND, response2.getStatusCode());

  }

  @Test
  public void testGetPriceOfCartById() throws ToppingNotFoundException {
    ObjectId cartId = new ObjectId();
    Cart cart = new Cart(DBStoreItems.EASTLAKE_STORE.getId(), cartId);
    cartRepository.save(cart);

    PizzaSize mediumSize = DBPizzaSizes.MEDIUM;
    pizzaSizeService.addPizzaSize(mediumSize);

    Pizza pizza = new Pizza(mediumSize.getId(), true);
    cartService.addPizzaToCart(cart, pizza);
    cartService.addPizzaToCart(cart, pizza);

    final ResponseEntity<PriceResponse> cartPrice = cartApi
        .getPriceOfCartById(DBStoreItems.EASTLAKE_STORE.getId(), cart.getId());
    assertEquals(HttpStatus.OK, cartPrice.getStatusCode());

    Cart cart2 = new Cart(DBStoreItems.BROOKLYN_STORE.getId(), cartId);

    final ResponseEntity<PriceResponse> cartPrice2 = cartApi
        .getPriceOfCartById(DBStoreItems.BROOKLYN_STORE.getId(), cart2.getId());
    assertEquals(HttpStatus.NOT_FOUND, cartPrice2.getStatusCode());
  }

  @Test
  public void testAddPizzaToCart() throws Exception {
    ObjectId cartId = new ObjectId();
    Cart cart = new Cart(DBStoreItems.STONE_WAY_STORE.getId(), cartId);
    cartRepository.save(cart);

    PizzaSize smallSize = DBPizzaSizes.SMALL;
    pizzaSizeService.addPizzaSize(smallSize);

    Pizza pizza = new Pizza(smallSize.getId(), true);
    cartService.addPizzaToCart(cart, pizza);
    cartService.addPizzaToCart(cart, pizza);

    final ResponseEntity<CartAddResponse> cartResponse = cartApi
        .addPizzaToCart(cart.getStoreID(), cart.getId(), pizza);
    assertEquals(HttpStatus.OK, cartResponse.getStatusCode());

    ObjectId cartId2 = new ObjectId();
    Cart cart2 = new Cart(DBStoreItems.EASTLAKE_STORE.getId(), cartId2);
    Pizza pizza2 = new Pizza(smallSize.getId(), false);

    final ResponseEntity<CartAddResponse> cartResponse2 = cartApi
        .addPizzaToCart(cart.getStoreID(), cart2.getId(), pizza2);
    assertEquals(HttpStatus.BAD_REQUEST, cartResponse2.getStatusCode());

    Pizza pizza3 = new Pizza(DBPizzaSizes.MEDIUM.getId(), true);

    final ResponseEntity<CartAddResponse> cartResponse3 = cartApi
        .addPizzaToCart(cart2.getStoreID(), cart.getId(), pizza3);
    assertEquals(HttpStatus.NOT_FOUND, cartResponse3.getStatusCode());

    ObjectId cartId4 = new ObjectId();
    Cart cart4 = new Cart(DBStoreItems.STONE_WAY_STORE.getId(), cartId4);
    Pizza pizza4 = new Pizza(DBPizzaSizes.LARGE.getId(), true);
    ToppingItem pepperoni = new ToppingItem(DBToppingItems.PEPPERONI.getId(),
        DBToppingItems.PEPPERONI.getToppingName(), DBToppingItems.PEPPERONI.getToppingType(),
        DBToppingItems.PEPPERONI.getToppingSmallPrice(),
        DBToppingItems.PEPPERONI.getToppingMediumPrice(),
        DBToppingItems.PEPPERONI.getToppingLargePrice(),
        DBToppingItems.PEPPERONI.getToppingGluten());
    pizza4.getToppingIDs().add(pepperoni.getId());

    ObjectId cartId5 = new ObjectId();
    Cart cart5 = new Cart(DBStoreItems.STONE_WAY_STORE.getId(), cartId5);
    cartRepository.save(cart5);

    PizzaSize largeSize = DBPizzaSizes.LARGE;
    pizzaSizeService.addPizzaSize(largeSize);

    Pizza pizza5 = new Pizza(largeSize.getId(), true);
    cartService.addPizzaToCart(cart, pizza5);
    pizza5.getToppingIDs().add(pepperoni.getId());
    pizza5.getToppingIDs().add(pepperoni.getId());
    pizza5.getToppingIDs().add(pepperoni.getId());
    pizza5.getToppingIDs().add(pepperoni.getId());
    pizza5.getToppingIDs().add(pepperoni.getId());

    cart5.getPizzas().add(pizza5);

    final ResponseEntity<CartAddResponse> cartResponse5 = cartApi
        .addPizzaToCart(cart4.getStoreID(), cart5.getId(), pizza5);
    assertEquals(HttpStatus.BAD_REQUEST, cartResponse5.getStatusCode());

    ObjectId cartId6 = new ObjectId();
    Cart cart6 = new Cart(DBStoreItems.STONE_WAY_STORE.getId(), cartId6);
    cartRepository.save(cart6);

    PizzaSize largeSize6 = DBPizzaSizes.LARGE;
    pizzaSizeService.addPizzaSize(largeSize6);
    Pizza pizza6 = new Pizza(largeSize6.getId(), true);
    cartService.addPizzaToCart(cart, pizza6);
    pizza6.getToppingIDs().add(pepperoni.getId());
    pizza6.getToppingIDs().add(pepperoni.getId());
    pizza6.getToppingIDs().add(pepperoni.getId());

    final ResponseEntity<CartAddResponse> cartResponse6 = cartApi
        .addPizzaToCart(cart6.getStoreID(), cart6.getId(), pizza6);
    assertEquals(HttpStatus.BAD_REQUEST, cartResponse6.getStatusCode());

  }


  @Test
  public void testAddSideToCart() throws Exception {
    ObjectId cartId = new ObjectId();
    Cart cart = new Cart(DBStoreItems.BROOKLYN_STORE.getId(), cartId);
    cartRepository.save(cart);

    SideItem side = DBSideItems.BROWNIE;
    SideItem brownie = new SideItem(side.getName(), side.getName(), side.getPrice(),
        side.getType());

    final ResponseEntity<CartAddResponse> cartResponse = cartApi
        .addSideToCart(cart.getStoreID(), cart.getId(), brownie.getId());
    assertEquals(HttpStatus.NOT_FOUND, cartResponse.getStatusCode());

//    ObjectId cartId2= new ObjectId();
//    Cart cart2 = new Cart(DBStoreItems.BROOKLYN_STORE.getId(), cartId2);
//
//    SideItem applePie = new SideItem("applePie", "apple pie", 6.99, "dessert");
//    final ResponseEntity<CartAddResponse> cartResponse2 = cartApi
//        .addSideToCart(cart.getStoreID(), cart2.getId(), applePie.getId());
//    assertEquals(HttpStatus.NOT_FOUND, cartResponse2.getStatusCode());

  }

  @Test
  public void deleteCart() throws Exception {
    ObjectId cartId = new ObjectId();
    Cart cart = new Cart(DBStoreItems.STONE_WAY_STORE.getId(), cartId);
    cartRepository.save(cart);

    PizzaSize smallSize = DBPizzaSizes.SMALL;
    pizzaSizeService.addPizzaSize(smallSize);

    Pizza pizza = new Pizza(smallSize.getId(), true);
    cartService.addPizzaToCart(cart, pizza);
    cartService.addPizzaToCart(cart, pizza);

    final HttpStatus deleteCartResponse = cartApi.deleteCart(cart.getStoreID(), cart.getId());
    assertEquals(HttpStatus.NO_CONTENT, deleteCartResponse);

    ObjectId cartId2 = new ObjectId();
    Cart cart2 = new Cart(DBStoreItems.STONE_WAY_STORE.getId(), cartId2);

    final HttpStatus deleteCartResponse2 = cartApi.deleteCart(cart2.getStoreID(), cart2.getId());
    assertEquals(HttpStatus.NOT_FOUND, deleteCartResponse2);
  }

  @Test
  public void deleteSideFromCart() throws Exception {
    ObjectId cartId = new ObjectId();
    Cart cart = new Cart(DBStoreItems.BROOKLYN_STORE.getId(), cartId);
    cartRepository.save(cart);

    SideItem side = DBSideItems.BROWNIE;
    SideItem brownie = new SideItem(side.getName(), side.getName(), side.getPrice(),
        side.getType());

    final HttpStatus deleteSideResponse = cartApi
        .deleteSideFromCart(cart.getStoreID(), cart.getId(), brownie.getId());
    assertEquals(HttpStatus.BAD_REQUEST, deleteSideResponse);

    ObjectId cartId2 = new ObjectId();
    Cart cart2 = new Cart(DBStoreItems.STONE_WAY_STORE.getId(), cartId2);

    final HttpStatus deleteSideResponse2 = cartApi
        .deleteSideFromCart(cart2.getStoreID(), cart2.getId(), brownie.getId());
    assertEquals(HttpStatus.NOT_FOUND, deleteSideResponse2);
  }


  @Test
  public void deletePizzaFromCart() throws Exception {
    ObjectId cartId = new ObjectId();
    Cart cart = new Cart(DBStoreItems.STONE_WAY_STORE.getId(), cartId);
    cartRepository.save(cart);

    PizzaSize smallSize = DBPizzaSizes.SMALL;
    pizzaSizeService.addPizzaSize(smallSize);

    Pizza pizza = new Pizza(smallSize.getId(), true);
    cartService.addPizzaToCart(cart, pizza);
    cartService.addPizzaToCart(cart, pizza);

    final HttpStatus deletePizzaResponse = cartApi
        .deletePizzaFromCart(cart.getStoreID(), cart.getId(), pizza);
    assertEquals(HttpStatus.NO_CONTENT, deletePizzaResponse);

    ObjectId cartId2 = new ObjectId();
    Cart cart2 = new Cart(DBStoreItems.STONE_WAY_STORE.getId(), cartId2);

    final HttpStatus deletePizzaResponse2 = cartApi
        .deletePizzaFromCart(cart2.getStoreID(), cart2.getId(), pizza);
    assertEquals(HttpStatus.NOT_FOUND, deletePizzaResponse2);

    Pizza pizza2 = new Pizza(smallSize.getId(), false);

    final HttpStatus deletePizzaResponse3 = cartApi
        .deletePizzaFromCart(cart.getStoreID(), cart.getId(), pizza2);
    assertEquals(HttpStatus.BAD_REQUEST, deletePizzaResponse3);

  }

}

