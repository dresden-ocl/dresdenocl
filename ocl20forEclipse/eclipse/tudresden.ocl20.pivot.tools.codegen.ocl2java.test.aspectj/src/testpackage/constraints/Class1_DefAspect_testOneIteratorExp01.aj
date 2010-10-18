package testpackage.constraints;

/**
 * <p>Generated Aspect to enforce OCL constraint.</p>
 *
 * @author OCL22Java of Dresden OCL2 for Eclipse
 * @Generated
 */
public privileged aspect Class1_DefAspect_testOneIteratorExp01 {

    /**
     * <p>Defines the method testOneIteratorExp01() defined by the constraint
     * <code>context Class1
     *       def: testOneIteratorExp01() : Boolean =
    Set{ 1, 2, 3}->one(i: Integer | i = 2)</code></p>
     */
    public Boolean testpackage.Class1.testOneIteratorExp01( ) {
        java.util.HashSet<Integer> collection1;
        collection1 = new java.util.HashSet<Integer>();
        
        collection1.add(new Integer(1));
        collection1.add(new Integer(2));
        collection1.add(new Integer(3));
        Boolean result1;
        result1 = false;
        
        /* Iterator One: Iterate and check, if exactly one element fulfills the condition. */
        for (Integer i : collection1) {
            if (((Object) i).equals(new Integer(2))) {
                if (result1) {
                    // Found a second element.
                    result1 = false;
                    break;
                } else {
                    // Found a first element.
                    result1 = true;
        	    }
            }
            // no else
        }
    
        return result1;
    }
}