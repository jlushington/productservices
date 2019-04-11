package com.nodedynamics.productservices.services.product;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.WebSession;

import com.google.gson.Gson;
import com.nodedynamics.productservices.common.Global;
import com.nodedynamics.productservices.model.common.ResponseModel;
import com.nodedynamics.productservices.model.product.EventModel;
import com.nodedynamics.productservices.repo.EventRepository;
import com.nodedynamics.productservices.services.BaseService;

import reactor.core.publisher.Mono;

@Service
public class EventService implements BaseService<EventModel>{
	
Logger log = LoggerFactory.getLogger(EventService.class);
	
	private WebSession Session;
	
	@Autowired
	private EventRepository repo;
	
	@Autowired
	Gson gson = new Gson();

	@Override
	public void Init(WebSession session) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Mono<String> Store(EventModel Model) {
		
		//SAVE MODEL
		repo.save(Model);

		return Mono.just(gson.toJson(ResponseModel.builder()
				.MessageTypeID(Global.MessageTypeID.SUCCESS.key)
				.MessageType(Global.MessageType.SUCCESS.key)
				.Message("Created Product Successfully Added")
				.build()));
	}

	@Override
	public Mono<String> Update(EventModel Model) {
		return null;
	}

	@Override
	public Mono<String> DeleteAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Mono<String> Delete(EventModel Model) {
		repo.deleteById(Model.getID());
		return Mono.just(gson.toJson(ResponseModel.builder()
				.MessageTypeID(Global.MessageTypeID.SUCCESS.key)
				.MessageType(Global.MessageType.SUCCESS.key)
				.Message("Product Successfully Deleted")
				.build()));
	}

	@Override
	public Mono<String> GetAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Mono<String> Get(EventModel Model) {

			return Mono.just(gson.toJson(repo.findById(Model.getID())));
		
	}
	
	public Mono<String>GetLatest(){
		
		return Mono.just(gson.toJson(repo.findTopByOrderByEventStartDate()));
		
	}

}