/**
 * GuestDao.java
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

import ar.com.camd.hotel.model.Guest;
import ar.com.camd.hotel.model.Nationality;
import ar.com.camd.hotel.model.PaymentMethod;
import ar.com.camd.hotel.model.Reserve;

/**
 * <code>GuestDao</code>
 * Allows CRUD operations for guest entity.
 * 
 * @author   Christian Ariel Modesto Duarte <duarte.camd@gmail.com>
 * @version  1.0.0-SNAPSHOT
 */
public class GuestDao implements Dao<Guest> {

	private Connection con;

	private final String QRY_FIND_ALL = "SELECT "
			+ "g.id, "
			+ "g.name, "
			+ "g.lastname, "
			+ "g.birthdate, "
			+ "g.nationality, "
			+ "g.phone_number, "
			+ "r.id, "
			+ "r.checkin_date, "
			+ "r.checkout_date, "
			+ "r.value, "
			+ "r.payment_method "
			+ "FROM guest AS g "
			+ "INNER JOIN reserve AS r "
			+ "ON g.id = r.id";
	
	private final String QRY_FIND_BY_LAST_NAME = "SELECT "
			+ "g.id, "
			+ "g.name, "
			+ "g.lastname, "
			+ "g.birthdate, "
			+ "g.nationality, "
			+ "g.phone_number, "
			+ "r.id, "
			+ "r.checkin_date, "
			+ "r.checkout_date, "
			+ "r.value, "
			+ "r.payment_method "
			+ "FROM guest AS g "
			+ "INNER JOIN reserve AS r "
			+ "ON g.id = r.id "
			+ "AND g.lastname = ?";

	private final String QRY_FIND_BY_RESERVE_ID = "SELECT "
			+ "g.id, "
			+ "g.name, "
			+ "g.lastname, "
			+ "g.birthdate, "
			+ "g.nationality, "
			+ "g.phone_number, "
			+ "r.id, "
			+ "r.checkin_date, "
			+ "r.checkout_date, "
			+ "r.value, "
			+ "r.payment_method "
			+ "FROM guest AS g "
			+ "INNER JOIN reserve AS r "
			+ "ON g.id = r.id "
			+ "AND g.id_reserve = ?";

	private final String QRY_REMOVE = "DELETE FROM hotel.guest WHERE id = ?";

	/**
	 * @param con The data base connection.
	 */
	public GuestDao(Connection con) {
		this.con = con;
	}

	@Override
	public Guest save(Guest t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Guest> findAll() {
		List<Guest> guests = new ArrayList<>();
		try {
			PreparedStatement preparedStatement = con.prepareStatement(QRY_FIND_ALL);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Reserve reserve = new Reserve(
						resultSet.getInt("id"), 
						resultSet.getDate("checkin_date").toLocalDate(),
						resultSet.getDate("checkout_date").toLocalDate(),
						resultSet.getBigDecimal("value"),
						PaymentMethod.valueOf(resultSet.getString("payment_method")));
				
				Guest guest = new Guest(
						resultSet.getInt("id"), 
						resultSet.getString("name"),
						resultSet.getString("lastname"),
						resultSet.getDate("birthdate").toLocalDate(),
						Nationality.valueOf(resultSet.getString("nationality")),
						resultSet.getString("phone_number"),
						reserve);

				System.out.println(guest);
				guests.add(guest);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return guests;
	}

	@Override
	public Guest find(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Removes the guest by id.
	 * @param id The guest id.
	 * @return The guests amount removed.
	 */
	public Integer remove(Integer id) {
		try {
			final PreparedStatement preparedStatement = this.con.prepareStatement(QRY_REMOVE);
			try (preparedStatement) {
				preparedStatement.setInt(1, id);
				preparedStatement.execute();
			
				int updateCount = preparedStatement.getUpdateCount();
				System.out.printf("Cantidad de registros eliminados: %d%n", updateCount);
				return updateCount;
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Find guests by last name.
	 * @param lastName
	 * @return the guests.
	 */
	public List<Guest> find(String lastName) {
		List<Guest> guests = new ArrayList<>();
		try {
			PreparedStatement preparedStatement = con.prepareStatement(QRY_FIND_BY_LAST_NAME);
			preparedStatement.setString(1, lastName);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Reserve reserve = new Reserve(
						resultSet.getInt("id"), 
						resultSet.getDate("checkin_date").toLocalDate(),
						resultSet.getDate("checkout_date").toLocalDate(),
						resultSet.getBigDecimal("value"),
						PaymentMethod.valueOf(resultSet.getString("payment_method")));

				Guest guest = new Guest(
						resultSet.getInt("id"), 
						resultSet.getString("name"),
						resultSet.getString("lastname"),
						resultSet.getDate("birthdate").toLocalDate(),
						Nationality.valueOf(resultSet.getString("nationality")),
						resultSet.getString("phone_number"),
						reserve);

				System.out.println(guest);
				guests.add(guest);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return guests;
	}

	/**
	 * Find guests by reserve id.
	 * @param reserveId The reserve id.
	 * @return the guests.
	 */
	public Guest findByReserveId(Integer reserveId) {
		Guest guest = null;
		try {
			PreparedStatement preparedStatement = con.prepareStatement(QRY_FIND_BY_RESERVE_ID);
			preparedStatement.setInt(1, reserveId);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				Reserve reserve = new Reserve(
						resultSet.getInt("id"), 
						resultSet.getDate("checkin_date").toLocalDate(),
						resultSet.getDate("checkout_date").toLocalDate(),
						resultSet.getBigDecimal("value"),
						PaymentMethod.valueOf(resultSet.getString("payment_method")));

				guest = new Guest(
						resultSet.getInt("id"), 
						resultSet.getString("name"),
						resultSet.getString("lastname"),
						resultSet.getDate("birthdate").toLocalDate(),
						Nationality.valueOf(resultSet.getString("nationality")),
						resultSet.getString("phone_number"),
						reserve);

				System.out.println(guest);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return guest;
	}

	@Override
	public void remove(Guest t) {
		// TODO Auto-generated method stub
	}
}