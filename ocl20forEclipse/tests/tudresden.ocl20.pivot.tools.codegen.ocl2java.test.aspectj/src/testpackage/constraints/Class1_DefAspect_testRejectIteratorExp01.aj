package testpackage.constraints;

/**
 * <p>Generated Aspect to enforce OCL constraint.</p>
 *
 * @author OCL22Java of Dresden OCL2 for Eclipse
 * @Generated
 */
public privileged aspect Class1_DefAspect_testRejectIteratorExp01 {

    /**
     * <p>Defines the method testRejectIteratorExp01() defined by the constraint
     * <code>context Class1
     *       def: testRejectIteratorExp01() : Set(Integer) = Set{1, 2, 3}->reject(i: Integer | i = 2)</code></p>
     */
    public java.util.Set<Integer> testpackage.Class1.testRejectIteratorExp01( ) {
        java.util.HashSet<Integer> collection1;
        collection1 = new java.util.HashSet<Integer>();
        
        collection1.add(new Integer(1));
        collection1.add(new Integer(2));
        collection1.add(new Integer(3));
        java.util.HashSet<Integer> result1;
        result1 = new java.util.HashSet<Integer>();
        
        /* Iterator Reject: Select all elements which do not fulfill the condition. */
        for (Integer anElement1 : collection1) {
            if (!((Object) anElement1).equals(new Integer(2))) {
                result1.add(anElement1);
            }
            // no else
        }
    
        return result1;
    }
}