package com.target.myeretail.impl;

import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import javax.ws.rs.core.Response;

import org.mongojack.JacksonDBCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mongodb.BasicDBObject;
import com.target.myeretail.Response.RetailResponse;
import com.target.myeretail.constants.ProductConstants;
import com.target.myeretail.db.MongoJackDao;
import com.target.myeretail.db.MongoJackDaoImpl;
import com.target.myeretail.exception.ProductException;
import com.target.myeretail.model.Product;
import com.target.myeretail.resources.ProductApiService;
import com.target.myeretail.util.MongoUtils;
import com.target.myeretail.util.TargetClient;

public class ProductApiServiceImpl extends ProductApiService {

	static MongoJackDao<Product> productPriceDao;
	static MongoJackDao<Product> productNameDao;
	
	protected final static Logger LOG = LoggerFactory.getLogger(ProductApiServiceImpl.class);

	static {
		JacksonDBCollection<Product, String> productPriceDb = JacksonDBCollection
				.wrap(MongoUtils.getCollectionByName(ProductConstants.PRODUCTS_PRICE), Product.class, String.class);

		JacksonDBCollection<Product, String> productNameDb = JacksonDBCollection
				.wrap(MongoUtils.getCollectionByName(ProductConstants.PRODUCTS_NAME), Product.class, String.class);

		productPriceDao = new MongoJackDaoImpl<Product>(productPriceDb);
		productNameDao = new MongoJackDaoImpl<Product>(productNameDb);
	}

	@Override
	public Response getProductDetails(String id) {
		Product product = null;
		try {
			LOG.info("get product details for id", id);
			
			LOG.info("Async call to api.target.com for product name");
			Future<Product> future= Executors.newCachedThreadPool().submit(
					new TargetClient(id, "descriptions", "TCIN", "43cJWpLjH8Z8oR18KdrZDBKAgLLQKJjz"));
			
			product = (Product) productPriceDao.get(id);

			product.setName(future.get().getName());

			return RetailResponse.getSuccessResponse(product);
		} catch (ProductException e) {
			return RetailResponse.getProductExceptionResponse(e);
		} catch (Exception e) {
			return RetailResponse.getUnknownExceptionResponse(e);
		}

	}


	@Override
	public Response updateProductDetails(String id, Product product) {
		try {
			LOG.info("updating product with id " , id);
			BasicDBObject query = new BasicDBObject("id", id);
			product.setId(null);
			Product result = (Product) productPriceDao.update(query, product);
			return RetailResponse.getSuccessResponse(result);

		} catch (ProductException e) {
			return RetailResponse.getProductExceptionResponse(e);
		} catch (Exception e) {
			return RetailResponse.getUnknownExceptionResponse(e);
		}
	}

	@Override
	public Response getProductNameFromExternalAPI(String id, String fields, String id_type, String key) {

		try {
			LOG.info("external api (mocked) called for id " , id);
			Product productName = (Product) productNameDao
					.get(new BasicDBObject("id", id).append("fields", fields).append("id_type", id_type));
			return RetailResponse.getSuccessResponse(productName);

		} catch (ProductException e) {
			return RetailResponse.getProductExceptionResponse(e);
		} catch (Exception e) {
			return RetailResponse.getUnknownExceptionResponse(e);
		}
	}
	
	/*public static void main(String[] args) {

	try {
		System.out.println("------- insert done ------- ");
		Product productPrice = (Product) productPriceDao.get("56789");

		System.out.println(productPrice);

		Product productName = (Product) productNameDao
				.get(new BasicDBObject("id", "56789").append("fields", "descriptions").append("id_type", "TCIN"));
		System.out.println(productName.getName());
		productPrice.setName(productName.getName());

		System.out.println("final product " + productPrice);
	} catch (Exception e) {
		e.printStackTrace();
	}
}
*/

	
}
