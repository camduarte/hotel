/**
 * Guest.java
 * 
 * @copyright 2023, Christian Ariel Modesto Duarte. All rights reserved.
 */
package ar.com.camd.hotel.model;

import java.time.LocalDate;

/**
 * <code>Guest</code>
 * Represents a hotel guest.
 * 
 * @author   Christian Ariel Modesto Duarte <duarte.camd@gmail.com>
 * @version  1.0.0-SNAPSHOT
 */
public class Guest {
	private Integer id;
	private String name;
	private String lastname;
	private LocalDate birthdate;
	private Nationality nationality;
	private String phoneNumber;
	private Reserve reserve;

	/**
	 * @param id The identification number.
	 * @param name The guest name
	 * @param lastname The guest last name.
	 * @param birthdate The guest birth date.
	 * @param nationality The guest nationality.
	 * @param phoneNumber The guest phone number.
	 * @param reserve The reserve.
	 */
	public Guest(Integer id, String name, String lastname, LocalDate birthdate, Nationality nationality,
			String phoneNumber, Reserve reserve) {
		this.id = id;
		this.name = name;
		this.lastname = lastname;
		this.birthdate = birthdate;
		this.nationality = nationality;
		this.phoneNumber = phoneNumber;
		this.reserve = reserve;
	}

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the lastname
	 */
	public String getLastname() {
		return lastname;
	}

	/**
	 * @return the birthdate
	 */
	public LocalDate getBirthdate() {
		return birthdate;
	}

	/**
	 * @return the nationality
	 */
	public Nationality getNationality() {
		return nationality;
	}

	/**
	 * @return the phoneNumber
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}

	/**
	 * @return the reserve
	 */
	public Reserve getReserve() {
		return reserve;
	}

	@Override
	public String toString() {
		return String.format("{id: %d, name: %s, lastname: %s, birthdate: %s, nationality: %s, phoneNumber: %s, reserve: %s}", 
				this.id, 
				this.name, 
				this.lastname, 
				this.birthdate.toString(), 
				this.nationality, 
				this.phoneNumber,
				this.reserve
				);
	}
}