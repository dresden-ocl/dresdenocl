package testpackage.constraints;

/**
 * <p>Generated Aspect to enforce OCL constraint.</p>
 *
 * @author OCL22Java of Dresden OCL2 for Eclipse
 * @Generated
 */
public privileged aspect Class1_DefAspect_testIterateExp01 {

    /**
     * <p>Defines the method testIterateExp01() defined by the constraint
     * <code>context Class1
     *       def: testIterateExp01() : Integer =
    Set{1, 2, 3}->iterate(i: Integer; sum: Integer = 0 | sum + i)</code></p>
     */
    public Integer testpackage.Class1.testIterateExp01( ) {
        java.util.HashSet<Integer> collection1;
        collection1 = new java.util.HashSet<Integer>();
        
        collection1.add(new Integer(1));
        collection1.add(new Integer(2));
        collection1.add(new Integer(3));
        Integer result1;
        result1 = new Integer(0);
        /* IterateExp: Iterate through all elements and perform an operation on them. */
        for (Integer anElement1 : collection1) {
            result1 = (result1 + anElement1);
        }
    
        return result1;
    }
}