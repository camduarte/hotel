/**
 * Reserve.java
 * 
 * @copyright 2023, Christian Ariel Modesto Duarte. All rights reserved.
 */
package ar.com.camd.hotel.model;

import java.math.BigDecimal;
import java.util.Date;

/**
 * <code>Reserve</code>
 * Represents a hotel reservation.
 * 
 * @author   Christian Ariel Modesto Duarte <duarte.camd@gmail.com>
 * @version  1.0.0-SNAPSHOT
 */
public class Reserve {
	private Integer id;
	private Date entryDate;
	private Date exitDate;
	private BigDecimal value;
	private PaymentMethod paymentMethod;
}