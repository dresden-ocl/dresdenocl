package tudresden.ocl20.pivot.examples.royalsandloyals.constraints;

/**
 * <p>Generated Aspect to enforce OCL constraint.</p>
 *
 * @author OCL22Java of Dresden OCL2 for Eclipse
 * @Generated
 */
public privileged aspect Transaction_PostAspect4 {

    /**
     * <p>Pointcut for all calls on {@link tudresden.ocl20.pivot.examples.royalsandloyals.Transaction#getProgram()}.</p>
     */
    protected pointcut getProgramCaller(tudresden.ocl20.pivot.examples.royalsandloyals.Transaction aClass):
    	call(* tudresden.ocl20.pivot.examples.royalsandloyals.Transaction.getProgram())
    	&& target(aClass);
    
    /**
     * <p>Checks a postcondition for the operation {@link Transaction#getProgram()} defined by the constraint
     * <code>context Transaction::getProgram() : tudresden.ocl20.pivot.examples.royalsandloyals.LoyaltyProgram
     *       post: self.oclIsTypeOf(Transaction)</code></p>
     */
    tudresden.ocl20.pivot.examples.royalsandloyals.LoyaltyProgram around(tudresden.ocl20.pivot.examples.royalsandloyals.Transaction aClass): getProgramCaller(aClass) {
        /* Disable this constraint for subclasses of Transaction. */
        if (aClass.getClass().getCanonicalName().equals("tudresden.ocl20.pivot.examples.royalsandloyals.Transaction")) {
    
        tudresden.ocl20.pivot.examples.royalsandloyals.LoyaltyProgram result;
        result = proceed(aClass);
    
        if (!aClass.getClass().getCanonicalName().equals(tudresden.ocl20.pivot.examples.royalsandloyals.Transaction.class.getCanonicalName())) {
        	// TODO Auto-generated code executed when constraint is violated.
        	String msg = "Error: Constraint 'undefined' (post: self.oclIsTypeOf(Transaction)) was violated for Object " + aClass.toString() + ".";
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