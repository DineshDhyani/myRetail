package com.target.myeretail.db;

import static com.mongodb.client.model.Projections.include;

import org.mongojack.DBCursor;
import org.mongojack.DBProjection;
import org.mongojack.DBQuery;
import org.mongojack.DBQuery.Query;
import org.mongojack.JacksonDBCollection;
import org.mongojack.WriteResult;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

/**
 * mangojack db implementation
 * 
 * @author ddhyani
 *
 * @param <T>
 */
public class MongoJackDaoImpl<T> implements MongoJackDao<T> {

	JacksonDBCollection<T, String> col;

	public MongoJackDaoImpl(JacksonDBCollection<T, String> col) {

		this.col = col;
	}

	public Object update(DBObject query, DBObject updateObject) throws Exception {
		System.out.println("update object" + updateObject);
		System.out.println("update query" + query);
		Object result = col.findAndModify(query, null, null, false, updateObject, true, false);
		return result;
	}

	public Object update(DBObject query, T updateObject) throws Exception {
		System.out.println("update object" + updateObject);
		System.out.println("update query" + query);
		Object result = col.findAndModify(query, null, null, false, col.convertToDbObject(updateObject), true, false);
		System.out.println("updatd object :" + result);
		return result;
	}

	public Object updateGroup(DBObject query, T updateObject) throws Exception {
		System.out.println("update object" + updateObject);
		System.out.println("update query" + query);
		col.update(query, col.convertToDbObject(updateObject));
		return true;
	}

	public boolean updateSingleField(DBObject query, DBObject updateObject) throws Exception {
		col.update(query, updateObject, true, false);
		return true;
	}

	public boolean updateMulti(DBObject query, DBObject updateObject) throws Exception {
		col.update(query, updateObject, true, true);
		return true;
	}

	public Object save(T obj) throws Exception {
		WriteResult<T, String> result = col.insert(obj);
		return result.getSavedObject();
	}

	public Object saveAndReturnObject(T obj) throws Exception {
		WriteResult<T, String> result = col.insert(obj);
		return result.getSavedObject();
	}

	public Object update(Query query, T updateObject) throws Exception {
		System.out.println("is() query ::" + query);
		WriteResult<T, String> result = col.update(query, updateObject, true, false);
		return result.getSavedObject();
	}

	public Object get(DBObject query) throws Exception {
		T obj = col.findOne(query);
		return obj;
	}
	
	public Object get(String id) throws Exception {
		Object obj= (T) col.find(DBQuery.is("_id", id), DBProjection.exclude("_id"));
		return obj;
	}

	public Object remove(DBObject query) throws Exception {
		System.out.println(" running remove query "+query);
		Object result = col.remove(query);
		return result;
	}

	public DBCursor<T> getAll(DBObject query) throws Exception {
		System.out.println(" search query :" + query);
		DBCursor<T> cursor = col.find(query);

		return cursor;
	}

	public DBCursor<T> projectField(DBObject query, String field) throws Exception {
		System.out.println(" search query :" + query);
		include(field);
		DBCursor<T> cursor = col.find(query);

		return cursor;
	}

	public Object findAndModify(BasicDBObject searchQuery, BasicDBObject updateQuery) {

		DBObject result1 = (DBObject) col.findAndModify(searchQuery, null, null, false, updateQuery, true, false);
		return result1;
	}

	public Object findOne(BasicDBObject query, BasicDBObject fields) {

		T cursor = col.findOne(query, fields);
		return cursor;

	}

	public DBCursor<T> getAllWithPagination(DBObject query, int offset, int limit, String sortBy) throws Exception {
		System.out.println(query);

		DBObject orderBy = null;

		if (null == sortBy || sortBy.equals("recency")) {
			orderBy = new BasicDBObject("createdUpdatedTime", -1).append("_id", 1);
		}

		System.out.println("paginated request will be sent");

		DBCursor<T> cursor = col.find(query).skip(offset).sort(orderBy).limit(limit); // pagination
																						// applied
		return cursor;
	}

	

}
