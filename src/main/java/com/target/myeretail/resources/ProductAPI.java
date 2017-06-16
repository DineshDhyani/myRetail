package com.target.myeretail.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.target.myeretail.impl.ProductApiServiceImpl;
import com.target.myeretail.model.Product;
import com.wordnik.swagger.annotations.ApiParam;

@Path("myretail/v1")
@Produces(MediaType.APPLICATION_JSON)
public class ProductAPI {

	private ProductApiService delegate;

	public ProductAPI() {

		delegate = new ProductApiServiceImpl();
	}

	@GET
	@Path("products/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response getProductDetails(@PathParam("id") String id) {
		System.out.println("getting product details for id:: " + id);
		return delegate.getProductDetails(id);
	}

	@Path("products/{id}")
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateProductDetails(@PathParam("id") String id, @ApiParam(value = "update data") Product product) {

		return delegate.updateProductDetails(id, product);
	}
	
/**
 * external api mocking for api.target.com
 * TO DO: needs to be used only in home test environment :)
 * @param id
 * @return
 */
	@GET
	@Path("mockurl/products/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response getProductNameFromExternalAPI(@PathParam("id") String id,
			@QueryParam("fields") String fields,
			@QueryParam("id_type") String id_type,
			@QueryParam("key") String key
			
			) {
		System.out.println("getting product details for id:: " + id);
		return delegate.getProductNameFromExternalAPI( id,  fields, id_type, key   );
	}

}