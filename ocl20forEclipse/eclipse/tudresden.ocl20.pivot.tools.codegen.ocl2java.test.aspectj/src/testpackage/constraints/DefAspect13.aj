package testpackage.constraints;

/**
 * <p>Generated Aspect to enforce OCL constraint.</p>
 *
 * @author OCL22Java of Dresden OCL2 for Eclipse
 * @Generated
 */
public privileged aspect DefAspect13 {

    /* Declares a new super class containing the new attribute or method. */
    declare parents : testpackage.Class1 extends testpackage.constraints.ExtendedClass1;
    
    /**
     * <p>Pointcut for all calls on {@link testpackage.Class1#testForAllIteratorExp01()}.</p>
     */
    protected pointcut testForAllIteratorExp01Caller(testpackage.Class1 aClass):
    	call(* testpackage.Class1.testForAllIteratorExp01())
    	&& target(aClass);
    
    /**
     * <p>Defines the method testForAllIteratorExp01() defined by the constraint
     * <code>context Class1
     *       def: testForAllIteratorExp01() : Boolean =
    Set{1, 2, 3}->forAll(i: Integer | i > 0)</code></p>
     */
    Boolean around(testpackage.Class1 aClass): testForAllIteratorExp01Caller(aClass) {
        java.util.HashSet<Integer> collection1;
        collection1 = new java.util.HashSet<Integer>();
        
        collection1.add(new Integer(1));
        collection1.add(new Integer(2));
        collection1.add(new Integer(3));
        Boolean result1;
        result1 = true;
        
        /* Iterator ForAll: Iterate and check, if all elements fulfill the condition. */
        for (Integer i : collection1) {
            if (!(i > new Integer(0))) {
                result1 = false;
                break;
            }
            // no else
        }
    
        return result1;
    }
}