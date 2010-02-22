package tudresden.ocl20.pivot.examples.royalsandloyals.constraints;

/**
 * <p>Generated Aspect to enforce OCL constraint.</p>
 *
 * @author OCL22Java of Dresden OCL2 for Eclipse
 * @Generated
 */
public privileged aspect InvAspect12 {

    /* Declares a new super class containing the new attribute or method. */
    declare parents : tudresden.ocl20.pivot.examples.royalsandloyals.LoyaltyAccount extends tudresden.ocl20.pivot.examples.royalsandloyals.constraints.ExtendedLoyaltyAccount;
    
    /**
     * <p>Pointcut for all calls on {@link tudresden.ocl20.pivot.examples.royalsandloyals.LoyaltyAccount#checkInvariants()}.</p>
     */
    protected pointcut checkInvariantsCaller(tudresden.ocl20.pivot.examples.royalsandloyals.LoyaltyAccount aClass):
    	call(void tudresden.ocl20.pivot.examples.royalsandloyals.LoyaltyAccount.checkInvariants())
    	&& target(aClass);
    
    /**
     * <p><code>Checks an invariant on the class LoyaltyAccount defined by the constraint
     * <code>context LoyaltyAccount
     *       inv: transactions[].card[].owner[].asSet().size().=( 1)</code></p>
     */
    after(tudresden.ocl20.pivot.examples.royalsandloyals.LoyaltyAccount aClass) : checkInvariantsCaller(aClass) {
        /* Disable this constraint for subclasses of LoyaltyAccount. */
        if (aClass.getClass().getCanonicalName().equals("tudresden.ocl20.pivot.examples.royalsandloyals.LoyaltyAccount")) {
        tudresden.ocl20.pivot.ocl2java.types.OclBag<tudresden.ocl20.pivot.examples.royalsandloyals.CustomerCard> result2;
        result2 = new tudresden.ocl20.pivot.ocl2java.types.OclBag<tudresden.ocl20.pivot.examples.royalsandloyals.CustomerCard>();
        
        /* Iterator Collect: Iterate through all elements and collect them. Elements which are collections are flattened. */
        for (tudresden.ocl20.pivot.examples.royalsandloyals.Transaction anElement2 : aClass.transactions) {
            result2.add(anElement2.card);
        }
        tudresden.ocl20.pivot.ocl2java.types.OclBag<tudresden.ocl20.pivot.examples.royalsandloyals.Customer> result1;
        result1 = new tudresden.ocl20.pivot.ocl2java.types.OclBag<tudresden.ocl20.pivot.examples.royalsandloyals.Customer>();
        
        /* Iterator Collect: Iterate through all elements and collect them. Elements which are collections are flattened. */
        for (tudresden.ocl20.pivot.examples.royalsandloyals.CustomerCard anElement1 : result2) {
            result1.add(anElement1.owner);
        }
    
        if (!((Object) result1.asSet().size()).equals(new Integer(1))) {
        	// TODO Auto-generated code executed when constraint is violated.
        	throw new RuntimeException("Error: Constraint was violated.");
        }
        // no else.
        }
        // no else.
    }
}