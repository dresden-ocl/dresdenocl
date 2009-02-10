package tudresden.ocl20.pivot.examples.royalsandloyals;

import tudresden.ocl20.pivot.ocl2java.types.OclSet;

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

	protected OclSet<Membership> memberships = new OclSet<Membership>();

	protected OclSet<Service> availableServices = new OclSet<Service>();

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

	public OclSet<Membership> getMemberships() {
		return memberships;
	}

	public void addMembership(Membership aMembership) {
		this.memberships.add(aMembership);
	}

	public OclSet<Service> getAvailableServices() {
		return availableServices;
	}

	public void addAvailableService(Service anAvailableService) {
		this.availableServices.add(anAvailableService);
	}
}
