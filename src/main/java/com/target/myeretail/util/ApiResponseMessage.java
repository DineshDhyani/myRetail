package com.target.myeretail.util;



@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2016-03-28T13:06:53.817Z")
public class ApiResponseMessage {

	ResponseError error;
	Object data;
	Object meta;
	ResponseLink links;

	public ApiResponseMessage() {
	}

	public ApiResponseMessage(Object data) {
		this(data, null);
	}

	public ApiResponseMessage(Object data, ResponseError error) {
		this(data, error, null);
	}

	public ApiResponseMessage(Object data, ResponseError error, Object meta) {
		this(data, error, meta, null);
	}

	public ApiResponseMessage(Object data, ResponseError error, Object meta, ResponseLink links) {
		this.data = data;
		this.error = error;
		this.meta = meta;
		this.links = links;
	}

	public ResponseError getError() {
		return error;
	}

	public ApiResponseMessage error(ResponseError error) {
		this.error = error;
		return this;
	}

	public Object getData() {
		return data;
	}

	public ApiResponseMessage data(Object data) {
		this.data = data;
		return this;

	}

	public Object getMeta() {
		return meta;
	}

	public ApiResponseMessage meta(Object meta) {
		this.meta = meta;
		return this;

	}

	public ResponseLink getLinks() {
		return links;
	}

	public ApiResponseMessage links(ResponseLink links) {
		this.links = links;
		return this;

	}

}
