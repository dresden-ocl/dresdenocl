package org.dresdenocl.examples.royalandloyal.ocl22javacode.constraints;

/**
 * <p>Generated Aspect to enforce OCL constraint.</p>
 *
 * @author OCL22Java of Dresden OCL2 for Eclipse
 * @Generated
 */
public privileged aspect LoyaltyProgram_InvAspect_knownServiceLevel {

    declare parents: org.dresdenocl.examples.royalandloyal.ocl22javacode.LoyaltyProgram implements org.dresdenocl.tools.codegen.ocl2java.types.OclCheckable;

    public void org.dresdenocl.examples.royalandloyal.ocl22javacode.LoyaltyProgram.checkInvariants() {
        /* Remains empty. Is only filled with behavior by advice(s). */
    }
    /**
     * <p>Pointcut for all calls on {@link org.dresdenocl.examples.royalandloyal.ocl22javacode.LoyaltyProgram#checkInvariants()}.</p>
     */
    protected pointcut checkInvariantsCaller(org.dresdenocl.examples.royalandloyal.ocl22javacode.LoyaltyProgram aClass):
    	call(void checkInvariants())
    	&& target(aClass);

    /**
     * <p><code>Checks an invariant on the class LoyaltyProgram defined by the constraint
     * <code>context LoyaltyProgram
     *       inv knownServiceLevel: levels->includes(membership.currentLevel)</code></p>
     */
    after(org.dresdenocl.examples.royalandloyal.ocl22javacode.LoyaltyProgram aClass) : checkInvariantsCaller(aClass) {
        /* Disable this constraint for subclasses of LoyaltyProgram. */
        if (aClass.getClass().getCanonicalName().equals("org.dresdenocl.examples.royalandloyal.ocl22javacode.LoyaltyProgram")) {
        if (!org.dresdenocl.tools.codegen.ocl2java.types.util.OclCollections.includes(aClass.levels, aClass.membership.currentLevel)) {
        	// TODO Auto-generated code executed when constraint is violated.
        	String msg = "Error: Constraint 'knownServiceLevel' (inv knownServiceLevel: levels->includes(membership.currentLevel)) was violated for Object " + aClass.toString() + ".";
        	throw new RuntimeException(msg);
        }
        // no else.
        }
        // no else.
    }
}