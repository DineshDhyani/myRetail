package com.target.myeretail.db;

import org.mongojack.DBProjection;
import org.mongojack.DBQuery;
import org.mongojack.JacksonDBCollection;
import org.mongojack.WriteResult;

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

	public Object update(DBObject query, T updateObject) throws Exception {
		System.out.println("update object" + updateObject);
		System.out.println("update query" + query);
		Object result = col.findAndModify(query, null, null, false, col.convertToDbObject(updateObject), true, false);
		System.out.println("updatd object :" + result);
		return result;
	}

	public Object save(T obj) throws Exception {
		WriteResult<T, String> result = col.insert(obj);
		return result.getSavedObject();
	}

	public Object get(DBObject query) throws Exception {
		T obj = col.findOne(query);
		return obj;
	}

	public Object get(String id) throws Exception {
		Object obj = (T) col.findOne(DBQuery.is("id", id), DBProjection.exclude("_id"));
		return obj;
	}

}
