package testpackage.constraints;

/**
 * <p>Generated Aspect to enforce OCL constraint.</p>
 *
 * @author OCL22Java of Dresden OCL2 for Eclipse
 * @Generated
 */
public privileged aspect Class1_PostAspect_staticPostOperation01 {

    /**
     * <p>Pointcut for all calls on {@link testpackage.Class1#staticPostOperation01(Integer anInt)}.</p>
     */
    protected pointcut staticPostOperation01Caller(Integer anInt):
    	execution(* testpackage.Class1.staticPostOperation01(Integer)) && args(anInt);
    
    /**
     * <p>Checks a postcondition for the operation {@link Class1#staticPostOperation01(, Integer anInt)} defined by the constraint
     * <code>context Class1::staticPostOperation01(anInt: Integer) : Integer
     *       post testStaticPost01: result = anInt * 2</code></p>
     */
    Integer around(Integer anInt): staticPostOperation01Caller(anInt) {
    
        Integer result;
        result = proceed(anInt);
    
        if (!((Object) result).equals((anInt * new Integer(2)))) {
        	// TODO Auto-generated code executed when constraint is violated.
        	throw new RuntimeException("Error: Constraint was violated.");
        }
        // no else.
    
        return result;
    }
}