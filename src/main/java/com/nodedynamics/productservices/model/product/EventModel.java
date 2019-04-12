package com.nodedynamics.productservices.model.product;

import java.util.List;
import java.util.Optional;

import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.nodedynamics.productservices.model.CoreModel;
import com.nodedynamics.productservices.model.common.ImageModel;
import com.nodedynamics.productservices.model.common.PhoneModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
@Builder
@Document(collection="ProductEvent")
public class EventModel extends CoreModel{
	
	@Id
	private String iD;
	private String userID;
	
	@NotNull(message = "Event Name must not be null")
	private String eventName;
	
	@NotNull(message = "Event Description must not be null")
	private String eventDescription;
	
	@NotNull(message = "Location ID must not be null")
	private String locationID;
	private Optional<List<PhoneModel>> eventNumbers;
	
	@NotNull(message = "Age Requirement must not be null")
	private String age;
	private String eventURL;
	
	@NotNull(message = "Event Start Date must not be null")
	private String eventStartDate;
	
	@NotNull(message = "Event End Date must not be null")
	private String eventEndDate;
	
	@NotNull(message = "Event Ticket Information must not be null")
	private List<TicketPricingModel> eventPricing;
	
	private Optional<List<ImageModel>>eventImage;;
	
	private String productType;
	
	
}
