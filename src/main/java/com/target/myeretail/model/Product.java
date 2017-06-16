package com.target.myeretail.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Product {
	@JsonProperty("_id")
	private String id = null;

	@JsonProperty("id")
	private String productid;
	//@JsonIgnore
	private CurrentPrice current_price = null;
	@JsonIgnore
	private String fields;
	
	@JsonProperty("name")
	private String name;

	private String id_type;
	
	
	
	public String getFields() {
		return fields;
	}

	public void setFields(String fields) {
		this.fields = fields;
	}

	public String getId_type() {
		return id_type;
	}

	public void setId_type(String id_type) {
		this.id_type = id_type;
	}

	
	@JsonIgnore(value = true)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getProductid() {
		return productid;
	}

	public void setProductid(String productid) {
		this.productid = productid;
	}


	@JsonIgnore(value = true)
	public String getId() {
		return id.toString();
	}

	@JsonIgnore(value = true)
	public void setId(String id) {
		this.id = id;
	}

	public CurrentPrice getCurrent_price() {
		return current_price;
	}

	public void setCurrent_price(CurrentPrice current_price) {
		this.current_price = current_price;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", productid=" + productid + ", current_price=" + current_price + ", fields="
				+ fields + ", name=" + name + ", id_type=" + id_type + "]";
	}
	

	

}
