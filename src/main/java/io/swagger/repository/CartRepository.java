package io.swagger.repository;

import io.swagger.model.Cart;
import io.swagger.model.SideItem;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CartRepository extends MongoRepository<Cart, String> {

}
