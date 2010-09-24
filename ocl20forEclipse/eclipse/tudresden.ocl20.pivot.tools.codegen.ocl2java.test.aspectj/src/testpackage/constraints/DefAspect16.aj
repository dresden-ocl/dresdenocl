package testpackage.constraints;

/**
 * <p>Generated Aspect to enforce OCL constraint.</p>
 *
 * @author OCL22Java of Dresden OCL2 for Eclipse
 * @Generated
 */
public privileged aspect DefAspect16 {

    /* Declares a new super class containing the new attribute or method. */
    declare parents : testpackage.Class1 extends testpackage.constraints.ExtendedClass1;
    
    /**
     * <p>Pointcut for all calls on {@link testpackage.Class1#testCollectionLiteralExp05()}.</p>
     */
    protected pointcut testCollectionLiteralExp05Caller(testpackage.Class1 aClass):
    	call(* testpackage.Class1.testCollectionLiteralExp05())
    	&& target(aClass);
    
    /**
     * <p>Defines the method testCollectionLiteralExp05() defined by the constraint
     * <code>context Class1
     *       def: testCollectionLiteralExp05(): 
      Set(Integer) =
    Set { 0, 1, 2 }</code></p>
     */
    java.util.Set<Integer> around(testpackage.Class1 aClass): testCollectionLiteralExp05Caller(aClass) {
        java.util.HashSet<Integer> collection1;
        collection1 = new java.util.HashSet<Integer>();
        
        collection1.add(new Integer(0));
        collection1.add(new Integer(1));
        collection1.add(new Integer(2));
    
        return collection1;
    }
}