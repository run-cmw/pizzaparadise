package io.swagger.service;

import io.swagger.model.StoreItem;
import io.swagger.repository.StoreItemRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service for the Store API
 */
@Service
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
   * @param id store's unique identifier
   * @param streetNumAndName street portion of store's address
   * @param city city portion of store's address
   * @param state state portion of store's address
   * @param zipCode zip code portion of store's address
   * @param offersGlutenFree whether store offers gluten free pizza
   * @return StoreItem that was added.
   */
  public StoreItem addStore(String id, String streetNumAndName, String city, String state, String zipCode, boolean offersGlutenFree) {
    StoreItem newStore = new StoreItem(id, streetNumAndName, city, state, zipCode, offersGlutenFree);
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