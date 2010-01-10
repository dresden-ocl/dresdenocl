package tudresden.ocl20.pivot.examples.royalsandloyals.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import tudresden.ocl20.pivot.examples.royalsandloyals.Customer;
import tudresden.ocl20.pivot.examples.royalsandloyals.CustomerCard;
import tudresden.ocl20.pivot.examples.royalsandloyals.Date;
import tudresden.ocl20.pivot.examples.royalsandloyals.LoyaltyAccount;
import tudresden.ocl20.pivot.examples.royalsandloyals.Membership;

/**
 * <p>
 * Provides some tests to tests the generated aspects for body constraints.
 * </p>
 * 
 * @author Claas Wilke
 */
public class TestBodyAspects {

	/**
	 * <p>
	 * Tests the generated Aspect {@link BodyAspect2}.
	 * </p>
	 */
	@Test
	public void testBodyAspect2() {
	
		LoyaltyAccount account1;
		Membership membership1;
		CustomerCard card1;
		Customer owner1;
		
		account1 = new LoyaltyAccount();
		
		membership1 = new Membership();
		account1.setMembership(membership1);
		
		card1 = new CustomerCard();
		membership1.setCard(card1);
		
		owner1 = new Customer(21);
		card1.setOwner(owner1);
		
		assertEquals("", account1.getCustomerName());
		
		owner1.setName("owner1");
		assertEquals("owner1", account1.getCustomerName());
	}

	/**
	 * <p>
	 * Tests the generated Aspect {@link BodyAspect3}.
	 * </p>
	 */
	@Test
	public void testBodyAspect3() {
		
		assertNotNull(Date.nowAsString());	
	}
}