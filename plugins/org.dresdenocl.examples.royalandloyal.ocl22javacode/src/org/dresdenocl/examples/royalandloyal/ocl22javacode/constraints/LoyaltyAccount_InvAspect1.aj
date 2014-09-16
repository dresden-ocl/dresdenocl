package org.dresdenocl.examples.royalandloyal.ocl22javacode.constraints;

/**
 * <p>Generated Aspect to enforce OCL constraint.</p>
 *
 * @author OCL22Java of Dresden OCL2 for Eclipse
 * @Generated
 */
public privileged aspect LoyaltyAccount_InvAspect1 {

    /**
     * <p>Pointcut for all calls on {@link org.dresdenocl.examples.royalandloyal.ocl22javacode.LoyaltyAccount#checkInvariants()}.</p>
     */
    protected pointcut checkInvariantsCaller(org.dresdenocl.examples.royalandloyal.ocl22javacode.LoyaltyAccount aClass):
    	call(void checkInvariants())
    	&& target(aClass);

    /**
     * <p><code>Checks an invariant on the class LoyaltyAccount defined by the constraint
     * <code>context LoyaltyAccount
     *       inv: points > 0 implies transactions->exists(t | t.points > 0)</code></p>
     */
    after(org.dresdenocl.examples.royalandloyal.ocl22javacode.LoyaltyAccount aClass) : checkInvariantsCaller(aClass) {
        /* Disable this constraint for subclasses of LoyaltyAccount. */
        if (aClass.getClass().getCanonicalName().equals("org.dresdenocl.examples.royalandloyal.ocl22javacode.LoyaltyAccount")) {
        Boolean result1;
        result1 = false;

        /* Iterator Exists: Iterate and check, if any element fulfills the condition. */
        for (org.dresdenocl.examples.royalandloyal.ocl22javacode.Transaction anElement1 : aClass.transactions) {
            if ((anElement1.points > new Integer(0))) {
                result1 = true;
                break;
            }
            // no else
        }

        if (!(!(aClass.points > new Integer(0)) || result1)) {
        	// TODO Auto-generated code executed when constraint is violated.
        	String msg = "Error: Constraint 'undefined' (inv: points > 0 implies transactions->exists(t | t.points > 0)) was violated for Object " + aClass.toString() + ".";
        	throw new RuntimeException(msg);
        }
        // no else.
        }
        // no else.
    }
}