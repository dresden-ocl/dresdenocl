package tudresden.ocl20.pivot.examples.royalsandloyals;

/**
 * <p>
 * Represents an implementation of the class {@link Transaction} of the Loyals
 * and Royals example.
 * </p>
 * 
 * @author Claas Wilke
 */
public class Transaction {

	protected float amount;

	protected int points;
	
	protected Date date;
	
	protected CustomerCard card;
	
	protected LoyaltyAccount account;	
	
	protected Service service;
		
	public LoyaltyProgram getProgram() {
		return this.service.getLevel().getProgram();
	}

	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public CustomerCard getCard() {
		return card;
	}

	public void setCard(CustomerCard card) {
		this.card = card;
	}

	public LoyaltyAccount getAccount() {
		return account;
	}

	public void setAccount(LoyaltyAccount account) {
		this.account = account;
	}

	public Service getService() {
		return service;
	}

	public void setService(Service service) {
		this.service = service;
	}
}
