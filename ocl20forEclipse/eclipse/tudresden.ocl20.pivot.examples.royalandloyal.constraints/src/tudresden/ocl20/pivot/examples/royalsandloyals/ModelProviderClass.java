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

		int countOfTransactions;

		allObjects = ModelProviderClass.getModelObjects();

		countOfTransactions = 0;

		for (Object anElement : allObjects) {

			if (anElement instanceof Transaction) {
				countOfTransactions++;
			}
		}

		System.out.println("Example contains " + countOfTransactions
				+ " Transactions.");
	}

	/**
	 * @return A model instance of the royal and loyal UML model.
	 */
	public static List<Object> getModelObjects() {
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
