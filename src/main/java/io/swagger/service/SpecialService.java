package io.swagger.service;

import io.swagger.model.SpecialItem;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class SpecialService {

  public List<SpecialItem> getAllSpecialItems() {
    List<SpecialItem> listOfSpecials = new ArrayList<>();


    return listOfSpecials;
  }

  public SpecialItem getSpecialById(String id) {
    for (SpecialItem item : getAllSpecialItems()) {
      if (item.getId().equals(id)) {
        return item;
      }
    }
    return null;
  }

  public void addSpecialItem(SpecialItem newSpecial) {
    throw new UnsupportedOperationException("Not yet implemented!");
  }
}
