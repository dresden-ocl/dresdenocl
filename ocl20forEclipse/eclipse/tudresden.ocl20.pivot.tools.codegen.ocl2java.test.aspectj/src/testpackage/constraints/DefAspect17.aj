package testpackage.constraints;

/**
 * <p>Generated Aspect to enforce OCL constraint.</p>
 *
 * @author OCL22Java of Dresden OCL2 for Eclipse
 * @Generated
 */
public privileged aspect DefAspect17 {

    /* Declares a new super class containing the new attribute or method. */
    declare parents : testpackage.Class1 extends testpackage.constraints.ExtendedClass1;
    
    /**
     * <p>Pointcut for all calls on {@link testpackage.Class1#testBooleanAnd01(Boolean source, Boolean arg01)}.</p>
     */
    protected pointcut testBooleanAnd01Caller(testpackage.Class1 aClass, Boolean source, Boolean arg01):
    	call(* testpackage.Class1.testBooleanAnd01(Boolean, Boolean))
    	&& target(aClass) && args(source, arg01);
    
    /**
     * <p>Defines the method testBooleanAnd01(Boolean source, Boolean arg01) defined by the constraint
     * <code>context Class1
     *       def: testBooleanAnd01(source: Boolean, arg01: Boolean): Boolean =
    source and arg01</code></p>
     */
    Boolean around(testpackage.Class1 aClass, Boolean source, Boolean arg01): testBooleanAnd01Caller(aClass, source, arg01) {
        return (source && arg01);
    }
}