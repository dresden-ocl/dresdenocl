package testpackage.constraints;

/**
 * <p>Generated Aspect to enforce OCL constraint.</p>
 *
 * @author OCL22Java of Dresden OCL2 for Eclipse
 * @Generated
 */
public privileged aspect DefAspect110 {

    /* Declares a new super class containing the new attribute or method. */
    declare parents : testpackage.Class1 extends testpackage.constraints.ExtendedClass1;
    
    /**
     * <p>Pointcut for all calls on {@link testpackage.Class1#testRealAbs01(Float source)}.</p>
     */
    protected pointcut testRealAbs01Caller(testpackage.Class1 aClass, Float source):
    	call(* testpackage.Class1.testRealAbs01(Float))
    	&& target(aClass) && args(source);
    
    /**
     * <p>Defines the method testRealAbs01(Float source) defined by the constraint
     * <code>context Class1
     *       def: testRealAbs01(source: Real): Real =
    source.abs()</code></p>
     */
    Float around(testpackage.Class1 aClass, Float source): testRealAbs01Caller(aClass, source) {
        return java.lang.Math.abs(source);
    }
}