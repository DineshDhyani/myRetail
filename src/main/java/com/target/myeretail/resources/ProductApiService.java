package com.target.myeretail.resources;

import javax.ws.rs.core.Response;

import com.target.myeretail.model.Product;

public abstract class ProductApiService {

	public abstract Response getProductDetails(String id);

	public abstract Response updateProductDetails(String id, Product product);

	public abstract Response getProductNameFromExternalAPI(String id, String fields, String id_type, String key) ;

	
}
