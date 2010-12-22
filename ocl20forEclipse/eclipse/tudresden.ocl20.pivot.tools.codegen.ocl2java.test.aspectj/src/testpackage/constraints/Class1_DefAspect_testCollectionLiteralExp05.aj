package testpackage.constraints;

/**
 * <p>Generated Aspect to enforce OCL constraint.</p>
 *
 * @author OCL22Java of Dresden OCL2 for Eclipse
 * @Generated
 */
public privileged aspect Class1_DefAspect_testCollectionLiteralExp05 {

    /**
     * <p>Defines the method testCollectionLiteralExp05() defined by the constraint
     * <code>context Class1
     *       def: testCollectionLiteralExp05():    Set(Integer) = Set { 0, 1, 2 }</code></p>
     */
    public java.util.Set<Integer> testpackage.Class1.testCollectionLiteralExp05( ) {
        java.util.HashSet<Integer> collection1;
        collection1 = new java.util.HashSet<Integer>();
        
        collection1.add(new Integer(0));
        collection1.add(new Integer(1));
        collection1.add(new Integer(2));
    
        return collection1;
    }
}