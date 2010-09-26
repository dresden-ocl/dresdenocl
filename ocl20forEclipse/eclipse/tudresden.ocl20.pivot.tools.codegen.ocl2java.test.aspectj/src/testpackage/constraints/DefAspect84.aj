package testpackage.constraints;

/**
 * <p>Generated Aspect to enforce OCL constraint.</p>
 *
 * @author OCL22Java of Dresden OCL2 for Eclipse
 * @Generated
 */
public privileged aspect DefAspect84 {

    /* Declares a new super class containing the new attribute or method. */
    declare parents : testpackage.Class1 extends testpackage.constraints.ExtendedClass1;
    
    /**
     * <p>Pointcut for all calls on {@link testpackage.Class1#testIntegerNegation01(Integer source)}.</p>
     */
    protected pointcut testIntegerNegation01Caller(testpackage.Class1 aClass, Integer source):
    	call(* testpackage.Class1.testIntegerNegation01(Integer))
    	&& target(aClass) && args(source);
    
    /**
     * <p>Defines the method testIntegerNegation01(Integer source) defined by the constraint
     * <code>context Class1
     *       def: testIntegerNegation01(source: Integer): Integer =
    - source</code></p>
     */
    Integer around(testpackage.Class1 aClass, Integer source): testIntegerNegation01Caller(aClass, source) {
        return -(source);
    }
}