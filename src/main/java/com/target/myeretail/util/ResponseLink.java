package com.target.myeretail.util;


public class ResponseLink {
	private String nextOffset;

	public String getNextOffset() {
		return nextOffset;
	}

	public void setNextOffset(String nextOffset) {
		this.nextOffset = nextOffset;
	}

	public ResponseLink nextOffset(String nextOffset) {
		this.nextOffset = nextOffset;
		return this;
	}
}
