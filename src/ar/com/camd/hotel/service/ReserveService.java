/**
 * ReserveService.java
 * 
 * @copyright 2023, Christian Ariel Modesto Duarte. All rights reserved.
 */
package ar.com.camd.hotel.service;

import java.math.BigDecimal;
import java.time.LocalDate;
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

	/**
	 * Removes the reserve by id.
	 * @param id The reserve id.
	 * @return The reservations amount removed.
	 */
	public Integer remove(Integer id);
	
	/**
    * Saves the reserve.
    * 
    * @param reserve The reserve.
    */
	public Reserve save(Reserve reserve);
	
	/**
	 * Calculates the reservation value.
	 * @return The reservation value.
	 */
	public BigDecimal calculateValue(LocalDate checkin, LocalDate checkout);
}
