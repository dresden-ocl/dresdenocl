package testpackage.constraints;

/**
 * <p>Generated Aspect to enforce OCL constraint.</p>
 *
 * @author OCL22Java of Dresden OCL2 for Eclipse
 * @Generated
 */
public privileged aspect DefAspect112 {

    /* Declares a new super class containing the new attribute or method. */
    declare parents : testpackage.Class1 extends testpackage.constraints.ExtendedClass1;
    
    /**
     * <p>Pointcut for all calls on {@link testpackage.Class1#testRealFloor01(Float source)}.</p>
     */
    protected pointcut testRealFloor01Caller(testpackage.Class1 aClass, Float source):
    	call(* testpackage.Class1.testRealFloor01(Float))
    	&& target(aClass) && args(source);
    
    /**
     * <p>Defines the method testRealFloor01(Float source) defined by the constraint
     * <code>context Class1
     *       def: testRealFloor01(source: Real): Integer =
    source.floor()</code></p>
     */
    Integer around(testpackage.Class1 aClass, Float source): testRealFloor01Caller(aClass, source) {
        return (new Integer(new Double(java.lang.Math.floor(source)).intValue()));
    }
}