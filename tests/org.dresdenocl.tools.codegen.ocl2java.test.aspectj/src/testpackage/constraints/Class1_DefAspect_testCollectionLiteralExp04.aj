package testpackage.constraints;

/**
 * <p>Generated Aspect to enforce OCL constraint.</p>
 *
 * @author OCL22Java of Dresden OCL2 for Eclipse
 * @Generated
 */
public privileged aspect Class1_DefAspect_testCollectionLiteralExp04 {

    /**
     * <p>Defines the method testCollectionLiteralExp04() defined by the constraint
     * <code>context Class1
     *       def: testCollectionLiteralExp04():    Sequence(Integer) = Sequence { 0, 1, 2 }</code></p>
     */
    public java.util.List<Integer> testpackage.Class1.testCollectionLiteralExp04() {
        java.util.ArrayList<Integer> collection1;
        collection1 = new java.util.ArrayList<Integer>();

        collection1.add(new Integer(0));
        collection1.add(new Integer(1));
        collection1.add(new Integer(2));

        return collection1;
    }
}