package testpackage.constraints;

/**
 * <p>Generated Aspect to enforce OCL constraint.</p>
 *
 * @author OCL22Java of Dresden OCL2 for Eclipse
 * @Generated
 */
public privileged aspect DefAspect109 {

    /* Declares a new super class containing the new attribute or method. */
    declare parents : testpackage.Class1 extends testpackage.constraints.ExtendedClass1;
    
    /**
     * <p>Pointcut for all calls on {@link testpackage.Class1#testRealNegation01(Float source)}.</p>
     */
    protected pointcut testRealNegation01Caller(testpackage.Class1 aClass, Float source):
    	call(* testpackage.Class1.testRealNegation01(Float))
    	&& target(aClass) && args(source);
    
    /**
     * <p>Defines the method testRealNegation01(Float source) defined by the constraint
     * <code>context Class1
     *       def: testRealNegation01(source: Real): Real =
    - source</code></p>
     */
    Float around(testpackage.Class1 aClass, Float source): testRealNegation01Caller(aClass, source) {
        return -(source);
    }
}