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
}