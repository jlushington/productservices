package com.nodedynamics.productservices.api;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.google.gson.Gson;
import com.nodedynamics.productservices.model.product.EventModel;
import com.nodedynamics.productservices.services.product.EventService;
import reactor.core.publisher.Mono;


@RestController
@RequestMapping("/api/product")
public class ProductController {
	
	Logger log = LoggerFactory.getLogger(ProductController.class);
	
	@Autowired
	Gson gson;
	
	@Autowired
	EventService service;
	
	//LIST LATEST PRODUCT
	@CrossOrigin(origins = "*") //TODO: NEED TO REMOVE AND INIT PROPER CORS
	@GetMapping(value = "/listlatestproducts")
	public Mono<String> ListLatestProducts(){		
		return service.GetLatest();
	}
	

	@CrossOrigin(origins = "*") //TODO: NEED TO REMOVE AND INIT PROPER CORS
	@GetMapping(value = "/listproducts")
	public Mono<String> ListProducts(){	
		return service.GetAll();

	}
	
	@CrossOrigin(origins = "*") //TODO: NEED TO REMOVE AND INIT PROPER CORS
	@GetMapping(value = "/list3latest")
	public Mono<String> List3Latest(){	
		return service.Get3Latest();
	}
	
	@CrossOrigin(origins = "*") //TODO: NEED TO REMOVE AND INIT PROPER CORS
	@GetMapping(value = "/list3latestimages")
	public Mono<String> List3LatestImages(){	
		return service.Get3LatestImages();
	}
	
	
	
	//ADD PRODUCT
	@CrossOrigin(origins = "*") //TODO: NEED TO REMOVE AND INIT PROPER CORS
	@PostMapping(value = "/addproduct", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Mono<String> Addproduct(@RequestBody @Valid EventModel request){
			log.info(gson.toJson(request));
		return service.Store(request);
	}
	
	
	//GET PRODUCT BY ID
	@CrossOrigin(origins = "*") //TODO: NEED TO REMOVE AND INIT PROPER CORS
	@PostMapping(value = "/getproduct", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Mono<String> GetProduct(@RequestBody String request){			
		return service.Get(gson.fromJson(request, EventModel.class));
	}
	
	
	//DELETE PRODUCT BY ID
	//@CrossOrigin(origins = "*") //TODO: NEED TO REMOVE AND INIT PROPER CORS
	@PostMapping(value = "/deleteproduct", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Mono<String> DeleteProduct(@RequestBody String request){
			
		return service.Delete(gson.fromJson(request, EventModel.class));
	}

}
