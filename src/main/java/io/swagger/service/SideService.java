package io.swagger.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.swagger.model.SideItem;
import io.swagger.repository.SideItemRepository;

/**
 * Service for the Side API
 */
@Service
public class SideService {
  @Autowired
  public SideItemRepository sideItemRepository;

  /**
   * Get all sides.
   * @return a list of all SideItems.
   */
  public List<SideItem> getAllSides() {
    return sideItemRepository.findAll();
  }

  /**
   * Get a specific SideItem by id
   * @param id id of requested SideItem
   * @return specified SideItem
   */
  public SideItem getSideById(String id) {
    for (SideItem item : sideItemRepository.findAll()) {
      if (item.getId().equals(id)) {
        return item;
      }
    }
    return null;
  }

  /**
   * Add a SideItem.
   * @param id side's unique identifier
   * @param name name of side item
   * @param price price of side item
   * @param type the type of side item: drink, condiment, or appetizer
   * @return SideItem that was added.
   */
  public SideItem addSide(String id, String name, Double price, String type) {
    SideItem newSide = new SideItem(id, name, price, type);
    sideItemRepository.save(newSide);
    return newSide;
  }

  /**
   * Delete a SideItem by id.
   * @param id id of SideItem to delete
   */
  public void deleteSide(String id) {
    sideItemRepository.deleteById(id);
  }
}