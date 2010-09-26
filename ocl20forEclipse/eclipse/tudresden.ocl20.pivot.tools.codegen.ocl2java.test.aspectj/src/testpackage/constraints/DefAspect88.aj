package testpackage.constraints;

/**
 * <p>Generated Aspect to enforce OCL constraint.</p>
 *
 * @author OCL22Java of Dresden OCL2 for Eclipse
 * @Generated
 */
public privileged aspect DefAspect88 {

    /* Declares a new super class containing the new attribute or method. */
    declare parents : testpackage.Class1 extends testpackage.constraints.ExtendedClass1;
    
    /**
     * <p>Pointcut for all calls on {@link testpackage.Class1#testOclAnyEquals01(testpackage.Class1 source, testpackage.Class1 arg01)}.</p>
     */
    protected pointcut testOclAnyEquals01Caller(testpackage.Class1 aClass, testpackage.Class1 source, testpackage.Class1 arg01):
    	call(* testpackage.Class1.testOclAnyEquals01(testpackage.Class1, testpackage.Class1))
    	&& target(aClass) && args(source, arg01);
    
    /**
     * <p>Defines the method testOclAnyEquals01(testpackage.Class1 source, testpackage.Class1 arg01) defined by the constraint
     * <code>context Class1
     *       def: testOclAnyEquals01(source: Class1, arg01: Class1): Boolean =
    source = arg01</code></p>
     */
    Boolean around(testpackage.Class1 aClass, testpackage.Class1 source, testpackage.Class1 arg01): testOclAnyEquals01Caller(aClass, source, arg01) {
        return source.equals(arg01);
    }
}