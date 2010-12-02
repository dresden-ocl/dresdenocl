package tudresden.ocl20.pivot.examples.royalsandloyals.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import tudresden.ocl20.pivot.examples.royalsandloyals.Customer;
import tudresden.ocl20.pivot.examples.royalsandloyals.CustomerCard;
import tudresden.ocl20.pivot.examples.royalsandloyals.Date;
import tudresden.ocl20.pivot.examples.royalsandloyals.Earning;
import tudresden.ocl20.pivot.examples.royalsandloyals.LoyaltyAccount;
import tudresden.ocl20.pivot.examples.royalsandloyals.Transaction;

/**
 * <p>
 * Provides some tests to tests the generated aspects for derive constraints.
 * </p>
 * 
 * @author Claas Wilke
 */
public class TestDeriveAspects {

	/**
	 * <p>
	 * Tests the generated Aspect {@link DeriveAspect1}.
	 * </p>
	 */
	@Test
	public void testDeriveAspect1() {

		CustomerCard customerCard1;
		Customer customer1;

		customerCard1 = new CustomerCard();
		customer1 = new Customer(21);

		customerCard1.setOwner(customer1);

		assertEquals(" ", customerCard1.getPrintedName());

		customer1.setName("Mustermann");

		assertEquals(" Mustermann", customerCard1.getPrintedName());

		customer1.setTitle("Dr.");

		assertEquals("Dr. Mustermann", customerCard1.getPrintedName());
	}

	/**
	 * <p>
	 * Tests the generated Aspect {@link DeriveAspect2}.
	 * </p>
	 */
	@Test
	public void testDeriveAspect2() {

		LoyaltyAccount account1;
		Transaction transaction1;
		Transaction transaction2;
		Transaction transaction3;

		account1 = new LoyaltyAccount();

		assertEquals(0, account1.getTotalPointsEarned());

		transaction1 = new Earning();
		account1.addTransaction(transaction1);

		assertEquals(0, account1.getTotalPointsEarned());

		transaction1.setPoints(100);
		assertEquals(100, account1.getTotalPointsEarned());

		transaction2 = new Earning();
		transaction2.setPoints(150);
		account1.addTransaction(transaction2);

		assertEquals(250, account1.getTotalPointsEarned());

		transaction3 = new Transaction();
		transaction3.setPoints(-250);
		account1.addTransaction(transaction3);

		assertEquals(250, account1.getTotalPointsEarned());
	}

	/**
	 * <p>
	 * Tests the generated Aspect {@link DeriveAspect3}.
	 * </p>
	 */
	@Test
	public void testDeriveAspect3() {

		assertEquals(Date.nowString, Date.nowAsString());
	}
}
