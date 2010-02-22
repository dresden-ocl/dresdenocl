package tudresden.ocl20.pivot.examples.royalsandloyals.constraints;

/**
 * <p>Generated Aspect to enforce OCL constraint.</p>
 *
 * @author OCL22Java of Dresden OCL2 for Eclipse
 * @Generated
 */
public privileged aspect InvAspect6 {

    /* Declares a new super class containing the new attribute or method. */
    declare parents : tudresden.ocl20.pivot.examples.royalsandloyals.Customer extends tudresden.ocl20.pivot.examples.royalsandloyals.constraints.ExtendedCustomer;
    
    /**
     * <p>Pointcut for all calls on {@link tudresden.ocl20.pivot.examples.royalsandloyals.Customer#checkInvariants()}.</p>
     */
    protected pointcut checkInvariantsCaller(tudresden.ocl20.pivot.examples.royalsandloyals.Customer aClass):
    	call(void tudresden.ocl20.pivot.examples.royalsandloyals.Customer.checkInvariants())
    	&& target(aClass);
    
    /**
     * <p><code>Checks an invariant on the class Customer defined by the constraint
     * <code>context Customer
     *       inv: programs[].size().=( cards[] -> select (  | valid[].=( true)).size())</code></p>
     */
    after(tudresden.ocl20.pivot.examples.royalsandloyals.Customer aClass) : checkInvariantsCaller(aClass) {
        /* Disable this constraint for subclasses of Customer. */
        if (aClass.getClass().getCanonicalName().equals("tudresden.ocl20.pivot.examples.royalsandloyals.Customer")) {
        tudresden.ocl20.pivot.ocl2java.types.OclSet<tudresden.ocl20.pivot.examples.royalsandloyals.CustomerCard> result1;
        result1 = new tudresden.ocl20.pivot.ocl2java.types.OclSet<tudresden.ocl20.pivot.examples.royalsandloyals.CustomerCard>();
        
        /* Iterator Select: Select all elements which fulfill the condition. */
        for (tudresden.ocl20.pivot.examples.royalsandloyals.CustomerCard anElement1 : aClass.cards) {
            if (((Object) anElement1.valid).equals(new Boolean(true))) {
                result1.add(anElement1);
            }
            // no else
        }
    
        if (!((Object) aClass.programs.size()).equals(result1.size())) {
        	// TODO Auto-generated code executed when constraint is violated.
        	throw new RuntimeException("Error: Constraint was violated.");
        }
        // no else.
        }
        // no else.
    }
}