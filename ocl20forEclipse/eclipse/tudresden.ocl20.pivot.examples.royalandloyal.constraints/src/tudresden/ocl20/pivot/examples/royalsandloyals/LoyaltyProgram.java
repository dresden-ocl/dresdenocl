package tudresden.ocl20.pivot.examples.royalsandloyals;

import tudresden.ocl20.pivot.ocl2java.types.OclOrderedSet;
import tudresden.ocl20.pivot.ocl2java.types.OclSet;

/**
 * <p>
 * Represents an implementation of the class {@link LoyaltyProgram} of the Loyals and
 * Royals example.
 * </p>
 * 
 * @author Claas Wilke
 */
public class LoyaltyProgram {
	
	protected String name;
	
	protected OclSet<Customer> participants = new OclSet<Customer>();
	
	protected OclSet<ProgramPartner> partners = new OclSet<ProgramPartner>();
	
	protected OclOrderedSet<ServiceLevel> levels = new OclOrderedSet<ServiceLevel>(); 
	
	protected Membership membership;
	
	public void addService(ProgramPartner aPartner, ServiceLevel aLevel, Service aService) {
		
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

	public OclSet<Customer> getParticipants() {
		return participants;
	}

	public OclSet<ProgramPartner> getPartners() {
		return partners;
	}

	public void addPartner(ProgramPartner aPartner) {
		this.partners.add(aPartner);
	}

	public OclOrderedSet<ServiceLevel> getLevels() {
		return levels;
	}

	public void addLevel(ServiceLevel aLevel) {
		this.levels.add(aLevel);
	}
}
