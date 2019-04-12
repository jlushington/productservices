package com.nodedynamics.productservices.model.common;

import com.nodedynamics.productservices.model.CoreModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
@Builder
public class ImageModel extends CoreModel{
	
	private String imageID;
	private String imageName;
	private String name;
	private String type;
	private String size;
	private String base64;
	private String imageLoc;
	private String imageDescription;
	private String imageType;
	private int loginID;
	private int productID;


}
