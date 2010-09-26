package testpackage.constraints;

/**
 * <p>Generated Aspect to enforce OCL constraint.</p>
 *
 * @author OCL22Java of Dresden OCL2 for Eclipse
 * @Generated
 */
public privileged aspect DefAspect26 {

    /* Declares a new super class containing the new attribute or method. */
    declare parents : testpackage.Class1 extends testpackage.constraints.ExtendedClass1;
    
    /**
     * <p>Pointcut for all calls on {@link testpackage.Class1#testCollectionLiteralExp04()}.</p>
     */
    protected pointcut testCollectionLiteralExp04Caller(testpackage.Class1 aClass):
    	call(* testpackage.Class1.testCollectionLiteralExp04())
    	&& target(aClass);
    
    /**
     * <p>Defines the method testCollectionLiteralExp04() defined by the constraint
     * <code>context Class1
     *       def: testCollectionLiteralExp04(): 
      Sequence(Integer) =
    Sequence { 0, 1, 2 }</code></p>
     */
    java.util.List<Integer> around(testpackage.Class1 aClass): testCollectionLiteralExp04Caller(aClass) {
        java.util.ArrayList<Integer> collection1;
        collection1 = new java.util.ArrayList<Integer>();
        
        collection1.add(new Integer(0));
        collection1.add(new Integer(1));
        collection1.add(new Integer(2));
    
        return collection1;
    }
}