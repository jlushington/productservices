package com.nodedynamics.productservices.model.product;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import com.nodedynamics.productservices.model.CoreModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
@Builder
@Document(collection="Wishlist")
public class WishlistModel extends CoreModel{
	
	@Id
	private String iD;
	private String userID;
	private Long dateCreated;
	private String productID;
	

}
