package com.target.myeretail.util;

public class ResponseError {
	private String code;
	private String message;
	private SourceErrorPointer source;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public ResponseError code(String code) {
		this.code = code;
		return this;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public ResponseError message(String message) {
		this.message = message;
		return this;
	}

	public SourceErrorPointer getSource() {
		return source;
	}

	public void setSource(SourceErrorPointer source) {
		this.source = source;
	}

	public ResponseError source(SourceErrorPointer source) {
		this.source = source;
		return this;
	}

}
