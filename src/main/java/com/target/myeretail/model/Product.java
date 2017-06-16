package com.target.myeretail.model;

import org.bson.types.ObjectId;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Product {
	@JsonProperty("_id")
	private ObjectId id=null;
	private String name;
	private CurrentPrice current_price = null;

	@Override
	public String toString() {
		return "Product [_id=" + id + ", name=" + name + ", current_price=" + current_price + "]";
	}
	@JsonIgnore(value = true)
	public String getId() {
		return id.toString();
	}
	@JsonIgnore(value = true)
	public void setId(String id) {
		this.id = new ObjectId(id);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public CurrentPrice getCurrent_price() {
		return current_price;
	}

	public void setCurrent_price(CurrentPrice current_price) {
		this.current_price = current_price;
	}

}
