package testpackage.constraints;

/**
 * <p>Generated Aspect to enforce OCL constraint.</p>
 *
 * @author OCL22Java of Dresden OCL2 for Eclipse
 * @Generated
 */
public privileged aspect DefAspect18 {

    /* Declares a new super class containing the new attribute or method. */
    declare parents : testpackage.Class1 extends testpackage.constraints.ExtendedClass1;
    
    /**
     * <p>Pointcut for all calls on {@link testpackage.Class1#testRejectIteratorExp01()}.</p>
     */
    protected pointcut testRejectIteratorExp01Caller(testpackage.Class1 aClass):
    	call(* testpackage.Class1.testRejectIteratorExp01())
    	&& target(aClass);
    
    /**
     * <p>Defines the method testRejectIteratorExp01() defined by the constraint
     * <code>context Class1
     *       def: testRejectIteratorExp01() : Set(Integer) =
    Set{1, 2, 3}->reject(i: Integer | i = 2)</code></p>
     */
    java.util.Set<Integer> around(testpackage.Class1 aClass): testRejectIteratorExp01Caller(aClass) {
        java.util.HashSet<Integer> collection1;
        collection1 = new java.util.HashSet<Integer>();
        
        collection1.add(new Integer(1));
        collection1.add(new Integer(2));
        collection1.add(new Integer(3));
        java.util.HashSet<Integer> result1;
        result1 = new java.util.HashSet<Integer>();
        
        /* Iterator Reject: Select all elements which do not fulfill the condition. */
        for (Integer i : collection1) {
            if (!((Object) i).equals(new Integer(2))) {
                result1.add(i);
            }
            // no else
        }
    
        return result1;
    }
}