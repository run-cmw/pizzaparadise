package io.swagger.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import io.swagger.model.SideItem;

/**
 * SideItem Repository interface
 */
public interface SideItemRepository extends MongoRepository<SideItem, String> {

}