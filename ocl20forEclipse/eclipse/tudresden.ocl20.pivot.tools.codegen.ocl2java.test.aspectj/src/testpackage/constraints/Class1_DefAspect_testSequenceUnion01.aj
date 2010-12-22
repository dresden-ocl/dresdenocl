package testpackage.constraints;

/**
 * <p>Generated Aspect to enforce OCL constraint.</p>
 *
 * @author OCL22Java of Dresden OCL2 for Eclipse
 * @Generated
 */
public privileged aspect Class1_DefAspect_testSequenceUnion01 {

    /**
     * <p>Defines the method testSequenceUnion01(java.util.List<Object> source, java.util.List<Object> arg01) defined by the constraint
     * <code>context Class1
     *       def: testSequenceUnion01(source: Sequence(OclAny), arg01: Sequence(OclAny)): Sequence(OclAny) = source ->union(arg01)</code></p>
     */
    public java.util.List<Object> testpackage.Class1.testSequenceUnion01(java.util.List<Object> source, java.util.List<Object> arg01) {
        return tudresden.ocl20.pivot.tools.codegen.ocl2java.types.util.OclSequences.union(source, arg01);
    }
}