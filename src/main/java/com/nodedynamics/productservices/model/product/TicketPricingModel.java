package com.nodedynamics.productservices.model.product;

import org.springframework.data.annotation.Id;
import com.nodedynamics.productservices.model.CoreModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
@Builder
public class TicketPricingModel extends CoreModel{
	
	@Id
	private String ticketPricingID;
	private String ticketPricingTitle;
	private String ticketPricingDescription;
	private String ticketPricingCurrency;
	private Double ticketPricingAmount;
	private Integer ticketPricingNumber;
	private Integer maxTicket;
	private String taxesID;
	
}
