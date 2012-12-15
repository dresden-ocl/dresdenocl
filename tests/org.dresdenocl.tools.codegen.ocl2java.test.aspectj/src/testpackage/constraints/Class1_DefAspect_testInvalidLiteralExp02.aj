package testpackage.constraints;

/**
 * <p>Generated Aspect to enforce OCL constraint.</p>
 *
 * @author OCL22Java of Dresden OCL2 for Eclipse
 * @Generated
 */
public privileged aspect Class1_DefAspect_testInvalidLiteralExp02 {

    /**
     * <p>Defines the method testInvalidLiteralExp02() defined by the constraint
     * <code>context Class1
     *       def: testInvalidLiteralExp02(): Boolean = invalid.oclIsInvalid()</code></p>
     */
    public Boolean testpackage.Class1.testInvalidLiteralExp02() {
        Boolean result1;

        /* Check if the expression results in invalid. */
        try {
            /* DUMMY variable is necessary to form literals into a statement. */
            Object DUMMY = (Object) tudresden.ocl20.pivot.tools.codegen.ocl2java.types.OclInvalidException.getInvalidLiteral(); 
            result1 = false;
        }

        catch (Exception e) {
            result1 = true;
        }

        return result1;
    }
}