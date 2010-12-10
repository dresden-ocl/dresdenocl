package testpackage.constraints;

/**
 * <p>Generated Aspect to enforce OCL constraint.</p>
 *
 * @author OCL22Java of Dresden OCL2 for Eclipse
 * @Generated
 */
public privileged aspect Class1_DefAspect_testIsUniqueIteratorExp01 {

    /**
     * <p>Defines the method testIsUniqueIteratorExp01() defined by the constraint
     * <code>context Class1
     *       def: testIsUniqueIteratorExp01() : Boolean =
    Set{1, 2, 3}->isUnique(i: Integer | i)</code></p>
     */
    public Boolean testpackage.Class1.testIsUniqueIteratorExp01( ) {
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
        for (Integer anElement1 : collection1) {
            if (collection2.contains(anElement1)) {
                result1 = false;
                break;
            } else {
                collection2.add(anElement1);
            }
        }
    
        return result1;
    }
}