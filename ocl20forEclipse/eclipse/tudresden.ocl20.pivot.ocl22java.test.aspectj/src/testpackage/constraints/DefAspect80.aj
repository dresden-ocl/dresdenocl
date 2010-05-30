package testpackage.constraints;

/**
 * <p>Generated Aspect to enforce OCL constraint.</p>
 *
 * @author OCL22Java of Dresden OCL2 for Eclipse
 * @Generated
 */
public privileged aspect DefAspect80 {

    /* Declares a new super class containing the new attribute or method. */
    declare parents : testpackage.Class1 extends testpackage.constraints.ExtendedClass1;
    
    /**
     * <p>Pointcut for all calls on {@link testpackage.Class1#testRealRound01(Float source)}.</p>
     */
    protected pointcut testRealRound01Caller(testpackage.Class1 aClass, Float source):
    	call(* testpackage.Class1.testRealRound01(Float))
    	&& target(aClass) && args(source);
    
    /**
     * <p>Defines the method testRealRound01(Float source) defined by the constraint
     * <code>context Class1
     *       def: testRealRound01 = source[].round()</code></p>
     */
    Integer around(testpackage.Class1 aClass, Float source): testRealRound01Caller(aClass, source) {
        return java.lang.Math.round(source);
    }
}