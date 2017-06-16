package com.target.myeretail.impl;

import javax.ws.rs.core.Response;

import org.bson.types.ObjectId;
import org.mongojack.JacksonDBCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mongodb.BasicDBObject;
import com.target.myeretail.db.MongoJackDao;
import com.target.myeretail.db.MongoJackDaoImpl;
import com.target.myeretail.exception.ProductException;
import com.target.myeretail.model.CurrentPrice;
import com.target.myeretail.model.Product;
import com.target.myeretail.resources.ProductApiService;
import com.target.myeretail.util.ApiResponseMessage;
import com.target.myeretail.util.MongoUtils;
import com.target.myeretail.util.ResponseError;

public class ProductApiServiceImpl extends ProductApiService {

	static MongoJackDao<Product> productDao;
	protected final static Logger LOG = LoggerFactory.getLogger(ProductApiServiceImpl.class);

	static {
		JacksonDBCollection<Product, String> productDb = JacksonDBCollection
				.wrap(MongoUtils.getCollectionByName(ProductConstants.PRODUCTS), Product.class, String.class);

		productDao = new MongoJackDaoImpl<Product>(productDb);
	}

	@Override
	public Response getProductDetails(String id) {
		Product product =null;
		try {
			 product = (Product) productDao.get(new BasicDBObject("_id", new ObjectId(id)));
		
		return getSuccessResponse(product);
	} catch (ProductException e) {
		return getProductExceptionResponse(e);
	} catch (Exception e) {
		return getUnknownExceptionResponse(e);
	}

	}
	public static void main(String[] args) {
	//
		Product p  = new Product();
		p.setName("test");
		CurrentPrice cp= new CurrentPrice();
		cp.setCurrency_code("INDIA");
		cp.setValue(156.0);
		p.setCurrent_price(cp);
		
		try {
			// productDao.save(p);
			System.out.println("------- insert done ------- ");
			Object product = (Product) productDao.get("59430af599f52e216569a22b");
			System.out.println(product);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	@Override
	public Response updateProductDetails(String id, Product product) {
		try{
		BasicDBObject query = new BasicDBObject("_id", new ObjectId(id));
		product.setId(null);
	Product result =	(Product) productDao.update(query, product);
	return getSuccessResponse(product);
	
		} catch (ProductException e) {
		return getProductExceptionResponse(e);
	} catch (Exception e) {
		return getUnknownExceptionResponse(e);
	}
	}
	
	private Response getSuccessResponse(Object result) {
		return Response.ok().entity(new ApiResponseMessage(result)).build();
	}

	private Response getProductExceptionResponse(ProductException e) {
		ResponseError error = new ResponseError().code(e.getCode()).message(e.getMessage());
		return Response.status(e.getStatus()).entity(new ApiResponseMessage().error(error)).build();
	}


	private Response getUnknownExceptionResponse(Exception e) {
		e.printStackTrace();
		ResponseError error = new ResponseError().message(e.getMessage());
		ApiResponseMessage res = new ApiResponseMessage().error(error);
		return Response.serverError().entity(res).build();
	}

}
