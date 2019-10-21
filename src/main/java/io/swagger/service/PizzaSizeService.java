package io.swagger.service;

import io.swagger.model.PizzaSize;
import io.swagger.repository.PizzaSizeRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class PizzaSizeService {

  @Autowired
  private PizzaSizeRepository pizzaSizeRepository;

  public List<PizzaSize> getAllPizzaSizes() {
    List<PizzaSize> sizes = pizzaSizeRepository.findAll();
    return sizes;
  }

  public PizzaSize getPizzaSizeById(String id) {
    for (PizzaSize size: pizzaSizeRepository.findAll()) {
      if (size.getId().equals(id)) {
        return size;
      }
    }
    return null;
  }
}
