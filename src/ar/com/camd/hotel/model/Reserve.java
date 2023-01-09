/**
 * Reserve.java
 * 
 * @copyright 2023, Christian Ariel Modesto Duarte. All rights reserved.
 */
package ar.com.camd.hotel.model;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * <code>Reserve</code> Represents a hotel reservation.
 * 
 * @author Christian Ariel Modesto Duarte <duarte.camd@gmail.com>
 * @version 1.0.0-SNAPSHOT
 */
public class Reserve {
	private Integer id;
	private LocalDate checkinDate;
	private LocalDate checkoutDate;
	private BigDecimal value;
	private PaymentMethod paymentMethod;

	/**
	 * @param id            The id.
	 * @param checkinDate   The check-in date.
	 * @param checkoutDate  The checkout date.
	 * @param value         The value.
	 * @param paymentMethod The payment method.
	 */
	public Reserve(Integer id, LocalDate entryDate, LocalDate exitDate, BigDecimal value, PaymentMethod paymentMethod) {
		this.id = id;
		this.checkinDate = entryDate;
		this.checkoutDate = exitDate;
		this.value = value;
		this.paymentMethod = paymentMethod;
	}

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @return the checkinDate
	 */
	public LocalDate getCheckinDate() {
		return checkinDate;
	}

	/**
	 * @return the checkoutDate
	 */
	public LocalDate getCheckoutDate() {
		return checkoutDate;
	}

	/**
	 * @return the value
	 */
	public BigDecimal getValue() {
		return value;
	}

	/**
	 * @return the paymentMethod
	 */
	public PaymentMethod getPaymentMethod() {
		return paymentMethod;
	}

	@Override
	public String toString() {
		return String.format("{'id': %d, checkinDate: %s, checkoutDate: %s, value: %f, paymentMethod: %s}", this.id,
				this.checkinDate.toString(), this.checkoutDate.toString(), this.value, this.paymentMethod);
	}
}