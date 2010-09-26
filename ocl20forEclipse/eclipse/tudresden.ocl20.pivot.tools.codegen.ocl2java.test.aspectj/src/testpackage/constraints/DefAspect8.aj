package testpackage.constraints;

/**
 * <p>Generated Aspect to enforce OCL constraint.</p>
 *
 * @author OCL22Java of Dresden OCL2 for Eclipse
 * @Generated
 */
public privileged aspect DefAspect8 {

    /* Declares a new super class containing the new attribute or method. */
    declare parents : testpackage.Class1 extends testpackage.constraints.ExtendedClass1;
    
    /**
     * <p>Pointcut for all calls on {@link testpackage.Class1#testAnyIteratorExp01()}.</p>
     */
    protected pointcut testAnyIteratorExp01Caller(testpackage.Class1 aClass):
    	call(* testpackage.Class1.testAnyIteratorExp01())
    	&& target(aClass);
    
    /**
     * <p>Defines the method testAnyIteratorExp01() defined by the constraint
     * <code>context Class1
     *       def: testAnyIteratorExp01() : Integer =
    Set{1, 2, 3}->any(i: Integer | i = 2)</code></p>
     */
    Integer around(testpackage.Class1 aClass): testAnyIteratorExp01Caller(aClass) {
        java.util.HashSet<Integer> collection1;
        collection1 = new java.util.HashSet<Integer>();
        
        collection1.add(new Integer(1));
        collection1.add(new Integer(2));
        collection1.add(new Integer(3));
        Integer result1;
        result1 = null;
        
        /* Iterator Any: Iterate through the elements and return one element that fulfills the condition. */
        for (Integer i : collection1) {
            if (((Object) i).equals(new Integer(2))) {
                result1 = i;
                break;
            }
            // no else
        }
    
        return result1;
    }
}