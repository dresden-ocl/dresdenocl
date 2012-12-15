package testpackage.constraints;

/**
 * <p>Generated Aspect to enforce OCL constraint.</p>
 *
 * @author OCL22Java of Dresden OCL2 for Eclipse
 * @Generated
 */
public privileged aspect Class1_DefAspect_testInvalidLiteralExp01 {

    /**
     * <p>Defines the method testInvalidLiteralExp01() defined by the constraint
     * <code>context Class1
     *       def: testInvalidLiteralExp01(): Integer = invalid</code></p>
     */
    public Integer testpackage.Class1.testInvalidLiteralExp01() {
        return (Integer) tudresden.ocl20.pivot.tools.codegen.ocl2java.types.OclInvalidException.getInvalidLiteral();
    }
}