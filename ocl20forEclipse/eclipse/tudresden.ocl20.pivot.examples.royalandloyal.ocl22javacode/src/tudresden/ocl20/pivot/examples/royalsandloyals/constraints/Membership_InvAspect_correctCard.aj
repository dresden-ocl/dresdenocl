package tudresden.ocl20.pivot.examples.royalsandloyals.constraints;

/**
 * <p>Generated Aspect to enforce OCL constraint.</p>
 *
 * @author OCL22Java of Dresden OCL2 for Eclipse
 * @Generated
 */
public privileged aspect Membership_InvAspect_correctCard {

    declare parents: tudresden.ocl20.pivot.examples.royalsandloyals.Membership implements tudresden.ocl20.pivot.tools.codegen.ocl2java.types.OclCheckable;
    
    public void tudresden.ocl20.pivot.examples.royalsandloyals.Membership.checkInvariants() {
        /* Remains empty. Is only filled with behavior by advice(s). */
    }
    /**
     * <p>Pointcut for all calls on {@link tudresden.ocl20.pivot.examples.royalsandloyals.Membership#checkInvariants()}.</p>
     */
    protected pointcut checkInvariantsCaller(tudresden.ocl20.pivot.examples.royalsandloyals.Membership aClass):
    	call(void checkInvariants())
    	&& target(aClass);
    
    /**
     * <p><code>Checks an invariant on the class Membership defined by the constraint
     * <code>context Membership
     *       inv correctCard: program.participants.cards->flatten()->includes(self.card)</code></p>
     */
    after(tudresden.ocl20.pivot.examples.royalsandloyals.Membership aClass) : checkInvariantsCaller(aClass) {
        /* Disable this constraint for subclasses of Membership. */
        if (aClass.getClass().getCanonicalName().equals("tudresden.ocl20.pivot.examples.royalsandloyals.Membership")) {
        java.util.ArrayList<tudresden.ocl20.pivot.examples.royalsandloyals.CustomerCard> result1;
        result1 = new java.util.ArrayList<tudresden.ocl20.pivot.examples.royalsandloyals.CustomerCard>();
        
        /* Iterator Collect: Iterate through all elements and collect them. Elements which are collections are flattened. */
        for (tudresden.ocl20.pivot.examples.royalsandloyals.Customer anElement1 : aClass.program.participants) {
            result1.addAll(anElement1.cards);
        }
    
        if (!tudresden.ocl20.pivot.tools.codegen.ocl2java.types.util.OclCollections.includes((java.util.List<tudresden.ocl20.pivot.examples.royalsandloyals.CustomerCard>) tudresden.ocl20.pivot.tools.codegen.ocl2java.types.util.OclBags.flatten(result1), aClass.card)) {
        	// TODO Auto-generated code executed when constraint is violated.
        	String msg = "Error: Constraint 'correctCard' (inv correctCard: program.participants.cards->flatten()->includes(self.card)) was violated for Object " + aClass.toString() + ".";
        	throw new RuntimeException(msg);
        }
        // no else.
        }
        // no else.
    }
}