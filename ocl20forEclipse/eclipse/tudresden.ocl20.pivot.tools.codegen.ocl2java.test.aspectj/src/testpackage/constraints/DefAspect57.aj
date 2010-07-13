package testpackage.constraints;

/**
 * <p>Generated Aspect to enforce OCL constraint.</p>
 *
 * @author OCL22Java of Dresden OCL2 for Eclipse
 * @Generated
 */
public privileged aspect DefAspect57 {

    /* Declares a new super class containing the new attribute or method. */
    declare parents : testpackage.Class1 extends testpackage.constraints.ExtendedClass1;
    
    /**
     * <p>Pointcut for all calls on {@link testpackage.Class1#testOclIsInvalid02()}.</p>
     */
    protected pointcut testOclIsInvalid02Caller(testpackage.Class1 aClass):
    	call(* testpackage.Class1.testOclIsInvalid02())
    	&& target(aClass);
    
    /**
     * <p>Defines the method testOclIsInvalid02() defined by the constraint
     * <code>context Class1
     *       def: testOclIsInvalid02 = Sequence{}.first().oclIsInvalid()</code></p>
     */
    Boolean around(testpackage.Class1 aClass): testOclIsInvalid02Caller(aClass) {
        java.util.ArrayList collection1;
        collection1 = new java.util.ArrayList();

        Boolean result1;
        
        /* Check if the expression results in invalid. */
        try {
            /* DUMMY variable is necessary to form literals into a statement. */
            Object DUMMY = tudresden.ocl20.pivot.ocl22java.types.util.OclSequences.first(collection1); 
            result1 = false;
        }
        
        catch (Exception e) {
            result1 = true;
        }
    
        return result1;
    }
}