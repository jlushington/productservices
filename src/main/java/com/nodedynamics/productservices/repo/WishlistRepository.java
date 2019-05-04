package com.nodedynamics.productservices.repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.nodedynamics.productservices.model.product.WishlistModel;

public interface WishlistRepository  extends MongoRepository<WishlistModel, String>{

}
