/**
 * FindController.java
 * 
 * @copyright 2023, Christian Ariel Modesto Duarte. All rights reserved.
 */
package ar.com.camd.hotel.controller;

import java.util.List;

import ar.com.camd.hotel.model.Guest;
import ar.com.camd.hotel.model.Reserve;
import ar.com.camd.hotel.service.GuestService;
import ar.com.camd.hotel.service.GuestServiceImpl;
import ar.com.camd.hotel.service.ReserveService;
import ar.com.camd.hotel.service.ReserveServiceImpl;

/**
 * <code>FindController</code>
 * Binds UI with data for reservation and guest.
 * 
 * @author   Christian Ariel Modesto Duarte <duarte.camd@gmail.com>
 * @version  1.0.0-SNAPSHOT
 */
public class FindController {

	private ReserveService reserveService;
	private GuestService guestService;

	/**
	 * Initialize the reserve service and guest service.
	 */
	public FindController() {
		this.reserveService = new ReserveServiceImpl();
		this.guestService = new GuestServiceImpl();
	}

	/**
	 * Gets all the reservations.
	 * @return The reservations.
	 */
	public List<Reserve> getReservations() {
		return this.reserveService.getAll();
	}

	/**
	 * Gets all the guests.
	 * @return The guests.
	 */
	public List<Guest> getGuests() {
		return this.guestService.getAll();
	}
}