package io.swagger.service;

import io.swagger.model.Cart;
import io.swagger.model.CartAddResponse;
import io.swagger.model.Pizza;
import io.swagger.model.PizzaSize;
import io.swagger.model.PriceResponse;
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
import java.util.Optional;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;


@Service
public class CartService {

  @Autowired
  private CartRepository cartRepository;

  @Autowired
  private SideItemRepository sideRepository;

  @Autowired
  private ToppingItemRepository toppingRepository;

  @Autowired
  private PizzaSizeRepository sizeRepository;

  @Autowired
  private StoreItemRepository storeRepository;

  /**
   * Get Cart information by StoreID and CartID.
   * @param storeId storeId given to this function.
   * @param cartId cartId given to this function.
   * @return Cart if cartId was found from the store. Null if cartID and storeID are not connected.
   */
  public Cart getCartItemsById(String storeId, String cartId) {
    for (Cart cart : cartRepository.findAll()) {
      if (cart.getId().equals(cartId) && cart.getStoreID().equals(storeId)) {
        cart.setTotalAmount(getTotalAmountInCart(cartId).getPrice());
        return cart;
      }
    }
    return null;
  }


  /**
   * Create pizza by using sizeId, gluten and 4 toppings and add this pizza to Cart. If the store
   * does not offer the glutenFree and input gluten is false, it will be returning CartAddResponse
   * with "Success:false" with message. When adding toppings to pizza, it will only include existing
   * toppings. If everything is successful, it will return CartAddResponse with "Success:True" and
   * show other details.
   *
   * @param storeId storeId given for checking gluten preference.
   * @param cartId cartId given for adding pizza.
   * @param sizeId sizeId given for making pizza.
   * @param gluten gluten(true = gluten / false = glutenFree) given for making pizza.
   * @param topping1 toppingId given for making pizza.
   * @param topping2 toppingId given for making pizza.
   * @param topping3 toppingId given for making pizza.
   * @param topping4 toppingId given for making pizza.
   * @return If successful, it will return CartAddResponse with "Success:True" and show other
   * details. If not successful, it will return CartAddResponse with "Success:false" with message.
   */
  public CartAddResponse addPizzaToCart(String storeId, String cartId, String sizeId,
      boolean gluten, String topping1, String topping2, String topping3, String topping4) {
    Optional<StoreItem> store = storeRepository.findById(storeId);
    if (!store.get().getOffersGlutenFree() && !gluten) {
      String message = "This store cannot provide this gluten preference.";
      return new CartAddResponse(false, null, null, null, message);
    }

    Cart cart = getCartItemsById(storeId, cartId);
    if (cart == null) {
      ObjectId cartObjectID = new ObjectId();
      cart = new Cart(storeId, cartObjectID);
      cartId = cartObjectID.toHexString();
    }

    List<String> items = new ArrayList<>();
    Pizza newPizza = new Pizza(sizeId, gluten);
    items.add(sizeId);
    if(gluten) {
      items.add("gluten");
    } else {
      items.add("glutenFree");
    }

    addToppingToPizza(newPizza, topping1, items);
    addToppingToPizza(newPizza, topping2, items);
    addToppingToPizza(newPizza, topping3, items);
    addToppingToPizza(newPizza, topping4, items);
    newPizza = getPizzaPrice(newPizza);
    cart.getPizzas().add(newPizza);
    cartRepository.save(cart);
    String message = "items are successfully added.";
    return new CartAddResponse(true, items, cartId, storeId, message);

  }


  /**
   * SUB-Function of addPizzaToCart()
   * This function add topping to pizza. Only adding toppings that exist in database.
   * @param pizza base pizza given for adding toppings.
   * @param topping toppingId given to add to pizza.
   * @param items if topping does exist and can be added to pizza, we also add to item List.
   */
  public void addToppingToPizza(Pizza pizza, String topping, List<String> items) {
    if (topping != null) {
      Optional<ToppingItem> item = toppingRepository.findById(topping);
      if (item.isPresent()) {
        if (pizza.getToppingCount() <= pizza.getMAX_TOPPING()) {
          pizza.getToppingIDs().add(topping);
          items.add(topping);
        }
      }
    }
  }

  /**
   * This function add side to Cart.
   * If the cartId doesn't exist in the store, we create new Cart in the store and add side.
   * Note that if input cartID doesn't exist, finalized cartID is newly made.
   * @param storeId storeId given to this function to check if storeId has cartId.
   * @param cartId cartId given to this function to check if storeId has cartId.
   * @param sideID sideId given to add to the Cart.
   * @return CartAddResponse that shows sideId, cartId, storeId, message if it is successful.
   */
  public CartAddResponse addSideToCart(String storeId, String cartId, String sideID) {
    Cart cart = getCartItemsById(storeId, cartId);

    if (cart == null) {
      ObjectId cartObjectId = new ObjectId();
      cartId = cartObjectId.toHexString();
      cart = new Cart(storeId, cartObjectId);
    }
    List<String> items = new ArrayList<>();
    cart.getSides().add(sideID);
    items.add(sideID);
    cartRepository.save(cart);
    String message = "Side item is successfully added";
    return new CartAddResponse(true, items, cartId, storeId, message);

  }

  /**
   * This function delete the Cart.
   * @param cartId cartId given to delete.
   * @return HttpStatus.NO_CONTENT if successfully deleted.
   */
  public HttpStatus deleteCart(String cartId) {
    cartRepository.deleteById(cartId);
    return HttpStatus.NO_CONTENT;
  }

  /**
   * Get the total price of items(pizzas + sides) in the Cart.
   * @param cartId cartId given to get the total price of whole items.
   * @return PriceResponse that shows "Success:True", price and currency.
   */
  public PriceResponse getTotalAmountInCart(String cartId) {
    Double price = 0.00;
    Cart cart = cartRepository.findById(cartId).get();
    price += getSidePrice(cart);
    price += getPizzasPrice(cart);
    price = Math.round(price * 100.0) / 100.0;
    cart.setTotalAmount(price);
    cartRepository.save(cart);
    return new PriceResponse(true, price, "USD");
  }

  /**
   * SUB-Function of getTotalAmountInCart()
   * Get the total price of all sideItems in the Cart.
   * @param cart cart given to get the total price of side items.
   * @return Double the price of all side items in the Cart.
   */
  public Double getSidePrice(Cart cart) {
    Double price = 0.00;
    List<String> sides = cart.getSides();
    for (String sideId : sides) {
      Optional<SideItem> sideItem = sideRepository.findById(sideId);
      price += sideItem.get().getPrice();
    }
    return Math.round(price * 100.0) / 100.0;
  }

  /**
   * SUB-Function of getTotalAmountInCart()
   * Get the total price of all Pizzas in the Cart.
   * @param cart cart given to calculate the total price of all pizzas in this Cart.
   * @return Double the price of all pizzas in the Cart.
   */
  public Double getPizzasPrice(Cart cart) {
    Double price = 0.00;
    List<Pizza> pizzas = cart.getPizzas();
    for (Pizza pizza : pizzas) {
      price += pizza.getPrice();
    }
    return Math.round(price * 100.0) / 100.0;
  }

  /**
   * SUB-Function of addPizzaToCart()
   * Get the price of the given pizza.
   * @param pizza pizza given to calculate the pizza price.
   * @return Pizza that contains updated pizza price.
   */
  public Pizza getPizzaPrice(Pizza pizza) {
    Double price = 0.00;
    String sizeID = pizza.getSizeID();
    Optional<PizzaSize> pizzaSize = sizeRepository.findById(sizeID);
    List<String> toppings = pizza.getToppingIDs();
    price += pizzaSize.get().getPrice();
    price += getPizzaToppingPrice(sizeID, toppings);
    pizza.setPrice(price);

    return pizza;

  }

  /**
   * SUB-Function of addPizzaToCart() and getPizzaPrice()
   * Calculate the price of all toppings based on the size of pizza.
   * If size of pizza is small, then we calculate the small topping prices.
   * @param sizeID sizeId given to provide the size of pizza
   * @param toppings list of toppings given to calculate the price
   * @return Double the price of all toppings in the pizza.
   */
  public Double getPizzaToppingPrice(String sizeID, List<String> toppings) {
    Double price = 0.00;
    for (String toppingID : toppings) {
      Optional<ToppingItem> topping = toppingRepository.findById(toppingID);
      if (sizeID.equals("small")) {
        price += topping.get().getToppingSmallPrice();
      } else if (sizeID.equals("medium")) {
        price += topping.get().getToppingMediumPrice();
      } else if (sizeID.equals("large")) {
        price += topping.get().getToppingLargePrice();
      }
    }
    return Math.round(price * 100.0) / 100.0;
  }

  /**
   * Delete a side from a Cart.
   * @param cartId cartId given to delete side.
   * @param sideId sideId given to delete.
   * @return HttpStatus.NO_CONTENT if side was in the cart and successfully removed.
   * HttpStatus.BAD_REQUEST, if sideId does not exist in the Cart.
   */
  public HttpStatus deleteSideFromCart(String cartId, String sideId) {
    Cart cart = cartRepository.findById(cartId).get();
    if (cart.getSides().contains(sideId)) {
      cart.getSides().remove(sideId);
      cartRepository.save(cart);
      return HttpStatus.NO_CONTENT;
    } else {
      return HttpStatus.BAD_REQUEST;
    }
  }

  /**
   * Delete a pizza from a Cart.
   * pizzaIndex can start from 0 to nth -1.
   * @param cartId cartId given to delete a pizza.
   * @param pizzaIndex pizzaIndex given to delete from list of pizza.
   * @return HttpStatus.NO_CONTENT, if pizza was successfully removed from cart.
   * HttpStatus.BAD_REQUEST, if pizzaIndex is less than 0 or greater than n-1.
   * If there is only one pizza, and user give 1 for pizzaIndex, it will return BAD_REQUEST.
   */
  public HttpStatus deletePizzaFromCart(String cartId, Integer pizzaIndex) {
    Cart cart = cartRepository.findById(cartId).get();
    if (cart.getPizzas().size() > pizzaIndex) {
      Pizza pizza = cart.getPizzas().get(pizzaIndex);
      cart.getPizzas().remove(pizza);
      cartRepository.save(cart);
      return HttpStatus.NO_CONTENT;
    }
    return HttpStatus.BAD_REQUEST;
  }

}
