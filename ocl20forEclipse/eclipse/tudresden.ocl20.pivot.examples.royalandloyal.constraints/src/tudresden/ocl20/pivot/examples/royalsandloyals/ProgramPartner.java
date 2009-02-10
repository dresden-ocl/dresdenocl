package tudresden.ocl20.pivot.examples.royalsandloyals;

import tudresden.ocl20.pivot.ocl2java.types.OclBag;

/**
 * <p>
 * Represents an implementation of the class {@link ProgramPartner} of the
 * Loyals and Royals example.
 * </p>
 * 
 * @author Claas Wilke
 */
public class ProgramPartner {

	protected String name;

	protected int numberOfCustomers;

	protected OclBag<LoyaltyProgram> programs = new OclBag<LoyaltyProgram>();

	protected OclBag<Service> deliveredServices = new OclBag<Service>();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getNumberOfCustomers() {
		return numberOfCustomers;
	}

	public void setNumberOfCustomers(int numberOfCustomers) {
		this.numberOfCustomers = numberOfCustomers;
	}

	public OclBag<LoyaltyProgram> getPrograms() {
		return programs;
	}

	public void addProgram(LoyaltyProgram aProgram) {
		this.programs.add(aProgram);
	}

	public OclBag<Service> getDeliveredServices() {
		return deliveredServices;
	}

	public void addDeliveredService(Service aDeliveredService) {
		this.deliveredServices.add(aDeliveredService);
	}

}
