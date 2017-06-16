package com.target.myeretail.util;


public class SourceErrorPointer {
	private String pointer;
	private String parameter;

	public String getPoniter() {
		return pointer;
	}

	public void setPoniter(String poniter) {
		this.pointer = poniter;
	}

	public SourceErrorPointer poniter(String poniter) {
		this.pointer = poniter;
		return this;
	}

	public String getParameter() {
		return parameter;
	}

	public void setParameter(String parameter) {
		this.parameter = parameter;
	}

	public SourceErrorPointer parameter(String parameter) {
		this.parameter = parameter;
		return this;
	}

}
