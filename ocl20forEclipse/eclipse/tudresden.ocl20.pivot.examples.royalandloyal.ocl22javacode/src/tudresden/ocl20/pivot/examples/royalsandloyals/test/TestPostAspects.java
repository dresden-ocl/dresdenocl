package tudresden.ocl20.pivot.examples.royalsandloyals.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import org.dresdenocl.tudresden.ocl20.pivot.examples.royalsandloyals.Customer;
import org.dresdenocl.tudresden.ocl20.pivot.examples.royalsandloyals.CustomerCard;
import org.dresdenocl.tudresden.ocl20.pivot.examples.royalsandloyals.Date;
import org.dresdenocl.tudresden.ocl20.pivot.examples.royalsandloyals.Earning;
import org.dresdenocl.tudresden.ocl20.pivot.examples.royalsandloyals.LoyaltyAccount;
import org.dresdenocl.tudresden.ocl20.pivot.examples.royalsandloyals.LoyaltyProgram;
import org.dresdenocl.tudresden.ocl20.pivot.examples.royalsandloyals.Membership;
import org.dresdenocl.tudresden.ocl20.pivot.examples.royalsandloyals.ProgramPartner;
import org.dresdenocl.tudresden.ocl20.pivot.examples.royalsandloyals.Service;
import org.dresdenocl.tudresden.ocl20.pivot.examples.royalsandloyals.ServiceLevel;
import org.dresdenocl.tudresden.ocl20.pivot.examples.royalsandloyals.Transaction;
import org.junit.Test;


/**
 * <p>
 * Provides some tests to tests the generated aspects for precondition
 * constraints.
 * </p>
 * 
 * @author Claas Wilke
 */
public class TestPostAspects {

	/**
	 * <p>
	 * Tests the generated Aspect {@link PostAspect1}.
	 * </p>
	 */
	@Test
	public void testPostAspect1() {

		LoyaltyProgram program1;
		Customer customer1;
		Customer customer2;

		program1 = new LoyaltyProgram();

		customer1 = new Customer(21);
		customer1.setName("c1");
		program1.enroll(customer1);

		customer2 = new Customer(22);
		customer2.setName("c2");
		program1.enroll(customer2);
	}

	/**
	 * <p>
	 * Tests the generated Aspects {@link PostAspect2}, {@link PostAspect3} and
	 * {@link PostAspect4}.
	 * </p>
	 */
	@Test
	public void testPostAspect2to4() {

		LoyaltyProgram program1;

		ProgramPartner partner1;
		ServiceLevel level1;
		Service service1;

		program1 = new LoyaltyProgram();

		partner1 = new ProgramPartner();
		program1.addPartner(partner1);

		level1 = new ServiceLevel();
		program1.addLevel(level1);

		service1 = new Service();

		program1.addService(partner1, level1, service1);
	}

	/**
	 * <p>
	 * Tests the generated Aspect {@link PostAspect5}.
	 * </p>
	 */
	@Test
	public void testPostAspect5() {

		LoyaltyAccount account1;
		Transaction transaction1;

		account1 = new LoyaltyAccount();
		account1.isEmpty();

		account1.setPoints(100);
		try {
			account1.isEmpty();
			fail("An expected exception was not thrown.");
		} catch (RuntimeException e) {
			/* Should not be empty -> error. */
		}

		transaction1 = new Transaction();
		account1.addTransaction(transaction1);

		/* Should work again. */
		account1.isEmpty();
	}

	/**
	 * <p>
	 * Tests the generated Aspect {@link PostAspect6}.
	 * </p>
	 */
	@Test
	public void testPostAspect6() {

		Customer customer1;

		customer1 = new Customer(21);

		customer1.birthdayHappens();
	}

	/**
	 * <p>
	 * Tests the generated Aspect {@link PostAspect7}.
	 * </p>
	 */
	@Test
	public void testPostAspect7() {

		Service service1;

		service1 = new Service();
		service1.setPointsEarned(0);
		service1.setPointsBurned(0);

		service1.upgradePointsEarned(100);
	}

	/**
	 * <p>
	 * Tests the generated Aspects {@link PostAspect8}, {@link PostAspect9} and
	 * {@link PostAspect10}.
	 * </p>
	 */
	@Test
	public void testPostAspect8to10() {

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
		 * The constraint post10 violates the method call for all sub classes of
		 * Transaction if the inheritance setting does not work properly.
		 */
		transaction2 = new Earning();
		
		transaction2.setService(service1);
		transaction2.setCard(card1);
		transaction2.getProgram();
	}
	
	/**
	 * <p>
	 * Tests the generated Aspect {@link PostAspect11}.
	 * </p>
	 */
	@Test
	public void testPostAspect11() {

		Date date1;
		
		date1 = Date.now();
		
		assertNotNull(date1);
	}
}
