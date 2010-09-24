package testpackage.constraints;

/**
 * <p>Generated Aspect to enforce OCL constraint.</p>
 *
 * @author OCL22Java of Dresden OCL2 for Eclipse
 * @Generated
 */
public privileged aspect DefAspect102 {

    /* Declares a new super class containing the new attribute or method. */
    declare parents : testpackage.Class1 extends testpackage.constraints.ExtendedClass1;
    
    /**
     * <p>Pointcut for all calls on {@link testpackage.Class1#testRealGreaterThanEqual01(Float source, Float arg01)}.</p>
     */
    protected pointcut testRealGreaterThanEqual01Caller(testpackage.Class1 aClass, Float source, Float arg01):
    	call(* testpackage.Class1.testRealGreaterThanEqual01(Float, Float))
    	&& target(aClass) && args(source, arg01);
    
    /**
     * <p>Defines the method testRealGreaterThanEqual01(Float source, Float arg01) defined by the constraint
     * <code>context Class1
     *       def: testRealGreaterThanEqual01(source: Real, arg01: Real): Boolean =
    source >= arg01</code></p>
     */
    Boolean around(testpackage.Class1 aClass, Float source, Float arg01): testRealGreaterThanEqual01Caller(aClass, source, arg01) {
        return (source >= arg01);
    }
}