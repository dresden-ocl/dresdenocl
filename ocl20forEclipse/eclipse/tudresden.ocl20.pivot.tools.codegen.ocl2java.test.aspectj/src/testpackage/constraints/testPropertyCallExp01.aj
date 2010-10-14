package testpackage.constraints;

/**
 * <p>Generated Aspect to enforce OCL constraint.</p>
 *
 * @author OCL22Java of Dresden OCL2 for Eclipse
 * @Generated
 */
public privileged aspect testPropertyCallExp01 {

    /* Declares a new super class containing the new attribute or method. */
    declare parents : testpackage.Class1 extends testpackage.constraints.ExtendedClass1;
    
    /**
     * <p>Pointcut for all calls on {@link testpackage.Class1#testPropertyCallExp01()}.</p>
     */
    protected pointcut testPropertyCallExp01Caller(testpackage.Class1 aClass):
    	call(* testpackage.Class1.testPropertyCallExp01())
    	&& target(aClass);
    
    /**
     * <p>Defines the method testPropertyCallExp01() defined by the constraint
     * <code>context Class1
     *       def: testPropertyCallExp01(): Integer =
    self.anInteger01</code></p>
     */
    Integer around(testpackage.Class1 aClass): testPropertyCallExp01Caller(aClass) {
        return aClass.anInteger01;
    }
}