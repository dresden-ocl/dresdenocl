package tudresden.ocl20.pivot.examples.royalsandloyals.test;

import static org.junit.Assert.fail;

import org.junit.Test;

import tudresden.ocl20.pivot.examples.royalsandloyals.Customer;
import tudresden.ocl20.pivot.examples.royalsandloyals.CustomerCard;
import tudresden.ocl20.pivot.examples.royalsandloyals.Earning;
import tudresden.ocl20.pivot.examples.royalsandloyals.LoyaltyProgram;
import tudresden.ocl20.pivot.examples.royalsandloyals.Membership;
import tudresden.ocl20.pivot.examples.royalsandloyals.ProgramPartner;
import tudresden.ocl20.pivot.examples.royalsandloyals.Service;
import tudresden.ocl20.pivot.examples.royalsandloyals.ServiceLevel;
import tudresden.ocl20.pivot.examples.royalsandloyals.Transaction;

/**
 * <p>
 * Provides some tests to tests the generated aspects for precondition
 * constraints.
 * </p>
 * 
 * @author Claas Wilke
 */
public class TestPreAspects {

	/**
	 * <p>
	 * Tests the generated Aspect {@link PreAspect1}.
	 * </p>
	 */
	@Test
	public void testPreAspect1() {

		LoyaltyProgram program1;
		Customer customer1;
		Customer customer2;

		program1 = new LoyaltyProgram();

		customer1 = new Customer(21);
		customer1.setName("");

		/* Violates the precondition. */
		try {
			program1.enroll(customer1);
			fail("An expected Exception was not thrown.");
		} catch (RuntimeException e) {
			/* Expected Exception. */
		}

		customer2 = new Customer(22);
		customer2.setName("Mr. Mustermann");

		/* Does not violate the precondition. */
		program1.enroll(customer2);
	}

	/**
	 * <p>
	 * Tests the generated Aspects {@link PreAspect2}, {@link PreAspect3} and
	 * {@link PreAspect4}.
	 * </p>
	 */
	@Test
	public void testPreAspect2to4() {

		LoyaltyProgram program1;

		ProgramPartner partner1;
		ServiceLevel level1;
		Service service1;

		program1 = new LoyaltyProgram();

		service1 = new Service();

		level1 = new ServiceLevel();
		partner1 = new ProgramPartner();

		/* Violates the precondition. */
		try {
			program1.addService(partner1, level1, service1);
			fail("An expected Exception was not thrown.");
		} catch (RuntimeException e) {
			/* Expected Exception. */
		}

		program1.addPartner(partner1);

		/* Still violates the precondition. */
		try {
			program1.addService(partner1, level1, service1);
			fail("An expected Exception was not thrown.");
		} catch (RuntimeException e) {
			/* Expected Exception. */
		}

		program1.addLevel(level1);

		/* Does not violate the precondition. */
		program1.addService(partner1, level1, service1);

	}

	/**
	 * <p>
	 * Tests the generated Aspect {@link PreAspect5}.
	 * </p>
	 */
	@Test
	public void testPreAspect5() {

		Transaction transaction1;
		Transaction transaction2;

		Service service1;
		ServiceLevel level1;
		LoyaltyProgram program1;

		CustomerCard card1;
		Membership membership1;

		transaction1 = new Transaction();

		service1 = new Service();
		transaction1.setService(service1);

		level1 = new ServiceLevel();
		service1.setLevel(level1);

		program1 = new LoyaltyProgram();
		level1.setProgram(program1);

		card1 = new CustomerCard();
		transaction1.setCard(card1);

		membership1 = new Membership();
		card1.setMembership(membership1);
		membership1.setProgram(program1);

		transaction1.getProgram();

		/*
		 * The constraint pre5 violates the method call for all sub classes of
		 * Transaction if the inheritance setting does not work properly.
		 */
		transaction2 = new Earning();

		transaction2.setService(service1);
		transaction2.setCard(card1);
		transaction2.getProgram();
	}

}
