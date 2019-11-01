package io.swagger.service;

import io.swagger.model.Cart;
import io.swagger.model.Pizza;
import io.swagger.model.PizzaSize;
import io.swagger.model.SideItem;
import io.swagger.model.ToppingItem;
import io.swagger.repository.CartRepository;
import io.swagger.repository.PizzaSizeRepository;
import io.swagger.repository.SideItemRepository;
import io.swagger.repository.ToppingItemRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
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

  public Cart getCartItemsById(String id) {
    for (Cart cart: cartRepository.findAll()) {
      if (cart.getId().equals(id)) {
        return cart;
      }
    }
    return null;
  }

  public Cart addPizzaToCart(String id, String sizeId, boolean gluten, String topping1,
      String topping2, String topping3, String topping4) {
    Cart cart = cartRepository.findById(id).orElse(new Cart(id));
    Optional<PizzaSize> size = sizeRepository.findById(sizeId);
    Pizza newPizza;
    if (size.isPresent()) {
      newPizza = new Pizza(sizeId, gluten);
    } else {
      return null;
    }
    addToppingToPizza(newPizza, topping1);
    addToppingToPizza(newPizza, topping2);
    addToppingToPizza(newPizza, topping3);
    addToppingToPizza(newPizza, topping4);
    cart.getPizzas().add(newPizza);
    cartRepository.save(cart);
    return cart;

  }

  public void addToppingToPizza(Pizza pizza, String topping) {
    if (topping != null) {
      Optional<ToppingItem> item = toppingRepository.findById(topping);
      if (item.isPresent()) {
        if (pizza.getToppingCount() <= pizza.getMAX_TOPPING()) {
          pizza.getToppingIDs().add(topping);
        }
      }
    }
  }

  public Cart addSideToCart(String id, String sideID) {
    Cart cart = cartRepository.findById(id).orElse(new Cart(id));
    Optional<SideItem> sideItem = sideRepository.findById(sideID);
    if (sideItem.isPresent()) {
      cart.getSides().add(sideID);
      cartRepository.save(cart);
      return cart;
    }
    return null;
  }

  public String deleteCart(String id) {
    if (cartRepository.existsById(id)) {
      cartRepository.deleteById(id);
      return "deleted with id " + id;
    }
    return "id does not exist: " + id;
  }

  public Double getTotalAmountInCart(String id) {
    Double price = 0.0;
    Cart cart = cartRepository.findById(id).get();
    price += getSidePrice(cart);
    price += getPizzaPrice(cart);

    cart.setTotalAmount(price);
    cartRepository.save(cart);
    return price;
  }

  public Double getSidePrice(Cart cart) {
    double price = 0.00;
    List<String> sides = cart.getSides();
    for (String sideId : sides) {
      Optional<SideItem> sideItem = sideRepository.findById(sideId);
      price += sideItem.get().getPrice();
    }
    return price;
  }

  public Double getPizzaPrice(Cart cart) {
    double price = 0.00;
    List<Pizza> pizzas = cart.getPizzas();
    for (Pizza pizza : pizzas) {
      String sizeID = pizza.getSizeID();
      Optional<PizzaSize> pizzaSize = sizeRepository.findById(sizeID);
      List<String> toppings = pizza.getToppingIDs();
      price += pizzaSize.get().getPrice();
      price += getPizzaToppingPrice(sizeID, toppings);

    }
    return price;
  }

  public Double getPizzaToppingPrice(String sizeID, List<String> toppings) {
    double price = 0.00;
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
    return price;
  }

}
