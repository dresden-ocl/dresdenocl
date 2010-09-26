package testpackage.constraints;

/**
 * <p>Generated Aspect to enforce OCL constraint.</p>
 *
 * @author OCL22Java of Dresden OCL2 for Eclipse
 * @Generated
 */
public privileged aspect DefAspect31 {

    /* Declares a new super class containing the new attribute or method. */
    declare parents : testpackage.Class1 extends testpackage.constraints.ExtendedClass1;
    
    /**
     * <p>Pointcut for all calls on {@link testpackage.Class1#testInvalidLiteralExp02()}.</p>
     */
    protected pointcut testInvalidLiteralExp02Caller(testpackage.Class1 aClass):
    	call(* testpackage.Class1.testInvalidLiteralExp02())
    	&& target(aClass);
    
    /**
     * <p>Defines the method testInvalidLiteralExp02() defined by the constraint
     * <code>context Class1
     *       def: testInvalidLiteralExp02(): Boolean =
    invalid.oclIsInvalid()</code></p>
     */
    Boolean around(testpackage.Class1 aClass): testInvalidLiteralExp02Caller(aClass) {
        Boolean result1;
        
        /* Check if the expression results in invalid. */
        try {
            /* DUMMY variable is necessary to form literals into a statement. */
            Object DUMMY = /* (TODO Probably add Type Cast here.) */ tudresden.ocl20.pivot.tools.codegen.ocl2java.types.OclInvalidException.getInvalidLiteral(); 
            result1 = false;
        }
        
        catch (Exception e) {
            result1 = true;
        }
    
        return result1;
    }
}