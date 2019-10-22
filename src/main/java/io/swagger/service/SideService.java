package io.swagger.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.swagger.model.SideItem;
import io.swagger.repository.SideItemRepository;

@Service
public class SideService {
  @Autowired
  public SideItemRepository sideItemRepository;

  public List<SideItem> getAllSides() {
    return sideItemRepository.findAll();
  }

  public SideItem getSideById(String id) {
    for (SideItem item : sideItemRepository.findAll()) {
      if (item.getId().equals(id)) {
        return item;
      }
    }
    return null;
  }

  public SideItem addSide(SideItem newSide) {
    sideItemRepository.save(newSide);
    return newSide;
  }

  public String deleteSide(String id) {
    if(sideItemRepository.existsById(id) ) {
      sideItemRepository.deleteById(id);
      return "deleted id: " + id;
    }
    return "id does not exist: " + id;
  }
}
