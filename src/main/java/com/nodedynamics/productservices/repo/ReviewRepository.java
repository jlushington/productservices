package com.nodedynamics.productservices.repo;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.nodedynamics.productservices.model.product.EventModel;
import com.nodedynamics.productservices.model.product.ReviewModel;

public interface ReviewRepository extends MongoRepository<ReviewModel, String>{
	
	public List <ReviewModel>findByProductID(String ProductID);

}
