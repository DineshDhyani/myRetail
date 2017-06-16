package com.target.myeretail.util;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;
import com.target.myeretail.impl.ProductConstants;

/**
 * singleton interface to get mongo db instance
 * 
 * @author ddhyani
 *
 */
public class MongoUtils {
	/**
	 * mongo client
	 */
	private static MongoClient mongoclient;

	/**
	 * not exposed constructor
	 */
	private MongoUtils() {

	}

	/**
	 * singleton mongodb instance
	 * 
	 * @return
	 */
	public static MongoClient getInstance() {

		if (mongoclient == null) {

			mongoclient = new MongoClient(new ServerAddress(ProductConstants.HOSTNAME, ProductConstants.PORT)
					);

		}
		return mongoclient;
	}

	/**
	 * get db collection
	 * 
	 * @param collectionName
	 * @return
	 */
	public static DBCollection getCollectionByName(String collectionName) {

		MongoClient client = MongoUtils.getInstance();
		DB db = client.getDB(ProductConstants.DBNAME);
		DBCollection col = db.getCollection(collectionName);

		return col;
	}

	

}
