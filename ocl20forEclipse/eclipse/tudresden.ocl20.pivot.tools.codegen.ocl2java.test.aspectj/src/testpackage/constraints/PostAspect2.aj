package testpackage.constraints;

/**
 * <p>Generated Aspect to enforce OCL constraint.</p>
 *
 * @author OCL22Java of Dresden OCL2 for Eclipse
 * @Generated
 */
public privileged aspect PostAspect2 {

    /**
     * <p>Pointcut for all calls on {@link testpackage.Class1#Class1(Integer in1)}.</p>
     */
    protected pointcut Class1Caller(Integer in1):
    	execution(testpackage.Class1.new(Integer)) && args(in1);
    
    /**
     * <p>Checks a postcondition for the operation {@link Class1#Class1(, Integer in1)} defined by the constraint
     * <code>context Class1::Class1(in1: Integer) : 
     *       post: true</code></p>
     */
    void around(Integer in1): Class1Caller(in1) {
    
        proceed(in1);
    
        if (!new Boolean(true)) {
        	// TODO Auto-generated code executed when constraint is violated.
        	throw new RuntimeException("Error: Constraint was violated.");
        }
        // no else.
    }
}