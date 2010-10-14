package testpackage.constraints;

/**
 * <p>Generated Aspect to enforce OCL constraint.</p>
 *
 * @author OCL22Java of Dresden OCL2 for Eclipse
 * @Generated
 */
public privileged aspect testForAllIteratorExp02 {

    /* Declares a new super class containing the new attribute or method. */
    declare parents : testpackage.Class1 extends testpackage.constraints.ExtendedClass1;
    
    /**
     * <p>Pointcut for all calls on {@link testpackage.Class1#testForAllIteratorExp02()}.</p>
     */
    protected pointcut testForAllIteratorExp02Caller(testpackage.Class1 aClass):
    	call(* testpackage.Class1.testForAllIteratorExp02())
    	&& target(aClass);
    
    /**
     * <p>Defines the method testForAllIteratorExp02() defined by the constraint
     * <code>context Class1
     *       def: testForAllIteratorExp02() : Boolean =
    Set{ 1, 2, 3}->forAll(i1: Integer, i2: Integer | i1 + i2 = 3)</code></p>
     */
    Boolean around(testpackage.Class1 aClass): testForAllIteratorExp02Caller(aClass) {
        java.util.HashSet<Integer> collection1;
        collection1 = new java.util.HashSet<Integer>();
        
        collection1.add(new Integer(1));
        collection1.add(new Integer(2));
        collection1.add(new Integer(3));
        Boolean result1;
        result1 = true;
        
        /* Iterator ForAll: Iterate and check, if all elements fulfill the condition. */
        for (Integer i1 : collection1) {
            java.util.HashSet<Integer> collection2;
            collection2 = new java.util.HashSet<Integer>();
            
            collection2.add(new Integer(1));
            collection2.add(new Integer(2));
            collection2.add(new Integer(3));
            Boolean result2;
            result2 = true;
            
            /* Iterator ForAll: Iterate and check, if all elements fulfill the condition. */
            for (Integer i2 : collection2) {
                if (!((Object) (i1 + i2)).equals(new Integer(3))) {
                    result2 = false;
                    break;
                }
                // no else
            }
        
            if (!result2) {
                result1 = false;
                break;
            }
            // no else
        }
    
        return result1;
    }
}