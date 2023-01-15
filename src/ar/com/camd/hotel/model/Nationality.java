/**
 * Nationality.java
 * 
 * @copyright 2023, Christian Ariel Modesto Duarte. All rights reserved.
 */
package ar.com.camd.hotel.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * <code>Nationality</code>
 * Represents the nationality of the guest.
 * 
 * @author   Christian Ariel Modesto Duarte <duarte.camd@gmail.com>
 * @version  1.0.0-SNAPSHOT
 */
public enum Nationality {
	AMERICAN("Estadounidense"),
	ARGENTINIAN("Argentino/a"),
	BOLIVIAN("Boliviano/a"),
	BRAZILEAN("Brazileño/a"),
	BRITISH("Británico/a"),
	CANADIAN("Canadiense"),
	CHILEAN("Chileno/a"),
	CHINESE("Chino/a"),
	COLOMBIAN("Colombiano/a"),
	CUBAN("Cubano/a"),
	DANISH("Danés"),
	DOMINICAN("Dominicano/a"),
	DUTCH("Holandesa"),
	FRENCH("Francés"),
	GERMAN("Alemán"),
	GUATEMALAN("Guatemalteco/a"),
	HONDURAN("Hondureño/a"),
	ITALIAN("Italiano/a"),
	JAPANESE("Japonés"),
	MEXICAN("Mexicano/a"),
	NICARAGUAN("Nicaragüense"),
	PANAMANIAN("Panameño/a"),
	PARAGUAYAN("Paraguayo/a"),
	PERUVIAN("Peruano/a"),
	RUSSIAN("Ruso/a"),
	SPANISH("Español/a"),
	URUGUAYAN("Uruguayo/a"),
	VENEZUELAN("Venezolano/a");

	private String description;

	private Nationality(String description) {
		this.description = description;
	}

	/**
	 * @return The nationality description.
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Finds the nationality by its description.
	 * @param description The nationality description.
	 * @return The nationality.	
	 */
	public static Nationality findByDescription(String description) {
		return Arrays.stream(values()).filter(
				value -> value.getDescription().equals(description)
				).findFirst().orElse(null);
	}

	/**
	 * Gets all nationalities descriptions. 
	 * @return The descriptions.
	 */
	public static List<String> getDescriptions() {
		List<String> descriptions = new ArrayList<>();
		Nationality[] nationalities = Nationality.values();
		for(Nationality nationality : nationalities) {
			descriptions.add(nationality.getDescription());
		}
		return descriptions;
	}
}