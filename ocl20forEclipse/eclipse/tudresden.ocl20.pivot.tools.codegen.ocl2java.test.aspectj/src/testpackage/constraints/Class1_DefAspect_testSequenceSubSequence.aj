package testpackage.constraints;

/**
 * <p>Generated Aspect to enforce OCL constraint.</p>
 *
 * @author OCL22Java of Dresden OCL2 for Eclipse
 * @Generated
 */
public privileged aspect Class1_DefAspect_testSequenceSubSequence {

    /**
     * <p>Defines the method testSequenceSubSequence(java.util.List<Object> source, Integer arg01, Integer arg02) defined by the constraint
     * <code>context Class1
     *       def: testSequenceSubSequence(source: Sequence(OclAny), arg01: Integer, arg02: Integer): Sequence(OclAny) =
    source ->subSequence(arg01, arg02)</code></p>
     */
    public java.util.List<Object> testpackage.Class1.testSequenceSubSequence(java.util.List<Object> source, Integer arg01, Integer arg02) {
        return tudresden.ocl20.pivot.tools.codegen.ocl2java.types.util.OclSequences.subSequence(source, arg01, arg02);
    }
}