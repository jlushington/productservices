package com.nodedynamics.productservices.api;

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
import com.nodedynamics.productservices.model.product.TaxesModel;
import com.nodedynamics.productservices.services.product.TaxesService;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/tax")
public class TaxesController {
	
	Logger log = LoggerFactory.getLogger(TaxesController.class);
	

	@Autowired
	Gson gson;
	
	@Autowired
	TaxesService service;
	
	
	//ADD TAXES
	@PostMapping(value = "/addtaxes", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Mono<String> AddTaxes(@RequestBody String request){
					
		log.info("TaxesController->AddTaxes->request: "+ request);
		return service.Store(gson.fromJson(request, TaxesModel.class));
	}
	
	//ADD TAXES
	@CrossOrigin(origins = "*") //TODO: NEED TO REMOVE AND INIT PROPER CORS
	@PostMapping(value = "/getalltaxes", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Mono<String> GetAllTaxes(@RequestBody String request){
						
		log.info("TaxesController->GetAllTaxes->request: "+ request);
					
		return service.GetAll();
	}
	
	//ADD TAXES BY ID
	@CrossOrigin(origins = "*") //TODO: NEED TO REMOVE AND INIT PROPER CORS
	@PostMapping(value = "/gettaxes", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Mono<String> GetTaxes(@RequestBody String request){
						
		log.info("TaxesController->GetTaxes->request: "+ request);
					
		return service.Get(gson.fromJson(request, TaxesModel.class));
	}
	
	//ADD TAXES BY ID

	@PostMapping(value = "/deletetaxes", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Mono<String> DeleteTax(@RequestBody String request){
							
		log.info("TaxesController->DeleteTax->request: "+ request);
						
		return service.Delete(gson.fromJson(request, TaxesModel.class));
	}

}
