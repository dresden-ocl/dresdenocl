package testpackage.constraints;

/**
 * <p>Generated Aspect to enforce OCL constraint.</p>
 *
 * @author OCL22Java of Dresden OCL2 for Eclipse
 * @Generated
 */
public privileged aspect Class1_PreAspect_staticPreOperation01 {

    /**
     * <p>Pointcut for all calls on {@link testpackage.Class1#staticPreOperation01(Integer anInt)}.</p>
     */
    protected pointcut staticPreOperation01Caller(Integer anInt):
    	execution(* testpackage.Class1.staticPreOperation01(Integer)) && args(anInt);
    
    /**
     * <p>Checks a precondition for the {@link Class1#staticPreOperation01(, Integer anInt)} defined by the constraint
     * <code>context Class1::staticPreOperation01(anInt: Integer) : Integer
     *       pre testStaticPre01: not anInt.oclIsUndefined()</code></p>
     */
    before(Integer anInt): staticPreOperation01Caller(anInt) {
        if (!!(((Integer) anInt) == null)) {
        	// TODO Auto-generated code executed when constraint is violated.
        	throw new RuntimeException("Error: Constraint was violated.");
        }
        // no else.
    }
}