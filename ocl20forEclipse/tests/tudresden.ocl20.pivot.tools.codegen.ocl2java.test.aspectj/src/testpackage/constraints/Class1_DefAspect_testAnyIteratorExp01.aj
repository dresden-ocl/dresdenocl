package testpackage.constraints;

/**
 * <p>Generated Aspect to enforce OCL constraint.</p>
 *
 * @author OCL22Java of Dresden OCL2 for Eclipse
 * @Generated
 */
public privileged aspect Class1_DefAspect_testAnyIteratorExp01 {

    /**
     * <p>Defines the method testAnyIteratorExp01() defined by the constraint
     * <code>context Class1
     *       def: testAnyIteratorExp01() : Integer = Set{1, 2, 3}->any(i: Integer | i = 2)</code></p>
     */
    public Integer testpackage.Class1.testAnyIteratorExp01() {
        java.util.HashSet<Integer> collection1;
        collection1 = new java.util.HashSet<Integer>();

        collection1.add(new Integer(1));
        collection1.add(new Integer(2));
        collection1.add(new Integer(3));
        Integer result1;
        result1 = null;

        /* Iterator Any: Iterate through the elements and return one element that fulfills the condition. */
        for (Integer anElement1 : collection1) {
            if (((Object) anElement1).equals(new Integer(2))) {
                result1 = anElement1;
                break;
            }
            // no else
        }

        return result1;
    }
}