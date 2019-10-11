package io.swagger.service;

import io.swagger.model.SpecialItem;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import org.springframework.stereotype.Service;

@Service
public class SpecialService {

  public List<SpecialItem> listOfSpecials = getAllSpecialItems();

  public List<SpecialItem> getAllSpecialItems() {
    listOfSpecials = new ArrayList<>();
    listOfSpecials.add(new SpecialItem("1", "Buy1Get1Free",
        "Only one special at a time. If you buy 1 pizza, you get 1 free pizza"
            + " that is equal or less value."));

    return listOfSpecials;
  }

  public SpecialItem getSpecialById(String id) {
    for(SpecialItem item : listOfSpecials) {
      if(item.getId().equals(id)) {
        return item;
      }
    }
    return null;

  }

  public void addSpecialItem(SpecialItem newSpecial) {
    listOfSpecials.add(newSpecial);
  }

}
