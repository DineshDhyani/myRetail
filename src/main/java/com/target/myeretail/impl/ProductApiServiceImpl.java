package com.target.myeretail.impl;

import javax.ws.rs.core.Response;

import org.mongojack.JacksonDBCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mongodb.BasicDBObject;
import com.target.myeretail.db.MongoJackDao;
import com.target.myeretail.db.MongoJackDaoImpl;
import com.target.myeretail.exception.ProductException;
import com.target.myeretail.model.Product;
import com.target.myeretail.resources.ProductApiService;
import com.target.myeretail.util.ApiResponseMessage;
import com.target.myeretail.util.MongoUtils;
import com.target.myeretail.util.ResponseError;
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
			product = (Product) productPriceDao.get(id);

			TargetClient t = new TargetClient();
			t.init(id, "descriptions", "TCIN", "43cJWpLjH8Z8oR18KdrZDBKAgLLQKJjz");
			product.setName(t.getProduct().getName());

			return getSuccessResponse(product);
		} catch (ProductException e) {
			return getProductExceptionResponse(e);
		} catch (Exception e) {
			return getUnknownExceptionResponse(e);
		}

	}

	public static void main(String[] args) {

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

	@Override
	public Response updateProductDetails(String id, Product product) {
		try {
			BasicDBObject query = new BasicDBObject("id", id);
			product.setId(null);
			Product result = (Product) productPriceDao.update(query, product);
			return getSuccessResponse(result);

		} catch (ProductException e) {
			return getProductExceptionResponse(e);
		} catch (Exception e) {
			return getUnknownExceptionResponse(e);
		}
	}

	@Override
	public Response getProductNameFromExternalAPI(String id, String fields, String id_type, String key) {

		try {
			Product productName = (Product) productNameDao
					.get(new BasicDBObject("id", id).append("fields", fields).append("id_type", id_type));
			return getSuccessResponse(productName);

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
