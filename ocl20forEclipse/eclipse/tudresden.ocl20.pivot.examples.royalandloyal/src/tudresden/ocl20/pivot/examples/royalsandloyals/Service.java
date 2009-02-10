package tudresden.ocl20.pivot.examples.royalsandloyals;

/**
 * <p>
 * Represents an implementation of the class {@link Service} of the Loyals and
 * Royals example.
 * </p>
 * 
 * @author Claas Wilke
 */
public class Service {

	protected boolean condition;

	protected int pointsBurned;

	protected int pointsEarned;

	protected String description;

	protected int serviceNr;

	protected ProgramPartner partner;

	protected ServiceLevel level;

	protected Transaction transaction;

	public int calcPoints() {

		return pointsEarned - pointsBurned;
	}

	public void upgradePointsEarned(Integer amount) {
		this.pointsEarned = this.pointsEarned + amount;
	}

	public boolean isCondition() {
		return condition;
	}

	public void setCondition(boolean condition) {
		this.condition = condition;
	}

	public int getPointsBurned() {
		return pointsBurned;
	}

	public void setPointsBurned(int pointsBurned) {
		this.pointsBurned = pointsBurned;
	}

	public int getPointsEarned() {
		return pointsEarned;
	}

	public void setPointsEarned(int pointsEarned) {
		this.pointsEarned = pointsEarned;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getServiceNr() {
		return serviceNr;
	}

	public void setServiceNr(int serviceNr) {
		this.serviceNr = serviceNr;
	}

	public ProgramPartner getPartner() {
		return partner;
	}

	public void setPartner(ProgramPartner partner) {
		this.partner = partner;
	}

	public Transaction getTransaction() {
		return transaction;
	}

	public void setTransaction(Transaction transaction) {
		this.transaction = transaction;
	}

	public ServiceLevel getLevel() {
		return level;
	}

	public void setLevel(ServiceLevel level) {
		this.level = level;
	}

}
