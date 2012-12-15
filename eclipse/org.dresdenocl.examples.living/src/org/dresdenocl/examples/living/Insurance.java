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
 * The {@link Class} {@link Insurance} is part of the Living Example of Dresden
 * OCL2 for Eclipse.
 * </p>
 * 
 * @author Claas Wilke
 */
public class Insurance {

	/** The current percentage of {@link Insurance}s. */
	private static float currentPercentage = 5;

	/** The current average years of {@link Insurance}s. */
	private static int averageYears = 12;

	/**
	 * <p>
	 * Calculates the offer of a given insurance sum.
	 * </p>
	 * 
	 * @param insuranceSum
	 *          The sum for that the offer shall be calculated.
	 * @return The calculated offer.
	 */
	static public float calculateOffer(float insuranceSum) {

		return (insuranceSum / averageYears / (1 + currentPercentage / 100));
	}

	/**
	 * <p>
	 * Returns the current percentage of {@link Insurance}s.
	 * </p>
	 * 
	 * @return The current percentage of {@link Insurance}s.
	 */
	static public float getPercentage() {

		return currentPercentage;
	}

	/**
	 * <p>
	 * Returns the current years of {@link Insurance}s.
	 * </p>
	 * 
	 * @return The current years of {@link Insurance}s.
	 */
	static public int getYears() {

		return averageYears;
	}

	/**
	 * <p>
	 * Sets the current percentage of {@link Insurance}s.
	 * </p>
	 * 
	 * @param percentage
	 *          The current percentage that shall be set.
	 */
	static public void setPercentage(float percentage) {

		currentPercentage = percentage;
	}

	/**
	 * <p>
	 * Sets the current years of {@link Insurance}s.
	 * </p>
	 * 
	 * @param years
	 *          The current years that shall be set.
	 */
	static public void setYears(int years) {

		averageYears = years;
	}
}