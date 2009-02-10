package tudresden.ocl20.pivot.examples.royalsandloyals.constraints;

@Generated
public privileged aspect InvAspect15 {

    /* Declares a new super class containing the new attribute or method. */
    declare parents : tudresden.ocl20.pivot.examples.royalsandloyals.Service extends tudresden.ocl20.pivot.examples.royalsandloyals.constraints.ExtendedService;
    
    /**
     * <p>Pointcut for all calls on {@link tudresden.ocl20.pivot.examples.royalsandloyals.Service#checkInvariants()}.</p>
     */
    protected pointcut checkInvariantsCaller(tudresden.ocl20.pivot.examples.royalsandloyals.Service aClass):
    	call(void tudresden.ocl20.pivot.examples.royalsandloyals.Service.checkInvariants())
    	&& target(aClass);
    
    /**
     * <p><code>Checks an invariant on the class Service defined by the constraint
     * <code>context Service
     *       inv: self[].oclIsInvalid().=( false)</code></p>
     */
    after(tudresden.ocl20.pivot.examples.royalsandloyals.Service aClass) : checkInvariantsCaller(aClass) {
        /* Disable this constraint for subclasses of Service. */
        if (aClass.getClass().getCanonicalName().equals("tudresden.ocl20.pivot.examples.royalsandloyals.Service")) {
        if (!((Object) (aClass == null)).equals(new Boolean(false))) {
        	// TODO Auto-generated code executed when constraint is violated.
        	throw new RuntimeException("Error: Constraint was violated.");
        }
        // no else.
        }
        // no else.
    }
}