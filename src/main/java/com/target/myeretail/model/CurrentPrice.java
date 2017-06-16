package com.target.myeretail.model;

public class CurrentPrice {
	
	@Override
	public String toString() {
		return "CurrentPrice [value=" + value + ", currency_code=" + currency_code + "]";
	}
	private Double value;
	private String currency_code="USD";
	
	public Double getValue() {
		return value;
	}
	public void setValue(Double value) {
		this.value = value;
	}
	public String getCurrency_code() {
		return currency_code;
	}
	public void setCurrency_code(String currency_code) {
		this.currency_code = currency_code;
	}

}
