package testpackage.constraints;

/**
 * <p>Generated Aspect to enforce OCL constraint.</p>
 *
 * @author OCL22Java of Dresden OCL2 for Eclipse
 * @Generated
 */
public privileged aspect Class1_DefAspect_testSequenceFlatten {

    /**
     * <p>Defines the method testSequenceFlatten(java.util.List<Object> source) defined by the constraint
     * <code>context Class1
     *       def: testSequenceFlatten(source: Sequence(OclAny)): Sequence(OclAny) = source ->flatten()</code></p>
     */
    public java.util.List<Object> testpackage.Class1.testSequenceFlatten(java.util.List<Object> source) {
        return (java.util.List<Object>) tudresden.ocl20.pivot.tools.codegen.ocl2java.types.util.OclSequences.flatten(source);
    }
}