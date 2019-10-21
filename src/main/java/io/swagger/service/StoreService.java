package io.swagger.service;

import io.swagger.model.StoreItem;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class StoreService {

  public List<StoreItem> listOfStores;

  public StoreService() {
    this.listOfStores = new ArrayList<>();
    listOfStores.add(
        new StoreItem(
            "9bbfc16f-d318-42f2-82f1-bf7365d4ee93",
            "4003 Stone Way N, Seattle, Washington 98103",
            "11",
            "00",
            null,
            "23",
            "00",
            null);

  }
  public List<StoreItem> getAllStoreItems() {
    return listOfStores;
  }

  public StoreItem getStoreById(String id) {
    for (StoreItem item : listOfStores) {
      if (item.getId().equals(id)) {
        return item;
      }
    }
    return null;
  }

  public void addStoreItem(StoreItem newStore) {
    listOfStores.add(newStore);
  }
}