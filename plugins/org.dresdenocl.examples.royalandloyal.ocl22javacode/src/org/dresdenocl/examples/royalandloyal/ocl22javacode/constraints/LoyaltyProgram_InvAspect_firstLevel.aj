package org.dresdenocl.examples.royalandloyal.ocl22javacode.constraints;

/**
 * <p>Generated Aspect to enforce OCL constraint.</p>
 *
 * @author OCL22Java of Dresden OCL2 for Eclipse
 * @Generated
 */
public privileged aspect LoyaltyProgram_InvAspect_firstLevel {

    /**
     * <p>Pointcut for all calls on {@link org.dresdenocl.examples.royalandloyal.ocl22javacode.LoyaltyProgram#checkInvariants()}.</p>
     */
    protected pointcut checkInvariantsCaller(org.dresdenocl.examples.royalandloyal.ocl22javacode.LoyaltyProgram aClass):
    	call(void checkInvariants())
    	&& target(aClass);

    /**
     * <p><code>Checks an invariant on the class LoyaltyProgram defined by the constraint
     * <code>context LoyaltyProgram
     *       inv firstLevel: levels->first().name = 'Silver'</code></p>
     */
    after(org.dresdenocl.examples.royalandloyal.ocl22javacode.LoyaltyProgram aClass) : checkInvariantsCaller(aClass) {
        /* Disable this constraint for subclasses of LoyaltyProgram. */
        if (aClass.getClass().getCanonicalName().equals("org.dresdenocl.examples.royalandloyal.ocl22javacode.LoyaltyProgram")) {
        if (!org.dresdenocl.tools.codegen.ocl2java.types.util.OclOrderedSets.first(aClass.levels).name.equals("Silver")) {
        	// TODO Auto-generated code executed when constraint is violated.
        	String msg = "Error: Constraint 'firstLevel' (inv firstLevel: levels->first().name = 'Silver') was violated for Object " + aClass.toString() + ".";
        	throw new RuntimeException(msg);
        }
        // no else.
        }
        // no else.
    }
}