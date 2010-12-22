package testpackage.constraints;

/**
 * <p>Generated Aspect to enforce OCL constraint.</p>
 *
 * @author OCL22Java of Dresden OCL2 for Eclipse
 * @Generated
 */
public privileged aspect Class1_DefAspect_testSequenceAsBag {

    /**
     * <p>Defines the method testSequenceAsBag(java.util.List<Object> source) defined by the constraint
     * <code>context Class1
     *       def: testSequenceAsBag(source: Sequence(OclAny)):    Bag(OclAny) = source ->asBag()</code></p>
     */
    public java.util.List<Object> testpackage.Class1.testSequenceAsBag(java.util.List<Object> source) {
        return tudresden.ocl20.pivot.tools.codegen.ocl2java.types.util.OclSequences.asBag(source);
    }
}