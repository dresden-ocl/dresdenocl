package tudresden.ocl20.pivot.examples.royalsandloyals.constraints;

/**
 * <p>Generated Aspect to enforce OCL constraint.</p>
 *
 * @author OCL22Java of Dresden OCL2 for Eclipse
 * @Generated
 */
public privileged aspect PreAspect5 {

    /**
     * <p>Pointcut for all calls on {@link tudresden.ocl20.pivot.examples.royalsandloyals.Transaction#getProgram()}.</p>
     */
    protected pointcut getProgramCaller(tudresden.ocl20.pivot.examples.royalsandloyals.Transaction aClass):
    	call(* tudresden.ocl20.pivot.examples.royalsandloyals.Transaction.getProgram())
    	&& target(aClass);
    
    /**
     * <p>Checks a precondition for the {@link Transaction#getProgram()} defined by the constraint
     * <code>context Transaction::getProgram() : tudresden.ocl20.pivot.examples.royalsandloyals.LoyaltyProgram
     *       pre: self[].oclIsTypeOf( Transaction[])</code></p>
     */
    before(tudresden.ocl20.pivot.examples.royalsandloyals.Transaction aClass): getProgramCaller(aClass) {
        /* Disable this constraint for subclasses of Transaction. */
        if (aClass.getClass().getCanonicalName().equals("tudresden.ocl20.pivot.examples.royalsandloyals.Transaction")) {
        if (!aClass.getClass().getCanonicalName().equals("tudresden.ocl20.pivot.examples.royalsandloyals.Transaction")) {
        	// TODO Auto-generated code executed when constraint is violated.
        	throw new RuntimeException("Error: Constraint was violated.");
        }
        // no else.
        }
        // no else.
    }
}