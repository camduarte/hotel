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

/**
 * <code>GuestDao</code>
 * Allows CRUD operations for guest entity.
 * 
 * @author   Christian Ariel Modesto Duarte <duarte.camd@gmail.com>
 * @version  1.0.0-SNAPSHOT
 */
public class GuestDao implements Dao<Guest> {

	private Connection con;

	private final String QRY_FIND_ALL = "SELECT id, name, lastname, birthdate, nationality, phone_number FROM hotel.guest";

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
				Guest guest = new Guest(
						resultSet.getInt("id"), 
						resultSet.getString("name"),
						resultSet.getString("lastname"),
						resultSet.getDate("birthdate").toLocalDate(),
						Nationality.valueOf(resultSet.getString("nationality")),
						resultSet.getString("phone_number"));
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

	@Override
	public void remove(Guest t) {
		// TODO Auto-generated method stub
		
	}

}
