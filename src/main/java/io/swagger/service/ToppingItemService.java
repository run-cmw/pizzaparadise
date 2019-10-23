package io.swagger.service;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.model.ToppingItem;
import io.swagger.repository.ToppingItemRepository;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class ToppingItemService {

  @Autowired
  public ToppingItemRepository toppingItemRepository;


  public List<ToppingItem> getAllTopping() {
    return toppingItemRepository.findAll();
  }

  public ToppingItem getToppingById(String id) {
    for (ToppingItem item : toppingItemRepository.findAll()) {
      if (item.getId().equals(id)) {
        return item;
      }
    }
    return null;
  }


  public ToppingItem addTopping(ToppingItem newTopping) {
    toppingItemRepository.save(newTopping);
    return newTopping;
  }


  public String deleteTopping(String id) {
    if(toppingItemRepository.existsById(id) ) {
      toppingItemRepository.deleteById(id);
      return "deleted with id " + id;
    }
    return "id does not exist: " + id;
  }
}