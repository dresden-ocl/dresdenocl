package testpackage.constraints;

/**
 * <p>Generated Aspect to enforce OCL constraint.</p>
 *
 * @author OCL22Java of Dresden OCL2 for Eclipse
 * @Generated
 */
public privileged aspect DefAspect35 {

    /* Declares a new super class containing the new attribute or method. */
    declare parents : testpackage.Class1 extends testpackage.constraints.ExtendedClass1;
    
    /**
     * <p>Pointcut for all calls on {@link testpackage.Class1#testTypeLiteralExp01()}.</p>
     */
    protected pointcut testTypeLiteralExp01Caller(testpackage.Class1 aClass):
    	call(* testpackage.Class1.testTypeLiteralExp01())
    	&& target(aClass);
    
    /**
     * <p>Defines the method testTypeLiteralExp01() defined by the constraint
     * <code>context Class1
     *       def: testTypeLiteralExp01(): OclType =
    Class1</code></p>
     */
    Class<?> around(testpackage.Class1 aClass): testTypeLiteralExp01Caller(aClass) {
        return testpackage.Class1.class;
    }
}