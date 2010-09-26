package testpackage.constraints;

/**
 * <p>Generated Aspect to enforce OCL constraint.</p>
 *
 * @author OCL22Java of Dresden OCL2 for Eclipse
 * @Generated
 */
public privileged aspect DefAspect24 {

    /* Declares a new super class containing the new attribute or method. */
    declare parents : testpackage.Class1 extends testpackage.constraints.ExtendedClass1;
    
    /**
     * <p>Pointcut for all calls on {@link testpackage.Class1#testCollectionLiteralExp02()}.</p>
     */
    protected pointcut testCollectionLiteralExp02Caller(testpackage.Class1 aClass):
    	call(* testpackage.Class1.testCollectionLiteralExp02())
    	&& target(aClass);
    
    /**
     * <p>Defines the method testCollectionLiteralExp02() defined by the constraint
     * <code>context Class1
     *       def: testCollectionLiteralExp02(): 
      Bag(Integer) =
    Bag { 0 .. 2 }</code></p>
     */
    java.util.List<Integer> around(testpackage.Class1 aClass): testCollectionLiteralExp02Caller(aClass) {
        java.util.ArrayList<Integer> collection1;
        collection1 = new java.util.ArrayList<Integer>();
        
        /* TODO: Auto-generated initialization does only work for numeric values. */
        for (Integer index1 = new Integer(0); index1 <= new Integer(2); index1++) {
            collection1.add(index1);
        }
    
        return collection1;
    }
}