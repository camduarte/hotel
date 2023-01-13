/**
 * Reserve.java
 * 
 * @copyright 2023, Christian Ariel Modesto Duarte. All rights reserved.
 */
package ar.com.camd.hotel.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/**
 * <code>Reserve</code> Represents a hotel reservation.
 * 
 * @author Christian Ariel Modesto Duarte <duarte.camd@gmail.com>
 * @version 1.0.0-SNAPSHOT
 */
public final class Reserve {
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

	/**
	 * Calculates the reservation value.
	 * @return The reservation value.
	 */
	public static BigDecimal calculateValue(LocalDate checkin, LocalDate checkout) {
		if (checkout.compareTo(checkin) > 0) {
			BigDecimal valuePerDay = new BigDecimal(3000D);
			BigDecimal days = new BigDecimal(ChronoUnit.DAYS.between(checkin, checkout));
			return valuePerDay.multiply(days);
		} else {
			System.out.println("Check-out date must be greather than check-in date.");
			return null;
		}
	}
}