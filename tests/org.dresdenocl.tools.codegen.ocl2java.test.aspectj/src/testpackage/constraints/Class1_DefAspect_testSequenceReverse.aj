package testpackage.constraints;

/**
 * <p>Generated Aspect to enforce OCL constraint.</p>
 *
 * @author OCL22Java of Dresden OCL2 for Eclipse
 * @Generated
 */
public privileged aspect Class1_DefAspect_testSequenceReverse {

    /**
     * <p>Defines the method testSequenceReverse(java.util.List<Object> source) defined by the constraint
     * <code>context Class1
     *       def: testSequenceReverse(source: Sequence(OclAny)): Sequence(OclAny) = source ->reverse()</code></p>
     */
    public java.util.List<Object> testpackage.Class1.testSequenceReverse(java.util.List<Object> source) {
        return org.dresdenocl.tools.codegen.ocl2java.types.util.OclSequences.reverse(source);
    }
}