package io.swagger.service;

import io.swagger.model.Cart;
import io.swagger.model.CartAddResponse;
import io.swagger.model.Pizza;
import io.swagger.model.PizzaSize;
import io.swagger.model.PriceResponse;
import io.swagger.model.SideItem;
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
   * Get Cart information by StoreID and CartID.
   * @param storeId storeId given to this function.
   * @param cartId cartId given to this function.
   * @return Cart if cartId was found from the store. Null if cartID and storeID are not connected.
   */
  public Cart getCartItemsById(String storeId, ObjectId cartId) {
    return getCartItemsById(storeId, cartId.toString());
  }

  /**
   * TODO: document
   * @param storeId
   * @param cartId
   * @return
   */
  public Cart getOrCreateCart(String storeId, ObjectId cartId) {
    Cart cart = getCartItemsById(storeId, cartId);
    if (cart == null) {
      cart = new Cart(storeId, cartId);
      cartRepository.insert(cart);
    }

    return cart;
  }

  /**
   * TODO: document
   */
  public Cart getOrCreateCart(String storeId, String cartId) {
    return getOrCreateCart(storeId, new ObjectId(cartId));
  }

  /**
   * TODO: fix this documentation
   * Create pizza by using sizeId, gluten and 4 toppings and add this pizza to Cart. If the store
   * does not offer the glutenFree and input gluten is false, it will be returning CartAddResponse
   * with "Success:false" with message. When adding toppings to pizza, it will only include existing
   * toppings. If everything is successful, it will return CartAddResponse with "Success:True" and
   * show other details.
   * 
   * TODO: document that the cart will be created if it does not exist
   *
   * @param storeId storeId holding the cart.
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
  public Pizza addPizzaToCart(String storeId, String cartId, String sizeId,
      boolean gluten, String [] toppings) {
    Cart cart = getOrCreateCart(storeId, cartId);
 
    Pizza pizza = new Pizza(sizeId, gluten);

    for (String toppingId : toppings) {
      addToppingToPizza(pizza, toppingId);
    }

    // This is a weird data flow
    pizza = getPizzaPrice(pizza);
    cart.getPizzas().add(pizza);
    cartRepository.save(cart);
    return pizza;
  }


  /**
   * SUB-Function of addPizzaToCart()
   * This function add topping to pizza. Only adding toppings that exist in database.
   * @param pizza base pizza given for adding toppings.
   * @param topping toppingId given to add to pizza.
   * @param items if topping does exist and can be added to pizza, we also add to item List.
   */
  public void addToppingToPizza(Pizza pizza, String topping) {
    if (topping != null) {
      Optional<ToppingItem> item = toppingRepository.findById(topping);
      if (!item.isPresent()) {
        // TODO: make this a checked exception!
        throw new RuntimeException("TOPPING_NOT_FOUND");
      }
      if (pizza.getToppingCount() >= Pizza.MAXIMUM_TOPPING_COUNT) {
        // TODO: make this a checked exception!
        // Note that this string is the same as we're using elsewhere...factor it out
        throw new RuntimeException("TOO_MANY_TOPPINGS");
      }
      pizza.getToppingIDs().add(topping);
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
    Cart cart = getOrCreateCart(storeId, cartId);

    List<String> items = new ArrayList<>();
    cart.getSides().add(sideID);
    items.add(sideID);
    cartRepository.save(cart);
    return new CartAddResponse(items, cartId, storeId);
  }

  /**
   * This function delete the Cart.
   * @param cartId cartId given to delete.
   */
  public void deleteCart(String cartId) {
    cartRepository.deleteById(cartId);
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
