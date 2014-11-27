package org.dresdenocl.examples.royalandloyal.constraints;

/**
 * <p>Generated Aspect to enforce OCL constraint.</p>
 *
 * @author OCL22Java of Dresden OCL2 for Eclipse
 * @Generated
 */
public privileged aspect Service_InvAspect1 {

    declare parents: org.dresdenocl.examples.royalandloyal.Service implements org.dresdenocl.tools.codegen.ocl2java.types.OclCheckable;

    public void org.dresdenocl.examples.royalandloyal.Service.checkInvariants() {
        /* Remains empty. Is only filled with behavior by advice(s). */
    }

    /**
     * <p>Pointcut for all calls on {@link org.dresdenocl.examples.royalandloyal.Service#checkInvariants()}.</p>
     */
    protected pointcut checkInvariantsCaller(org.dresdenocl.examples.royalandloyal.Service aClass):
    	call(void checkInvariants())
    	&& target(aClass);

    /**
     * <p><code>Checks an invariant on the class Service defined by the constraint
     * <code>context Service
     *       inv: self.oclIsInvalid() = false</code></p>
     */
    after(org.dresdenocl.examples.royalandloyal.Service aClass) : checkInvariantsCaller(aClass) {
        /* Disable this constraint for subclasses of Service. */
        if (aClass.getClass().getCanonicalName().equals("org.dresdenocl.examples.royalandloyal.Service")) {
        Boolean result1;

        /* Check if the expression results in invalid. */
        try {
            /* DUMMY variable is necessary to form literals into a statement. */
            Object DUMMY = aClass; 
            result1 = false;
        }

        catch (Exception e) {
            result1 = true;
        }

        if (!((Object) result1).equals(new Boolean(false))) {
        	// TODO Auto-generated code executed when constraint is violated.
        	String msg = "Error: Constraint 'undefined' (inv: self.oclIsInvalid() = false) was violated for Object " + aClass.toString() + ".";
        	throw new RuntimeException(msg);
        }
        // no else.
        }
        // no else.
    }
}