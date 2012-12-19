package org.dresdenocl.examples.royalsandloyals.ocl22javacode.constraints;

/**
 * <p>Generated Aspect to enforce OCL constraint.</p>
 *
 * @author OCL22Java of Dresden OCL2 for Eclipse
 * @Generated
 */
public privileged aspect Burning_InvAspect4 {

    declare parents: org.dresdenocl.examples.royalsandloyals.ocl22javacode.Burning implements org.dresdenocl.tools.codegen.ocl2java.types.OclCheckable;

    public void org.dresdenocl.examples.royalsandloyals.ocl22javacode.Burning.checkInvariants() {
        /* Remains empty. Is only filled with behavior by advice(s). */
    }
    /**
     * <p>Pointcut for all calls on {@link org.dresdenocl.examples.royalsandloyals.ocl22javacode.Burning#checkInvariants()}.</p>
     */
    protected pointcut checkInvariantsCaller(org.dresdenocl.examples.royalsandloyals.ocl22javacode.Burning aClass):
    	call(void checkInvariants())
    	&& target(aClass);

    /**
     * <p><code>Checks an invariant on the class Burning defined by the constraint
     * <code>context Burning
     *       inv: self.points = self.oclAsType(Transaction).points</code></p>
     */
    after(org.dresdenocl.examples.royalsandloyals.ocl22javacode.Burning aClass) : checkInvariantsCaller(aClass) {
        /* Disable this constraint for subclasses of Burning. */
        if (aClass.getClass().getCanonicalName().equals("org.dresdenocl.examples.royalsandloyals.ocl22javacode.Burning")) {
        if (!((Object) aClass.points).equals(((org.dresdenocl.examples.royalsandloyals.ocl22javacode.Transaction) aClass).points)) {
        	// TODO Auto-generated code executed when constraint is violated.
        	String msg = "Error: Constraint 'undefined' (inv: self.points = self.oclAsType(Transaction).points) was violated for Object " + aClass.toString() + ".";
        	throw new RuntimeException(msg);
        }
        // no else.
        }
        // no else.
    }
}