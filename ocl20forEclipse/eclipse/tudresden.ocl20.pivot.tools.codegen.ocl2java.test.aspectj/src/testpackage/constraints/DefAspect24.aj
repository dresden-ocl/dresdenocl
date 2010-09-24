package testpackage.constraints;

/**
 * <p>Generated Aspect to enforce OCL constraint.</p>
 *
 * @author OCL22Java of Dresden OCL2 for Eclipse
 * @Generated
 */
public privileged aspect DefAspect24 {

    /* Declares a new super class containing the new attribute or method. */
    declare parents : testpackage.Class1 extends testpackage.constraints.ExtendedClass1;
    
    /**
     * <p>Pointcut for all calls on {@link testpackage.Class1#testUndefinedLiteralExp01()}.</p>
     */
    protected pointcut testUndefinedLiteralExp01Caller(testpackage.Class1 aClass):
    	call(* testpackage.Class1.testUndefinedLiteralExp01())
    	&& target(aClass);
    
    /**
     * <p>Defines the method testUndefinedLiteralExp01() defined by the constraint
     * <code>context Class1
     *       def: testUndefinedLiteralExp01(): Integer =
    null</code></p>
     */
    Integer around(testpackage.Class1 aClass): testUndefinedLiteralExp01Caller(aClass) {
        return null;
    }
}