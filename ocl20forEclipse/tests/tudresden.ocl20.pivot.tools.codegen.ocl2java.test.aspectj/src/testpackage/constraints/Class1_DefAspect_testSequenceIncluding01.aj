package testpackage.constraints;

/**
 * <p>Generated Aspect to enforce OCL constraint.</p>
 *
 * @author OCL22Java of Dresden OCL2 for Eclipse
 * @Generated
 */
public privileged aspect Class1_DefAspect_testSequenceIncluding01 {

    /**
     * <p>Defines the method testSequenceIncluding01(java.util.List<Object> source, Object arg01) defined by the constraint
     * <code>context Class1
     *       def: testSequenceIncluding01(source: Sequence(OclAny), arg01: OclAny): Sequence(OclAny) = source ->including(arg01)</code></p>
     */
    public java.util.List<Object> testpackage.Class1.testSequenceIncluding01(java.util.List<Object> source, Object arg01) {
        return tudresden.ocl20.pivot.tools.codegen.ocl2java.types.util.OclSequences.including(source, arg01);
    }
}