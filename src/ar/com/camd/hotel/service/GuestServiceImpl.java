/**
 * GuestServiceImpl.java
 * 
 * @copyright 2023, Christian Ariel Modesto Duarte. All rights reserved.
 */
package ar.com.camd.hotel.service;

import java.util.List;

import ar.com.camd.hotel.dao.GuestDao;
import ar.com.camd.hotel.factory.ConnectionFactory;
import ar.com.camd.hotel.model.Guest;

/**
 * <code>GuestServiceImpl</code>
 * Insert class description here.
 * 
 * @author   Christian Ariel Modesto Duarte <duarte.camd@gmail.com>
 * @version  1.0.0-SNAPSHOT
 */
public class GuestServiceImpl implements GuestService {

	private GuestDao guestDao;

	/**
	 * Initialize the guest dao.
	 */
	public GuestServiceImpl() {
		this.guestDao = new GuestDao(new ConnectionFactory().recuperarConexion());
	}

	@Override
	public List<Guest> getAll() {
		return this.guestDao.findAll();
	}

}
