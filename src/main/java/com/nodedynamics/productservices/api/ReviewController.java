package com.nodedynamics.productservices.api;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.google.gson.Gson;
import com.nodedynamics.productservices.model.product.ReviewModel;
import com.nodedynamics.productservices.services.product.ReviewService;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/review")
public class ReviewController {
	
	Logger log = LoggerFactory.getLogger(ProductController.class);
	
	@Autowired
	Gson gson;
	
	@Autowired
	ReviewService service;
	
	
	//ADD REVIEW
	@CrossOrigin(origins = "*") //TODO: NEED TO REMOVE AND INIT PROPER CORS
	@PostMapping(value = "/addreview", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Mono<String> AddReview(@RequestBody @Valid ReviewModel request){
		return service.Store(request);
	}
	
	//GET REVIEW
	@CrossOrigin(origins = "*") //TODO: NEED TO REMOVE AND INIT PROPER CORS
	@PostMapping(value = "/getreview", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Mono<String> GetReview(@RequestBody @Valid ReviewModel request){
		return service.Get(request);
	}
	
		
}
