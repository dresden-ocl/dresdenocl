package testpackage.constraints;

/**
 * <p>Generated Aspect to enforce OCL constraint.</p>
 *
 * @author OCL22Java of Dresden OCL2 for Eclipse
 * @Generated
 */
public privileged aspect testOperationCallExp01 {

    /* Declares a new super class containing the new attribute or method. */
    declare parents : testpackage.Class1 extends testpackage.constraints.ExtendedClass1;
    
    /**
     * <p>Pointcut for all calls on {@link testpackage.Class1#testOperationCallExp01()}.</p>
     */
    protected pointcut testOperationCallExp01Caller(testpackage.Class1 aClass):
    	call(* testpackage.Class1.testOperationCallExp01())
    	&& target(aClass);
    
    /**
     * <p>Defines the method testOperationCallExp01() defined by the constraint
     * <code>context Class1
     *       def: testOperationCallExp01(): Integer =
    self.getInteger()</code></p>
     */
    Integer around(testpackage.Class1 aClass): testOperationCallExp01Caller(aClass) {
        return aClass.getInteger();
    }
}