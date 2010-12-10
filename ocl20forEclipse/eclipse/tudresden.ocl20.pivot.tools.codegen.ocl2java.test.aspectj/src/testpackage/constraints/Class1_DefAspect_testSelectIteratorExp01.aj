package testpackage.constraints;

/**
 * <p>Generated Aspect to enforce OCL constraint.</p>
 *
 * @author OCL22Java of Dresden OCL2 for Eclipse
 * @Generated
 */
public privileged aspect Class1_DefAspect_testSelectIteratorExp01 {

    /**
     * <p>Defines the method testSelectIteratorExp01() defined by the constraint
     * <code>context Class1
     *       def: testSelectIteratorExp01() : Set(Integer) =
    Set{1, 2, 3}->select(i: Integer | i = 2)</code></p>
     */
    public java.util.Set<Integer> testpackage.Class1.testSelectIteratorExp01( ) {
        java.util.HashSet<Integer> collection1;
        collection1 = new java.util.HashSet<Integer>();
        
        collection1.add(new Integer(1));
        collection1.add(new Integer(2));
        collection1.add(new Integer(3));
        java.util.HashSet<Integer> result1;
        result1 = new java.util.HashSet<Integer>();
        
        /* Iterator Select: Select all elements which fulfill the condition. */
        for (Integer anElement1 : collection1) {
            if (((Object) anElement1).equals(new Integer(2))) {
                result1.add(anElement1);
            }
            // no else
        }
    
        return result1;
    }
}