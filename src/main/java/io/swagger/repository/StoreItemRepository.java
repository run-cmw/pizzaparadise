package io.swagger.repository;

import io.swagger.model.StoreItem;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Interface for StoreItem repository
 */
public interface StoreItemRepository extends MongoRepository<StoreItem, String> {

}