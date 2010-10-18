package testpackage.constraints;

/**
 * <p>Generated Aspect to enforce OCL constraint.</p>
 *
 * @author OCL22Java of Dresden OCL2 for Eclipse
 * @Generated
 */
public privileged aspect Class1_DefAspect_testOrderedSetAsSequence {

    /**
     * <p>Defines the method testOrderedSetAsSequence(java.util.List<Object> source) defined by the constraint
     * <code>context Class1
     *       def: testOrderedSetAsSequence(source: OrderedSet(OclAny)): 
      Sequence(OclAny) =
    source ->asSequence()</code></p>
     */
    public java.util.List<Object> testpackage.Class1.testOrderedSetAsSequence(java.util.List<Object> source) {
        return tudresden.ocl20.pivot.tools.codegen.ocl2java.types.util.OclOrderedSets.asSequence(source);
    }
}