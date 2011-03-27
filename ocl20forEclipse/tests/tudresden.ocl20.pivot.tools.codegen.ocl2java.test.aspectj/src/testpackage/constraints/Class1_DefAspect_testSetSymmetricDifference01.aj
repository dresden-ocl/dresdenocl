package testpackage.constraints;

/**
 * <p>Generated Aspect to enforce OCL constraint.</p>
 *
 * @author OCL22Java of Dresden OCL2 for Eclipse
 * @Generated
 */
public privileged aspect Class1_DefAspect_testSetSymmetricDifference01 {

    /**
     * <p>Defines the method testSetSymmetricDifference01(java.util.Set<Object> source, java.util.Set<Object> arg01) defined by the constraint
     * <code>context Class1
     *       def: testSetSymmetricDifference01(source: Set(OclAny), arg01: Set(OclAny)): Set(OclAny) = source ->symmetricDifference(arg01)</code></p>
     */
    public java.util.Set<Object> testpackage.Class1.testSetSymmetricDifference01(java.util.Set<Object> source, java.util.Set<Object> arg01) {
        return tudresden.ocl20.pivot.tools.codegen.ocl2java.types.util.OclSets.symmetricDifference(source, arg01);
    }
}