/**
 * Copyright (C) 2009 by Claas Wilke (info@claaswilke.de)
 * 
 * This file is part of the Royal and Loyal Example of Dresden OCL2 for Eclipse.
 * 
 * Dresden OCL2 for Eclipse is free software: you can redistribute it and/or modify 
 * it under the terms of the GNU Lesser General Public License as published by the 
 * Free Software Foundation, either version 3 of the License, or (at your option)
 * any later version.
 * 
 * Dresden OCL2 for Eclipse is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY 
 * or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License 
 * for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public License along 
 * with Dresden OCL2 for Eclipse. If not, see <http://www.gnu.org/licenses/>.
 */
package tudresden.ocl20.pivot.examples.royalsandloyals;

/**
 * <p>
 * Represents an implementation of the class {@link Burning} of the Loyals and
 * Royals example.
 * </p>
 * 
 * @author Claas Wilke
 */
public class Burning extends Transaction {

	@Override
	public String toString() {

		StringBuilder builder = new StringBuilder();
		builder.append("Burning [");
		if (account != null) {
			builder.append("account=");
			builder.append(account.getCustomerName());
			builder.append(", ");
		}
		builder.append("amount=");
		builder.append(amount);
		builder.append(", ");
		if (card != null) {
			builder.append("card=");
			builder.append(card.getPrintedName());
			builder.append(", ");
		}
		if (date != null) {
			builder.append("date=");
			builder.append(date);
			builder.append(", ");
		}
		builder.append("points=");
		builder.append(points);
		builder.append(", ");
		if (service != null) {
			builder.append("service=");
			builder.append(service.getServiceNr());
		}
		builder.append("]");
		return builder.toString();
	}

}