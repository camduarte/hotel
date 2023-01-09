/**
 * Dao.java
 * 
 * @copyright 2023, Christian Ariel Modesto Duarte. All rights reserved.
 */
package ar.com.camd.hotel.dao;

import java.util.List;

/**
 * <code>GenericDao</code>
 * Basic CRUD interface.
 * 
 * @author   Christian Ariel Modesto Duarte <duarte.camd@gmail.com>
 * @version  1.0.0-SNAPSHOT
 */
public interface Dao<T> {
	
	/**
    * Persist the given entity into through EntityManager.
    * 
    * @param t entity to be saved.
    */
   public T save(T t);

   /**
    * Find all items of this type in the database.
    * 
    * @return a List of T elements from database.
    */
   public List<T> findAll();

   /**
    * Find an item from database based on its ID.
    * 
    * @param id to look for.
    * 
    * @return found entity or null if no entity is found.
    */
   public T find(Integer id);

   /**
    * Delete the item from database.
    * 
    * @param t item to delete.
    */
   public void remove(T t);
}