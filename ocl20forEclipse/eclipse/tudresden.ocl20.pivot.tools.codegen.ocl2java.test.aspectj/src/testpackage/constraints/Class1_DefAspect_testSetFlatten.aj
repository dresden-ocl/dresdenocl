package testpackage.constraints;

/**
 * <p>Generated Aspect to enforce OCL constraint.</p>
 *
 * @author OCL22Java of Dresden OCL2 for Eclipse
 * @Generated
 */
public privileged aspect Class1_DefAspect_testSetFlatten {

    /**
     * <p>Defines the method testSetFlatten(java.util.Set<Object> source) defined by the constraint
     * <code>context Class1
     *       def: testSetFlatten(source: Set(OclAny)): Set(OclAny) =
    source ->flatten()</code></p>
     */
    public java.util.Set<Object> testpackage.Class1.testSetFlatten(java.util.Set<Object> source) {
        return (java.util.Set<Object>) tudresden.ocl20.pivot.tools.codegen.ocl2java.types.util.OclSets.flatten(source);
    }
}