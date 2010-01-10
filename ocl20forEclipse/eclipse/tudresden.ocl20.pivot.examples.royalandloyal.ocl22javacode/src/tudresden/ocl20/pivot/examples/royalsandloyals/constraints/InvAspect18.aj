package tudresden.ocl20.pivot.examples.royalsandloyals.constraints;

@Generated
public privileged aspect InvAspect18 {

    /* Declares a new super class containing the new attribute or method. */
    declare parents : tudresden.ocl20.pivot.examples.royalsandloyals.CustomerCard extends tudresden.ocl20.pivot.examples.royalsandloyals.constraints.ExtendedCustomerCard;
    
    /**
     * <p>Pointcut for all calls on {@link tudresden.ocl20.pivot.examples.royalsandloyals.CustomerCard#checkInvariants()}.</p>
     */
    protected pointcut checkInvariantsCaller(tudresden.ocl20.pivot.examples.royalsandloyals.CustomerCard aClass):
    	call(void tudresden.ocl20.pivot.examples.royalsandloyals.CustomerCard.checkInvariants())
    	&& target(aClass);
    
    /**
     * <p><code>Checks an invariant on the class CustomerCard defined by the constraint
     * <code>context CustomerCard
     *       inv: let correctDate:Boolean=self[].validFrom[].isBefore( Date::now()).and( self[].validThru[].isAfter( Date::now())) in if valid[] then correctDate[].=( true) else correctDate[].=( false)</code></p>
     */
    after(tudresden.ocl20.pivot.examples.royalsandloyals.CustomerCard aClass) : checkInvariantsCaller(aClass) {
        /* Disable this constraint for subclasses of CustomerCard. */
        if (aClass.getClass().getCanonicalName().equals("tudresden.ocl20.pivot.examples.royalsandloyals.CustomerCard")) {
        Boolean correctDate;
        correctDate = (aClass.validFrom.isBefore(tudresden.ocl20.pivot.examples.royalsandloyals.Date.now()) && aClass.validThru.isAfter(tudresden.ocl20.pivot.examples.royalsandloyals.Date.now()));
        
        Boolean ifExpResult1;
        
        if (aClass.valid) {
            ifExpResult1 = ((Object) correctDate).equals(new Boolean(true));
        } else {
            ifExpResult1 = ((Object) correctDate).equals(new Boolean(false));
        }
    
        if (!ifExpResult1) {
        	// TODO Auto-generated code executed when constraint is violated.
        	throw new RuntimeException("Error: Constraint was violated.");
        }
        // no else.
        }
        // no else.
    }
}