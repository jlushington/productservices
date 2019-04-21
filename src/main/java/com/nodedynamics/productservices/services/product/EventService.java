package com.nodedynamics.productservices.services.product;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.google.gson.Gson;
import com.nodedynamics.productservices.common.Global;
import com.nodedynamics.productservices.common.imagemanager.ImageManager;
import com.nodedynamics.productservices.model.common.ImageModel;
import com.nodedynamics.productservices.model.common.ResponseModel;
import com.nodedynamics.productservices.model.product.EventModel;
import com.nodedynamics.productservices.repo.EventRepository;
import com.nodedynamics.productservices.services.BaseService;

import reactor.core.publisher.Mono;

@Service
public class EventService implements BaseService<EventModel>{
	
Logger log = LoggerFactory.getLogger(EventService.class);

	
	@Autowired
	private EventRepository repo;
	
	@Autowired
	Gson gson = new Gson();


	@Override
	public Mono<String> Store(EventModel Model) {
		
		Iterator<ImageModel> images=Model.getEventImage().iterator();
		
		List<ImageModel>rtnImages= new ArrayList<ImageModel>();
		
		ImageManager manager =new ImageManager();
		
		while(images.hasNext()) {
			ImageModel image = images.next();
						
			manager.Connect();
			
			ImageModel imagemodel=manager.Commit(image);
			
			rtnImages.add(imagemodel);
	
		}

		EventModel m = EventModel.builder()
				.eventName(Model.getEventName())
				.eventDescription(Model.getEventDescription())
				.locationID(Model.getLocationID())
				.age(Model.getAge())
				.eventURL(Model.getEventURL())
				.eventStartDate(Model.getEventStartDate())
				.eventEndDate(Model.getEventEndDate())
				.eventPricing(Model.getEventPricing())
				.productType(Model.getProductType())
				.eventImage(rtnImages)
				.build();
		
		//SAVE MODEL
		repo.save(m);

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
		log.info("EventService->GetAll");
		log.info("EventService->GetAll->gson.toJson(repo.findAll()): "+ gson.toJson(repo.findAll()));
		
		return Mono.just(gson.toJson(repo.findAll()));
	}

	@Override
	public Mono<String> Get(EventModel Model) {

			return Mono.just(gson.toJson(repo.findById(Model.getID())));
		
	}
	
	public Mono<String>GetLatest(){
		
		return Mono.just(gson.toJson(repo.findTopByOrderByEventStartDate()));
		
	}

}
