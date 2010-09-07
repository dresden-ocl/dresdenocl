package testpackage.constraints;

/**
 * <p>Generated Aspect to enforce OCL constraint.</p>
 *
 * @author OCL22Java of Dresden OCL2 for Eclipse
 * @Generated
 */
public privileged aspect DefAspect56 {

    /* Declares a new super class containing the new attribute or method. */
    declare parents : testpackage.Class1 extends testpackage.constraints.ExtendedClass1;
    
    /**
     * <p>Pointcut for all calls on {@link testpackage.Class1#testOclIsInvalid01()}.</p>
     */
    protected pointcut testOclIsInvalid01Caller(testpackage.Class1 aClass):
    	call(* testpackage.Class1.testOclIsInvalid01())
    	&& target(aClass);
    
    /**
     * <p>Defines the method testOclIsInvalid01() defined by the constraint
     * <code>context Class1
     *       def: testOclIsInvalid01(): Boolean =
    self.oclIsInvalid()</code></p>
     */
    Boolean around(testpackage.Class1 aClass): testOclIsInvalid01Caller(aClass) {
        Boolean result1;
        
        /* Check if the expression results in invalid. */
        try {
            /* DUMMY variable is necessary to form literals into a statement. */
            Object DUMMY = aClass; 
            result1 = false;
        }
        
        catch (Exception e) {
            result1 = true;
        }
    
        return result1;
    }
}