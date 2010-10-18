package testpackage.constraints;

/**
 * <p>Generated Aspect to enforce OCL constraint.</p>
 *
 * @author OCL22Java of Dresden OCL2 for Eclipse
 * @Generated
 */
public privileged aspect Class1_DefAspect_testSequenceAsSet {

    /**
     * <p>Defines the method testSequenceAsSet(java.util.List<Object> source) defined by the constraint
     * <code>context Class1
     *       def: testSequenceAsSet(source: Sequence(OclAny)): 
      Set(OclAny) =
    source ->asSet()</code></p>
     */
    public java.util.Set<Object> testpackage.Class1.testSequenceAsSet(java.util.List<Object> source) {
        return tudresden.ocl20.pivot.tools.codegen.ocl2java.types.util.OclSequences.asSet(source);
    }
}