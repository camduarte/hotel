/**
 * ReserveDao.java
 * 
 * @copyright 2023, Christian Ariel Modesto Duarte. All rights reserved.
 */
package ar.com.camd.hotel.dao;

import java.sql.Connection;
import java.sql.Date;
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
	final private Connection con;

	private final String QRY_FIND_ALL = "SELECT id, checkin_date, checkout_date, value, payment_method FROM hotel.reserve";
	private final String QRY_REMOVE = "DELETE FROM hotel.reserve WHERE id = ?";
	private final String QRY_INSERT = "INSERT INTO hotel.reserve (checkin_date, checkout_date, value, payment_method) VALUES (?, ?, ?, ?)";
	private final String QRY_UPDATE = "UPDATE hotel.reserve SET checkin_date = ?, checkout_date = ?, value = ?, payment_method = ? WHERE id = ?";

	/**
	 * @param con The data base connection.
	 */
	public ReserveDao(Connection con) {
		this.con = con;
	}

	@Override
	public Reserve save(Reserve reserve) {
		try {
			final PreparedStatement preparedStatement = this.con.prepareStatement(QRY_INSERT, PreparedStatement.RETURN_GENERATED_KEYS);
			try(preparedStatement) {
				preparedStatement.setDate(1, Date.valueOf(reserve.getCheckinDate()));
				preparedStatement.setDate(2, Date.valueOf(reserve.getCheckoutDate()));
				preparedStatement.setBigDecimal(3, reserve.getValue());
				preparedStatement.setString(4, reserve.getPaymentMethod().name());
				preparedStatement.executeUpdate();

				final ResultSet resultSet = preparedStatement.getGeneratedKeys();
				try(resultSet) {
					if (resultSet.next()) {
						reserve.setId(resultSet.getInt(1));
						return reserve;
					} else {
						System.out.printf("We can't get reserve id. %n%s%n", reserve);
					}
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return reserve;
	}

	@Override
	public List<Reserve> findAll() {
		List<Reserve> reservations = new ArrayList<>();
		try {
			final PreparedStatement preparedStatement = con.prepareStatement(QRY_FIND_ALL);
			try(preparedStatement){
				final ResultSet resultSet = preparedStatement.executeQuery();
				try(resultSet) {
					while (resultSet.next()) {
						Reserve reserve = new Reserve(
							resultSet.getInt("id"), 
							resultSet.getDate("checkin_date").toLocalDate(),
							resultSet.getDate("checkout_date").toLocalDate(),
							resultSet.getBigDecimal("value"),
							PaymentMethod.valueOf(resultSet.getString("payment_method")));
						System.out.println(reserve);
						reservations.add(reserve);
					}
					return reservations;
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
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

	/**
	 * Removes the reserve by id.
	 * @param id The reserve id.
	 * @return The reservations amount removed.
	 */
	public Integer remove(Integer id) {
		try {
			final PreparedStatement preparedStatement = this.con.prepareStatement(QRY_REMOVE);
			try (preparedStatement) {
				preparedStatement.setInt(1, id);
				preparedStatement.executeUpdate();

				int updateCount = preparedStatement.getUpdateCount();
				System.out.printf("Cantidad de registros eliminados: %d%n", updateCount);
				return updateCount;
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Updates the reserve.
	 * @param reserve The reserve.
	 * @return The reserve amount updated.
	 */
	public Integer update(Reserve reserve) {
		try {
			final PreparedStatement preparedStatement = this.con.prepareStatement(QRY_UPDATE);
			try(preparedStatement) {
				preparedStatement.setDate(1, Date.valueOf(reserve.getCheckinDate()));
				preparedStatement.setDate(2, Date.valueOf(reserve.getCheckoutDate()));
				preparedStatement.setBigDecimal(3, reserve.getValue());
				preparedStatement.setString(4, reserve.getPaymentMethod().name());
				preparedStatement.setInt(5, reserve.getId());
				preparedStatement.executeUpdate();
				return preparedStatement.getUpdateCount();
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}