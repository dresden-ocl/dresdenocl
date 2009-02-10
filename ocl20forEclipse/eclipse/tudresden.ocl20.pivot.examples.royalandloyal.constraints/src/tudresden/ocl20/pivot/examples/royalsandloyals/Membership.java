package tudresden.ocl20.pivot.examples.royalsandloyals;

import tudresden.ocl20.pivot.ocl2java.types.OclSet;

/**
 * <p>
 * Represents an implementation of the class {@link Membership} of the Loyals
 * and Royals example.
 * </p>
 * 
 * @author Claas Wilke
 */
public class Membership {

	protected CustomerCard card;

	protected OclSet<LoyaltyAccount> accounts = new OclSet<LoyaltyAccount>();

	protected LoyaltyProgram program;

	protected ServiceLevel currentLevel;
	
	public CustomerCard getCard() {
		return card;
	}

	public void setCard(CustomerCard card) {
		this.card = card;
	}

	public OclSet<LoyaltyAccount> getAccounts() {
		return accounts;
	}

	public void addAccount(LoyaltyAccount anAccount) {
		this.accounts.add(anAccount);
	}

	public LoyaltyProgram getProgram() {
		return program;
	}

	public void setProgram(LoyaltyProgram program) {
		this.program = program;
	}

	public ServiceLevel getCurrentLevel() {
		return currentLevel;
	}

	public void setCurrentLevel(ServiceLevel currentLevel) {
		this.currentLevel = currentLevel;
	}
}
