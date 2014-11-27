package org.dresdenocl.examples.royalandloyal.constraints;

/**
 * <p>Generated Aspect to enforce OCL constraint.</p>
 *
 * @author OCL22Java of Dresden OCL2 for Eclipse
 * @Generated
 */
public privileged aspect LoyaltyProgram_InvAspect_noAccounts {

    declare parents: org.dresdenocl.examples.royalandloyal.LoyaltyProgram implements org.dresdenocl.tools.codegen.ocl2java.types.OclCheckable;

    public void org.dresdenocl.examples.royalandloyal.LoyaltyProgram.checkInvariants() {
        /* Remains empty. Is only filled with behavior by advice(s). */
    }

    /**
     * <p>Pointcut for all calls on {@link org.dresdenocl.examples.royalandloyal.LoyaltyProgram#checkInvariants()}.</p>
     */
    protected pointcut checkInvariantsCaller(org.dresdenocl.examples.royalandloyal.LoyaltyProgram aClass):
    	call(void checkInvariants())
    	&& target(aClass);

    /**
     * <p><code>Checks an invariant on the class LoyaltyProgram defined by the constraint
     * <code>context LoyaltyProgram
     *       inv noAccounts:    partners.deliveredServices     ->forAll(pointsEarned = 0 and pointsBurned = 0)     implies membership.accounts->isEmpty()</code></p>
     */
    after(org.dresdenocl.examples.royalandloyal.LoyaltyProgram aClass) : checkInvariantsCaller(aClass) {
        /* Disable this constraint for subclasses of LoyaltyProgram. */
        if (aClass.getClass().getCanonicalName().equals("org.dresdenocl.examples.royalandloyal.LoyaltyProgram")) {
        java.util.ArrayList<org.dresdenocl.examples.royalandloyal.Service> result2;
        result2 = new java.util.ArrayList<org.dresdenocl.examples.royalandloyal.Service>();

        /* Iterator Collect: Iterate through all elements and collect them. Elements which are collections are flattened. */
        for (org.dresdenocl.examples.royalandloyal.ProgramPartner anElement1 : aClass.partners) {
            result2.addAll(anElement1.deliveredServices);
        }
        Boolean result1;
        result1 = true;

        /* Iterator ForAll: Iterate and check, if all elements fulfill the condition. */
        for (org.dresdenocl.examples.royalandloyal.Service anElement2 : result2) {
            if (!(((Object) anElement2.pointsEarned).equals(new Integer(0)) && ((Object) anElement2.pointsBurned).equals(new Integer(0)))) {
                result1 = false;
                break;
            }
            // no else
        }

        if (!(!result1 || org.dresdenocl.tools.codegen.ocl2java.types.util.OclCollections.isEmpty(aClass.membership.accounts))) {
        	// TODO Auto-generated code executed when constraint is violated.
        	String msg = "Error: Constraint 'noAccounts' (inv noAccounts:    partners.deliveredServices     ->forAll(pointsEarned = 0 and pointsBurned = 0)     implies membership.accounts->isEmpty()) was violated for Object " + aClass.toString() + ".";
        	throw new RuntimeException(msg);
        }
        // no else.
        }
        // no else.
    }
}