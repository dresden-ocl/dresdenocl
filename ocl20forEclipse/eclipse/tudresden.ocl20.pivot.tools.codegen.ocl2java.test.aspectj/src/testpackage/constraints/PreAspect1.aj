package testpackage.constraints;

/**
 * <p>Generated Aspect to enforce OCL constraint.</p>
 *
 * @author OCL22Java of Dresden OCL2 for Eclipse
 * @Generated
 */
public privileged aspect PreAspect1 {

    /**
     * <p>Pointcut for all calls on {@link testpackage.Class1#preOperation01(Integer in1)}.</p>
     */
    protected pointcut preOperation01Caller(testpackage.Class1 aClass, Integer in1):
    	call(* testpackage.Class1.preOperation01(Integer))
    	&& target(aClass) && args(in1);
    
    /**
     * <p>Checks a precondition for the {@link Class1#preOperation01(, Integer in1)} defined by the constraint
     * <code>context Class1::preOperation01(in1: Integer) : Integer
     *       pre: not anInt.oclIsUndefined()</code></p>
     */
    before(testpackage.Class1 aClass, Integer in1): preOperation01Caller(aClass, in1) {
        /* Disable this constraint for subclasses of Class1. */
        if (aClass.getClass().getCanonicalName().equals("testpackage.Class1")) {
        if (!!(anInt == null)) {
        	// TODO Auto-generated code executed when constraint is violated.
        	throw new RuntimeException("Error: Constraint was violated.");
        }
        // no else.
        }
        // no else.
    }
}