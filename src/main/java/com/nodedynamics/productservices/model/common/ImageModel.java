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
	
	private String ImageID;
	private String ImageName;
	private String ImageLoc;
	private String ImageDescription;
	private String ImageType;
	private int LoginID;
	private int ProductID;
	private int UserTypeID;
	private int VendorID;
	private int VendorStoreID;


}
