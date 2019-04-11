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
public class ResponseModel extends CoreModel{
	
	private String MessageType;
	private int MessageTypeID;
	private String Message;

}
