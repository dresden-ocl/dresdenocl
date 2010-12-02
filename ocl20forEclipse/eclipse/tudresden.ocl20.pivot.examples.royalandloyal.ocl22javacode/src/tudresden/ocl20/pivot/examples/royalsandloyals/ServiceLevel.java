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

import java.util.HashSet;
import java.util.Set;

/**
 * <p>
 * Represents an implementation of the class {@link ServiceLevel} of the Loyals
 * and Royals example.
 * </p>
 * 
 * @author Claas Wilke
 */
public class ServiceLevel {

	protected String name;

	protected LoyaltyProgram program;

	protected Set<Membership> memberships = new HashSet<Membership>();

	protected Set<Service> availableServices = new HashSet<Service>();

	public String getName() {

		return name;
	}

	public void setName(String name) {

		this.name = name;
	}

	public LoyaltyProgram getProgram() {

		return program;
	}

	public void setProgram(LoyaltyProgram aProgram) {

		this.program = aProgram;
	}

	public Set<Membership> getMemberships() {

		return memberships;
	}

	public void addMembership(Membership aMembership) {

		this.memberships.add(aMembership);
	}

	public Set<Service> getAvailableServices() {

		return availableServices;
	}

	public void addAvailableService(Service anAvailableService) {

		this.availableServices.add(anAvailableService);
	}
}