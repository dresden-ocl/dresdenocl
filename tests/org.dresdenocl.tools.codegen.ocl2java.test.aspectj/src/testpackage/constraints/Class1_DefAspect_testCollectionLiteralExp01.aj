package testpackage.constraints;

/**
 * <p>Generated Aspect to enforce OCL constraint.</p>
 *
 * @author OCL22Java of Dresden OCL2 for Eclipse
 * @Generated
 */
public privileged aspect Class1_DefAspect_testCollectionLiteralExp01 {

    /**
     * <p>Defines the method testCollectionLiteralExp01() defined by the constraint
     * <code>context Class1
     *       def: testCollectionLiteralExp01():    Bag(Integer) = Bag { 0, 1, 2 }</code></p>
     */
    public java.util.List<Integer> testpackage.Class1.testCollectionLiteralExp01() {
        java.util.ArrayList<Integer> collection1;
        collection1 = new java.util.ArrayList<Integer>();

        collection1.add(new Integer(0));
        collection1.add(new Integer(1));
        collection1.add(new Integer(2));

        return collection1;
    }
}