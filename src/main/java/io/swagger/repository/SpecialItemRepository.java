package io.swagger.repository;

import io.swagger.model.SpecialItem;
import org.springframework.data.mongodb.repository.MongoRepository;

// Special item Repository interface
public interface SpecialItemRepository extends MongoRepository<SpecialItem, String> {

}
