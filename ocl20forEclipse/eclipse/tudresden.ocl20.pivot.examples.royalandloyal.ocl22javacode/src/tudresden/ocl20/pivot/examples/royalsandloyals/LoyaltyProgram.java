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

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * <p>
 * Represents an implementation of the class {@link LoyaltyProgram} of the
 * Loyals and Royals example.
 * </p>
 * 
 * @author Claas Wilke
 */
public class LoyaltyProgram {

	protected String name;

	protected Set<Customer> participants = new HashSet<Customer>();

	protected Set<ProgramPartner> partners = new HashSet<ProgramPartner>();

	protected List<ServiceLevel> levels = new ArrayList<ServiceLevel>();

	protected Membership membership;

	public void addService(ProgramPartner aPartner, ServiceLevel aLevel,
			Service aService) {

		aPartner.addDeliveredService(aService);
		this.partners.add(aPartner);

		aLevel.addAvailableService(aService);
		this.levels.add(aLevel);
	}

	public Boolean enroll(Customer aCustomer) {

		return this.participants.add(aCustomer);
	}

	public String getName() {

		return name;
	}

	public void setName(String name) {

		this.name = name;
	}

	public Membership getMembership() {

		return membership;
	}

	public void setMembership(Membership membership) {

		this.membership = membership;

		membership.setProgram(this);
	}

	public Set<Customer> getParticipants() {

		return participants;
	}

	public Set<ProgramPartner> getPartners() {

		return partners;
	}

	public void addPartner(ProgramPartner aPartner) {

		this.partners.add(aPartner);
	}

	public List<ServiceLevel> getLevels() {

		return levels;
	}

	public void addLevel(ServiceLevel aLevel) {

		this.levels.add(aLevel);
	}
}