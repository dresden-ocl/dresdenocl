package testpackage.constraints;

/**
 * <p>Generated Aspect to enforce OCL constraint.</p>
 *
 * @author OCL22Java of Dresden OCL2 for Eclipse
 * @Generated
 */
public privileged aspect DefAspect25 {

    /* Declares a new super class containing the new attribute or method. */
    declare parents : testpackage.Class1 extends testpackage.constraints.ExtendedClass1;
    
    /**
     * <p>Pointcut for all calls on {@link testpackage.Class1#testIfExp01()}.</p>
     */
    protected pointcut testIfExp01Caller(testpackage.Class1 aClass):
    	call(* testpackage.Class1.testIfExp01())
    	&& target(aClass);
    
    /**
     * <p>Defines the method testIfExp01() defined by the constraint
     * <code>context Class1
     *       def: testIfExp01(): Integer =
    if (true)
      then
    	1
      else -1
    endif</code></p>
     */
    Integer around(testpackage.Class1 aClass): testIfExp01Caller(aClass) {
        Integer ifExpResult1;
        
        if (new Boolean(true)) {
            ifExpResult1 = new Integer(1);
        } else {
            ifExpResult1 = -(new Integer(1));
        }
    
        return ifExpResult1;
    }
}