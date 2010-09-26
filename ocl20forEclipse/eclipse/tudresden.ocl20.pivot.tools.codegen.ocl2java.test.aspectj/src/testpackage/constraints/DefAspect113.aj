package testpackage.constraints;

/**
 * <p>Generated Aspect to enforce OCL constraint.</p>
 *
 * @author OCL22Java of Dresden OCL2 for Eclipse
 * @Generated
 */
public privileged aspect DefAspect113 {

    /* Declares a new super class containing the new attribute or method. */
    declare parents : testpackage.Class1 extends testpackage.constraints.ExtendedClass1;
    
    /**
     * <p>Pointcut for all calls on {@link testpackage.Class1#testRealGreaterThan01(Float source, Float arg01)}.</p>
     */
    protected pointcut testRealGreaterThan01Caller(testpackage.Class1 aClass, Float source, Float arg01):
    	call(* testpackage.Class1.testRealGreaterThan01(Float, Float))
    	&& target(aClass) && args(source, arg01);
    
    /**
     * <p>Defines the method testRealGreaterThan01(Float source, Float arg01) defined by the constraint
     * <code>context Class1
     *       def: testRealGreaterThan01(source: Real, arg01: Real): Boolean =
    source > arg01</code></p>
     */
    Boolean around(testpackage.Class1 aClass, Float source, Float arg01): testRealGreaterThan01Caller(aClass, source, arg01) {
        return (source > arg01);
    }
}