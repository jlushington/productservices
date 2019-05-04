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
import com.nodedynamics.productservices.model.product.WishlistModel;
import com.nodedynamics.productservices.services.product.WishlistService;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/wishlist")
public class WishlistController {
	
	Logger log = LoggerFactory.getLogger(WishlistController.class);
	
	@Autowired
	Gson gson;
	
	@Autowired
	WishlistService service;
	
	
	//ADD TO WISHLIST
	@CrossOrigin(origins = "*") //TODO: NEED TO REMOVE AND INIT PROPER CORS
	@PostMapping(value = "/addtowishlist", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Mono<String> AddWishlist(@RequestBody @Valid WishlistModel request){
		return service.Store(request);
	}

}
