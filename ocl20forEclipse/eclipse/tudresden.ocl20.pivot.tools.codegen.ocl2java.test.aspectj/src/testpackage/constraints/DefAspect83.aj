package testpackage.constraints;

/**
 * <p>Generated Aspect to enforce OCL constraint.</p>
 *
 * @author OCL22Java of Dresden OCL2 for Eclipse
 * @Generated
 */
public privileged aspect DefAspect83 {

    /* Declares a new super class containing the new attribute or method. */
    declare parents : testpackage.Class1 extends testpackage.constraints.ExtendedClass1;
    
    /**
     * <p>Pointcut for all calls on {@link testpackage.Class1#testOclIsUndefined01(testpackage.Class1 source)}.</p>
     */
    protected pointcut testOclIsUndefined01Caller(testpackage.Class1 aClass, testpackage.Class1 source):
    	call(* testpackage.Class1.testOclIsUndefined01(testpackage.Class1))
    	&& target(aClass) && args(source);
    
    /**
     * <p>Defines the method testOclIsUndefined01(testpackage.Class1 source) defined by the constraint
     * <code>context Class1
     *       def: testOclIsUndefined01(source: Class1): Boolean =
    source.oclIsUndefined()</code></p>
     */
    Boolean around(testpackage.Class1 aClass, testpackage.Class1 source): testOclIsUndefined01Caller(aClass, source) {
        return (source == null);
    }
}