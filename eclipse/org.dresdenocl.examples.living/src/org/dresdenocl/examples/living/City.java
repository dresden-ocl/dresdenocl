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
 * The {@link Class} {@link City} is part of the Living Example of Dresden OCL2
 * for Eclipse.
 * </p>
 * 
 * @author Claas Wilke
 */
public class City {

	/** The name of this {@link City}. */
	private String name = "";

	/** The area of this {@link City}. */
	private long area;

	/** The {@link Accomodation}s of this {@link City}. */
	public Vector<Accomodation> accomodations;

	/**
	 * <p>
	 * Creates a new {@link City}.
	 * </p>
	 * 
	 * @param name
	 *          The name of the {@link City}.
	 */
	public City(String name) {

		this.name = name;
	}

	/**
	 * <p>
	 * Creates a new {@link City}.
	 * </p>
	 * 
	 * @param name
	 *          The name of the {@link City}.
	 * @param area
	 *          The area of the {@link City}.
	 */
	public City(String name, long area) {

		this.name = name;
		this.area = area;
		accomodations = new Vector<Accomodation>();
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
	
		StringBuffer resultBuffer;
		resultBuffer = new StringBuffer();
	
		resultBuffer.append("City[name = ");
		resultBuffer.append(this.name);
		resultBuffer.append(", area = ");
		resultBuffer.append(this.area);
		resultBuffer.append("]");
	
		return resultBuffer.toString();
	}

	/**
	 * <p>
	 * Adds an {@link Accomodation} to this {@link City}.
	 * </p>
	 * 
	 * @param accomodation
	 *          The {@link Accomodation} that shall be added.
	 */
	public void addAccomodation(Accomodation accomodation) {

		/* Probably initialize the vector. */
		if (this.accomodations == null) {
			this.accomodations = new Vector<Accomodation>();
		}
		// no else.

		if (!this.accomodations.contains(accomodation)) {
			this.accomodations.add(accomodation);
		}
		// no else.
	}

	/**
	 * <p>
	 * Returns the name of this {@link City}.
	 * </p>
	 * 
	 * @return The name of this {@link City}.
	 */
	public String getName() {

		return name;
	}

	/**
	 * <p>
	 * Removes a given {@link Accomodation} from this {@link City}.
	 * </p>
	 * 
	 * @param accomodation
	 *          The {@link Accomodation} that shall be removed.
	 */
	public void removeAccomodation(Accomodation accomodation) {

		if (!accomodations.contains(accomodation)) {
			accomodations.remove(accomodation);
		}
		// no else.
	}
}