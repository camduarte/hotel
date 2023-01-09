/**
 * Guest.java
 * 
 * @copyright 2023, Christian Ariel Modesto Duarte. All rights reserved.
 */
package ar.com.camd.hotel.model;

import java.util.Date;

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
	private Date birthday;
	private Nationality nationality;
	private String phoneNumber;
	private Reserve reserve;
}