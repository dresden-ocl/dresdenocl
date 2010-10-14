package testpackage.constraints;

/**
 * <p>Generated Aspect to enforce OCL constraint.</p>
 *
 * @author OCL22Java of Dresden OCL2 for Eclipse
 * @Generated
 */
public privileged aspect testRealLiteralExp01 {

    /* Declares a new super class containing the new attribute or method. */
    declare parents : testpackage.Class1 extends testpackage.constraints.ExtendedClass1;
    
    /**
     * <p>Pointcut for all calls on {@link testpackage.Class1#testRealLiteralExp01()}.</p>
     */
    protected pointcut testRealLiteralExp01Caller(testpackage.Class1 aClass):
    	call(* testpackage.Class1.testRealLiteralExp01())
    	&& target(aClass);
    
    /**
     * <p>Defines the method testRealLiteralExp01() defined by the constraint
     * <code>context Class1
     *       def: testRealLiteralExp01(): Real = 42.7</code></p>
     */
    Float around(testpackage.Class1 aClass): testRealLiteralExp01Caller(aClass) {
        return new Float(42.7);
    }
}