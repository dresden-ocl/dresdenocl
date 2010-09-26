package testpackage.constraints;

/**
 * <p>Generated Aspect to enforce OCL constraint.</p>
 *
 * @author OCL22Java of Dresden OCL2 for Eclipse
 * @Generated
 */
public privileged aspect DefAspect29 {

    /* Declares a new super class containing the new attribute or method. */
    declare parents : testpackage.Class1 extends testpackage.constraints.ExtendedClass1;
    
    /**
     * <p>Pointcut for all calls on {@link testpackage.Class1#testIntegerLiteralExp01()}.</p>
     */
    protected pointcut testIntegerLiteralExp01Caller(testpackage.Class1 aClass):
    	call(* testpackage.Class1.testIntegerLiteralExp01())
    	&& target(aClass);
    
    /**
     * <p>Defines the method testIntegerLiteralExp01() defined by the constraint
     * <code>context Class1
     *       def: testIntegerLiteralExp01(): Integer =
    42</code></p>
     */
    Integer around(testpackage.Class1 aClass): testIntegerLiteralExp01Caller(aClass) {
        return new Integer(42);
    }
}