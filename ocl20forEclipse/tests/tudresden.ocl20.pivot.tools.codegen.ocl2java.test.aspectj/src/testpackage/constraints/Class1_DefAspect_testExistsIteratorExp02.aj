package testpackage.constraints;

/**
 * <p>Generated Aspect to enforce OCL constraint.</p>
 *
 * @author OCL22Java of Dresden OCL2 for Eclipse
 * @Generated
 */
public privileged aspect Class1_DefAspect_testExistsIteratorExp02 {

    /**
     * <p>Defines the method testExistsIteratorExp02() defined by the constraint
     * <code>context Class1
     *       def: testExistsIteratorExp02() : Boolean = Set{1, 2, 3}->exists(i1: Integer, i2: Integer | i1 + i2 = 3)</code></p>
     */
    public Boolean testpackage.Class1.testExistsIteratorExp02( ) {
        java.util.HashSet<Integer> collection1;
        collection1 = new java.util.HashSet<Integer>();
        
        collection1.add(new Integer(1));
        collection1.add(new Integer(2));
        collection1.add(new Integer(3));
        Boolean result1;
        result1 = false;
        
        /* Iterator Exists: Iterate and check, if any element fulfills the condition. */
        for (Integer anElement1 : collection1) {
            java.util.HashSet<Integer> collection2;
            collection2 = new java.util.HashSet<Integer>();
            
            collection2.add(new Integer(1));
            collection2.add(new Integer(2));
            collection2.add(new Integer(3));
            Boolean result2;
            result2 = false;
            
            /* Iterator Exists: Iterate and check, if any element fulfills the condition. */
            for (Integer anElement2 : collection2) {
                if (((Object) (anElement1 + anElement2)).equals(new Integer(3))) {
                    result2 = true;
                    break;
                }
                // no else
            }
        
            if (result2) {
                result1 = true;
                break;
            }
            // no else
        }
    
        return result1;
    }
}