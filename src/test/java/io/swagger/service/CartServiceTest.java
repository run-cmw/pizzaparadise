package io.swagger.service;

import static org.junit.Assert.assertEquals;

import io.swagger.model.Cart;
import io.swagger.model.CartAddResponse;
import io.swagger.model.Pizza;
import io.swagger.model.PizzaSize;
import io.swagger.model.SideItem;
import io.swagger.model.StoreItem;
import io.swagger.model.ToppingItem;
import io.swagger.repository.CartRepository;
import io.swagger.repository.PizzaSizeRepository;
import io.swagger.repository.SideItemRepository;
import io.swagger.repository.StoreItemRepository;
import io.swagger.repository.ToppingItemRepository;
import java.util.ArrayList;
import java.util.List;
import org.bson.types.ObjectId;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@TestPropertySource(locations = "classpath:/application-test.properties")
public class CartServiceTest {

  @Autowired
  public CartService cartService;

  @Autowired
  public CartRepository cartRepo;

  @Autowired
  public StoreItemRepository storeRepo;

  @Autowired
  private ToppingItemRepository toppingRepo;

  @Autowired
  private PizzaSizeRepository sizeRepo;

  @Autowired
  private SideItemRepository sideRepo;

  @Test
  public void getCartByIdTest() {
    cartRepo.deleteAll();
    ObjectId cartId1 = new ObjectId();
    String storeId1 = "eastlake";
    Cart cart1 = new Cart(storeId1, cartId1);


    assertEquals(0, cartRepo.count());
    cartRepo.insert(cart1);
    assertEquals(1, cartRepo.count());
    Assert.assertEquals(cartService.getCartItemsById(storeId1, cartId1.toString()), cart1);
    Assert.assertEquals(cartService.getCartItemsById(storeId1, cartId1.toString()).getId(), cartId1.toString());
    Assert.assertEquals(cartService.getCartItemsById(storeId1, cartId1.toString()).getStoreID(), storeId1);
    Assert.assertEquals(cartService.getCartItemsById(storeId1, cartId1.toString()).isSpecialApplied(), false);
    Assert.assertEquals(cartService.getCartItemsById(storeId1, cartId1.toString()).getTotalAmount(), (Double) 0.0);
    Assert.assertEquals(cartService.getCartItemsById(storeId1, cartId1.toString()).getPizzas(), new ArrayList<>());
    Assert.assertEquals(cartService.getCartItemsById(storeId1, cartId1.toString()).getSides(), new ArrayList<>());

    String store2 = "brooklyn";
    ObjectId cartId2 = new ObjectId();
    Assert.assertEquals(cartService.getCartItemsById(store2, cartId1.toString()), null);
    Assert.assertEquals(cartService.getCartItemsById(store2, "hello"), null);

    sideRepo.deleteAll();
    SideItem side1 = new SideItem("16OzWater", "16 oz water", 1.49, "drink");
    sideRepo.insert(side1);
    Cart cart2 = new Cart(store2, cartId2);
    cart2.setSpecialApplied(true);
    cart2.setTotalAmount(1.49);
    cart2.getSides().add("16OzWater");
    cartRepo.insert(cart2);
    Assert.assertEquals(cartRepo.count(), 2);
    Assert.assertEquals(cartService.getCartItemsById(store2, cartId2.toString()), cart2);
    Assert.assertEquals(cartService.getCartItemsById(store2, cartId2.toString()).toString(), cart2.toString());
    Assert.assertEquals(cartService.getCartItemsById(store2, cartId2.toString()).getId(), cartId2.toString());
    Assert.assertEquals(cartService.getCartItemsById(store2, cartId2.toString()).getStoreID(), store2);
    Assert.assertEquals(cartService.getCartItemsById(store2, cartId2.toString()).isSpecialApplied(), true);
    Assert.assertEquals(cartService.getCartItemsById(store2, cartId2.toString()).getTotalAmount(), (Double) 1.49);
    Assert.assertEquals(cartService.getCartItemsById(store2, cartId2.toString()).getPizzas(), new ArrayList<>());
    Assert.assertEquals(cartService.getCartItemsById(store2, cartId2.toString()).getSides().size(), 1);
    Assert.assertEquals(cartService.getCartItemsById(store2, cartId2.toString()).getSides().contains("16OzWater"), true);
    Assert.assertNotEquals(cartService.getCartItemsById(store2, cartId2.toString()), cart1);

  }

  @Test
  public void addPizzaToCartTest() {
    cartRepo.deleteAll();
    ObjectId cartId1 = new ObjectId();
    String storeId1 = "moonglow";
    Cart cart1 = new Cart(storeId1, cartId1);

    sizeRepo.deleteAll();;
    PizzaSize size1 = new PizzaSize("medium", "Medium", "13", 17.99);
    PizzaSize size2 = new PizzaSize("large", "Large", "16", 20.99);

    sizeRepo.save(size1);
    sizeRepo.save(size2);

    toppingRepo.deleteAll();
    ToppingItem toppingItem1 = new ToppingItem("bacon1", "bacon", "meat", 2.50, 2.75, 3.00, "gluten");
    ToppingItem toppingItem2 = new ToppingItem("broccoli1", "broccoli", "vegetable", 2.00, 2.25,
        2.50, "non-gluten");
    toppingRepo.save(toppingItem1);
    toppingRepo.save(toppingItem2);


    cartRepo.insert(cart1);
    storeRepo.deleteAll();
    StoreItem store1 = new StoreItem("moonglow", "999 Moonglow Ave", "Emeryville", "California", "70802", true);
    StoreItem store2 = new StoreItem("plank", "777 Plank Rd", "Baton Rouge", "Louisiana", "98105", false);
    storeRepo.save(store1);
    storeRepo.save(store2);

    CartAddResponse response1 = cartService.addPizzaToCart("moonglow", cartId1.toString(), "medium", false, "bacon1", "broccoli1", null, null);
    List<String> items1 = new ArrayList<>();
    items1.add("medium");
    items1.add("glutenFree");
    items1.add("bacon1");
    items1.add("broccoli1");
    CartAddResponse response2 = new CartAddResponse(true, items1, cartId1.toString(), "moonglow", "items are successfully added.");
    Assert.assertEquals(response1, response2);


    CartAddResponse response3 = cartService.addPizzaToCart("plank", cartId1.toString(), "medium", false, "bacon1", "broccoli1", null, null);
    CartAddResponse response4 = new CartAddResponse(false, null, null, null, "This store cannot provide this gluten preference.");
    Assert.assertEquals(response3.toString(), response4.toString());

    CartAddResponse response5 = cartService.addPizzaToCart("plank", "NoCardId", "large", true, "bacon1", null, null, null);
    List<String> items2 = new ArrayList<>();
    items2.add("large");
    items2.add("gluten");
    items2.add("bacon1");
    Assert.assertEquals(response5.getSuccess(), true);
    Assert.assertEquals(response5.getStoreID(), "plank");
    Assert.assertNotEquals(response5.getCartID(), "NoCartId");
    Assert.assertEquals(response5.getItems(), items2);
    Assert.assertEquals(response5.getItems().size(), 3);
    Assert.assertEquals(response5.getMessage(), "items are successfully added.");

  }

  @Test
  public void addToppingToPizzaTest() {
    cartRepo.deleteAll();
    toppingRepo.deleteAll();
    ToppingItem toppingItem1 = new ToppingItem("bacon1", "bacon", "meat", 2.50, 2.75, 3.00, "gluten");
    ToppingItem toppingItem2 = new ToppingItem("broccoli1", "broccoli", "vegetable", 2.00, 2.25,
        2.50, "non-gluten");

    Pizza pizza = new Pizza("medium", false);
    Assert.assertEquals(pizza.getToppingIDs().size(), 0);
    toppingRepo.insert(toppingItem1);
    toppingRepo.insert(toppingItem2);
    cartService.addToppingToPizza(pizza, "bacon1", new ArrayList<>());
    Assert.assertEquals(pizza.getToppingIDs().size(), 1);
    Assert.assertEquals(pizza.getToppingIDs().contains("bacon1"), true);

  }

  @Test
  public void getPizzasPriceTest() {
    cartRepo.deleteAll();
    ObjectId cartId1 = new ObjectId();
    String storeId1 = "eastlake";
    Cart cart1 = new Cart(storeId1, cartId1);
    Assert.assertEquals(cartService.getPizzasPrice(cart1), (Double) 0.0);
    toppingRepo.deleteAll();
    ToppingItem toppingItem1 = new ToppingItem("bacon1", "bacon", "meat", 2.50, 2.75, 3.00, "gluten");
    ToppingItem toppingItem2 = new ToppingItem("broccoli1", "broccoli", "vegetable", 2.00, 2.25,
        2.50, "non-gluten");

    sizeRepo.deleteAll();
    PizzaSize size1 = new PizzaSize("medium", "Medium", "13", 17.99);
    sizeRepo.insert(size1);

    Pizza pizza1 = new Pizza("medium", false);
    Assert.assertEquals(pizza1.getToppingIDs().size(), 0);
    Assert.assertEquals(pizza1.getPrice(), (Double) 0.0);
    toppingRepo.insert(toppingItem1);
    toppingRepo.insert(toppingItem2);
    cartService.addToppingToPizza(pizza1, "bacon1", new ArrayList<>());
    cartService.addToppingToPizza(pizza1, "broccoli1", new ArrayList<>());
    Assert.assertEquals(pizza1.getToppingIDs().size(), 2);

    pizza1 = cartService.getPizzaPrice(pizza1);
    cart1.getPizzas().add(pizza1);
    Assert.assertEquals(cartService.getPizzasPrice(cart1), (Double) 22.99);
  }


  @Test
  public void getPizzaPriceTest() {
    cartRepo.deleteAll();
    ObjectId cartId1 = new ObjectId();
    String storeId1 = "eastlake";
    Cart cart1 = new Cart(storeId1, cartId1);
    toppingRepo.deleteAll();
    ToppingItem toppingItem1 = new ToppingItem("bacon1", "bacon", "meat", 2.50, 2.75, 3.00, "gluten");
    ToppingItem toppingItem2 = new ToppingItem("broccoli1", "broccoli", "vegetable", 2.00, 2.25,
        2.50, "non-gluten");

    sizeRepo.deleteAll();;
    PizzaSize size1 = new PizzaSize("medium", "Medium", "13", 17.99);
    sizeRepo.insert(size1);

    Pizza pizza = new Pizza("medium", false);
    Assert.assertEquals(pizza.getToppingIDs().size(), 0);
    Assert.assertEquals(pizza.getPrice(), (Double) 0.0);
    toppingRepo.insert(toppingItem1);
    toppingRepo.insert(toppingItem2);
    cartService.addToppingToPizza(pizza, "bacon1", new ArrayList<>());
    cartService.addToppingToPizza(pizza, "broccoli1", new ArrayList<>());
    Assert.assertEquals(pizza.getToppingIDs().size(), 2);

    pizza = cartService.getPizzaPrice(pizza);
    Assert.assertEquals(pizza.getPrice(), (Double) 22.99);

  }

  @Test
  public void getPizzaToppingPriceTest() {
    toppingRepo.deleteAll();
    ToppingItem toppingItem1 = new ToppingItem("bacon1", "bacon", "meat", 2.50, 2.75, 3.00, "gluten");
    ToppingItem toppingItem2 = new ToppingItem("broccoli1", "broccoli", "vegetable", 2.00, 2.25,
        2.50, "non-gluten");
    toppingRepo.insert(toppingItem1);
    toppingRepo.insert(toppingItem2);

    List<String> toppingId = new ArrayList<>();
    Double price = cartService.getPizzaToppingPrice("small", toppingId);
    Assert.assertEquals(price, (Double) 0.00);
    toppingId.add("bacon1");
    toppingId.add("broccoli1");
    price = cartService.getPizzaToppingPrice("small", toppingId);
    Assert.assertEquals(price, (Double) 4.50);

    price = cartService.getPizzaToppingPrice("medium", toppingId);
    Assert.assertEquals(price, (Double) 5.0);

    price = cartService.getPizzaToppingPrice("large", toppingId);
    Assert.assertEquals(price, (Double) 5.50);

  }

  @Test
  public void getSidePriceTest() {
    cartRepo.deleteAll();
    ObjectId cartId1 = new ObjectId();
    String storeId1 = "brooklyn";
    Cart cart1 = new Cart(storeId1, cartId1);

    Assert.assertEquals(cartService.getSidePrice(cart1), (Double) 0.0);

    sideRepo.deleteAll();
    SideItem side1 = new SideItem("16OzWater", "16 oz water", 1.49, "drink");
    sideRepo.insert(side1);

    cart1.getSides().add("16OzWater");
    Assert.assertEquals(cartService.getSidePrice(cart1), (Double) 1.49);

    SideItem side2 = new SideItem("snack", "Snack", 5.99, "dessert");
    sideRepo.insert(side2);

    cart1.getSides().add("snack");
    Assert.assertEquals(cart1.getSides().size(), 2);
    Assert.assertEquals(cartService.getSidePrice(cart1), (Double) 7.48);
  }

  @Test
  public void addSideToCartTest() {
    cartRepo.deleteAll();
    ObjectId cartId1 = new ObjectId();
    String storeId1 = "stoneWay";
    Cart cart1 = new Cart(storeId1, cartId1);
    cartRepo.insert(cart1);

    List<String> sides = new ArrayList<>();
    sides.add("16OzWater");
    CartAddResponse response1 = new CartAddResponse(true, sides, cartId1.toString(), storeId1,"Side item is successfully added");
    Assert.assertEquals(cartService.addSideToCart(storeId1, cartId1.toString(), "16OzWater"), response1);

    CartAddResponse response2 = cartService.addSideToCart(storeId1, "noCartId", "16OzWater");
    Assert.assertEquals(response2.getSuccess(), true);
    Assert.assertEquals(response2.getStoreID(), storeId1);
    Assert.assertNotEquals(response2.getCartID(), cartId1.toString());
    Assert.assertEquals(response2.getMessage(), "Side item is successfully added");
    Assert.assertEquals(response2.getItems().size(), 1);
    Assert.assertEquals(response2.getItems().contains("16OzWater"), true);
  }

  @Test
  public void deleteCartTest() {
    cartRepo.deleteAll();
    assertEquals(0, cartRepo.count());
    ObjectId cartId1 = new ObjectId();
    String storeId1 = "stoneWay";
    Cart cart1 = new Cart(storeId1, cartId1);
    cartRepo.insert(cart1);

    ObjectId cartId2 = new ObjectId();
    String storeId2 = "eastlake";
    Cart cart2 = new Cart(storeId2, cartId2);
    cartRepo.insert(cart2);

    ObjectId cartId3 = new ObjectId();
    String storeId3 = "eastlake";
    Cart cart3 = new Cart(storeId3, cartId3);
    cartRepo.insert(cart3);

    assertEquals(3, cartRepo.count());
    Assert.assertEquals(cartService.deleteCart(cartId3.toString()), HttpStatus.NO_CONTENT);
    Assert.assertEquals(cartService.getCartItemsById(storeId3, cartId3.toString()), null);
    assertEquals(2, cartRepo.count());
    Assert.assertEquals(cartService.getCartItemsById(storeId2, cartId2.toString()).toString(), cart2.toString());
    Assert.assertEquals(cartService.getCartItemsById(storeId1, cartId1.toString()).toString(), cart1.toString());

  }

  @Test
  public void deleteSideFromCartTest() {
    cartRepo.deleteAll();
    assertEquals(0, cartRepo.count());
    ObjectId cartId1 = new ObjectId();
    String storeId1 = "stoneWay";
    Cart cart1 = new Cart(storeId1, cartId1);
    cart1.getSides().add("16OzWater");
    cart1.getSides().add("2LiterWater");
    cart1.getSides().add("cheesesticks");
    cartRepo.insert(cart1);

    Assert.assertEquals(cartService.deleteSideFromCart(cartId1.toString(), "16OzWater"), HttpStatus.NO_CONTENT);
    Assert.assertEquals(cartRepo.findById(cartId1.toString()).get().getSides().size(),2);

    Assert.assertEquals(cartRepo.findById(cartId1.toString()).get().getSides().contains("2LiterWater"),true);
    Assert.assertEquals(cartRepo.findById(cartId1.toString()).get().getSides().contains("cheesesticks"),true);
    Assert.assertEquals(cartRepo.findById(cartId1.toString()).get().getSides().contains("16OzWater"),false);
    Assert.assertEquals(cartService.deleteSideFromCart(cartId1.toString(), "16OzWaters"), HttpStatus.BAD_REQUEST);
    Assert.assertEquals(cartService.deleteSideFromCart(cartId1.toString(), "cheesesticks"), HttpStatus.NO_CONTENT);
    Assert.assertEquals(cartRepo.findById(cartId1.toString()).get().getSides().size(),1);
  }

  @Test
  public void deletePizzaFromCartTest() {
    cartRepo.deleteAll();
    assertEquals(0, cartRepo.count());
    ObjectId cartId1 = new ObjectId();
    String storeId1 = "stoneWay";
    Cart cart1 = new Cart(storeId1, cartId1);
    Pizza pizza1 = new Pizza("large", true);
    Pizza pizza2 = new Pizza("medium", false);
    Pizza pizza3 = new Pizza("small", true);
    cart1.getPizzas().add(pizza1);
    cart1.getPizzas().add(pizza2);
    cart1.getPizzas().add(pizza3);
    cartRepo.insert(cart1);

    Assert.assertEquals(cartService.deletePizzaFromCart(cartId1.toString(), 3), HttpStatus.BAD_REQUEST);
    Assert.assertEquals(cartService.deletePizzaFromCart(cartId1.toString(), 0), HttpStatus.NO_CONTENT);
    Assert.assertEquals(cartRepo.findById(cartId1.toString()).get().getPizzas().size(), 2);
    Assert.assertEquals(cartRepo.findById(cartId1.toString()).get().getPizzas().get(0), pizza2);
    Assert.assertEquals(cartRepo.findById(cartId1.toString()).get().getPizzas().contains(pizza3), true);
    Assert.assertEquals(cartRepo.findById(cartId1.toString()).get().getPizzas().contains(pizza1), false);


  }

}
