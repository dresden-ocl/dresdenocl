package org.dresdenocl.examples.royalsandloyals.ocl22javacode.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Set;

import org.dresdenocl.examples.royalsandloyals.ocl22javacode.Burning;
import org.dresdenocl.examples.royalsandloyals.ocl22javacode.Customer;
import org.dresdenocl.examples.royalsandloyals.ocl22javacode.CustomerCard;
import org.dresdenocl.examples.royalsandloyals.ocl22javacode.Date;
import org.dresdenocl.examples.royalsandloyals.ocl22javacode.Earning;
import org.dresdenocl.examples.royalsandloyals.ocl22javacode.LoyaltyAccount;
import org.dresdenocl.examples.royalsandloyals.ocl22javacode.LoyaltyProgram;
import org.dresdenocl.examples.royalsandloyals.ocl22javacode.Membership;
import org.dresdenocl.examples.royalsandloyals.ocl22javacode.ProgramPartner;
import org.dresdenocl.examples.royalsandloyals.ocl22javacode.Service;
import org.dresdenocl.examples.royalsandloyals.ocl22javacode.ServiceLevel;
import org.dresdenocl.examples.royalsandloyals.ocl22javacode.Transaction;
import org.junit.Test;


/**
 * <p>
 * Provides some tests to tests the generated aspects for def constraints.
 * </p>
 * 
 * @author Claas Wilke
 */
public class TestDefAspects {

	/**
	 * <p>
	 * Tests the generated Aspect {@link DefAspect1}.
	 * </p>
	 */
	@Test
	public void testDefAspect1() {

		LoyaltyAccount anAccount;
		Transaction transaction1;
		Transaction transaction2;
		Transaction transaction3;

		anAccount = new LoyaltyAccount();
		assertEquals(new Float(0), (Float) anAccount.getTurnover());

		transaction1 = new Transaction();
		transaction1.setAmount(1);
		anAccount.addTransaction(transaction1);
		assertEquals(new Float(1.0), (Float) anAccount.getTurnover());

		transaction2 = new Transaction();
		transaction2.setAmount((float) 1.5);
		anAccount.addTransaction(transaction2);
		assertEquals(new Float(2.5), (Float) anAccount.getTurnover());

		transaction3 = new Transaction();
		transaction3.setAmount((float) -2.5);
		anAccount.addTransaction(transaction3);
		assertEquals(new Float(0), (Float) anAccount.getTurnover());
	}

	/**
	 * <p>
	 * Tests the generated Aspect {@link DefAspect2}.
	 * </p>
	 */
	@Test
	public void testDefAspect2() {

		LoyaltyProgram aProgram;

		ServiceLevel level1;
		ServiceLevel level2;

		Service service1;
		Service service2;
		Service service3;

		aProgram = new LoyaltyProgram();

		assertEquals(0, aProgram.getServicesByLevel("level1").size());

		level1 = new ServiceLevel();
		level1.setName("level1");
		aProgram.addLevel(level1);

		assertEquals(0, aProgram.getServicesByLevel("level1").size());

		service1 = new Service();
		level1.addAvailableService(service1);

		aProgram.getServicesByLevel("level1");

		assertEquals(1, aProgram.getServicesByLevel("level1").size());

		service2 = new Service();
		level1.addAvailableService(service2);

		assertEquals(2, aProgram.getServicesByLevel("level1").size());

		level2 = new ServiceLevel();
		level2.setName("level2");
		aProgram.addLevel(level2);

		service3 = new Service();
		level2.addAvailableService(service3);

		assertEquals(2, aProgram.getServicesByLevel("level1").size());
		assertEquals(1, aProgram.getServicesByLevel("level2").size());

		assertEquals(0, aProgram.getServicesByLevel("level3").size());
	}

	/**
	 * <p>
	 * Tests the generated Aspect {@link DefAspect2}.
	 * </p>
	 */
	@Test
	public void testDefAspect3() {

		Membership membership1;
		ServiceLevel level1;

		membership1 = new Membership();
		level1 = new ServiceLevel();
		membership1.setCurrentLevel(level1);

		assertNull(membership1.getCurrentLevelName());

		level1.setName("level1");

		assertEquals("level1", membership1.getCurrentLevelName());
	}

	/**
	 * <p>
	 * Tests the generated Aspect {@link DefAspect4}.
	 * </p>
	 */
	@Test
	public void testDefAspect4() {

		LoyaltyAccount anAccount;
		Transaction transaction1;
		Transaction transaction2;
		Service service1;
		Service service2;

		anAccount = new LoyaltyAccount();

		assertEquals(0, anAccount.getUsedServices().size());

		transaction1 = new Transaction();
		service1 = new Service();
		transaction1.setService(service1);
		anAccount.addTransaction(transaction1);

		assertEquals(1, anAccount.getUsedServices().size());

		transaction2 = new Transaction();
		service2 = new Service();
		transaction2.setService(service2);
		anAccount.addTransaction(transaction2);

		assertEquals(2, anAccount.getUsedServices().size());
	}

	/**
	 * <p>
	 * Tests the generated Aspect {@link DefAspect5}.
	 * </p>
	 */
	@Test
	public void testDefAspect5() {

		Customer customer1;

		customer1 = new Customer(21);
		customer1.setName("Mustermann");

		assertEquals("M", customer1.getInitial());
	}

	/**
	 * <p>
	 * Tests the generated Aspect {@link DefAspect6}.
	 * </p>
	 */
	@Test
	public void testDefAspect6() {

		CustomerCard card1;
		Date date1;
		Transaction transaction1;
		Transaction transaction2;

		card1 = new CustomerCard();
		date1 = new Date(2008, 12, 1);

		assertEquals(new Integer(0), (Integer) card1.getTotalPoints(date1));

		transaction1 = new Transaction();
		transaction1.setDate(new Date(2008, 12, 2));
		transaction1.setPoints(100);
		card1.addTransaction(transaction1);

		assertEquals(new Integer(100), (Integer) card1.getTotalPoints(date1));

		transaction2 = new Transaction();
		transaction2.setDate(new Date(2008, 11, 2));
		transaction2.setPoints(100);
		card1.addTransaction(transaction2);

		assertEquals(new Integer(100), (Integer) card1.getTotalPoints(date1));
	}

	/**
	 * <p>
	 * Tests the generated Aspect {@link DefAspect7}.
	 * </p>
	 */
	@Test
	public void testDefAspect7() {

		CustomerCard card1;
		CustomerCard card2;
		CustomerCard card3;

		card1 = new CustomerCard();

		assertTrue(card1.getAllInstances().contains(card1));

		card2 = new CustomerCard();

		assertTrue(card1.getAllInstances().contains(card1));
		assertTrue(card1.getAllInstances().contains(card2));

		card3 = new CustomerCard();

		assertTrue(card1.getAllInstances().contains(card1));
		assertTrue(card1.getAllInstances().contains(card2));
		assertTrue(card1.getAllInstances().contains(card3));
	}

	/**
	 * <p>
	 * Tests the generated Aspect {@link DefAspect8}.
	 * 
	 * context ProgramPartner def: getBurningTransactions =
	 * self[].deliveredServices[].transaction[] -> iterate (t:Transaction ;
	 * resultSet:Set( Transaction )=Set{} | if t[].oclIsTypeOf( Burning[]) then
	 * resultSet[].including( t[]) else resultSet[] )
	 * </p>
	 */
	@Test
	public void testDefAspect8() {

		ProgramPartner programPartner1;

		Service service1;
		Service service2;

		Transaction transaction1;
		Transaction transaction2;

		Set<Transaction> burningTransactions;

		programPartner1 = new ProgramPartner();

		burningTransactions = programPartner1.getBurningTransactions();
		assertEquals(0, burningTransactions.size());

		service1 = new Service();
		programPartner1.addDeliveredService(service1);
		transaction1 = new Burning();
		service1.setTransaction(transaction1);

		burningTransactions = programPartner1.getBurningTransactions();
		assertEquals(1, burningTransactions.size());
		assertTrue(burningTransactions.contains(transaction1));

		service2 = new Service();
		programPartner1.addDeliveredService(service2);
		transaction2 = new Earning();
		service2.setTransaction(transaction2);

		burningTransactions = programPartner1.getBurningTransactions();
		assertEquals(1, burningTransactions.size());
		assertTrue(burningTransactions.contains(transaction1));
		assertFalse(burningTransactions.contains(transaction2));
	}
}