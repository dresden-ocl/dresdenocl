package testpackage.constraints;

/**
 * <p>Generated Aspect to enforce OCL constraint.</p>
 *
 * @author OCL22Java of Dresden OCL2 for Eclipse
 * @Generated
 */
public privileged aspect DefAspect38 {

    /* Declares a new super class containing the new attribute or method. */
    declare parents : testpackage.Class1 extends testpackage.constraints.ExtendedClass1;
    
    /**
     * <p>Pointcut for all calls on {@link testpackage.Class1#testLetExp01()}.</p>
     */
    protected pointcut testLetExp01Caller(testpackage.Class1 aClass):
    	call(* testpackage.Class1.testLetExp01())
    	&& target(aClass);
    
    /**
     * <p>Defines the method testLetExp01() defined by the constraint
     * <code>context Class1
     *       def: testLetExp01(): Integer =
    let senseOfLife = 42
    in senseOfLife</code></p>
     */
    Integer around(testpackage.Class1 aClass): testLetExp01Caller(aClass) {
        Integer senseOfLife;
        senseOfLife = new Integer(42);
        
    
        return senseOfLife;
    }
}