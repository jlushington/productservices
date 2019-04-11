package com.nodedynamics.productservices.model.venue;

import org.springframework.data.annotation.Id;

import com.nodedynamics.productservices.model.CoreModel;
import com.nodedynamics.productservices.model.common.ImageModel;
import com.nodedynamics.productservices.model.common.ImageModel.ImageModelBuilder;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;


@AllArgsConstructor
@Getter
@ToString
@Builder
public class VenueModel extends CoreModel{
	
	@Id
	private String venueID;
	private String venueName;
	private String venueDesciption;
	private String Address1;
	private String Address2;
	private String venueCity;
	private String venueProvince;
	private String venueCountry;
	private String venuePostalCode;

}
