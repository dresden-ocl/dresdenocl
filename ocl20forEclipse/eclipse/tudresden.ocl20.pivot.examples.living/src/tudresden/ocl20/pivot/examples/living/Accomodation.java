/*
Copyright (C) 2009 by Claas Wilke (info@claaswilke.de)

This file is part of the Living Examples of Dresden OCL2 for Eclipse.

Dresden OCL2 for Eclipse is free software: you can redistribute it and/or modify 
it under the terms of the GNU Lesser General Public License as published by the 
Free Software Foundation, either version 3 of the License, or (at your option)
any later version.

Dresden OCL2 for Eclipse is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY 
or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License 
for more details.

You should have received a copy of the GNU Lesser General Public License along 
with Dresden OCL2 for Eclipse. If not, see <http://www.gnu.org/licenses/>.
 */
package tudresden.ocl20.pivot.examples.living;

import java.util.Calendar;
import java.util.Vector;

/**
 * <p>
 * The {@link Class} {@link Accomodation} is part of the Living Example of
 * Dresden OCL2 for Eclipse.
 * </p>
 * 
 * @author Claas Wilke
 */
public class Accomodation implements Comparable<Accomodation> {

	/** The numeric value for type permanent residence. */
	public static final int PERMANENTRESIDENCE = 1;

	/** The numeric value for type secondary residence. */
	public static final int SECONDARYRESIDENCE = 2;

	/** The category of this {@link Accomodation}. */
	protected int category;

	/** The price of this {@link Accomodation}. */
	protected int price;

	/** The street of this {@link Accomodation}. */
	protected String street;

	/** The house number of this {@link Accomodation}. */
	protected int housenumber;

	/** The zip of this {@link Accomodation}. */
	protected String zip;

	/** The {@link City} of this {@link Accomodation}. */
	protected City city;

	/** The {@link Insurance}s of this {@link Accomodation}. */
	public Vector<Insurance> insurance;

	/**
	 * <p>
	 * Creates a new {@link Accomodation}.
	 * </p>
	 * 
	 * @param street
	 *          The street of the {@link Accomodation}.
	 * @param housenumber
	 *          The house number of the {@link Accomodation}.
	 * @param zip
	 *          The zip of the {@link Accomodation}.
	 * @param city
	 *          The {@link City} of the {@link Accomodation}.
	 */
	public Accomodation(String street, int housenumber, String zip, City city) {

		this.street = street;
		this.housenumber = housenumber;
		this.zip = zip;
		this.city = city;
		insurance = new Vector<Insurance>();
		this.city.addAccomodation(this);
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	public int compareTo(Accomodation accomodation) {

		if (accomodation.price < price) {
			return -1;
		}

		else if (accomodation.price > price) {
			return 1;
		}

		else {
			return 0;
		}
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {

		StringBuffer resultBuffer;

		resultBuffer = new StringBuffer();
		resultBuffer.append("Accomodation[");
		resultBuffer.append(street);
		resultBuffer.append(" ");
		resultBuffer.append(housenumber);
		resultBuffer.append(", ");
		resultBuffer.append(zip);
		resultBuffer.append(" ");
		resultBuffer.append(city);
		resultBuffer.append("]");

		return resultBuffer.toString();
	}

	/**
	 * <p>
	 * Checks whether or not this {@link Accomodation} is available at a given
	 * {@link Calendar}.
	 * </p>
	 * 
	 * @param date
	 *          The {@link Calendar} that shall be checked.
	 * @return True, if this {@link Accomodation} is available.
	 */
	public boolean isAvailable(Calendar date) {

		/* Default implementation. */
		return false;
	}

	/**
	 * <p>
	 * Returns the category of this {@link Accomodation}.
	 * </p>
	 * 
	 * @return The category of this {@link Accomodation}.
	 */
	public int getCategory() {

		return category;
	}

	/**
	 * <p>
	 * Returns the {@link City} of this {@link Accomodation}.
	 * </p>
	 * 
	 * @return The {@link City} of this {@link Accomodation}.
	 */
	public City getCity() {

		return city;
	}

	/**
	 * <p>
	 * Returns the house number of this {@link Accomodation}.
	 * </p>
	 * 
	 * @return The house number of this {@link Accomodation}.
	 */
	public int getHousenumber() {

		return housenumber;
	}

	/**
	 * <p>
	 * Returns the street of this {@link Accomodation}.
	 * </p>
	 * 
	 * @return The street of this {@link Accomodation}.
	 */
	public String getStreet() {

		return street;
	}

	/**
	 * <p>
	 * Returns the zip of this {@link Accomodation}.
	 * </p>
	 * 
	 * @return The zip of this {@link Accomodation}.
	 */
	public String getZip() {

		return zip;
	}

	/**
	 * <p>
	 * Sets the category of this {@link Accomodation}.
	 * </p>
	 * 
	 * @param category
	 *          The category of this {@link Accomodation}.
	 * @return True if the category has already been set to the given value.
	 */
	public boolean setCategory(int category) {

		if (this.category == category) {
			return true;
		}

		else {
			this.category = category;
			return false;
		}
	}
}