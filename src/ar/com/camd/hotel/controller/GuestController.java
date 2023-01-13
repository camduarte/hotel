/**
 * GuestController.java
 * 
 * @copyright 2023, Christian Ariel Modesto Duarte. All rights reserved.
 */

package ar.com.camd.hotel.controller;

import ar.com.camd.hotel.service.GuestService;
import ar.com.camd.hotel.service.GuestServiceImpl;

/**
 * <code>GuestController</code>
 * Binds guest view with data.
 * 
 * @author   Christian Ariel Modesto Duarte <duarte.camd@gmail.com>
 * @version  1.0.0-SNAPSHOT
 */
public class GuestController {
	private GuestService guestService;
	
	/**
	 * Initialize the guest service.
	 */
	public GuestController() {
		this.guestService = new GuestServiceImpl();
	}
}