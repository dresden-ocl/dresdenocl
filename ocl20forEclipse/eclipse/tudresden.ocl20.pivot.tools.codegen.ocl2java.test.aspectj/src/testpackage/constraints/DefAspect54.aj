package testpackage.constraints;

/**
 * <p>Generated Aspect to enforce OCL constraint.</p>
 *
 * @author OCL22Java of Dresden OCL2 for Eclipse
 * @Generated
 */
public privileged aspect DefAspect54 {

    /* Declares a new super class containing the new attribute or method. */
    declare parents : testpackage.Class1 extends testpackage.constraints.ExtendedClass1;
    
    /**
     * <p>Pointcut for all calls on {@link testpackage.Class1#testBooleanNot01(Boolean source)}.</p>
     */
    protected pointcut testBooleanNot01Caller(testpackage.Class1 aClass, Boolean source):
    	call(* testpackage.Class1.testBooleanNot01(Boolean))
    	&& target(aClass) && args(source);
    
    /**
     * <p>Defines the method testBooleanNot01(Boolean source) defined by the constraint
     * <code>context Class1
     *       def: testBooleanNot01(source: Boolean): Boolean =
    not source</code></p>
     */
    Boolean around(testpackage.Class1 aClass, Boolean source): testBooleanNot01Caller(aClass, source) {
        return !source;
    }
}