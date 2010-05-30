package testpackage.constraints;

/**
 * <p>Generated Aspect to enforce OCL constraint.</p>
 *
 * @author OCL22Java of Dresden OCL2 for Eclipse
 * @Generated
 */
public privileged aspect DefAspect79 {

    /* Declares a new super class containing the new attribute or method. */
    declare parents : testpackage.Class1 extends testpackage.constraints.ExtendedClass1;
    
    /**
     * <p>Pointcut for all calls on {@link testpackage.Class1#testRealPlus01(Float source, Float arg01)}.</p>
     */
    protected pointcut testRealPlus01Caller(testpackage.Class1 aClass, Float source, Float arg01):
    	call(* testpackage.Class1.testRealPlus01(Float, Float))
    	&& target(aClass) && args(source, arg01);
    
    /**
     * <p>Defines the method testRealPlus01(Float source, Float arg01) defined by the constraint
     * <code>context Class1
     *       def: testRealPlus01 = source[].+( arg01[])</code></p>
     */
    Float around(testpackage.Class1 aClass, Float source, Float arg01): testRealPlus01Caller(aClass, source, arg01) {
        return (source + arg01);
    }
}