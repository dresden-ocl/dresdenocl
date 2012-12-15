package testpackage.constraints;

/**
 * <p>Generated Aspect to enforce OCL constraint.</p>
 *
 * @author OCL22Java of Dresden OCL2 for Eclipse
 * @Generated
 */
public privileged aspect Class1_InvAspect_testInvariant04 {

    /**
     * <p>Pointcut for all calls on {@link testpackage.Class1#checkInvariants()}.</p>
     */
    protected pointcut checkInvariantsCaller(testpackage.Class1 aClass):
    	call(void checkInvariants())
    	&& target(aClass);

    /**
     * <p><code>Checks an invariant on the class Class1 defined by the constraint
     * <code>context Class1
     *       inv testInvariant04: self.isInvariant04Violated</code></p>
     */
    after(testpackage.Class1 aClass) : checkInvariantsCaller(aClass) {
        /* Disable this constraint for subclasses of Class1. */
        if (aClass.getClass().getCanonicalName().equals("testpackage.Class1")) {
        if (!aClass.isInvariant04Violated) {
        	// TODO Auto-generated code executed when constraint is violated.
        	String msg = "Error: Constraint 'testInvariant04' (inv testInvariant04: self.isInvariant04Violated) was violated for Object " + aClass.toString() + ".";
        	throw new RuntimeException(msg);
        }
        // no else.
        }
        // no else.
    }
}