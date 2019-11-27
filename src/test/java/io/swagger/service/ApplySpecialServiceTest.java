package io.swagger.service;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
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
import java.util.ArrayList;
import java.util.List;
import junit.framework.TestCase;
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

  @Autowired
  private SideService sideService;

  @Before
  public void setUp() {
    cartRepository.deleteAll();
    specialItemRepository.deleteAll();
    storeItemRepository.deleteAll();
  }

  private void setUpSpecialsRepo() {
    specialItemService.addSpecial(DBSpecialItems.BUY_1_GET_1_FREE);
    specialItemService.addSpecial(DBSpecialItems.BUY_1_PIZZA_GET_SODA_FREE);
    specialItemService.addSpecial(
        DBSpecialItems.BUY_2_LARGE_PIZZA_NO_TOPPING);
  }

  private Pizza setUpSmallPizza() {
    return new Pizza(DBPizzaSizes.SMALL.getId(), false);
  }

  private Pizza setUpLargePizza() {
    return new Pizza(DBPizzaSizes.LARGE.getId(), true);
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
    Cart validCart1 = setUpBuy1PizzaGetFreePizzaCart();
    Cart validCart2 = setUpBuy1PizzaGetFreeSodaCart();
    Cart validCart3 = setUpBuy2LargeToppinglessPizzasGet30PercentOffCart();

    assertTrue(applySpecialService.checkCartAtStore(
        validCart1.getId(), setUpBuy1PizzaGetFreePizzaCart().getStoreID()));
    assertTrue(applySpecialService.checkCartAtStore(
        validCart2.getId(), setUpBuy1PizzaGetFreeSodaCart().getStoreID()));
    assertTrue(applySpecialService.checkCartAtStore(
        validCart3.getId(), setUpBuy2LargeToppinglessPizzasGet30PercentOffCart().getStoreID()));
  }

  @Test
  public void testCheckCartAtStore_Failure() throws ToppingNotFoundException {
    Cart invalidCart1 = setUpBuy1PizzaGetFreePizzaCart();
    Cart invalidCart2 = setUpBuy1PizzaGetFreeSodaCart();
    Cart invalidCart3 = setUpBuy2LargeToppinglessPizzasGet30PercentOffCart();

    assertFalse(applySpecialService.checkCartAtStore(
        invalidCart3.getId(), setUpBuy1PizzaGetFreePizzaCart().getStoreID()));
    assertFalse(applySpecialService.checkCartAtStore(
        invalidCart1.getId(), setUpBuy1PizzaGetFreeSodaCart().getStoreID()));
    assertFalse(applySpecialService.checkCartAtStore(
        invalidCart2.getId(), setUpBuy2LargeToppinglessPizzasGet30PercentOffCart().getStoreID()));
  }

  @Test
  public void testApplyBuy1Get1Special_Success() throws ToppingNotFoundException {
    Cart validCart = setUpBuy1PizzaGetFreePizzaCart();
    ApplySpecialResponse response = applySpecialService.applyBuy1Get1Special(
        DBStoreItems.BROOKLYN_STORE.getId(),
        validCart.getId());
    Double savings = 13.99;

    assertTrue(validCart.getPizzas().size() > 1);
    assertEquals(DBSpecialItems.BUY_1_GET_1_FREE.getId(), response.getSpecialId());
    assertTrue(response.getSuccess());
    assertNull(response.getMessage());
    TestCase.assertEquals(savings, response.getSavings());
  }

  @Test
  public void testApplyBuy1Get1Special_Failure() throws ToppingNotFoundException {
    Cart invalidCart = setUpBuy1PizzaGetFreeSodaCart();
    ApplySpecialResponse response = applySpecialService.applyBuy1Get1Special(
        DBStoreItems.EASTLAKE_STORE.getId(),
        invalidCart.getId());
    Double savings = 0.0;

    assertFalse(invalidCart.getPizzas().size() > 1);
    assertFalse(response.getSuccess());
    assertEquals(Message.ERROR_FREE_PIZZA, response.getMessage());
    TestCase.assertEquals(savings, response.getSavings());
  }

  @Test
  public void testApply30PercentOffSpecial_Success() throws ToppingNotFoundException {
    Cart validCart = setUpBuy2LargeToppinglessPizzasGet30PercentOffCart();
    List<Pizza> pizzas = validCart.getPizzas();
    List<String> emptyList = new ArrayList<>();
    ApplySpecialResponse response = applySpecialService.apply30PercentOffSpecial(
        DBStoreItems.STONE_WAY_STORE.getId(),
        validCart.getId());
    Double savings = 12.59;

    assertEquals(DBPizzaSizes.LARGE.getId(), pizzas.get(0).getSizeID());
    assertEquals(DBPizzaSizes.LARGE.getId(), pizzas.get(1).getSizeID());
    assertEquals(emptyList, pizzas.get(0).getToppingIDs());
    assertEquals(emptyList, pizzas.get(1).getToppingIDs());
    assertEquals(DBSpecialItems.BUY_2_LARGE_PIZZA_NO_TOPPING.getId(), response.getSpecialId());
    assertTrue(response.getSuccess());
    assertNull(response.getMessage());
    TestCase.assertEquals(savings, response.getSavings());
  }

  @Test
  public void testApply30PercentOffSpecial_Failure() throws ToppingNotFoundException {
    Cart invalidCart = setUpBuy1PizzaGetFreeSodaCart();
    ApplySpecialResponse response = applySpecialService.apply30PercentOffSpecial(
        DBStoreItems.EASTLAKE_STORE.getId(),
        invalidCart.getId());
    Double savings = 0.0;

    assertFalse(invalidCart.getPizzas().contains(DBPizzaSizes.LARGE));
    assertFalse(response.getSuccess());
    assertEquals(Message.ERROR_DISCOUNT_30_PERCENT, response.getMessage());
    TestCase.assertEquals(savings, response.getSavings());
  }

  @Test
  public void testApplyFreeSodaSpecial_Success() throws ToppingNotFoundException {
    Cart validCart = setUpBuy1PizzaGetFreeSodaCart();
    ApplySpecialResponse response = applySpecialService.applyFreeSodaSpecial(
        DBStoreItems.EASTLAKE_STORE.getId(),
        validCart.getId());
    Double savings = 1.49;

    assertTrue(validCart.getPizzas().size() > 0);
    assertTrue(applySpecialService.hasDrink(validCart.getSides()));
    assertEquals(DBSpecialItems.BUY_1_PIZZA_GET_SODA_FREE.getId(), response.getSpecialId());
    assertTrue(response.getSuccess());
    assertNull(response.getMessage());
    TestCase.assertEquals(savings, response.getSavings());
  }

  @Test
  public void testApplyFreeSodaSpecial_Failure() throws ToppingNotFoundException {
    Cart invalidCart = setUpBuy1PizzaGetFreePizzaCart();
    ApplySpecialResponse response = applySpecialService.applyFreeSodaSpecial(
        DBStoreItems.BROOKLYN_STORE.getId(),
        invalidCart.getId());
    Double savings = 0.0;

    assertFalse(applySpecialService.hasDrink(invalidCart.getSides()));
    assertFalse(response.getSuccess());
    assertEquals(Message.ERROR_FREE_SODA, response.getMessage());
    TestCase.assertEquals(savings, response.getSavings());
  }

  @Test
  public void testApplySpecial_Success_FreePizza() throws ToppingNotFoundException {
    Cart validCart = setUpBuy1PizzaGetFreePizzaCart();
    ApplySpecialResponse response = applySpecialService.applySpecial(
        "buy1Get1Free",
        DBStoreItems.BROOKLYN_STORE.getId(),
        validCart.getId());

    assertTrue(response.getSuccess());
  }

  @Test
  public void testApplySpecial_Success_Discount30() throws ToppingNotFoundException {
    Cart validCart = setUpBuy2LargeToppinglessPizzasGet30PercentOffCart();
    ApplySpecialResponse response = applySpecialService.applySpecial(
        "buy2LargePizzaNoTopping",
        DBStoreItems.STONE_WAY_STORE.getId(),
        validCart.getId());

    assertTrue(response.getSuccess());
  }

  @Test
  public void testApplySpecial_Success_FreeSoda() throws ToppingNotFoundException {
    Cart validCart = setUpBuy1PizzaGetFreeSodaCart();
    ApplySpecialResponse response = applySpecialService.applySpecial(
        "buy1PizzaGetSodaFree",
        DBStoreItems.EASTLAKE_STORE.getId(),
        validCart.getId());

    System.out.println(DBSpecialItems.BUY_1_PIZZA_GET_SODA_FREE.getId());
    System.out.println(DBStoreItems.EASTLAKE_STORE.getId());
    System.out.println(validCart.getId());
    System.out.println(validCart.getPizzas());
    System.out.println(validCart.getSides());
    System.out.println(validCart.isSpecialApplied());
    System.out.println(validCart.getStoreID());
    System.out.println(validCart.getTotalAmount());
    System.out.println(applySpecialService.applySpecial(
        DBSpecialItems.BUY_1_PIZZA_GET_SODA_FREE.getId(),
        DBStoreItems.EASTLAKE_STORE.getId(),
        validCart.getId()));

    assertTrue(response.getSuccess());
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
