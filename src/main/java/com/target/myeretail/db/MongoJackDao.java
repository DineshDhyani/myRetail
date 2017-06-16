package com.target.myeretail.db;

import com.mongodb.DBObject;

/**
 * mongojack db service to handle CRUD actions in db
 * 
 * @author ddhyani
 *
 * @param <T>
 */
public interface MongoJackDao<T> {

	public abstract Object save(T obj) throws Exception;

	public Object update(DBObject query, T updateObject) throws Exception;

	public abstract Object get(DBObject query) throws Exception;

	public abstract Object get(String query) throws Exception;

}
