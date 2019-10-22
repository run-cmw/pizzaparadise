package io.swagger.service;

import io.swagger.model.StoreItem;
import io.swagger.repository.StoreItemRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    return newStore;
  }

  public String deleteStore(String id) {
    if(storeItemRepository.existsById(id) ) {
      storeItemRepository.deleteById(id);
      return "deleted id: " + id;
    }
    return "id does not exist: " + id;
  }
}
