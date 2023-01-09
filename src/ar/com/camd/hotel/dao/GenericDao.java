/**
 * GenericDao.java
 * 
 * @copyright 2023, Christian Ariel Modesto Duarte. All rights reserved.
 */
package ar.com.camd.hotel.dao;

import java.util.List;

/**
 * <code>GenericDao</code>
 * Implements basic CRUD.
 * 
 * @author   Christian Ariel Modesto Duarte <duarte.camd@gmail.com>
 * @version  1.0.0-SNAPSHOT
 * @param <T>
 */
public class GenericDao<T> implements Dao<T> {

	@Override
	public T save(T t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<T> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T find(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void remove(T t) {
		// TODO Auto-generated method stub
		
	}
}