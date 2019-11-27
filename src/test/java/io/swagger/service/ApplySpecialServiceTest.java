package io.swagger.service;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;

import io.swagger.DBPizzaSizes;
import io.swagger.DBSideItems;
import io.swagger.DBSpecialItems;
import io.swagger.DBStoreItems;
import io.swagger.Message;
import io.swagger.exceptions.ToppingNotFoundException;
import io.swagger.model.ApplySpecialResponse;
import io.swagger.model.Cart;
import io.swagger.model.Pizza;
import io.swagger.model.SpecialItem;
import io.swagger.repository.CartRepository;
import io.swagger.repository.SpecialItemRepository;
import io.swagger.repository.StoreItemRepository;
import org.bson.types.ObjectId;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner.class)
@TestPropertySource("classpath:/application-test.properties")
@EnableAutoConfiguration
@SpringBootTest
public class ApplySpecialServiceTest {

  @Autowired
  private CartRepository cartRepository;

  @Autowired
  private SpecialItemRepository specialItemRepository;

  @Autowired
  private StoreItemRepository storeItemRepository;

  @Autowired
  private CartService cartService;

  @Autowired
  private SpecialItemService specialItemService;

  @Autowired
  private ApplySpecialService applySpecialService;

  @Before
  public void setUp() {
    cartRepository.deleteAll();
    specialItemRepository.deleteAll();
    storeItemRepository.deleteAll();
  }

  private void setUpSpecialsRepo() {
    SpecialItem special1 = specialItemService.addSpecial(DBSpecialItems.BUY_1_GET_1_FREE);
    SpecialItem special2 = specialItemService.addSpecial(DBSpecialItems.BUY_1_PIZZA_GET_SODA_FREE);
    SpecialItem special3 = specialItemService.addSpecial(
        DBSpecialItems.BUY_2_LARGE_PIZZA_NO_TOPPING);
  }

  private Pizza setUpSmallPizza() {
    Pizza pizza = new Pizza(DBPizzaSizes.SMALL.getId(), false);
    return pizza;
  }

  private Pizza setUpLargePizza() {
    Pizza pizza = new Pizza(DBPizzaSizes.LARGE.getId(), true);
    return pizza;
  }

  private Cart setUpBuy1PizzaGetFreePizzaCart() throws ToppingNotFoundException {
    ObjectId cartId = new ObjectId();
    Cart cart = new Cart(DBStoreItems.BROOKLYN_STORE.getId(), cartId);
    Pizza smallPizza = setUpSmallPizza();
    cartRepository.insert(cart);
    cartService.addPizzaToCart(cart, smallPizza);
    cartService.addPizzaToCart(cart, smallPizza);
    return cart;
  }

  private Cart setUpBuy2LargeToppinglessPizzasGet30PercentOffCart()
      throws ToppingNotFoundException {
    ObjectId cartId = new ObjectId();
    Cart cart = new Cart(DBStoreItems.STONE_WAY_STORE.getId(), cartId);
    Pizza largePizza = setUpLargePizza();
    cartRepository.insert(cart);
    cartService.addPizzaToCart(cart, largePizza);
    cartService.addPizzaToCart(cart, largePizza);
    return cart;
  }

  private Cart setUpBuy1PizzaGetFreeSodaCart() throws ToppingNotFoundException {
    ObjectId cartId = new ObjectId();
    Cart cart = new Cart(DBStoreItems.EASTLAKE_STORE.getId(), cartId);
    cartRepository.insert(cart);
    cartService.addPizzaToCart(cart, setUpSmallPizza());
    cartService.addSideToCart(cart, DBSideItems.SMALL_PEACH_CRUSH.getId());
    return cart;
  }

  @Test
  public void testCheckSpecial_True() {
    setUpSpecialsRepo();
    assertTrue(specialItemRepository.existsById(DBSpecialItems.BUY_1_GET_1_FREE.getId()));
  }

  @Test
  public void testCheckSpecial_False() {
    setUpSpecialsRepo();
    final SpecialItem nonexistentSpecial = new SpecialItem("noSpecial",
        "no special", "");
    assertFalse(specialItemRepository.existsById(nonexistentSpecial.getId()));
  }

  @Test
  public void testCheckCartAtStore_Success() throws ToppingNotFoundException {
    setUpBuy1PizzaGetFreePizzaCart();
    setUpBuy1PizzaGetFreeSodaCart();
    setUpBuy2LargeToppinglessPizzasGet30PercentOffCart();
    assertEquals("brooklyn", setUpBuy1PizzaGetFreePizzaCart().getStoreID());
    assertEquals("eastlake", setUpBuy1PizzaGetFreeSodaCart().getStoreID());
    assertEquals("stoneWay", setUpBuy2LargeToppinglessPizzasGet30PercentOffCart().
        getStoreID());
  }

  @Test
  public void testCheckCartAtStore_Failure() throws ToppingNotFoundException {
    assertNotEquals("eastlake", setUpBuy1PizzaGetFreePizzaCart().getStoreID());
  }

  @Test
  public void testApplyBuy1Get1Special_Success() throws ToppingNotFoundException {
    Cart validCart = setUpBuy1PizzaGetFreePizzaCart();

    final String responseJson = "ApplySpecialResponse{"
        + "specialId='buy1Get1Free', "
        + "success='true', "
        + "message='null', "
        + "savings='13.99'"
        + "}";

    assertEquals(responseJson,
        applySpecialService.applyBuy1Get1Special(
            DBStoreItems.BROOKLYN_STORE.getId(),
            validCart.getId()).toString());
  }

  @Test
  public void testApplyBuy1Get1Special_Failure() throws ToppingNotFoundException {
    Cart invalidCart = setUpBuy1PizzaGetFreeSodaCart();

    final String responseJson = "ApplySpecialResponse{"
        + "specialId='null', "
        + "success='false', "
        + "message='"
        + Message.ERROR_FREE_PIZZA
        + "', "
        + "savings='0.0'"
        + "}";

    assertEquals(responseJson,
        applySpecialService.applyBuy1Get1Special(
            DBStoreItems.EASTLAKE_STORE.getId(),
            invalidCart.getId()).toString());
  }

  @Test
  public void testApply30PercentOffSpecial_Success() throws ToppingNotFoundException {
    Cart validCart = setUpBuy2LargeToppinglessPizzasGet30PercentOffCart();

    final String responseJson = "ApplySpecialResponse{"
        + "specialId='buy2LargePizzaNoTopping', "
        + "success='true', "
        + "message='null', "
        + "savings='12.59'"
        + "}";

    assertEquals(responseJson,
        applySpecialService.apply30PercentOffSpecial(
            DBStoreItems.STONE_WAY_STORE.getId(),
            validCart.getId()).toString());
  }

  @Test
  public void testApply30PercentOffSpecial_Failure() throws ToppingNotFoundException {
    Cart invalidCart = setUpBuy1PizzaGetFreeSodaCart();

    final String responseJson = "ApplySpecialResponse{"
        + "specialId='null', "
        + "success='false', "
        + "message='"
        + Message.ERROR_DISCOUNT_30_PERCENT
        + "', "
        + "savings='0.0'"
        + "}";

    assertEquals(responseJson,
        applySpecialService.apply30PercentOffSpecial(
            DBStoreItems.EASTLAKE_STORE.getId(),
            invalidCart.getId()).toString());
  }

  @Test
  public void testApplyFreeSodaSpecial_Success() throws ToppingNotFoundException {
    Cart validCart = setUpBuy1PizzaGetFreeSodaCart();

    final String responseJson = "ApplySpecialResponse{"
        + "specialId='buy1PizzaGetSodaFree', "
        + "success='true', "
        + "message='null', "
        + "savings='1.49'"
        + "}";

    assertEquals(responseJson,
        applySpecialService.applyFreeSodaSpecial(
            DBStoreItems.EASTLAKE_STORE.getId(),
            validCart.getId()).toString());
  }

  @Test
  public void testApplyFreeSodaSpecial_Failure() throws ToppingNotFoundException {
    Cart invalidCart = setUpBuy1PizzaGetFreePizzaCart();

    final String responseJson = "ApplySpecialResponse{"
        + "specialId='null', "
        + "success='false', "
        + "message='"
        + Message.ERROR_FREE_SODA
        + "', "
        + "savings='0.0'"
        + "}";

    assertEquals(responseJson,
        applySpecialService.applyFreeSodaSpecial(
            DBStoreItems.BROOKLYN_STORE.getId(),
            invalidCart.getId()).toString());
  }

  @Test
  public void testApplySpecial_Success_FreePizza() throws ToppingNotFoundException {
    Cart validCart = setUpBuy1PizzaGetFreePizzaCart();
    ApplySpecialResponse response = applySpecialService.applySpecial(
        DBSpecialItems.BUY_1_GET_1_FREE.getId(),
        DBStoreItems.BROOKLYN_STORE.getId(),
        validCart.getId());
    System.out.println(DBSpecialItems.BUY_1_GET_1_FREE.getId());
    System.out.println(DBStoreItems.BROOKLYN_STORE.getId());
    System.out.println(validCart.getId());

//    assertTrue(response.getSuccess());
  }

  @Test
  public void testApplySpecial_Success_Discount30() throws ToppingNotFoundException {
    Cart validCart = setUpBuy2LargeToppinglessPizzasGet30PercentOffCart();
    ApplySpecialResponse response = applySpecialService.applySpecial(
        DBSpecialItems.BUY_2_LARGE_PIZZA_NO_TOPPING.getId(),
        DBStoreItems.STONE_WAY_STORE.getId(),
        validCart.getId());

    assertTrue(response.getSuccess());
  }

  @Test
  public void testApplySpecial_Success_FreeSoda() throws ToppingNotFoundException {
    Cart cart = setUpBuy1PizzaGetFreeSodaCart();
    ApplySpecialResponse response = applySpecialService.applySpecial(
        DBSpecialItems.BUY_1_PIZZA_GET_SODA_FREE.getId(),
        DBStoreItems.EASTLAKE_STORE.getId(),
        cart.getId());

    System.out.println(response.toString());
//    assertTrue(response.getSuccess());
  }

  @Test
  public void testApplySpecial_Failure_InvalidSpecial() throws ToppingNotFoundException {
    Cart validCart = setUpBuy2LargeToppinglessPizzasGet30PercentOffCart();
    String invalidSpecialId = "badId";

    assertNull(applySpecialService.applySpecial(
        invalidSpecialId,
        DBStoreItems.STONE_WAY_STORE.getId(),
        validCart.getId()));
  }

  @Test
  public void testApplySpecial_Failure_CartNotAtStore() throws ToppingNotFoundException {
    Cart validCart = setUpBuy2LargeToppinglessPizzasGet30PercentOffCart();
    String invalidStore = DBStoreItems.BROOKLYN_STORE.getId();

    assertNull(applySpecialService.applySpecial(
        DBSpecialItems.BUY_2_LARGE_PIZZA_NO_TOPPING.getId(),
        invalidStore,
        validCart.getId()));
  }

  @Test
  public void testApplySpecial_Failure_SpecialAlreadyApplied() throws ToppingNotFoundException {
    Cart validCart = setUpBuy2LargeToppinglessPizzasGet30PercentOffCart();
    validCart.setSpecialApplied(true);

    assertNull(applySpecialService.applySpecial(
        DBSpecialItems.BUY_2_LARGE_PIZZA_NO_TOPPING.getId(),
        DBStoreItems.STONE_WAY_STORE.getId(),
        validCart.getId()));
  }
}
