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
@Document(collection="Taxes")
public class TaxesModel extends CoreModel{
	
	@Id
	private String iD;
	private String taxName;
	private String taxDescription;
	private Double taxAmount;
	private String taxAmountType;
}
