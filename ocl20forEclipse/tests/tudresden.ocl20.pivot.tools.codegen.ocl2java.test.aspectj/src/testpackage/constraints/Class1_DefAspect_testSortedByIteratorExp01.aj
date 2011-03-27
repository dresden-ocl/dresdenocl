package testpackage.constraints;

/**
 * <p>Generated Aspect to enforce OCL constraint.</p>
 *
 * @author OCL22Java of Dresden OCL2 for Eclipse
 * @Generated
 */
public privileged aspect Class1_DefAspect_testSortedByIteratorExp01 {

    /**
     * <p>Defines the method testSortedByIteratorExp01() defined by the constraint
     * <code>context Class1
     *       def: testSortedByIteratorExp01() : OrderedSet(Integer) = OrderedSet{3, 2, 1}->sortedBy(i: Integer | i)</code></p>
     */
    public java.util.List<Integer> testpackage.Class1.testSortedByIteratorExp01( ) {
        java.util.ArrayList<Integer> collection1;
        collection1 = new java.util.ArrayList<Integer>();
        
        collection1.add(new Integer(3));
        collection1.add(new Integer(2));
        collection1.add(new Integer(1));
        java.util.ArrayList<Integer> result1;
        java.util.Comparator<Integer> comparator1;
        
        result1 = collection1;
        
        comparator1 = new java.util.Comparator<Integer>() {
        
            /** Method which compares two elements of the collection. */
            public int compare(Integer anElement1, Integer anElement2) {
                int result2;
        
                result2 = 0;
        
                if (anElement1 < anElement2) {
                    result2 = -1;
                } else if (anElement1 > anElement2) {
                    result2 = 1;
                }
        
                return result2;
            }
        };
        
        java.util.Collections.sort(result1, comparator1);
    
        return result1;
    }
}