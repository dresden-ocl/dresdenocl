package testpackage.constraints;

/**
 * <p>Generated Aspect to enforce OCL constraint.</p>
 *
 * @author OCL22Java of Dresden OCL2 for Eclipse
 * @Generated
 */
public privileged aspect DefAspect33 {

    /* Declares a new super class containing the new attribute or method. */
    declare parents : testpackage.Class1 extends testpackage.constraints.ExtendedClass1;
    
    /**
     * <p>Pointcut for all calls on {@link testpackage.Class1#testStringLiteralExp01()}.</p>
     */
    protected pointcut testStringLiteralExp01Caller(testpackage.Class1 aClass):
    	call(* testpackage.Class1.testStringLiteralExp01())
    	&& target(aClass);
    
    /**
     * <p>Defines the method testStringLiteralExp01() defined by the constraint
     * <code>context Class1
     *       def: testStringLiteralExp01(): String =
    'some'</code></p>
     */
    String around(testpackage.Class1 aClass): testStringLiteralExp01Caller(aClass) {
        return "some";
    }
}