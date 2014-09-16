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
package org.dresdenocl.examples.living;

/**
 * <p>
 * The {@link Class} {@link Flat} is part of the Living Example of Dresden OCL2
 * for Eclipse.
 * </p>
 * 
 * @author Claas Wilke
 */
public class Flat extends Accomodation {

	/** The number of this {@link Flat}. */
	protected int number;

	/** The {@link House} of this {@link Flat}. */
	public House myHouse;

	/**
	 * <p>
	 * Creates a new {@link Flat}.
	 * </p>
	 * 
	 * @param house
	 *          The {@link House} of this {@link Flat}.
	 * @param number
	 *          The number of this {@link Flat}.
	 */
	public Flat(House house, int number) {

		super(house.getStreet(), house.getHousenumber(), house.getZip(), house
				.getCity());

		this.number = number;
		house.addFlat(this);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * org.dresdenocl.examples.living.Accomodation#compareTo(tudresden.
	 * ocl20.pivot.examples.living.Accomodation)
	 */
	public int compareTo(Accomodation accomodation) {
	
		if (accomodation instanceof Flat) {
			Flat flat;
			flat = (Flat) accomodation;
	
			if (flat.number < number) {
				return -1;
			}
	
			else if (flat.number > number) {
				return 1;
			}
	
			else {
				return 0;
			}
		}
	
		else {
			return -1;
		}
	}

	/*
	 * (non-Javadoc)
	 * @see org.dresdenocl.examples.living.Accomodation#toString()
	 */
	@Override
	public String toString() {
	
		StringBuffer resultBuffer;
		resultBuffer = new StringBuffer();
	
		resultBuffer.append("Flat[street = ");
		resultBuffer.append(street);
		resultBuffer.append(" ");
		resultBuffer.append(housenumber);
		resultBuffer.append(", city = ");
		resultBuffer.append(zip);
		resultBuffer.append(" ");
		resultBuffer.append(city);
		resultBuffer.append(", appartment number = ");
		resultBuffer.append(number);
		resultBuffer.append("]");
	
		return resultBuffer.toString();
	}

	/**
	 * <p>
	 * Sets the {@link House} of this {@link Flat}.
	 * 
	 * @param house
	 *          The {@link House} of this {@link Flat}.
	 */
	public void setHouse(House house) {

		/* House is set vice versa. */
		this.myHouse = house;
	}
}