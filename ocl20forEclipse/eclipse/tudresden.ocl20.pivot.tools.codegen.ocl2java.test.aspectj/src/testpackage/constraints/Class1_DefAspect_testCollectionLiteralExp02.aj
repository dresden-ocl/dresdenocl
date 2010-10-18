package testpackage.constraints;

/**
 * <p>Generated Aspect to enforce OCL constraint.</p>
 *
 * @author OCL22Java of Dresden OCL2 for Eclipse
 * @Generated
 */
public privileged aspect Class1_DefAspect_testCollectionLiteralExp02 {

    /**
     * <p>Defines the method testCollectionLiteralExp02() defined by the constraint
     * <code>context Class1
     *       def: testCollectionLiteralExp02(): 
      Bag(Integer) =
    Bag { 0 ..2 }</code></p>
     */
    public java.util.List<Integer> testpackage.Class1.testCollectionLiteralExp02( ) {
        java.util.ArrayList<Integer> collection1;
        collection1 = new java.util.ArrayList<Integer>();
        
        /* TODO: Auto-generated initialization does only work for numeric values. */
        for (Integer index1 = new Integer(0); index1 <= new Integer(2); index1++) {
            collection1.add(index1);
        }
    
        return collection1;
    }
}