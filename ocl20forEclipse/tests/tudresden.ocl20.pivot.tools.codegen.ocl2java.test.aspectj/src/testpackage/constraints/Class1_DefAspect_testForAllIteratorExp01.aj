package testpackage.constraints;

/**
 * <p>Generated Aspect to enforce OCL constraint.</p>
 *
 * @author OCL22Java of Dresden OCL2 for Eclipse
 * @Generated
 */
public privileged aspect Class1_DefAspect_testForAllIteratorExp01 {

    /**
     * <p>Defines the method testForAllIteratorExp01() defined by the constraint
     * <code>context Class1
     *       def: testForAllIteratorExp01() : Boolean = Set{1, 2, 3}->forAll(i: Integer | i > 0)</code></p>
     */
    public Boolean testpackage.Class1.testForAllIteratorExp01() {
        java.util.HashSet<Integer> collection1;
        collection1 = new java.util.HashSet<Integer>();

        collection1.add(new Integer(1));
        collection1.add(new Integer(2));
        collection1.add(new Integer(3));
        Boolean result1;
        result1 = true;

        /* Iterator ForAll: Iterate and check, if all elements fulfill the condition. */
        for (Integer anElement1 : collection1) {
            if (!(anElement1 > new Integer(0))) {
                result1 = false;
                break;
            }
            // no else
        }

        return result1;
    }
}