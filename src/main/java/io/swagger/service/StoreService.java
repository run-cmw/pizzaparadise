package io.swagger.service;

import io.swagger.model.StoreItem;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.springframework.stereotype.Service;

@Service
public class StoreService {

  public List<StoreItem> storeItems;

  public StoreService() {
    this.storeItems = new ArrayList<>();
    storeItems.add(
        new StoreItem(
            d290f1ee6c544b0190e6d701748f0851,
            "4003 Stone Way N",
            "Seattle",
            "Washington",
            "98103"));
  }
  public List<StoreItem> getAllStoreItems() {
    return storeItems;
  }

  public StoreItem getStoreById(UUID id) {
    for (StoreItem item : storeItems) {
      if (item.getId().equals(id)) {
        return item;
      }
    }
    return null;
  }

  public void addStoreItem(StoreItem newStore) {
    storeItems.add(newStore);
  }
}