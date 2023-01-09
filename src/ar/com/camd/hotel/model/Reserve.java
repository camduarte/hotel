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
	private LocalDate entryDate;
	private LocalDate exitDate;
	private BigDecimal value;
	private PaymentMethod paymentMethod;

	/**
	 * @param id            The id.
	 * @param entryDate     The entry date.
	 * @param exitDate      The exit date.
	 * @param value         The value.
	 * @param paymentMethod The payment method.
	 */
	public Reserve(Integer id, LocalDate entryDate, LocalDate exitDate, BigDecimal value, PaymentMethod paymentMethod) {
		this.id = id;
		this.entryDate = entryDate;
		this.exitDate = exitDate;
		this.value = value;
		this.paymentMethod = paymentMethod;
	}

	@Override
	public String toString() {
		return String.format("{'id': %d, entryDate: %s, exitDate: %s, value: %f, paymentMethod: %s}", this.id,
				this.entryDate.toString(), this.exitDate.toString(), this.value, this.paymentMethod);
	}
}