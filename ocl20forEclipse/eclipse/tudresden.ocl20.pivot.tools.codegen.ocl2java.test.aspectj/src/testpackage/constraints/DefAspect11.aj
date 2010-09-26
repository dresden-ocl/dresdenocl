package testpackage.constraints;

/**
 * <p>Generated Aspect to enforce OCL constraint.</p>
 *
 * @author OCL22Java of Dresden OCL2 for Eclipse
 * @Generated
 */
public privileged aspect DefAspect11 {

    /* Declares a new super class containing the new attribute or method. */
    declare parents : testpackage.Class1 extends testpackage.constraints.ExtendedClass1;
    
    /**
     * <p>Pointcut for all calls on {@link testpackage.Class1#testExistsIteratorExp01()}.</p>
     */
    protected pointcut testExistsIteratorExp01Caller(testpackage.Class1 aClass):
    	call(* testpackage.Class1.testExistsIteratorExp01())
    	&& target(aClass);
    
    /**
     * <p>Defines the method testExistsIteratorExp01() defined by the constraint
     * <code>context Class1
     *       def: testExistsIteratorExp01() : Boolean =
    Set{1, 2, 3}->exists(i: Integer | i = 2)</code></p>
     */
    Boolean around(testpackage.Class1 aClass): testExistsIteratorExp01Caller(aClass) {
        java.util.HashSet<Integer> collection1;
        collection1 = new java.util.HashSet<Integer>();
        
        collection1.add(new Integer(1));
        collection1.add(new Integer(2));
        collection1.add(new Integer(3));
        Boolean result1;
        result1 = false;
        
        /* Iterator Exists: Iterate and check, if any element fulfills the condition. */
        for (Integer i : collection1) {
            if (((Object) i).equals(new Integer(2))) {
                result1 = true;
                break;
            }
            // no else
        }
    
        return result1;
    }
}