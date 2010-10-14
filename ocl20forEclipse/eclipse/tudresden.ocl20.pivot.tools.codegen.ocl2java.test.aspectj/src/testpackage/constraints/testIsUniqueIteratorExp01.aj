package testpackage.constraints;

/**
 * <p>Generated Aspect to enforce OCL constraint.</p>
 *
 * @author OCL22Java of Dresden OCL2 for Eclipse
 * @Generated
 */
public privileged aspect testIsUniqueIteratorExp01 {

    /* Declares a new super class containing the new attribute or method. */
    declare parents : testpackage.Class1 extends testpackage.constraints.ExtendedClass1;
    
    /**
     * <p>Pointcut for all calls on {@link testpackage.Class1#testIsUniqueIteratorExp01()}.</p>
     */
    protected pointcut testIsUniqueIteratorExp01Caller(testpackage.Class1 aClass):
    	call(* testpackage.Class1.testIsUniqueIteratorExp01())
    	&& target(aClass);
    
    /**
     * <p>Defines the method testIsUniqueIteratorExp01() defined by the constraint
     * <code>context Class1
     *       def: testIsUniqueIteratorExp01() : Boolean =
    Set{ 1, 2, 3}->isUnique(i: Integer | i)</code></p>
     */
    Boolean around(testpackage.Class1 aClass): testIsUniqueIteratorExp01Caller(aClass) {
        java.util.HashSet<Integer> collection1;
        collection1 = new java.util.HashSet<Integer>();
        
        collection1.add(new Integer(1));
        collection1.add(new Integer(2));
        collection1.add(new Integer(3));
        java.util.Set<Integer> collection2;
        Boolean result1;
        
        collection2 = new java.util.HashSet<Integer>();
        result1 = true;
        
        /* Iterator IsUnique: Iterate and check, if all elements are unique. */
        for (Integer i : collection1) {
            if (collection2.contains(i)) {
                result1 = false;
                break;
            } else {
                collection2.add(i);
            }
        }
    
        return result1;
    }
}