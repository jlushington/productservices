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
public class TaxesModel extends CoreModel{
	
	@Id
	private String iD;
	private String taxName;
	private String taxDescription;
	private Double taxAmount;
	private String taxAmountType;
}
