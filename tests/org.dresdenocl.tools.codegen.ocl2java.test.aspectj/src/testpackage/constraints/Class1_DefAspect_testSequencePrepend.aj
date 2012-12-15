package testpackage.constraints;

/**
 * <p>Generated Aspect to enforce OCL constraint.</p>
 *
 * @author OCL22Java of Dresden OCL2 for Eclipse
 * @Generated
 */
public privileged aspect Class1_DefAspect_testSequencePrepend {

    /**
     * <p>Defines the method testSequencePrepend(java.util.List<Object> source, Object arg01) defined by the constraint
     * <code>context Class1
     *       def: testSequencePrepend(source: Sequence(OclAny), arg01: OclAny): Sequence(OclAny) = source ->prepend(arg01)</code></p>
     */
    public java.util.List<Object> testpackage.Class1.testSequencePrepend(java.util.List<Object> source, Object arg01) {
        return tudresden.ocl20.pivot.tools.codegen.ocl2java.types.util.OclSequences.prepend(source, arg01);
    }
}