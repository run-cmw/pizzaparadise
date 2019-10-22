package io.swagger.service;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.model.StoreItem;
import io.swagger.repository.StoreItemRepository;
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
public class StoreService {
  @Autowired
  public StoreItemRepository storeItemRepository;

  public List<StoreItem> getAllStores() {
    return storeItemRepository.findAll();
  }

  public StoreItem getStoreById(String id) {
    for (StoreItem item : storeItemRepository.findAll()) {
      if (item.getId().equals(id)) {
        return item;
      }
    }
    return null;
  }

  public StoreItem addStore(StoreItem newStore) {
    storeItemRepository.save(newStore);
    return newStore
  }

  public String deleteStore(String id) {
    if(storeItemRepository.existsById(id) ) {
      storeItemRepository.deleteById(id);
      return "deleted id: " + id;
    }
    return "id does not exist: " + id;
  }
}