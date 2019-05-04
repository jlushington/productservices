package com.nodedynamics.productservices.services.product;


import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.google.gson.Gson;
import com.nodedynamics.productservices.common.Global;
import com.nodedynamics.productservices.model.common.ResponseModel;
import com.nodedynamics.productservices.model.product.WishlistModel;
import com.nodedynamics.productservices.repo.WishlistRepository;
import com.nodedynamics.productservices.security.JwtTokenProvider;
import com.nodedynamics.productservices.services.BaseService;

import reactor.core.publisher.Mono;

@Service
public class WishlistService implements BaseService<WishlistModel>{
	
	@Autowired
	private WishlistRepository repo;
	
	@Autowired
	Gson gson = new Gson();
	
	@Autowired
	private JwtTokenProvider tokenProvider;
	

	@Override
	public Mono<String> Store(WishlistModel Model) {
		ResponseModel reponse=null;
		
		if(tokenProvider.validateToken(Model.getUserID())) {
			WishlistModel data =WishlistModel.builder()
			.productID(Model.getProductID())
			.userID(tokenProvider.getUserIdFromJWT(Model.getUserID()))
			.dateCreated(Instant.now().toEpochMilli())
			.build();
			
			repo.save(data);
			
			reponse=ResponseModel.builder()
					.MessageTypeID(Global.MessageTypeID.SUCCESS.key)
					.MessageType(Global.MessageType.SUCCESS.key)
					.Message("Added to Wishlist Successfully")
					.build();
			
			
			
		}else {
			reponse=ResponseModel.builder()
			.MessageTypeID(Global.MessageTypeID.ERROR.key)
			.MessageType(Global.MessageType.ERROR.key)
			.Message("Token is not Valid")
			.build();
			
		}
		
		// TODO Auto-generated method stub
		return Mono.just(gson.toJson(reponse));
	}

	@Override
	public Mono<String> Update(WishlistModel Model) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Mono<String> DeleteAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Mono<String> Delete(WishlistModel Model) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Mono<String> GetAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Mono<String> Get(WishlistModel Model) {
		// TODO Auto-generated method stub
		return null;
	}

}
