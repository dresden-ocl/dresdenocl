package tudresden.ocl20.pivot.examples.royalsandloyals;

import tudresden.ocl20.pivot.ocl2java.types.OclSet;

/**
 * <p>
 * Represents an implementation of the class {@link Customer} of the Loyals and
 * Royals example.
 * </p>
 * 
 * @author Claas Wilke
 */
public class Customer {

	protected int age;

	protected Date dateOfBirth;

	protected boolean isMale;

	protected String name = "";

	protected String title = "";

	protected OclSet<CustomerCard> cards = new OclSet<CustomerCard>();

	protected OclSet<LoyaltyProgram> programs = new OclSet<LoyaltyProgram>();

	public Customer(int age) {
		this.age = age;
	}

	public void birthdayHappens() {
		this.age++;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public boolean isMale() {
		return isMale;
	}

	public void setMale(boolean isMale) {
		this.isMale = isMale;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public OclSet<CustomerCard> getCards() {
		return cards;
	}

	public void addCard(CustomerCard aCard) {
		this.cards.add(aCard);

		aCard.setOwner(this);
	}

	public OclSet<LoyaltyProgram> getPrograms() {
		return programs;
	}

	public void addProgram(LoyaltyProgram aProgram) {
		this.programs.add(aProgram);
	}

}
