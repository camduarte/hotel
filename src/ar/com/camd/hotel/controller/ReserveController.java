/**
 * ReserveController.java
 * 
 * @copyright 2023, Christian Ariel Modesto Duarte. All rights reserved.
 */
package ar.com.camd.hotel.controller;

import java.math.BigDecimal;
import java.time.LocalDate;

import ar.com.camd.hotel.model.Reserve;
import ar.com.camd.hotel.service.ReserveService;
import ar.com.camd.hotel.service.ReserveServiceImpl;

/**
 * <code>ReserveController</code>
 * Binds UI with data for reservation load.
 * 
 * @author   Christian Ariel Modesto Duarte <duarte.camd@gmail.com>
 * @version  1.0.0-SNAPSHOT
 */
public class ReserveController {
	private ReserveService reserveService;

	/**
	 * Initialize reserve service.
	 */
	public ReserveController() {
		this.reserveService = new ReserveServiceImpl();
	}

	/**
	 * Calculates the reservation value.
	 * @return The reservation value.
	 */
	public BigDecimal calculateValue(LocalDate checkin, LocalDate checkout) {
		return this.reserveService.calculateValue(checkin, checkout);
	}
	
	/**
    * Saves the reserve.
    * 
    * @param reserve The reserve.
    */
	public Reserve save(Reserve reserve) {
		return this.reserveService.save(reserve);
	}
}