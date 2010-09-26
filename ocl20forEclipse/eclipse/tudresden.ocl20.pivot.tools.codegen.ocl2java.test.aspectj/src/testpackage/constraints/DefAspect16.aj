package testpackage.constraints;

/**
 * <p>Generated Aspect to enforce OCL constraint.</p>
 *
 * @author OCL22Java of Dresden OCL2 for Eclipse
 * @Generated
 */
public privileged aspect DefAspect16 {

    /* Declares a new super class containing the new attribute or method. */
    declare parents : testpackage.Class1 extends testpackage.constraints.ExtendedClass1;
    
    /**
     * <p>Pointcut for all calls on {@link testpackage.Class1#testIterateExp01()}.</p>
     */
    protected pointcut testIterateExp01Caller(testpackage.Class1 aClass):
    	call(* testpackage.Class1.testIterateExp01())
    	&& target(aClass);
    
    /**
     * <p>Defines the method testIterateExp01() defined by the constraint
     * <code>context Class1
     *       def: testIterateExp01() : Integer =
    Set{1, 2, 3}->iterate(i: Integer; sum: Integer = 0 | sum + i)</code></p>
     */
    Integer around(testpackage.Class1 aClass): testIterateExp01Caller(aClass) {
        java.util.HashSet<Integer> collection1;
        collection1 = new java.util.HashSet<Integer>();
        
        collection1.add(new Integer(1));
        collection1.add(new Integer(2));
        collection1.add(new Integer(3));
        Integer sum;
        sum = new Integer(0);
        /* IterateExp: Iterate through all elements and perform an operation on them. */
        for (Integer i : collection1) {
            sum = (sum + i);
        }
    
        return sum;
    }
}