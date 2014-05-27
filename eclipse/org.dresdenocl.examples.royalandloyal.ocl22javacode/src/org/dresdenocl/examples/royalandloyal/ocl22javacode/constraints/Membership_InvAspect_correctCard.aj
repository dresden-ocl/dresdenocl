package org.dresdenocl.examples.royalandloyal.ocl22javacode.constraints;

/**
 * <p>Generated Aspect to enforce OCL constraint.</p>
 *
 * @author OCL22Java of Dresden OCL2 for Eclipse
 * @Generated
 */
public privileged aspect Membership_InvAspect_correctCard {

    declare parents: org.dresdenocl.examples.royalandloyal.ocl22javacode.Membership implements org.dresdenocl.tools.codegen.ocl2java.types.OclCheckable;

    public void org.dresdenocl.examples.royalandloyal.ocl22javacode.Membership.checkInvariants() {
        /* Remains empty. Is only filled with behavior by advice(s). */
    }
    /**
     * <p>Pointcut for all calls on {@link org.dresdenocl.examples.royalandloyal.ocl22javacode.Membership#checkInvariants()}.</p>
     */
    protected pointcut checkInvariantsCaller(org.dresdenocl.examples.royalandloyal.ocl22javacode.Membership aClass):
    	call(void checkInvariants())
    	&& target(aClass);

    /**
     * <p><code>Checks an invariant on the class Membership defined by the constraint
     * <code>context Membership
     *       inv correctCard: program.participants.cards->flatten()->includes(self.card)</code></p>
     */
    after(org.dresdenocl.examples.royalandloyal.ocl22javacode.Membership aClass) : checkInvariantsCaller(aClass) {
        /* Disable this constraint for subclasses of Membership. */
        if (aClass.getClass().getCanonicalName().equals("org.dresdenocl.examples.royalandloyal.ocl22javacode.Membership")) {
        java.util.ArrayList<org.dresdenocl.examples.royalandloyal.ocl22javacode.CustomerCard> result1;
        result1 = new java.util.ArrayList<org.dresdenocl.examples.royalandloyal.ocl22javacode.CustomerCard>();

        /* Iterator Collect: Iterate through all elements and collect them. Elements which are collections are flattened. */
        for (org.dresdenocl.examples.royalandloyal.ocl22javacode.Customer anElement1 : aClass.program.participants) {
            result1.addAll(anElement1.cards);
        }

        if (!org.dresdenocl.tools.codegen.ocl2java.types.util.OclCollections.includes((java.util.List<org.dresdenocl.examples.royalandloyal.ocl22javacode.CustomerCard>) org.dresdenocl.tools.codegen.ocl2java.types.util.OclBags.flatten(result1), aClass.card)) {
        	// TODO Auto-generated code executed when constraint is violated.
        	String msg = "Error: Constraint 'correctCard' (inv correctCard: program.participants.cards->flatten()->includes(self.card)) was violated for Object " + aClass.toString() + ".";
        	throw new RuntimeException(msg);
        }
        // no else.
        }
        // no else.
    }
}