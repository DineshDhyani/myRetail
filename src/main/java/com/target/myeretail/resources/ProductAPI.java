package com.target.myeretail.resources;



import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.target.myeretail.impl.ProductApiServiceImpl;
import com.target.myeretail.model.Product;
import com.wordnik.swagger.annotations.ApiParam;

@Path("myretail/product")
@Produces(MediaType.APPLICATION_JSON)
public class ProductAPI {
	ProductApiService delegate;
	
	public ProductAPI() {

		delegate = new ProductApiServiceImpl();
	}
    

    @GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
	public Response getProductDetails(
			@PathParam("id") String id 
			)    {
		System.out.println("getting product details for id:: "+ id);
		return  delegate.getProductDetails(id);
	}
    
    
    
    
    
    @Path("/{id}")
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateProductDetails(@PathParam("id") String id,
    		@ApiParam(value = "update data") Product product
    		) {
    	
    	return  delegate.updateProductDetails(id, product);
    }
}