package io.swagger.service;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.model.SpecialItem;
import io.swagger.repository.SpecialItemRepository;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class SpecialItemService {

  @Autowired
  public SpecialItemRepository specialItemRepository;


  public List<SpecialItem> getAllSpecials() {
    return specialItemRepository.findAll();
  }

  public SpecialItem getSpecialById(String id) {
    for (SpecialItem item : specialItemRepository.findAll()) {
      if (item.getId().equals(id)) {
        return item;
      }
    }
    return null;
  }


  public SpecialItem addSpecial(SpecialItem newSpecial) {
    specialItemRepository.save(newSpecial);
    return newSpecial;
  }


  public String deleteSpecial(String id) {
    if(specialItemRepository.existsById(id) ) {
      specialItemRepository.deleteById(id);
      return "deleted with id " + id;
    }
    return "id does not exist: " + id;
  }
}
