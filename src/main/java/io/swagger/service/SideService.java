package io.swagger.service;

import io.swagger.model.SideItem;
import io.swagger.repository.SideItemRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
  public Optional<SideItem> getSideById(String id) {
    return sideItemRepository.findById(id);
  }

  /**
   * Add a SideItem.
   * @param newSide new SideItem to add
   * @return SideItem that was added.
   */
  public SideItem addSide(SideItem newSide) {
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