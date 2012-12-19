package org.dresdenocl.examples.royalsandloyals.ocl22javacode.constraints;

/**
 * <p>Generated Aspect to enforce OCL constraint.</p>
 *
 * @author OCL22Java of Dresden OCL2 for Eclipse
 * @Generated
 */
public privileged aspect Transaction_PostAspect3 {

    /**
     * <p>Pointcut for all calls on {@link org.dresdenocl.examples.royalsandloyals.ocl22javacode.Transaction#getProgram()}.</p>
     */
    protected pointcut getProgramCaller(org.dresdenocl.examples.royalsandloyals.ocl22javacode.Transaction aClass):
    	call(* org.dresdenocl.examples.royalsandloyals.ocl22javacode.Transaction.getProgram())
    	&& target(aClass);

    /**
     * <p>Checks a postcondition for the operation {@link Transaction#getProgram()} defined by the constraint
     * <code>context Transaction::getProgram() : org.dresdenocl.examples.royalsandloyals.ocl22javacode.LoyaltyProgram
     *       post: result = self.card.membership.program</code></p>
     */
    org.dresdenocl.examples.royalsandloyals.ocl22javacode.LoyaltyProgram around(org.dresdenocl.examples.royalsandloyals.ocl22javacode.Transaction aClass): getProgramCaller(aClass) {
        /* Disable this constraint for subclasses of Transaction. */
        if (aClass.getClass().getCanonicalName().equals("org.dresdenocl.examples.royalsandloyals.ocl22javacode.Transaction")) {

        org.dresdenocl.examples.royalsandloyals.ocl22javacode.LoyaltyProgram result;
        result = proceed(aClass);

        if (!result.equals(aClass.card.membership.program)) {
        	// TODO Auto-generated code executed when constraint is violated.
        	String msg = "Error: Constraint 'undefined' (post: result = self.card.membership.program) was violated for Object " + aClass.toString() + ".";
        	throw new RuntimeException(msg);
        }
        // no else.

        return result;
        }

        else {
            return proceed(aClass);
        }
    }
}