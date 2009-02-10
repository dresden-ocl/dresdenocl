package tudresden.ocl20.pivot.examples.royalsandloyals;

import tudresden.ocl20.pivot.ocl2java.types.OclSet;

/**
 * <p>
 * Represents an implementation of the class {@link CustomerCard} of the Loyals
 * and Royals example.
 * </p>
 * 
 * @author Claas Wilke
 */
public class CustomerCard {

	protected Color color;

	protected String printedName;

	protected boolean valid;

	protected Date validFrom;

	protected Date validThru;

	protected Customer owner;
	
	protected Membership membership;

	protected OclSet<Transaction> transactions = new OclSet<Transaction>();
	
	public CustomerCard() {
		this.validFrom = new Date(2008, 1, 1);
		
		this.validThru = new Date(2009, 1, 1);
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public String getPrintedName() {
		return printedName;
	}

	public void setPrintedName(String printedName) {
		this.printedName = printedName;
	}

	public boolean isValid() {
		return valid;
	}

	public void setValid(boolean valid) {
		this.valid = valid;
	}

	public Date getValidFrom() {
		return validFrom;
	}

	public void setValidFrom(Date validFrom) {
		this.validFrom = validFrom;
	}

	public Date getValidThru() {
		return validThru;
	}

	public void setValidThru(Date validThru) {
		this.validThru = validThru;
	}

	public Customer getOwner() {
		return owner;
	}

	public void setOwner(Customer owner) {
		this.owner = owner;
	}

	public OclSet<Transaction> getTransactions() {
		return transactions;
	}

	public void addTransaction(Transaction aTransaction) {
		this.transactions.add(aTransaction);
	}

	public Membership getMembership() {
		return membership;
	}

	public void setMembership(Membership membership) {
		this.membership = membership;
	}
}
