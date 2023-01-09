/**
 * ReserveService.java
 * 
 * @copyright 2023, Christian Ariel Modesto Duarte. All rights reserved.
 */
package ar.com.camd.hotel.service;

import java.util.List;

import ar.com.camd.hotel.model.Reserve;

/**
 * <code>ReserveService</code>
 * Provides several reserve services.
 * 
 * @author   Christian Ariel Modesto Duarte <duarte.camd@gmail.com>
 * @version  1.0.0-SNAPSHOT
 */
public interface ReserveService {
	
	/**
	 * Gets all reservations.
	 * @return The reservations.
	 */
	public List<Reserve> getAll();
	
}
