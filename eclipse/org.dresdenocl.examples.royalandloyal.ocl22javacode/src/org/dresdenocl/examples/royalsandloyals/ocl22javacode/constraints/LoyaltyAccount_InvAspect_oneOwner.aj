package org.dresdenocl.examples.royalsandloyals.ocl22javacode.constraints;

/**
 * <p>Generated Aspect to enforce OCL constraint.</p>
 *
 * @author OCL22Java of Dresden OCL2 for Eclipse
 * @Generated
 */
public privileged aspect LoyaltyAccount_InvAspect_oneOwner {

    declare parents: org.dresdenocl.examples.royalsandloyals.ocl22javacode.LoyaltyAccount implements org.dresdenocl.tools.codegen.ocl2java.types.OclCheckable;

    public void org.dresdenocl.examples.royalsandloyals.ocl22javacode.LoyaltyAccount.checkInvariants() {
        /* Remains empty. Is only filled with behavior by advice(s). */
    }
    /**
     * <p>Pointcut for all calls on {@link org.dresdenocl.examples.royalsandloyals.ocl22javacode.LoyaltyAccount#checkInvariants()}.</p>
     */
    protected pointcut checkInvariantsCaller(org.dresdenocl.examples.royalsandloyals.ocl22javacode.LoyaltyAccount aClass):
    	call(void checkInvariants())
    	&& target(aClass);

    /**
     * <p><code>Checks an invariant on the class LoyaltyAccount defined by the constraint
     * <code>context LoyaltyAccount
     *       inv oneOwner: transactions.card.owner->asSet()->size() = 1</code></p>
     */
    after(org.dresdenocl.examples.royalsandloyals.ocl22javacode.LoyaltyAccount aClass) : checkInvariantsCaller(aClass) {
        /* Disable this constraint for subclasses of LoyaltyAccount. */
        if (aClass.getClass().getCanonicalName().equals("org.dresdenocl.examples.royalsandloyals.ocl22javacode.LoyaltyAccount")) {
        java.util.ArrayList<org.dresdenocl.examples.royalsandloyals.ocl22javacode.CustomerCard> result2;
        result2 = new java.util.ArrayList<org.dresdenocl.examples.royalsandloyals.ocl22javacode.CustomerCard>();

        /* Iterator Collect: Iterate through all elements and collect them. Elements which are collections are flattened. */
        for (org.dresdenocl.examples.royalsandloyals.ocl22javacode.Transaction anElement1 : aClass.transactions) {
            result2.add(anElement1.card);
        }
        java.util.ArrayList<org.dresdenocl.examples.royalsandloyals.ocl22javacode.Customer> result1;
        result1 = new java.util.ArrayList<org.dresdenocl.examples.royalsandloyals.ocl22javacode.Customer>();

        /* Iterator Collect: Iterate through all elements and collect them. Elements which are collections are flattened. */
        for (org.dresdenocl.examples.royalsandloyals.ocl22javacode.CustomerCard anElement2 : result2) {
            result1.add(anElement2.owner);
        }

        if (!((Object) org.dresdenocl.tools.codegen.ocl2java.types.util.OclCollections.size(org.dresdenocl.tools.codegen.ocl2java.types.util.OclBags.asSet(result1))).equals(new Integer(1))) {
        	// TODO Auto-generated code executed when constraint is violated.
        	String msg = "Error: Constraint 'oneOwner' (inv oneOwner: transactions.card.owner->asSet()->size() = 1) was violated for Object " + aClass.toString() + ".";
        	throw new RuntimeException(msg);
        }
        // no else.
        }
        // no else.
    }
}