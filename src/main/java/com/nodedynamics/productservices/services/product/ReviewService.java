package com.nodedynamics.productservices.services.product;

import java.time.Instant;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.google.gson.Gson;
import com.nodedynamics.productservices.common.Global;
import com.nodedynamics.productservices.model.common.ResponseModel;
import com.nodedynamics.productservices.model.product.ReviewModel;
import com.nodedynamics.productservices.repo.ReviewRepository;
import com.nodedynamics.productservices.security.JwtTokenProvider;
import com.nodedynamics.productservices.services.BaseService;
import reactor.core.publisher.Mono;

@Service
public class ReviewService implements BaseService<ReviewModel>{
	
	Logger log = LoggerFactory.getLogger(EventService.class);

	
	@Autowired
	private ReviewRepository repo;
	
	@Autowired
	Gson gson = new Gson();
	
	@Autowired
	private JwtTokenProvider tokenProvider;
	

	@Override
	public Mono<String> Store(ReviewModel Model) {
		ResponseModel reponse=null;

		
		if(tokenProvider.validateToken(Model.getUserID())) {
		
		ReviewModel rmodel=ReviewModel.builder()
				.userID(tokenProvider.getUserIdFromJWT(Model.getUserID()))
				.productID(Model.getProductID())
				.reviewValue(Model.getReviewValue())
				.reviewComment(Model.getReviewComment())
				.dateCreated(Instant.now().toEpochMilli())
				.reviewType(Model.getReviewType())
				.build();
		//SAVE MODEL
		reponse=ResponseModel.builder()
				.MessageTypeID(Global.MessageTypeID.SUCCESS.key)
				.MessageType(Global.MessageType.SUCCESS.key)
				.Message("Added Review Successfully")
				.build();
		
		repo.save(rmodel);
		}else
		{
			reponse=ResponseModel.builder()
					.MessageTypeID(Global.MessageTypeID.ERROR.key)
					.MessageType(Global.MessageType.ERROR.key)
					.Message("Token is not Valid")
					.build();
		}
		
		return Mono.just(gson.toJson(reponse));
	}

	@Override
	public Mono<String> Update(ReviewModel Model) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Mono<String> DeleteAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Mono<String> Delete(ReviewModel Model) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Mono<String> GetAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Mono<String> Get(ReviewModel Model) {
		return Mono.just(gson.toJson(repo.findByProductID(Model.getProductID())));
	}

}
