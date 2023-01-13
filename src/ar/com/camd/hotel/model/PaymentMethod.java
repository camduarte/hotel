/**
 * PaymentMethod.java
 * 
 * @copyright 2023, Christian Ariel Modesto Duarte. All rights reserved.
 */
package ar.com.camd.hotel.model;

import java.util.Arrays;

/**
 * <code>PaymentMethod</code>
 * Represents a payment method.
 * 
 * @author   Christian Ariel Modesto Duarte <duarte.camd@gmail.com>
 * @version  1.0.0-SNAPSHOT
 */
public enum PaymentMethod {
	CASH("Efectivo"), DEBIT("Tarjeta de débito"), CREDIT("Tarjeta de crédito");
	
	private String description;
	
	private PaymentMethod(String description) {
		this.description = description;
	}

	/**
	 * @return The Payment method description.
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Finds the payment method by its description.
	 * @param description The payment method description.
	 * @return The payment method.	
	 */
	public static PaymentMethod findByDescription(String description) {
		return Arrays.stream(values()).filter(
				value -> value.getDescription().equals(description)
				).findFirst().orElse(null);
	}
}