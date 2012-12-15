package testpackage.constraints;

/**
 * <p>Generated Aspect to enforce OCL constraint.</p>
 *
 * @author OCL22Java of Dresden OCL2 for Eclipse
 * @Generated
 */
public privileged aspect Class1_DefAspect_testClosureIteratorExp05 {

    /**
     * <p>Defines the method testClosureIteratorExp05() defined by the constraint
     * <code>context Class1
     *       def: testClosureIteratorExp05() : Set(Class1) = self->closure(children)</code></p>
     */
    public java.util.Set<testpackage.Class1> testpackage.Class1.testClosureIteratorExp05() {
    	/* Self variable probably used within the definition. */
    	testpackage.Class1 aClass = this;
    	
        /* Compute closure iterator. */
        java.util.Set<testpackage.Class1> result2;
        result2 = new java.util.HashSet<testpackage.Class1>();
        result2.add(aClass);java.util.HashSet<testpackage.Class1> result1;
        result1 = new java.util.HashSet<testpackage.Class1>();
        java.util.HashSet<testpackage.Class1> toVisit1 = new java.util.HashSet<testpackage.Class1>();
        toVisit1.addAll(result2);
                
        while (toVisit1.size() > 0) {
        	testpackage.Class1 anElement1 = toVisit1.iterator().next();
            toVisit1.remove(anElement1);
                	
            /* This is the body. */
            java.util.Set<testpackage.Class1> bodyResult1 = anElement1.children;
            	
        	if (bodyResult1 != null) {
        		// TODO replace anElem with generated var.
        		for (testpackage.Class1 anElem : bodyResult1) {
        			if(!result1.contains(anElem)) {
                		result1.add(anElem);
                		toVisit1.add(anElem);
                	}
                	// no else.
                }
                // end for.
            }
            // no else.
        }
        // end while.
        return result1;
    }
}