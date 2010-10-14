package testpackage.constraints;

/**
 * <p>Generated Aspect to enforce OCL constraint.</p>
 *
 * @author OCL22Java of Dresden OCL2 for Eclipse
 * @Generated
 */
public privileged aspect testEnumerationLiteralExp01 {

    /* Declares a new super class containing the new attribute or method. */
    declare parents : testpackage.Class1 extends testpackage.constraints.ExtendedClass1;
    
    /**
     * <p>Pointcut for all calls on {@link testpackage.Class1#testEnumerationLiteralExp01()}.</p>
     */
    protected pointcut testEnumerationLiteralExp01Caller(testpackage.Class1 aClass):
    	call(* testpackage.Class1.testEnumerationLiteralExp01())
    	&& target(aClass);
    
    /**
     * <p>Defines the method testEnumerationLiteralExp01() defined by the constraint
     * <code>context Class1
     *       def: testEnumerationLiteralExp01(): Enumeration1 =
    Enumeration1::literal1</code></p>
     */
    Enum around(testpackage.Class1 aClass): testEnumerationLiteralExp01Caller(aClass) {
        return testpackage.Enumeration1.literal1;
    }
}