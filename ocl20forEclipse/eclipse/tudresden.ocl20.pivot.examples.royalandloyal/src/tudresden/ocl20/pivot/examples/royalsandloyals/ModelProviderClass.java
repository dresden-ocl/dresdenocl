package tudresden.ocl20.pivot.examples.royalsandloyals;

import java.util.ArrayList;
import java.util.List;

import javax.swing.SpringLayout.Constraints;

/**
 * <p>
 * A class which creates a simple model instance of the royals and loyals UML
 * model.
 * </p>
 * 
 * @author Claas Wilke
 */
public class ModelProviderClass {

	public static void main(String[] args) {

		List<Object> allObjects;

		int countOfCustomers;
		int countOfCustomerCards;
		int countOfDates;
		int countOfLoyaltyAccounts;
		int countOfLoyaltyPrograms;
		int countOfMemberships;
		int countOfProgramPartners;
		int countOfTransactions;
		int countOfBurnings;

		allObjects = ModelProviderClass.getModelObjects();

		countOfCustomers = 0;
		countOfCustomerCards = 0;
		countOfDates = 0;
		countOfLoyaltyAccounts = 0;
		countOfLoyaltyPrograms = 0;
		countOfMemberships = 0;
		countOfProgramPartners = 0;
		countOfTransactions = 0;
		countOfBurnings = 0;

		for (Object anElement : allObjects) {

			if (anElement instanceof Customer) {
				countOfCustomers++;
			}

			else if (anElement instanceof CustomerCard) {
				countOfCustomerCards++;
			}

			else if (anElement instanceof Date) {
				countOfDates++;
			}

			else if (anElement instanceof LoyaltyAccount) {
				countOfLoyaltyAccounts++;
			}

			else if (anElement instanceof LoyaltyProgram) {
				countOfLoyaltyPrograms++;
			}

			else if (anElement instanceof Membership) {
				countOfMemberships++;
			}

			else if (anElement instanceof ProgramPartner) {
				countOfProgramPartners++;
			}

			else if (anElement instanceof Transaction) {
				countOfTransactions++;
			}

			if (anElement instanceof Burning) {
				countOfBurnings++;
			}
			// no else.
		}

		System.out.println("Example contains " + countOfCustomers
				+ " Customers.");
		System.out.println("Example contains " + countOfCustomerCards
				+ " CustomerCards.");
		System.out.println("Example contains " + countOfDates + " Dates.");
		System.out.println("Example contains " + countOfLoyaltyAccounts
				+ " LoyaltyAccounts.");
		System.out.println("Example contains " + countOfLoyaltyPrograms
				+ " LoyaltyPrograms.");
		System.out.println("Example contains " + countOfMemberships
				+ " Memberships.");
		System.out.println("Example contains " + countOfProgramPartners
				+ " ProgramPartners.");
		System.out.println("Example contains " + countOfTransactions
				+ " Transactions.");
		System.out
				.println("Example contains " + countOfBurnings + " Burnings.");
	}

	/**
	 * @return A model instance of the royal and loyal UML model.
	 */
	public static List<Object> getModelObjects() {

		/*
		 * PLEASE DO NOT CHANGE THE ORDERING IN WHICH THE OBJECTS ARE ADDED TO
		 * THE RESULT. OTHERWHISE THE INTERPRETER TEST SUITE WON'T WORK
		 * ANYMORE!!!
		 */
		List<Object> result;

		Customer customer1;
		Customer customer2;

		CustomerCard card1;
		CustomerCard card2;

		LoyaltyAccount account1;

		LoyaltyProgram program1;
		LoyaltyProgram program2;

		Membership membership1;
		Membership membership2;

		Service service1;

		ServiceLevel level1;
		ServiceLevel level2;
		ServiceLevel level3;

		Transaction transaction1;

		ProgramPartner partner1;

		result = new ArrayList<Object>();

		customer1 = new Customer(18);
		result.add(customer1);

		customer2 = new Customer(11);
		result.add(customer2);

		card1 = new CustomerCard();
		card1.setValidFrom(new Date(2008, 1, 1));
		card1.setValidThru(new Date(2009, 1, 1));
		result.add(card1);

		card2 = new CustomerCard();
		card2.setValidFrom(new Date(2009, 1, 1));
		card2.setValidThru(new Date(2008, 1, 1));
		result.add(card2);

		level1 = new ServiceLevel();
		result.add(level1);

		level2 = new ServiceLevel();
		result.add(level2);

		level3 = new ServiceLevel();
		result.add(level3);

		membership1 = new Membership();
		membership1.setCurrentLevel(level1);
		result.add(membership1);

		account1 = new LoyaltyAccount();
		account1.setMembership(membership1);
		membership1.addAccount(account1);
		result.add(account1);

		membership2 = new Membership();
		membership2.setCurrentLevel(level2);
		result.add(membership2);

		program1 = new LoyaltyProgram();
		program1.setMembership(membership1);
		program1.addLevel(level1);
		program1.addLevel(level2);
		result.add(program1);

		program2 = new LoyaltyProgram();
		program2.setMembership(membership2);
		program2.addLevel(level1);
		program2.addLevel(level3);
		result.add(program2);

		service1 = new Service();
		result.add(service1);

		partner1 = new ProgramPartner();
		result.add(partner1);

		level1.setProgram(program1);
		program1.addPartner(partner1);
		partner1.addProgram(program1);
		level1.addAvailableService(service1);
		service1.setLevel(level1);
		service1.setPartner(partner1);
		partner1.addDeliveredService(service1);

		transaction1 = new Transaction();
		result.add(transaction1);

		/* Add Objects for some iterator expression test cases. */
		result.addAll(getModelObjectsForIterator10());
		result.addAll(getModelObjectsForIterator12());
		result.addAll(getModelObjectsForIterator14());
		result.addAll(getModelObjectsForIterator15());

		/* Add Objects for some boolean expression test cases. */
		result.addAll(getModelObjectsForBoolean01());
		result.addAll(getModelObjectsForBoolean03());
		result.addAll(getModelObjectsForBoolean04());
		result.addAll(getModelObjectsForBoolean05());
		result.addAll(getModelObjectsForBoolean06());

		/* Add Objects for some iterator expression test cases. */
		result.addAll(getModelObjectsForIterator07());
		result.addAll(getModelObjectsForIterator08());
		result.addAll(getModelObjectsForIterator09());

		/* Add Objects for some invariant expression test cases. */
		result.addAll(getModelObjectsForInvariant01());

		/* Add Objects for some static object or operation test cases. */
		result.addAll(getModelObjectsForStatic01());

		/* Add Objects for some body expression test cases. */
		result.addAll(getModelObjectsForBody02());

		/* Add Objects for some definition test cases. */
		result.addAll(getModelObjectsForDef01());
		result.addAll(getModelObjectsForDef02());
		result.addAll(getModelObjectsForDef03());
		result.addAll(getModelObjectsForDef04());
		result.addAll(getModelObjectsForDef05());
		result.addAll(getModelObjectsForDef06());
		result.addAll(getModelObjectsForDef07());
		result.addAll(getModelObjectsForDef08());

		/* Add Objects for some derived value test cases. */
		result.addAll(getModelObjectsForDerive01());
		result.addAll(getModelObjectsForDerive02());

		/* Add Objects for some initial value test cases. */
		result.addAll(getModelObjectsForInit01());
		result.addAll(getModelObjectsForInit02());
		result.addAll(getModelObjectsForInit03());

		/* Add Objects for some invariant test cases. */
		result.addAll(getModelObjectsForInv04());
		result.addAll(getModelObjectsForInv05());
		result.addAll(getModelObjectsForInv06());
		result.addAll(getModelObjectsForInv07());
		result.addAll(getModelObjectsForInv08());
		result.addAll(getModelObjectsForInv09());
		result.addAll(getModelObjectsForInv10());
		result.addAll(getModelObjectsForInv11());
		result.addAll(getModelObjectsForInv12());
		result.addAll(getModelObjectsForInv13());
		result.addAll(getModelObjectsForInv14());
		result.addAll(getModelObjectsForInv15());
		result.addAll(getModelObjectsForInv18());

		return result;
	}

	/**
	 * @return A {@link List} containing all Objects used to test the
	 *         {@link Constraints} contained in the file 'boody02.ocl'.
	 */
	private static List<Object> getModelObjectsForBody02() {

		List<Object> result;

		result = new ArrayList<Object>();

		LoyaltyAccount account1;
		Membership membership1;
		CustomerCard card1;
		Customer customer1;

		customer1 = new Customer(25);
		customer1.setName("Testman");

		card1 = new CustomerCard();
		card1.setOwner(customer1);

		membership1 = new Membership();
		membership1.setCard(card1);

		account1 = new LoyaltyAccount();
		account1.setMembership(membership1);

		result.add(account1);
		result.add(membership1);
		result.add(card1);
		result.add(customer1);

		return result;
	}

	/**
	 * @return A {@link List} containing all Objects used to test the
	 *         {@link Constraints} contained in the file 'define01.ocl'.
	 */
	private static List<Object> getModelObjectsForDef01() {

		List<Object> result;

		result = new ArrayList<Object>();

		LoyaltyAccount account1;

		Transaction ta1;
		Transaction ta2;

		ta1 = new Transaction();
		ta1.setAmount(new Float(25.5));

		ta2 = new Transaction();
		ta2.setAmount(new Float(74.5));

		account1 = new LoyaltyAccount();
		account1.addTransaction(ta1);
		account1.addTransaction(ta2);

		result.add(account1);
		result.add(ta1);
		result.add(ta2);

		return result;
	}

	/**
	 * @return A {@link List} containing all Objects used to test the
	 *         {@link Constraints} contained in the file 'define02.ocl'.
	 */
	private static List<Object> getModelObjectsForDef02() {

		List<Object> result;

		result = new ArrayList<Object>();

		LoyaltyProgram aProgram;

		ServiceLevel level1;
		ServiceLevel level2;

		Service service1;
		Service service2;
		Service service3;

		service1 = new Service();
		service2 = new Service();
		service3 = new Service();

		level1 = new ServiceLevel();
		level1.setName("level1");
		level1.addAvailableService(service1);
		level1.addAvailableService(service2);

		level2 = new ServiceLevel();
		level2.setName("level2");
		level2.addAvailableService(service3);

		aProgram = new LoyaltyProgram();
		aProgram.addLevel(level1);
		aProgram.addLevel(level2);

		result.add(aProgram);
		result.add(level1);
		result.add(level2);
		result.add(service1);
		result.add(service2);
		result.add(service3);

		return result;
	}

	/**
	 * @return A {@link List} containing all Objects used to test the
	 *         {@link Constraints} contained in the file 'define03.ocl'.
	 */
	private static List<Object> getModelObjectsForDef03() {

		List<Object> result;

		result = new ArrayList<Object>();

		Membership membership1;

		ServiceLevel level1;

		level1 = new ServiceLevel();
		level1.setName("level1");

		membership1 = new Membership();
		membership1.setCurrentLevel(level1);

		result.add(membership1);
		result.add(level1);

		return result;
	}

	/**
	 * @return A {@link List} containing all Objects used to test the
	 *         {@link Constraints} contained in the file 'define04.ocl'.
	 */
	private static List<Object> getModelObjectsForDef04() {

		List<Object> result;

		result = new ArrayList<Object>();

		LoyaltyAccount account1;

		Transaction ta1;
		Transaction ta2;

		Service service1;
		Service service2;

		service1 = new Service();
		service2 = new Service();

		ta1 = new Transaction();
		ta1.setService(service1);

		ta2 = new Transaction();
		ta2.setService(service2);

		account1 = new LoyaltyAccount();
		account1.addTransaction(ta1);
		account1.addTransaction(ta2);

		result.add(account1);
		result.add(ta1);
		result.add(ta2);
		result.add(service1);
		result.add(service2);

		return result;
	}

	/**
	 * @return A {@link List} containing all Objects used to test the
	 *         {@link Constraints} contained in the file 'define05.ocl'.
	 */
	private static List<Object> getModelObjectsForDef05() {

		List<Object> result;

		result = new ArrayList<Object>();

		Customer customer1;

		customer1 = new Customer(25);
		customer1.setName("Tesman");

		result.add(customer1);

		return result;
	}

	/**
	 * @return A {@link List} containing all Objects used to test the
	 *         {@link Constraints} contained in the file 'define06.ocl'.
	 */
	private static List<Object> getModelObjectsForDef06() {

		List<Object> result;

		result = new ArrayList<Object>();

		CustomerCard card1;

		Transaction ta1;
		Transaction ta2;

		ta1 = new Transaction();
		ta1.setDate(Date.now());
		ta1.setPoints(100);

		ta2 = new Transaction();
		ta2.setDate(Date.now());
		ta2.setPoints(50);

		card1 = new CustomerCard();
		card1.addTransaction(ta1);
		card1.addTransaction(ta2);

		result.add(card1);
		result.add(ta1);
		result.add(ta2);

		return result;
	}

	/**
	 * @return A {@link List} containing all Objects used to test the
	 *         {@link Constraints} contained in the file 'define07.ocl'.
	 */
	private static List<Object> getModelObjectsForDef07() {

		List<Object> result;

		result = new ArrayList<Object>();

		CustomerCard card1;

		card1 = new CustomerCard();

		result.add(card1);

		return result;
	}

	/**
	 * @return A {@link List} containing all Objects used to test the
	 *         {@link Constraints} contained in the file 'define08.ocl'.
	 */
	private static List<Object> getModelObjectsForDef08() {

		List<Object> result;

		result = new ArrayList<Object>();

		ProgramPartner partner1;

		Service service1;
		Service service2;
		Service service3;

		Transaction ta1;
		Transaction ta2;
		Transaction ta3;

		ta1 = new Burning();
		ta2 = new Earning();
		ta3 = new Burning();

		service1 = new Service();
		service1.setTransaction(ta1);

		service2 = new Service();
		service2.setTransaction(ta2);

		service3 = new Service();
		service3.setTransaction(ta3);

		partner1 = new ProgramPartner();
		partner1.addDeliveredService(service1);
		partner1.addDeliveredService(service2);
		partner1.addDeliveredService(service3);

		result.add(partner1);
		result.add(service1);
		result.add(service2);
		result.add(service3);
		result.add(ta1);
		result.add(ta2);
		result.add(ta3);

		return result;
	}

	/**
	 * @return A {@link List} containing all Objects used to test the
	 *         {@link Constraints} contained in the file 'derive01.ocl'.
	 */
	private static List<Object> getModelObjectsForDerive01() {

		List<Object> result;

		result = new ArrayList<Object>();

		CustomerCard card1;

		Customer customer1;

		customer1 = new Customer(25);
		customer1.setName("Testman");
		customer1.setTitle("Mr.");

		card1 = new CustomerCard();
		card1.setOwner(customer1);

		result.add(card1);
		result.add(customer1);

		return result;
	}

	/**
	 * @return A {@link List} containing all Objects used to test the
	 *         {@link Constraints} contained in the file 'derive02.ocl'.
	 */
	private static List<Object> getModelObjectsForDerive02() {

		List<Object> result;

		result = new ArrayList<Object>();

		LoyaltyAccount account1;

		Transaction ta1;
		Transaction ta2;
		Transaction ta3;

		ta1 = new Earning();
		ta1.setPoints(25);

		ta2 = new Burning();
		ta2.setPoints(50);

		ta3 = new Earning();
		ta3.setPoints(25);

		account1 = new LoyaltyAccount();
		account1.addTransaction(ta1);
		account1.addTransaction(ta2);
		account1.addTransaction(ta3);

		result.add(account1);
		result.add(ta1);
		result.add(ta2);
		result.add(ta3);

		return result;
	}

	/**
	 * @return A {@link List} containing all Objects used to test the
	 *         {@link Constraints} contained in the file 'init01.ocl'.
	 */
	private static List<Object> getModelObjectsForInit01() {

		List<Object> result;

		result = new ArrayList<Object>();

		LoyaltyAccount account1;

		account1 = new LoyaltyAccount();

		result.add(account1);

		return result;
	}

	/**
	 * @return A {@link List} containing all Objects used to test the
	 *         {@link Constraints} contained in the file 'init02.ocl'.
	 */
	private static List<Object> getModelObjectsForInit02() {

		List<Object> result;

		result = new ArrayList<Object>();

		CustomerCard card1;

		card1 = new CustomerCard();

		result.add(card1);

		return result;
	}

	/**
	 * @return A {@link List} containing all Objects used to test the
	 *         {@link Constraints} contained in the file 'init03.ocl'.
	 */
	private static List<Object> getModelObjectsForInit03() {

		List<Object> result;

		result = new ArrayList<Object>();

		LoyaltyAccount account1;

		account1 = new LoyaltyAccount();

		result.add(account1);

		return result;
	}

	/**
	 * @return A {@link List} containing all Objects used to test the
	 *         {@link Constraints} contained in the file 'invariant04.ocl'.
	 */
	private static List<Object> getModelObjectsForInv04() {

		List<Object> result;

		result = new ArrayList<Object>();

		Membership membership1;
		Membership membership2;

		LoyaltyProgram program1;
		LoyaltyProgram program2;

		Customer customer1;
		Customer customer2;

		CustomerCard card1;
		CustomerCard card2;
		CustomerCard card3;

		card1 = new CustomerCard();
		card2 = new CustomerCard();
		card3 = new CustomerCard();

		customer1 = new Customer(25);
		customer1.addCard(card1);

		customer2 = new Customer(25);
		customer2.addCard(card2);

		program1 = new LoyaltyProgram();
		program1.enroll(customer1);

		program2 = new LoyaltyProgram();
		program2.enroll(customer2);

		membership1 = new Membership();
		membership1.setProgram(program1);
		membership1.setCard(card1);

		membership2 = new Membership();
		membership2.setProgram(program2);
		membership2.setCard(card3);

		result.add(membership1);
		result.add(membership2);
		result.add(program1);
		result.add(program2);
		result.add(customer1);
		result.add(customer2);
		result.add(card1);
		result.add(card2);
		result.add(card3);

		return result;
	}

	/**
	 * @return A {@link List} containing all Objects used to test the
	 *         {@link Constraints} contained in the file 'invariant05.ocl'.
	 */
	private static List<Object> getModelObjectsForInv05() {

		List<Object> result;

		result = new ArrayList<Object>();

		Membership membership1;
		Membership membership2;

		ServiceLevel level1;
		ServiceLevel level2;

		CustomerCard card1;
		CustomerCard card2;

		card1 = new CustomerCard();
		card2 = new CustomerCard();

		card1.setColor(Color.silver);
		card2.setColor(Color.silver);

		level1 = new ServiceLevel();
		level2 = new ServiceLevel();

		level1.setName("Silver");
		level2.setName("Gold");

		membership1 = new Membership();
		membership2 = new Membership();

		membership1.setCurrentLevel(level1);
		membership2.setCurrentLevel(level2);

		membership1.setCard(card1);
		membership2.setCard(card2);

		result.add(membership1);
		result.add(membership2);
		result.add(level1);
		result.add(level2);
		result.add(card1);
		result.add(card2);

		return result;
	}

	/**
	 * @return A {@link List} containing all Objects used to test the
	 *         {@link Constraints} contained in the file 'invariant06.ocl'.
	 */
	private static List<Object> getModelObjectsForInv06() {

		List<Object> result;

		result = new ArrayList<Object>();

		LoyaltyProgram program1;
		LoyaltyProgram program2;

		ProgramPartner partner1;
		ProgramPartner partner2;

		Service service1;

		service1 = new Service();

		partner1 = new ProgramPartner();
		partner2 = new ProgramPartner();

		partner1.addDeliveredService(service1);

		program1 = new LoyaltyProgram();
		program2 = new LoyaltyProgram();

		program1.addPartner(partner1);
		program2.addPartner(partner1);
		program2.addPartner(partner2);

		result.add(program1);
		result.add(program2);
		result.add(partner1);
		result.add(partner2);
		result.add(service1);

		return result;
	}

	/**
	 * @return A {@link List} containing all Objects used to test the
	 *         {@link Constraints} contained in the file 'invariant07.ocl'.
	 */
	private static List<Object> getModelObjectsForInv07() {

		List<Object> result;

		result = new ArrayList<Object>();

		Customer customer1;
		Customer customer2;

		LoyaltyProgram program1;
		LoyaltyProgram program2;

		CustomerCard card1;
		CustomerCard card2;
		CustomerCard card3;
		CustomerCard card4;

		card1 = new CustomerCard();
		card2 = new CustomerCard();
		card3 = new CustomerCard();
		card4 = new CustomerCard();

		card1.setValid(true);
		card2.setValid(true);
		card3.setValid(true);
		card4.setValid(false);

		program1 = new LoyaltyProgram();
		program2 = new LoyaltyProgram();

		customer1 = new Customer(25);
		customer2 = new Customer(25);

		customer1.addProgram(program1);
		customer1.addProgram(program2);
		customer2.addProgram(program1);
		customer2.addProgram(program2);

		customer1.addCard(card1);
		customer1.addCard(card2);
		customer2.addCard(card3);
		customer2.addCard(card4);

		result.add(customer1);
		result.add(customer2);
		result.add(program1);
		result.add(program2);
		result.add(card1);
		result.add(card2);
		result.add(card3);
		result.add(card4);

		return result;
	}

	/**
	 * @return A {@link List} containing all Objects used to test the
	 *         {@link Constraints} contained in the file 'invariant08.ocl'.
	 */
	private static List<Object> getModelObjectsForInv08() {

		List<Object> result;

		result = new ArrayList<Object>();

		LoyaltyProgram program1;
		LoyaltyProgram program2;

		Service service1;
		Service service2;

		ProgramPartner partner1;
		ProgramPartner partner2;

		Membership membership1;
		Membership membership2;

		LoyaltyAccount account1;

		account1 = new LoyaltyAccount();

		membership1 = new Membership();
		membership2 = new Membership();

		membership2.addAccount(account1);

		service1 = new Service();
		service2 = new Service();

		partner1 = new ProgramPartner();
		partner2 = new ProgramPartner();

		partner1.addDeliveredService(service1);
		partner2.addDeliveredService(service2);

		service1.setPointsBurned(0);
		service2.setPointsBurned(0);

		service1.setPointsEarned(0);
		service2.setPointsEarned(0);

		program1 = new LoyaltyProgram();
		program2 = new LoyaltyProgram();

		program1.addPartner(partner1);
		program2.addPartner(partner2);

		program1.setMembership(membership1);
		program2.setMembership(membership2);

		result.add(program1);
		result.add(program2);
		result.add(service1);
		result.add(service2);
		result.add(partner1);
		result.add(partner2);
		result.add(membership1);
		result.add(membership2);
		result.add(account1);

		return result;
	}

	/**
	 * @return A {@link List} containing all Objects used to test the
	 *         {@link Constraints} contained in the file 'invariant09.ocl'.
	 */
	private static List<Object> getModelObjectsForInv09() {

		List<Object> result;

		result = new ArrayList<Object>();

		/*
		 * context ProgramPartner inv nrOfParticipants: numberOfCustomers =
		 * programs.participants->size()
		 */
		ProgramPartner partner1;
		ProgramPartner partner2;

		LoyaltyProgram program1;
		LoyaltyProgram program2;

		Customer customer1;

		customer1 = new Customer(25);

		program1 = new LoyaltyProgram();
		program2 = new LoyaltyProgram();

		program1.enroll(customer1);

		partner1 = new ProgramPartner();
		partner2 = new ProgramPartner();

		partner1.setNumberOfCustomers(1);
		partner2.setNumberOfCustomers(1);

		partner1.addProgram(program1);
		partner2.addProgram(program2);

		result.add(partner1);
		result.add(partner2);
		result.add(program1);
		result.add(program2);
		result.add(customer1);

		return result;
	}

	/**
	 * @return A {@link List} containing all Objects used to test the
	 *         {@link Constraints} contained in the file 'invariant10.ocl'.
	 */
	private static List<Object> getModelObjectsForInv10() {

		List<Object> result;

		result = new ArrayList<Object>();

		LoyaltyProgram program1;
		LoyaltyProgram program2;

		ServiceLevel level1;
		ServiceLevel level2;

		level1 = new ServiceLevel();
		level2 = new ServiceLevel();

		level1.setName("Silver");
		level2.setName("Gold");

		program1 = new LoyaltyProgram();
		program2 = new LoyaltyProgram();

		program1.addLevel(level1);
		program1.addLevel(level2);
		program2.addLevel(level2);
		program2.addLevel(level1);

		result.add(program1);
		result.add(program2);
		result.add(level1);
		result.add(level2);

		return result;
	}

	/**
	 * @return A {@link List} containing all Objects used to test the
	 *         {@link Constraints} contained in the file 'invariant11.ocl'.
	 */
	private static List<Object> getModelObjectsForInv11() {

		List<Object> result;

		result = new ArrayList<Object>();

		ProgramPartner partner1;
		ProgramPartner partner2;

		Service service1;
		Service service2;

		Transaction ta1;
		Transaction ta2;

		ta1 = new Transaction();
		ta2 = new Transaction();

		ta1.setPoints(2000);
		ta2.setPoints(10000);

		service1 = new Service();
		service2 = new Service();

		service1.setTransaction(ta1);
		service2.setTransaction(ta2);

		partner1 = new ProgramPartner();
		partner2 = new ProgramPartner();

		partner1.addDeliveredService(service1);
		partner2.addDeliveredService(service2);

		result.add(partner1);
		result.add(partner2);
		result.add(service1);
		result.add(service2);
		result.add(ta1);
		result.add(ta2);

		return result;
	}

	/**
	 * @return A {@link List} containing all Objects used to test the
	 *         {@link Constraints} contained in the file 'invariant12.ocl'.
	 */
	private static List<Object> getModelObjectsForInv12() {

		List<Object> result;

		result = new ArrayList<Object>();

		ProgramPartner partner1;
		ProgramPartner partner2;

		Service service1;
		Service service2;

		Transaction ta1;
		Transaction ta2;

		ta1 = new Burning();
		ta2 = new Earning();

		ta1.setPoints(10000);
		ta2.setPoints(10000);

		service1 = new Service();
		service2 = new Service();

		service1.setTransaction(ta1);
		service2.setTransaction(ta2);

		partner1 = new ProgramPartner();
		partner2 = new ProgramPartner();

		partner1.addDeliveredService(service1);
		partner2.addDeliveredService(service2);

		result.add(partner1);
		result.add(partner2);
		result.add(service1);
		result.add(service2);
		result.add(ta1);
		result.add(ta2);

		return result;
	}

	/**
	 * @return A {@link List} containing all Objects used to test the
	 *         {@link Constraints} contained in the file 'invariant13.ocl'.
	 */
	private static List<Object> getModelObjectsForInv13() {

		List<Object> result;

		result = new ArrayList<Object>();

		CustomerCard card1;
		CustomerCard card2;

		card1 = new CustomerCard();
		card2 = new CustomerCard();

		card1.setValidFrom(new Date(2008, 1, 1));
		card2.setValidFrom(new Date(2008, 1, 1));

		card1.setValidThru(new Date(3008, 1, 1));
		card2.setValidThru(new Date(2009, 1, 1));

		card1.setValid(true);
		card2.setValid(true);

		result.add(card1);
		result.add(card2);

		return result;
	}

	/**
	 * @return A {@link List} containing all Objects used to test the
	 *         {@link Constraints} contained in the file 'invariant14.ocl'.
	 */
	private static List<Object> getModelObjectsForInv14() {

		List<Object> result;

		result = new ArrayList<Object>();

		LoyaltyAccount account1;
		LoyaltyAccount account2;

		Transaction ta1;
		Transaction ta2;
		Transaction ta3;
		Transaction ta4;

		CustomerCard card1;
		CustomerCard card2;
		CustomerCard card3;
		CustomerCard card4;

		Customer customer1;
		Customer customer2;

		customer1 = new Customer(25);
		customer2 = new Customer(25);

		card1 = new CustomerCard();
		card2 = new CustomerCard();
		card3 = new CustomerCard();
		card4 = new CustomerCard();

		card1.setOwner(customer1);
		card2.setOwner(customer1);
		card3.setOwner(customer1);
		card4.setOwner(customer2);

		ta1 = new Transaction();
		ta2 = new Transaction();
		ta3 = new Transaction();
		ta4 = new Transaction();

		ta1.setCard(card1);
		ta2.setCard(card2);
		ta3.setCard(card3);
		ta4.setCard(card4);

		account1 = new LoyaltyAccount();
		account2 = new LoyaltyAccount();

		account1.addTransaction(ta1);
		account1.addTransaction(ta2);
		account2.addTransaction(ta3);
		account2.addTransaction(ta4);

		result.add(account1);
		result.add(account2);
		result.add(ta1);
		result.add(ta2);
		result.add(ta3);
		result.add(ta4);
		result.add(card1);
		result.add(card2);
		result.add(card3);
		result.add(card4);
		result.add(customer1);
		result.add(customer2);

		return result;
	}

	/**
	 * @return A {@link List} containing all Objects used to test the
	 *         {@link Constraints} contained in the file 'invariant15.ocl'.
	 */
	private static List<Object> getModelObjectsForInv15() {

		List<Object> result;

		result = new ArrayList<Object>();

		LoyaltyAccount account1;
		LoyaltyAccount account2;

		Transaction ta1;

		ta1 = new Transaction();

		ta1.setPoints(100);

		account1 = new LoyaltyAccount();
		account2 = new LoyaltyAccount();

		account1.setPoints(100);
		account2.setPoints(100);

		account1.addTransaction(ta1);

		result.add(account1);
		result.add(account2);
		result.add(ta1);

		return result;
	}

	/**
	 * @return A {@link List} containing all Objects used to test the
	 *         {@link Constraints} contained in the file 'invariant18.ocl'.
	 */
	private static List<Object> getModelObjectsForInv18() {

		List<Object> result;

		result = new ArrayList<Object>();

		Transaction ta1;

		ta1 = new Burning();

		ta1.setPoints(100);

		result.add(ta1);

		return result;
	}

	/**
	 * @return A {@link List} containing all Objects used to test the
	 *         {@link Constraints} contained in the file 'boolean01.ocl'.
	 */
	private static List<Object> getModelObjectsForBoolean01() {

		List<Object> result;

		result = new ArrayList<Object>();

		Service service3;
		Service service4;

		service3 = new Service();
		service4 = new Service();

		service3.setPointsEarned(100);
		service3.setPointsBurned(0);

		service4.setPointsEarned(100);
		service4.setPointsBurned(50);

		result.add(service3);
		result.add(service4);

		return result;
	}

	/**
	 * @return A {@link List} containing all Objects used to test the
	 *         {@link Constraints} contained in the file 'boolean03.ocl'.
	 */
	private static List<Object> getModelObjectsForBoolean03() {

		List<Object> result;

		result = new ArrayList<Object>();

		Customer customer7;
		Customer customer8;
		Customer customer9;

		customer7 = new Customer(15);
		customer8 = new Customer(55);
		customer9 = new Customer(99);

		result.add(customer7);
		result.add(customer8);
		result.add(customer9);

		return result;
	}

	/**
	 * @return A {@link List} containing all Objects used to test the
	 *         {@link Constraints} contained in the file 'boolean04.ocl'.
	 */
	private static List<Object> getModelObjectsForBoolean04() {

		List<Object> result;

		result = new ArrayList<Object>();

		Customer customer10;
		Customer customer11;

		CustomerCard card3;
		CustomerCard card4;
		CustomerCard card5;
		CustomerCard card6;

		customer10 = new Customer(11);
		customer11 = new Customer(11);

		card3 = new CustomerCard();
		card4 = new CustomerCard();
		card5 = new CustomerCard();
		card6 = new CustomerCard();

		customer10.addCard(card3);
		customer10.addCard(card4);
		customer10.addCard(card5);
		customer10.addCard(card6);

		result.add(customer10);
		result.add(customer11);
		result.add(card3);
		result.add(card4);
		result.add(card5);
		result.add(card6);

		return result;
	}

	/**
	 * @return A {@link List} containing all Objects used to test the
	 *         {@link Constraints} contained in the file 'boolean05.ocl'.
	 */
	private static List<Object> getModelObjectsForBoolean05() {

		List<Object> result;

		result = new ArrayList<Object>();

		Customer customer12;
		Customer customer13;
		Customer customer14;

		customer12 = new Customer(25);
		customer12.setTitle("Mr.");
		customer13 = new Customer(25);
		customer13.setTitle("Ms.");
		customer14 = new Customer(25);
		customer14.setTitle("Dr.");

		result.add(customer12);
		result.add(customer13);
		result.add(customer14);

		return result;
	}

	/**
	 * @return A {@link List} containing all Objects used to test the
	 *         {@link Constraints} contained in the file 'boolean06.ocl'.
	 */
	private static List<Object> getModelObjectsForBoolean06() {

		List<Object> result;

		result = new ArrayList<Object>();

		Customer customer15;
		Customer customer16;

		customer15 = new Customer(25);
		customer15.setName("Foobar");
		customer16 = new Customer(25);
		customer16.setName("Nobar");

		result.add(customer15);
		result.add(customer16);

		return result;
	}

	/**
	 * @return A {@link List} containing all Objects used to test the
	 *         {@link Constraints} contained in the file 'invariant01.ocl'.
	 */
	private static List<Object> getModelObjectsForInvariant01() {

		List<Object> result;

		result = new ArrayList<Object>();

		Customer customer19;
		Customer customer20;
		Customer customer21;

		customer19 = new Customer(17);
		customer20 = new Customer(18);
		customer21 = new Customer(19);

		result.add(customer19);
		result.add(customer20);
		result.add(customer21);

		return result;
	}

	/**
	 * @return A {@link List} containing all Objects used to test the
	 *         {@link Constraints} contained in the file 'iterator07.ocl'.
	 */
	private static List<Object> getModelObjectsForIterator07() {

		List<Object> result;

		result = new ArrayList<Object>();

		CustomerCard card7;
		CustomerCard card8;

		Transaction transaction5;
		Transaction transaction6;

		card7 = new CustomerCard();
		card8 = new CustomerCard();

		transaction5 = new Transaction();
		transaction6 = new Transaction();

		transaction5.setPoints(100);
		transaction6.setPoints(200);

		card7.addTransaction(transaction5);
		card8.addTransaction(transaction5);
		card8.addTransaction(transaction6);

		result.add(card7);
		result.add(card8);
		result.add(transaction5);
		result.add(transaction6);

		return result;
	}

	/**
	 * @return A {@link List} containing all Objects used to test the
	 *         {@link Constraints} contained in the file 'iterator08.ocl'.
	 */
	private static List<Object> getModelObjectsForIterator08() {

		List<Object> result;

		result = new ArrayList<Object>();

		Customer customer17;
		Customer customer18;

		LoyaltyProgram program10;
		LoyaltyProgram program11;

		Membership membership6;
		Membership membership7;

		LoyaltyAccount account7;
		LoyaltyAccount account8;

		customer17 = new Customer(25);
		customer18 = new Customer(25);

		program10 = new LoyaltyProgram();
		program11 = new LoyaltyProgram();

		customer17.addProgram(program10);
		customer18.addProgram(program11);

		membership6 = new Membership();
		membership7 = new Membership();

		program10.setMembership(membership6);
		program11.setMembership(membership7);

		account7 = new LoyaltyAccount();
		account8 = new LoyaltyAccount();

		membership6.addAccount(account7);
		membership7.addAccount(account7);
		membership7.addAccount(account8);

		account7.setPoints(0);
		account8.setPoints(100);

		result.add(customer17);
		result.add(customer18);
		result.add(program10);
		result.add(program11);
		result.add(membership6);
		result.add(membership7);
		result.add(account7);
		result.add(account8);

		return result;
	}

	/**
	 * @return A {@link List} containing all Objects used to test the
	 *         {@link Constraints} contained in the file 'iterator09.ocl'.
	 */
	private static List<Object> getModelObjectsForIterator09() {

		List<Object> result;

		result = new ArrayList<Object>();

		LoyaltyProgram program12;
		LoyaltyProgram program13;

		Membership membership8;
		Membership membership9;

		LoyaltyAccount account9;
		LoyaltyAccount account10;

		program12 = new LoyaltyProgram();
		program13 = new LoyaltyProgram();

		membership8 = new Membership();
		membership9 = new Membership();

		program12.setMembership(membership8);
		program13.setMembership(membership9);

		account9 = new LoyaltyAccount();
		account10 = new LoyaltyAccount();

		membership8.addAccount(account9);
		membership8.addAccount(account10);
		membership9.addAccount(account10);

		account9.setNumber(9999);
		account10.setNumber(10000);

		result.add(program12);
		result.add(program13);
		result.add(membership8);
		result.add(membership9);
		result.add(account9);
		result.add(account10);

		return result;
	}

	/**
	 * @return A {@link List} containing all Objects used to test the
	 *         {@link Constraints} contained in the file 'iterator10.ocl'.
	 */
	private static List<Object> getModelObjectsForIterator10() {

		List<Object> result;

		result = new ArrayList<Object>();

		Customer customer3;
		Customer customer4;

		LoyaltyProgram program3;
		LoyaltyProgram program4;

		program3 = new LoyaltyProgram();
		program4 = new LoyaltyProgram();

		customer3 = new Customer(27);
		customer3.setName("Testman");

		customer4 = new Customer(95);
		customer4.setName("Testman");

		program3.enroll(customer3);
		program4.enroll(customer3);
		program4.enroll(customer4);

		result.add(program3);
		result.add(program4);
		result.add(customer3);
		result.add(customer4);

		return result;
	}

	/**
	 * @return A {@link List} containing all Objects used to test the
	 *         {@link Constraints} contained in the file 'iterator12.ocl'.
	 */
	private static List<Object> getModelObjectsForIterator12() {

		List<Object> result;

		result = new ArrayList<Object>();

		LoyaltyAccount account2;
		LoyaltyAccount account3;
		LoyaltyAccount account4;

		LoyaltyProgram program5;
		LoyaltyProgram program6;
		LoyaltyProgram program7;

		Membership membership3;
		Membership membership4;
		Membership membership5;

		program5 = new LoyaltyProgram();
		program6 = new LoyaltyProgram();
		program7 = new LoyaltyProgram();

		account2 = new LoyaltyAccount();
		account2.setNumber(9998);
		account3 = new LoyaltyAccount();
		account3.setNumber(9999);
		account4 = new LoyaltyAccount();
		account4.setNumber(10000);

		membership3 = new Membership();
		membership4 = new Membership();
		membership5 = new Membership();

		program5.setMembership(membership3);
		program6.setMembership(membership4);
		program7.setMembership(membership5);

		membership4.addAccount(account2);
		membership5.addAccount(account2);
		membership5.addAccount(account3);
		membership5.addAccount(account4);

		result.add(program5);
		result.add(program6);
		result.add(program7);

		result.add(account2);
		result.add(account3);
		result.add(account4);

		result.add(membership3);
		result.add(membership4);
		result.add(membership5);

		return result;
	}

	/**
	 * @return A {@link List} containing all Objects used to test the
	 *         {@link Constraints} contained in the file 'iterator14.ocl'.
	 */
	private static List<Object> getModelObjectsForIterator14() {

		List<Object> result;

		result = new ArrayList<Object>();

		Transaction transaction2;
		Transaction transaction3;
		Transaction transaction4;

		LoyaltyAccount account5;
		LoyaltyAccount account6;

		account5 = new LoyaltyAccount();
		account6 = new LoyaltyAccount();

		transaction2 = new Transaction();
		transaction3 = new Transaction();
		transaction4 = new Transaction();

		transaction2.setPoints(400);
		transaction3.setPoints(500);
		transaction4.setPoints(600);

		account5.addTransaction(transaction2);
		account5.addTransaction(transaction4);
		account6.addTransaction(transaction2);
		account6.addTransaction(transaction3);
		account6.addTransaction(transaction4);

		result.add(account5);
		result.add(account6);
		result.add(transaction2);
		result.add(transaction3);
		result.add(transaction4);

		return result;
	}

	/**
	 * @return A {@link List} containing all Objects used to test the
	 *         {@link Constraints} contained in the file 'iterator15.ocl'.
	 */
	private static List<Object> getModelObjectsForIterator15() {

		List<Object> result;

		result = new ArrayList<Object>();

		Customer customer5;
		Customer customer6;

		LoyaltyProgram program8;
		LoyaltyProgram program9;

		ProgramPartner partner2;
		ProgramPartner partner3;

		Service service2;

		customer5 = new Customer(25);
		customer6 = new Customer(25);

		program8 = new LoyaltyProgram();
		program9 = new LoyaltyProgram();

		customer5.addProgram(program8);
		customer6.addProgram(program9);

		partner2 = new ProgramPartner();
		partner3 = new ProgramPartner();

		program9.addPartner(partner2);
		program9.addPartner(partner3);

		service2 = new Service();

		partner3.addDeliveredService(service2);

		result.add(customer5);
		result.add(customer6);
		result.add(program8);
		result.add(program9);
		result.add(partner2);
		result.add(partner3);
		result.add(service2);

		return result;
	}

	/**
	 * @return A {@link List} containing all Objects used to test the
	 *         {@link Constraints} contained in the file 'iterator01.ocl'.
	 */
	private static List<Object> getModelObjectsForStatic01() {

		List<Object> result;

		result = new ArrayList<Object>();

		Transaction transaction1;

		Date date1;

		transaction1 = new Transaction();

		date1 = new Date(2008, 1, 1);

		transaction1.setDate(date1);

		result.add(transaction1);
		result.add(date1);

		return result;
	}
}
