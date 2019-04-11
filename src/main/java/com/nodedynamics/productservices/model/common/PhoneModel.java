package com.nodedynamics.productservices.model.common;



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
public class PhoneModel extends CoreModel{
	
	@Id
	private String phoneID;
	private String phoneName;
	private String phoneDescription;
	private String phoneNumber;
	private String phoneNumberExt;

}
