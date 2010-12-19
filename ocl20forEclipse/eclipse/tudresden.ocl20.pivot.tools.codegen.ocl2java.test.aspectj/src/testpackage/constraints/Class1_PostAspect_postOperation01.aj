package testpackage.constraints;

/**
 * <p>Generated Aspect to enforce OCL constraint.</p>
 *
 * @author OCL22Java of Dresden OCL2 for Eclipse
 * @Generated
 */
public privileged aspect Class1_PostAspect_postOperation01 {

    /**
     * <p>Pointcut for all calls on {@link testpackage.Class1#postOperation01(Integer anInt)}.</p>
     */
    protected pointcut postOperation01Caller(testpackage.Class1 aClass, Integer anInt):
    	call(* testpackage.Class1.postOperation01(Integer))
    	&& target(aClass) && args(anInt);
    
    /**
     * <p>Checks a postcondition for the operation {@link Class1#postOperation01(, Integer anInt)} defined by the constraint
     * <code>context Class1::postOperation01(anInt: Integer) : Integer
     *       post testPost01: result = anInt * 2</code></p>
     */
    Integer around(testpackage.Class1 aClass, Integer anInt): postOperation01Caller(aClass, anInt) {
        /* Disable this constraint for subclasses of Class1. */
        if (aClass.getClass().getCanonicalName().equals("testpackage.Class1")) {
    
        Integer result;
        result = proceed(aClass, anInt);
    
        if (!((Object) result).equals((anInt * new Integer(2)))) {
        	// TODO Auto-generated code executed when constraint is violated.
        	String msg = "Error: Constraint 'testPost01' (post testPost01: result = anInt * 2) was violated for Object " + aClass.toString() + ".";
        	throw new RuntimeException(msg);
        }
        // no else.
    
        return result;
        }
    
        else {
            return proceed(aClass, anInt);
        }
    }
}