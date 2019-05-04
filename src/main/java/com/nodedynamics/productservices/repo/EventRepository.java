package com.nodedynamics.productservices.repo;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.nodedynamics.productservices.model.product.EventModel;


public interface EventRepository extends MongoRepository<EventModel, String>{
	
	//@Query("db.col.find().sort({\"datetime\": -1}).limit(1)")
	public List<EventModel>findTopByOrderByEventStartDate();
	
	public List<EventModel>findTop3ByOrderByEventStartDateDesc();

}
