package testpackage.constraints;

/**
 * <p>Generated Aspect to enforce OCL constraint.</p>
 *
 * @author OCL22Java of Dresden OCL2 for Eclipse
 * @Generated
 */
public privileged aspect testBooleanLiteralExp01 {

    /* Declares a new super class containing the new attribute or method. */
    declare parents : testpackage.Class1 extends testpackage.constraints.ExtendedClass1;
    
    /**
     * <p>Pointcut for all calls on {@link testpackage.Class1#testBooleanLiteralExp01()}.</p>
     */
    protected pointcut testBooleanLiteralExp01Caller(testpackage.Class1 aClass):
    	call(* testpackage.Class1.testBooleanLiteralExp01())
    	&& target(aClass);
    
    /**
     * <p>Defines the method testBooleanLiteralExp01() defined by the constraint
     * <code>context Class1
     *       def: testBooleanLiteralExp01(): 
      Boolean =
    true</code></p>
     */
    Boolean around(testpackage.Class1 aClass): testBooleanLiteralExp01Caller(aClass) {
        return new Boolean(true);
    }
}