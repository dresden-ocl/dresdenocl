package tudresden.ocl20.pivot.examples.royalsandloyals.constraints;

/**
 * <p>Generated Aspect to enforce OCL constraint.</p>
 *
 * @author OCL22Java of Dresden OCL2 for Eclipse
 * @Generated
 */
public privileged aspect PostAspect8 {

    protected java.util.Map<Object, Object> newInstances = new java.util.WeakHashMap<Object, Object>();

    /**
     * <p>Adds all new instances of the class {@link tudresden.ocl20.pivot.examples.royalsandloyals.LoyaltyProgram} to the {@link java.util.Map}
     * newInstances which were created after the beginning of a constraint instrumentation
     * method which calls the OCL operation oclIsNew().</p>
     */
    after(tudresden.ocl20.pivot.examples.royalsandloyals.LoyaltyProgram aClass) : execution(tudresden.ocl20.pivot.examples.royalsandloyals.LoyaltyProgram.new(..)) && this(aClass) {
        this.newInstances.put(aClass, null);
    }

    /**
     * <p>Pointcut for all calls on {@link tudresden.ocl20.pivot.examples.royalsandloyals.Transaction#getProgram()}.</p>
     */
    protected pointcut getProgramCaller(tudresden.ocl20.pivot.examples.royalsandloyals.Transaction aClass):
    	call(* tudresden.ocl20.pivot.examples.royalsandloyals.Transaction.getProgram())
    	&& target(aClass);
    
    /**
     * <p>Checks a postcondition for the getProgram() defined by the constraint
     * <code>context Transaction::getProgram() : tudresden.ocl20.pivot.examples.royalsandloyals.LoyaltyProgram
     *       post: result[].oclIsNew().not()</code></p>
     */
    tudresden.ocl20.pivot.examples.royalsandloyals.LoyaltyProgram around(tudresden.ocl20.pivot.examples.royalsandloyals.Transaction aClass): getProgramCaller(aClass) {
        /* Disable this constraint for subclasses of Transaction. */
        if (aClass.getClass().getCanonicalName().equals("tudresden.ocl20.pivot.examples.royalsandloyals.Transaction")) {
    
        // Reset Map which collects new instances.
        this.newInstances.clear();
    
        tudresden.ocl20.pivot.examples.royalsandloyals.LoyaltyProgram result;
        result = proceed(aClass);
    
        if (!!this.newInstances.containsKey(result)) {
        	// TODO Auto-generated code executed when constraint is violated.
        	throw new RuntimeException("Error: Constraint was violated.");
        }
        // no else.
    
        return result;
        }
    
        else {
            return proceed(aClass);
        }
    }
}