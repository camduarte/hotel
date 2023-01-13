/**
 * GuestService.java
 * 
 * @copyright 2023, Christian Ariel Modesto Duarte. All rights reserved.
 */
package ar.com.camd.hotel.service;

import java.util.List;

import ar.com.camd.hotel.model.Guest;

/**
 * <code>GuestService</code>
 * Provides several guest services.
 * 
 * @author   Christian Ariel Modesto Duarte <duarte.camd@gmail.com>
 * @version  1.0.0-SNAPSHOT
 */
public interface GuestService {

	/**
	 * Gets all guests.
	 * @return The guests.
	 */
	public List<Guest> getAll();

	/**
	 * Gets guests by last name.
	 * @return The guests.
	 */
	public List<Guest> getByLastName(String lastName);

	/**
	 * Gets guests by reserve id.
	 * @param reserveId The reserve id.
	 * @return The guest.
	 */
	public Guest getByReserveId(Integer reserveId);
	
	/**
	 * Removes the guest by id.
	 * @param id The guest id.
	 * @return The guests amount removed.
	 */
	public Integer remove(Integer id);

	/**
	 * Updates the guest.
	 * @param guest The guest.
	 * @return The guests amount updated.
	 */
	public Integer update(Guest guest);

	/**
	 * Saves the guest.
	 * 
	 * @param guest The guest.
	 */
	public Guest save(Guest guest);
}
