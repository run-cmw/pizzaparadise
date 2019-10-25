package io.swagger.repository;

import io.swagger.model.ToppingItem;
import org.springframework.data.mongodb.repository.MongoRepository;

// Special item Repository interface
public interface ToppingItemRepository extends MongoRepository<ToppingItem, String> {

}