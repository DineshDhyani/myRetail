package com.target.myeretail.exception;

import javax.ws.rs.core.Response.Status;


public class ProductException extends Exception {

	
	private ProductErrorCode code;
	private Object data;

	private Status status = Status.OK;
	private ProductErrorMessage message;
	
	public static enum ProductErrorMessage {
		InvalidId ;
	}
	
	
	public static enum ProductErrorCode {
		ID_NOT_FOUND ;
	}

	
	public ProductException(Status status, ProductErrorCode code, ProductErrorMessage message) {
		this(status, code, message, null);
	}

	public ProductException(Status status, ProductErrorCode code, ProductErrorMessage message, Object data) {
		this.status = status;
		this.code = code;
		this.message = message;
		this.data = data;
	}
	
	public String getCode() {
		return code.name();
	}

	public Status getStatus() {
		return status;
	}

	public Object getData() {
		return data;
	}

	public String getMessage() {
		switch (message) {
		case InvalidId:
			return "Please provide valid id to proceed further";
		default:
			return super.getMessage();
		}
	}


}
