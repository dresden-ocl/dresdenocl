package org.dresdenocl.examples.royalsandloyals.constraints;

/**
 * <p>Generated Aspect to enforce OCL constraint.</p>
 *
 * @author OCL22Java of Dresden OCL2 for Eclipse
 * @Generated
 */
public privileged aspect Transaction_PostAspect_getProgram {

    protected java.util.Map<Object, Object> newInstances = new java.util.WeakHashMap<Object, Object>();

    /**
     * <p>Adds all new instances of the class {@link org.dresdenocl.examples.royalsandloyals.LoyaltyProgram} to the {@link java.util.Map}
     * newInstances which were created after the beginning of a constraint instrumentation
     * method which calls the OCL operation oclIsNew().</p>
     */
    after(org.dresdenocl.examples.royalsandloyals.LoyaltyProgram aClass) : execution(org.dresdenocl.examples.royalsandloyals.LoyaltyProgram.new(..)) && this(aClass) {
        this.newInstances.put(aClass, null);
    }

    /**
     * <p>Pointcut for all calls on {@link org.dresdenocl.examples.royalsandloyals.Transaction#getProgram()}.</p>
     */
    protected pointcut getProgramCaller(org.dresdenocl.examples.royalsandloyals.Transaction aClass):
    	call(* org.dresdenocl.examples.royalsandloyals.Transaction.getProgram())
    	&& target(aClass);

    /**
     * <p>Checks a postcondition for the operation {@link Transaction#getProgram()} defined by the constraint
     * <code>context Transaction::getProgram() : org.dresdenocl.examples.royalsandloyals.LoyaltyProgram
     *       post: not result.oclIsNew()</code></p>
     */
    org.dresdenocl.examples.royalsandloyals.LoyaltyProgram around(org.dresdenocl.examples.royalsandloyals.Transaction aClass): getProgramCaller(aClass) {
        /* Disable this constraint for subclasses of Transaction. */
        if (aClass.getClass().getCanonicalName().equals("org.dresdenocl.examples.royalsandloyals.Transaction")) {

        // Reset Map which collects new instances.
        this.newInstances.clear();

        org.dresdenocl.examples.royalsandloyals.LoyaltyProgram result;
        result = proceed(aClass);

        if (!!this.newInstances.containsKey(result)) {
        	// TODO Auto-generated code executed when constraint is violated.
        	String msg = "Error: Constraint 'undefined' (post: not result.oclIsNew()) was violated for Object " + aClass.toString() + ".";
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