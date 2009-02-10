package tudresden.ocl20.pivot.examples.royalsandloyals;

import tudresden.ocl20.pivot.ocl2java.types.OclSet;

/**
 * <p>
 * Represents an implementation of the class {@link LoyaltyAccount} of the
 * Loyals and Royals example.
 * </p>
 * 
 * @author Claas Wilke
 */
public class LoyaltyAccount {

	protected int points;

	protected int totalPointsEarned;

	protected int number;

	protected Membership membership;

	protected OclSet<Transaction> transactions = new OclSet<Transaction>();

	public void burn(int points) {
		this.points -= points;
	}

	public void earn(int points) {
		this.points += points;
	}

	public Boolean isEmpty() {
		return transactions.isEmpty();
	}

	public String getCustomerName() {
		// Implemented by BodyAspect 2.
		return null;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public int getTotalPointsEarned() {
		return totalPointsEarned;
	}

	public void setTotalPointsEarned(Integer totalPointsEarned) {
		this.totalPointsEarned = totalPointsEarned;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public Membership getMembership() {
		return membership;
	}

	public void setMembership(Membership membership) {
		this.membership = membership;
	}

	public OclSet<Transaction> getTransactions() {
		return transactions;
	}

	public void addTransaction(Transaction aTransaction) {
		this.transactions.add(aTransaction);
	}
}
