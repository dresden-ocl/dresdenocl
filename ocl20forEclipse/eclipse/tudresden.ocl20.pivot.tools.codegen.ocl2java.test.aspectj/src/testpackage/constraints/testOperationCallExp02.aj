package testpackage.constraints;

/**
 * <p>Generated Aspect to enforce OCL constraint.</p>
 *
 * @author OCL22Java of Dresden OCL2 for Eclipse
 * @Generated
 */
public privileged aspect testOperationCallExp02 {

    /* Declares a new super class containing the new attribute or method. */
    declare parents : testpackage.Class1 extends testpackage.constraints.ExtendedClass1;
    
    /**
     * <p>Pointcut for all calls on {@link testpackage.Class1#testOperationCallExp02()}.</p>
     */
    protected pointcut testOperationCallExp02Caller(testpackage.Class1 aClass):
    	call(* testpackage.Class1.testOperationCallExp02())
    	&& target(aClass);
    
    /**
     * <p>Defines the method testOperationCallExp02() defined by the constraint
     * <code>context Class1
     *       def: testOperationCallExp02(): Integer =
    Class1::getStaticInteger()</code></p>
     */
    Integer around(testpackage.Class1 aClass): testOperationCallExp02Caller(aClass) {
        return testpackage.Class1.getStaticInteger();
    }
}