/**
 * ReserveDao.java
 * 
 * @copyright 2023, Christian Ariel Modesto Duarte. All rights reserved.
 */
package ar.com.camd.hotel.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ar.com.camd.hotel.model.PaymentMethod;
import ar.com.camd.hotel.model.Reserve;

/**
 * <code>ReserveDao</code> Allows CRUD operations for reserve entity.
 * 
 * @author Christian Ariel Modesto Duarte <duarte.camd@gmail.com>
 * @version 1.0.0-SNAPSHOT
 */
public class ReserveDao implements Dao<Reserve> {
	private Connection con;

	private final String QRY_FIND_ALL = "SELECT id, entry_date, exit_date, value, payment_method FROM hotel.reserve";

	/**
	 * @param con The data base connection.
	 */
	public ReserveDao(Connection con) {
		this.con = con;
	}

	@Override
	public Reserve save(Reserve t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Reserve> findAll() {
		List<Reserve> reservations = new ArrayList<>();
		try {
			PreparedStatement preparedStatement = con.prepareStatement(QRY_FIND_ALL);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Reserve reserve = new Reserve(
						resultSet.getInt("id"), 
						resultSet.getDate("entry_date").toLocalDate(),
						resultSet.getDate("exit_date").toLocalDate(),
						resultSet.getBigDecimal("value"),
						PaymentMethod.valueOf(resultSet.getString("payment_method")));
				System.out.println(reserve);
				reservations.add(reserve);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return reservations;
	}

	@Override
	public Reserve find(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void remove(Reserve t) {
		// TODO Auto-generated method stub

	}

}