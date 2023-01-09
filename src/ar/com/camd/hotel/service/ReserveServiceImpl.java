/**
 * ReserveServiceImpl.java
 * 
 * @copyright 2023, Christian Ariel Modesto Duarte. All rights reserved.
 */
package ar.com.camd.hotel.service;

import java.util.List;

import ar.com.camd.hotel.dao.ReserveDao;
import ar.com.camd.hotel.factory.ConnectionFactory;
import ar.com.camd.hotel.model.Reserve;

/**
 * <code>ReserveServiceImpl</code>
 * Provides several reserve services.
 * 
 * @author   Christian Ariel Modesto Duarte <duarte.camd@gmail.com>
 * @version  1.0.0-SNAPSHOT
 */
public class ReserveServiceImpl implements ReserveService {

	private ReserveDao reserveDao;

	/**
	 * Initialize the reserve dao.
	 */
	public ReserveServiceImpl() {
		this.reserveDao = new ReserveDao(new ConnectionFactory().recuperarConexion());
	}

	@Override
	public List<Reserve> getAll() {
		return this.reserveDao.findAll();
	}

}
