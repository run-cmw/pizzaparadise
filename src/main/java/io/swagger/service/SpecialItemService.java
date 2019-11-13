package io.swagger.service;

import io.swagger.model.SpecialItem;
import io.swagger.repository.SpecialItemRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SpecialItemService {

  @Autowired
  public SpecialItemRepository specialItemRepository;


  /**
   * Get all Special Items
   * @return List of Special Items found in the database
   */
  public List<SpecialItem> getAllSpecials() {
    return specialItemRepository.findAll();
  }

  /**
   * Get SpecialItem by Id
   * @param id id given to search
   * @return the specialItem that matches with id.
   */
  public SpecialItem getSpecialById(String id) {
    for (SpecialItem item : specialItemRepository.findAll()) {
      if (item.getId().equals(id)) {
        return item;
      }
    }
    return null;
  }

  /**
   * Add special to the database
   * @param specialId new SpecialId given to add
   * @param name name of the specialItem
   * @param description description of specialItem
   * @return the specialItem that is added
   */
  public SpecialItem addSpecial(String  specialId, String name, String description) {
    SpecialItem newSpecial = new SpecialItem(specialId, name, description);
    specialItemRepository.save(newSpecial);
    return newSpecial;
  }


  /**
   * Delete special by id from database
   * @param id id given to delete
   */
  public void deleteSpecial(String id) {
    specialItemRepository.deleteById(id);
  }
}
