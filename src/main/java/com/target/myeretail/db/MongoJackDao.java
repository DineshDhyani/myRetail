package com.target.myeretail.db;

import org.mongojack.DBCursor;
import org.mongojack.DBQuery.Query;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

/**
 * mongojack db service to handle CRUD actions in db
 * 
 * @author ddhyani
 *
 * @param <T>
 */
public interface MongoJackDao<T> {

	/**
	 * save the document
	 * 
	 * @param document
	 * @return
	 * @throws Exception
	 */
	public abstract Object save(T obj) throws Exception;

	public abstract Object saveAndReturnObject(T obj) throws Exception;
	
	public Object update(DBObject query, DBObject updateObject) throws Exception;
	
	public boolean updateMulti(DBObject query, DBObject updateObject) throws Exception;
	
	public Object update(DBObject query, T updateObject) throws Exception;
	
	public Object updateGroup(DBObject query, T updateObject) throws Exception;
	
	

	public boolean updateSingleField(DBObject query, DBObject updateObject) throws Exception;

	public abstract DBCursor<T> getAllWithPagination(DBObject query, int offset, int limit, String sortBy) throws Exception;

	// public abstract DBCursor<T> getAllWithPagination(DBObject query,String
	// offset,int limit) throws Exception;

	public Object update(Query query, T updateObject) throws Exception;
	

	/**
	 * get the document
	 * 
	 * @param query
	 * @return
	 * @throws Exception
	 */
	public abstract Object get(DBObject query) throws Exception;
	public abstract Object get(String query) throws Exception;

	/**
	 * get all document
	 * 
	 * @return
	 * @throws Exception
	 */
	public abstract DBCursor<T> getAll(DBObject query) throws Exception;
	
	public abstract DBCursor<T> projectField(DBObject query, String field) throws Exception;

	/**
	 * remove the document
	 * 
	 * @param query
	 * @return
	 * @throws Exception
	 */
	public abstract Object remove(DBObject query) throws Exception;

	public abstract Object findAndModify(BasicDBObject searchQuery, BasicDBObject updateQuery) throws Exception;

	public abstract Object findOne(BasicDBObject query, BasicDBObject fields);

}
