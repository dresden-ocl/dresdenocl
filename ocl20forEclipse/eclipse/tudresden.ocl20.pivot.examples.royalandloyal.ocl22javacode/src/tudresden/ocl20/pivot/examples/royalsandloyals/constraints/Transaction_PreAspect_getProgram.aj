package tudresden.ocl20.pivot.examples.royalsandloyals.constraints;

/**
 * <p>Generated Aspect to enforce OCL constraint.</p>
 *
 * @author OCL22Java of Dresden OCL2 for Eclipse
 * @Generated
 */
public privileged aspect Transaction_PreAspect_getProgram {

    /**
     * <p>Pointcut for all calls on {@link tudresden.ocl20.pivot.examples.royalsandloyals.Transaction#getProgram()}.</p>
     */
    protected pointcut getProgramCaller(tudresden.ocl20.pivot.examples.royalsandloyals.Transaction aClass):
    	call(* tudresden.ocl20.pivot.examples.royalsandloyals.Transaction.getProgram())
    	&& target(aClass);
    
    /**
     * <p>Checks a precondition for the {@link Transaction#getProgram()} defined by the constraint
     * <code>context Transaction::getProgram() : tudresden.ocl20.pivot.examples.royalsandloyals.LoyaltyProgram
     *       pre: self.oclIsTypeOf(Transaction)</code></p>
     */
    before(tudresden.ocl20.pivot.examples.royalsandloyals.Transaction aClass): getProgramCaller(aClass) {
        /* Disable this constraint for subclasses of Transaction. */
        if (aClass.getClass().getCanonicalName().equals("tudresden.ocl20.pivot.examples.royalsandloyals.Transaction")) {
        if (!aClass.getClass().getCanonicalName().equals(tudresden.ocl20.pivot.examples.royalsandloyals.Transaction.class.getCanonicalName())) {
        	// TODO Auto-generated code executed when constraint is violated.
        	String msg = "Error: Constraint 'undefined' (pre: self.oclIsTypeOf(Transaction)) was violated for Object " + aClass.toString() + ".";
        	throw new RuntimeException(msg);
        }
        // no else.
        }
        // no else.
    }
}