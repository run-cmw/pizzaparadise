package io.swagger.service;

import io.swagger.model.StoreItem;
import io.swagger.repository.StoreItemRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
/**
 * Service for the Store API
 */
public class StoreService {
  @Autowired
  public StoreItemRepository storeItemRepository;

  /**
   * Get all stores.
   * @return a list of all StoreItems.
   */
  public List<StoreItem> getAllStores() {
    return storeItemRepository.findAll();
  }

  /**
   * Get a specific StoreItem by id
   * @param id id of requested StoreItem
   * @return specified StoreItem
   */
  public StoreItem getStoreById(String id) {
    for (StoreItem item : storeItemRepository.findAll()) {
      if (item.getId().equals(id)) {
        return item;
      }
    }
    return null;
  }

  /**
   * Add a StoreItem.
   * @param newStore new StoreItem to add
   * @return StoreItem that was added.
   */
  public StoreItem addStore(StoreItem newStore) {
    storeItemRepository.save(newStore);
    return newStore;
  }

  /**
   * Delete a StoreItem by id.
   * @param id id of StoreItem to delete
   */
  public void deleteStore(String id) {
    storeItemRepository.deleteById(id);
  }
}
