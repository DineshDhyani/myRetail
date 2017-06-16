package com.target.myeretail.Response;

import com.target.myeretail.exception.ProductException;

public class RetailResponse {
	public static javax.ws.rs.core.Response getSuccessResponse(Object result) {
		return javax.ws.rs.core.Response.ok().entity(new ApiResponseMessage(result)).build();
	}

	public static javax.ws.rs.core.Response getProductExceptionResponse(ProductException e) {
		ResponseError error = new ResponseError().code(e.getCode()).message(e.getMessage());
		return javax.ws.rs.core.Response.status(e.getStatus()).entity(new ApiResponseMessage().error(error)).build();
	}

	public static javax.ws.rs.core.Response getUnknownExceptionResponse(Exception e) {
		e.printStackTrace();
		ResponseError error = new ResponseError().message(e.getMessage());
		ApiResponseMessage res = new ApiResponseMessage().error(error);
		return javax.ws.rs.core.Response.serverError().entity(res).build();
	}

}
