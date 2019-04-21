package com.nodedynamics.productservices.services.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.google.gson.Gson;
import com.nodedynamics.productservices.common.Global;
import com.nodedynamics.productservices.model.common.ResponseModel;
import com.nodedynamics.productservices.model.product.TaxesModel;
import com.nodedynamics.productservices.repo.TaxesRepository;
import com.nodedynamics.productservices.services.BaseService;

import reactor.core.publisher.Mono;

@Service
public class TaxesService implements BaseService<TaxesModel>{
	
	
	@Autowired
	private TaxesRepository repo;
	
	@Autowired
	Gson gson = new Gson();

	@Override
	public Mono<String> Store(TaxesModel Model) {
		
		//SAVE MODEL
		repo.save(Model);

		return Mono.just(gson.toJson(ResponseModel.builder()
				.MessageTypeID(Global.MessageTypeID.SUCCESS.key)
				.MessageType(Global.MessageType.SUCCESS.key)
				.Message("Created Tax Successfully Added")
				.build()));
				
	}

	@Override
	public Mono<String> Update(TaxesModel Model) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Mono<String> DeleteAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Mono<String> Delete(TaxesModel Model) {
		repo.deleteById(Model.getID());
		return Mono.just(gson.toJson(ResponseModel.builder()
				.MessageTypeID(Global.MessageTypeID.SUCCESS.key)
				.MessageType(Global.MessageType.SUCCESS.key)
				.Message("Taxes Successfully Deleted")
				.build()));
		
	}

	@Override
	public Mono<String> GetAll() {
		return Mono.just(gson.toJson(repo.findAll()));
	}

	@Override
	public Mono<String> Get(TaxesModel Model) {
		return Mono.just(gson.toJson(repo.findById(Model.getID())));
	}

}
