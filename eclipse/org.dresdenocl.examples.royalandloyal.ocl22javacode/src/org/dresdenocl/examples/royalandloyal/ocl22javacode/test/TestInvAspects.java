package org.dresdenocl.examples.royalandloyal.ocl22javacode.test;

import static org.junit.Assert.fail;

import org.dresdenocl.examples.royalandloyal.ocl22javacode.Burning;
import org.dresdenocl.examples.royalandloyal.ocl22javacode.Color;
import org.dresdenocl.examples.royalandloyal.ocl22javacode.Customer;
import org.dresdenocl.examples.royalandloyal.ocl22javacode.CustomerCard;
import org.dresdenocl.examples.royalandloyal.ocl22javacode.Date;
import org.dresdenocl.examples.royalandloyal.ocl22javacode.Earning;
import org.dresdenocl.examples.royalandloyal.ocl22javacode.LoyaltyAccount;
import org.dresdenocl.examples.royalandloyal.ocl22javacode.LoyaltyProgram;
import org.dresdenocl.examples.royalandloyal.ocl22javacode.Membership;
import org.dresdenocl.examples.royalandloyal.ocl22javacode.ProgramPartner;
import org.dresdenocl.examples.royalandloyal.ocl22javacode.Service;
import org.dresdenocl.examples.royalandloyal.ocl22javacode.ServiceLevel;
import org.dresdenocl.examples.royalandloyal.ocl22javacode.Transaction;
import org.junit.Test;


/**
 * <p>
 * Provides some tests to tests the generated aspects for invariant constraints.
 * </p>
 * 
 * @author Claas Wilke
 */
public class TestInvAspects {

	/**
	 * <p>
	 * Tests the generated Aspect {@link InvAspect1}.
	 * </p>
	 */
	@Test
	public void testInvAspect1() {

		Customer customer1;

		/* Violates the invariant. */
		try {
			customer1 = new Customer(1);
			fail("An expected Exception was not thrown.");
		} catch (RuntimeException e) {
			/* Expected Exception. */
		}

		/* Does not violate the invariant. */
		customer1 = new Customer(22);

		/* Violates the invariant. */
		try {
			customer1.setAge(17);
			fail("An expected Exception was not thrown.");
		} catch (RuntimeException e) {
			/* Expected Exception. */
		}

		/* Violates the invariant. */
		try {
			customer1.getAge();
			fail("An expected Exception was not thrown.");
		} catch (RuntimeException e) {
			/* Expected Exception. */
		}

		/* Violates the invariant. */
		try {
			customer1.birthdayHappens();
			fail("An expected Exception was not thrown.");
		} catch (RuntimeException e) {
			/* Expected Exception. */
		}

		customer1 = new Customer(18);

		/* Does not violate the invariant. */
		customer1.setAge(42);

		/* Does not violate the invariant. */
		customer1.birthdayHappens();
	}

	/**
	 * <p>
	 * Tests the generated Aspect {@link InvAspect2}.
	 * </p>
	 */
	@Test
	public void testInvAspect2() {

		CustomerCard card1;

		card1 = new CustomerCard();

		card1.setValidFrom(new Date(2008, 1, 1));

		/* Violates the invariant. */
		try {
			card1.setValidThru(new Date(2007, 1, 1));
			fail("An expected Exception was not thrown.");
		} catch (RuntimeException e) {
			/* Expected Exception. */
		}

		/* Does not violate the invariant. */
		card1.setValidThru(new Date(2008, 1, 2));
	}

	/**
	 * <p>
	 * Tests the generated Aspects {@link InvAspect3}, {@link InvAspect4},
	 * {@link InvAspect5}, {@link InvAspect7} and {@link InvAspect9}.
	 * </p>
	 */
	@Test
	public void testInvAspect3to5_7and8() {

		LoyaltyProgram program1;
		Membership membership1;

		ServiceLevel level1;
		ServiceLevel level2;

		Customer customer1;
		CustomerCard card1;

		level1 = new ServiceLevel();
		level2 = new ServiceLevel();

		membership1 = new Membership();
		membership1.setCurrentLevel(level2);

		program1 = new LoyaltyProgram();
		program1.addLevel(level1);
		program1.setMembership(membership1);

		/* Check inv03. */

		/* Violates the invariant 3. */
		try {
			program1.checkInvariants();
			fail("An expected Exception was not thrown.");
		} catch (RuntimeException e) {
			/* Expected Exception. */
		}

		program1.addLevel(level2);

		/* Violates the invariant 9. */
		try {
			program1.checkInvariants();
			fail("An expected Exception was not thrown.");
		} catch (RuntimeException e) {
			/* Expected Exception. */
		}

		level1.setName("Silver");

		/* Does not violate the invariant 3 nor 9. */
		program1.checkInvariants();

		/* Check inv04. */

		/*
		 * context Membership inv correctCard:
		 * program.participants.cards->includes(self.card)
		 */
		card1 = new CustomerCard();

		customer1 = new Customer(21);
		customer1.setName("customer1");
		customer1.addCard(card1);

		program1.enroll(customer1);

		/* Violates the invariant 4. */
		try {
			membership1.checkInvariants();
			fail("An expected Exception was not thrown.");
		} catch (RuntimeException e) {
			/* Expected Exception. */
		}

		membership1.setCard(card1);

		/* Does not violate the invariant 4. */
		card1.setColor(Color.silver);
		level2.setName("Silver");
		membership1.checkInvariants();

		/* Check inv05. */
		ProgramPartner partner1;
		Service service1;

		partner1 = new ProgramPartner();
		program1.addPartner(partner1);

		/* Violates the invariant 5. */
		try {
			program1.checkInvariants();
			fail("An expected Exception was not thrown.");
		} catch (RuntimeException e) {
			/* Expected Exception. */
		}

		/* Does not violate the invariant 5. */
		service1 = new Service();
		partner1.addDeliveredService(service1);

		/* Check inv07. */

		/*
		 * context LoyaltyProgram inv noAccounts: partners.deliveredServices
		 * ->forAll(pointsEarned = 0 and pointsBurned = 0) implies
		 * membership.accounts->isEmpty()
		 */
		LoyaltyAccount account1;

		service1.setPointsEarned(0);
		service1.setPointsBurned(0);

		account1 = new LoyaltyAccount();
		membership1.addAccount(account1);

		/* Violates the invariant 7. */
		try {
			program1.checkInvariants();
			fail("An expected Exception was not thrown.");
		} catch (RuntimeException e) {
			/* Expected Exception. */
		}

		service1.setPointsEarned(100);

		/* Does not violate the invariant 7. */
		program1.checkInvariants();
	}

	/**
	 * <p>
	 * Tests the generated Aspect {@link InvAspect6}.
	 * </p>
	 */
	@Test
	public void testInvAspect6() {

		Customer customer1;
		LoyaltyProgram program1;

		CustomerCard card1;

		customer1 = new Customer(21);
		customer1.setName("customer1");

		/* Does not violate the invariant 6. */
		customer1.checkInvariants();

		program1 = new LoyaltyProgram();
		customer1.addProgram(program1);

		/* Violates the invariant 5. */
		try {
			customer1.checkInvariants();
			fail("An expected Exception was not thrown.");
		} catch (RuntimeException e) {
			/* Expected Exception. */
		}

		card1 = new CustomerCard();
		customer1.addCard(card1);

		/* Does not violate the invariant 6. */
		customer1.checkInvariants();

		card1.setValid(false);

		/* Violates the invariant 6. */
		try {
			customer1.checkInvariants();
			fail("An expected Exception was not thrown.");
		} catch (RuntimeException e) {
			/* Expected Exception. */
		}
	}

	/**
	 * <p>
	 * Tests the generated Aspects {@link InvAspect8}, {@link InvAspect10} and
	 * {@link InvAspect11}.
	 * </p>
	 */
	@Test
	public void testInvAspect8and10to11() {

		/*
		 * context ProgramPartner inv nrOfParticipants: numberOfCustomers =
		 * programs.participants->size()
		 */

		ProgramPartner partner1;
		Customer customer1;
		LoyaltyProgram program1;

		partner1 = new ProgramPartner();

		/* Does not violate the invariant 8. */
		partner1.checkInvariants();

		partner1.setNumberOfCustomers(1);

		/* Violates the invariant 8. */
		try {
			partner1.checkInvariants();
			fail("An expected Exception was not thrown.");
		} catch (RuntimeException e) {
			/* Expected Exception. */
		}

		customer1 = new Customer(21);
		customer1.setName("customer1");
		program1 = new LoyaltyProgram();

		program1.enroll(customer1);
		partner1.addProgram(program1);

		/* Does not violate the invariant 8. */
		partner1.checkInvariants();

		/* Check invariant 10 */
		Service service1;
		Service service2;
		Transaction transaction1;
		Transaction transaction2;

		service1 = new Service();
		partner1.addDeliveredService(service1);

		transaction1 = new Transaction();
		transaction1.setPoints(1000);
		service1.setTransaction(transaction1);

		/* Does not violate the invariant 10 and 11. */
		partner1.checkInvariants();

		service2 = new Service();
		partner1.addDeliveredService(service2);

		transaction2 = new Earning();
		transaction2.setPoints(10000);
		service2.setTransaction(transaction2);

		/* Violates the invariant 10 and 11. */
		try {
			partner1.checkInvariants();
			fail("An expected Exception was not thrown.");
		} catch (RuntimeException e) {
			/* Expected Exception. */
		}

		transaction2.setPoints(8999);

		/* Does not violate the invariant 10 and 11. */
		partner1.checkInvariants();
	}

	/**
	 * <p>
	 * Tests the generated Aspect {@link InvAspect12} and {@link InvAspect13}.
	 * </p>
	 */
	@Test
	public void testInvAspect12and13() {

		LoyaltyAccount account1;

		Transaction transaction1;
		Transaction transaction2;

		CustomerCard card1;
		CustomerCard card2;

		Customer customer1;
		Customer customer2;

		account1 = new LoyaltyAccount();

		/* Violates the invariant 12. */
		try {
			account1.checkInvariants();
			fail("An expected Exception was not thrown.");
		} catch (RuntimeException e) {
			/* Expected Exception. */
		}

		transaction1 = new Transaction();
		card1 = new CustomerCard();

		customer1 = new Customer(21);
		customer1.setName("customer1");
		customer1.addCard(card1);

		transaction1.setCard(card1);
		account1.addTransaction(transaction1);

		/* Does not violate the invariant 12. */
		account1.checkInvariants();

		transaction2 = new Transaction();
		card2 = new CustomerCard();

		customer2 = new Customer(21);
		customer2.setName("customer1");
		customer2.addCard(card2);

		transaction2.setCard(card2);
		account1.addTransaction(transaction2);

		/* Violates the invariant 12. */
		try {
			account1.checkInvariants();
			fail("An expected Exception was not thrown.");
		} catch (RuntimeException e) {
			/* Expected Exception. */
		}

		customer1.addCard(card2);

		/* Does not violate the invariant 12. */
		account1.checkInvariants();

		/* Check invariant 13. */

		account1.setPoints(1);

		/* Violates the invariant 13. */
		try {
			account1.checkInvariants();
			fail("An expected Exception was not thrown.");
		} catch (RuntimeException e) {
			/* Expected Exception. */
		}

		transaction1.setPoints(1);

		/* Does not violate the invariant 13. */
		account1.checkInvariants();
	}

	/**
	 * <p>
	 * Tests the generated Aspect {@link InvAspect14} and {@link InvAspect15}.
	 * </p>
	 */
	@Test
	public void testInvAspect14and15() {

		Service service1;

		service1 = new Service();

		service1.checkInvariants();
	}

	/**
	 * <p>
	 * Tests the generated Aspect {@link InvAspect16}.
	 * </p>
	 */
	@Test
	public void testInvAspect16() {

		Burning burning1;

		burning1 = new Burning();
		burning1.setPoints(100);

		burning1.checkInvariants();

	}

	/**
	 * <p>
	 * Tests the generated Aspect {@link InvAspect17}.
	 * </p>
	 */
	@Test
	public void testInvAspect17() {

		Membership membership1;
		Membership membership2;

		ServiceLevel level1;
		ServiceLevel level2;

		CustomerCard card1;
		CustomerCard card2;
		
		LoyaltyProgram program1;
		
		Customer customer1;

		level1 = new ServiceLevel();
		level1.setName("Silver");

		level2 = new ServiceLevel();
		level2.setName("Silver");

		membership1 = new Membership();
		membership1.setCurrentLevel(level1);

		membership2 = new Membership();
		membership2.setCurrentLevel(level2);

		card1 = new CustomerCard();
		card1.setColor(Color.gold);

		card2 = new CustomerCard();
		card2.setColor(Color.silver);

		membership1.setCard(card1);
		membership2.setCard(card2);
		
		/* Additional code to fulfill invariant 4. */
		program1 = new LoyaltyProgram();
		
		membership1.setProgram(program1);
		membership2.setProgram(program1);
		
		customer1 = new Customer(19);
		customer1.setName("Tesmann");
		
		program1.enroll(customer1);
		
		customer1.addCard(card1);
		customer1.addCard(card2);

		/* Violates the invariant 17. */
		try {
			membership1.checkInvariants();
			fail("An expected Exception was not thrown.");
		} catch (RuntimeException e) {
			/* Expected Exception. */
		}

		/* Does not violate the invariant. */
		membership2.checkInvariants();
	}
	
	/**
	 * <p>
	 * Tests the generated Aspect {@link InvAspect18}.
	 * </p>
	 */
	@Test
	public void testInvAspect18() {
		
		CustomerCard card1;
		
		card1 = new CustomerCard();
		
		card1.setValidFrom(new Date(2008, 1, 1));
		card1.setValidThru(new Date(2009, 1, 1));
		card1.setValid(true);

		/* Violates the invariant 18. */
		try {
			card1.checkInvariants();
			fail("An expected Exception was not thrown.");
		} catch (RuntimeException e) {
			/* Expected Exception. */
		}
		
		card1.setValidThru(new Date(3009, 1, 1));

		/* Does not violate the invariant. */
		card1.checkInvariants();

	}
}
