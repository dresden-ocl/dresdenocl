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

import java.util.Vector;

/**
 * <p>
 * The {@link Class} {@link House} is part of the Living Example of Dresden OCL2
 * for Eclipse.
 * </p>
 * 
 * @author Claas Wilke
 */
public class House extends Accomodation {

	/** The {@link Flat}s of this {@link House}. */
	public Vector<Flat> flat;

	/**
	 * <p>
	 * Adds a {@link Flat} to this {@link House}.
	 * </p>
	 * 
	 * @param flat
	 *          The {@link Flat} that shall be added.
	 */
	public void addFlat(Flat flat) {

		this.flat.add(flat);
		flat.setHouse(this);
	}

	/**
	 * <p>
	 * Creates a new {@link House}.
	 * </p>
	 * 
	 * @param street
	 *          The street of this {@link House}.
	 * @param housenumber
	 *          The house number of this {@link House}.
	 * @param zip
	 *          The zip of this {@link House}.
	 * @param city
	 *          The city of this {@link House}.
	 */
	public House(String street, int housenumber, String zip, City city) {

		super(street, housenumber, zip, city);
		flat = new Vector<Flat>();
	}

	/*
	 * (non-Javadoc)
	 * @see org.dresdenocl.examples.living.Accomodation#toString()
	 */
	@Override
	public String toString() {

		StringBuffer resultBuffer;
		resultBuffer = new StringBuffer();

		resultBuffer.append("House[street = ");
		resultBuffer.append(street);
		resultBuffer.append(" ");
		resultBuffer.append(housenumber);
		resultBuffer.append(", city = ");
		resultBuffer.append(zip);
		resultBuffer.append(" ");
		resultBuffer.append(city);
		resultBuffer.append("]");

		return resultBuffer.toString();
	}
}