package testpackage.constraints;

/**
 * <p>Generated Aspect to enforce OCL constraint.</p>
 *
 * @author OCL22Java of Dresden OCL2 for Eclipse
 * @Generated
 */
public privileged aspect Class1_DefAspect_testCollectionAsSequence {

    /**
     * <p>Defines the method testCollectionAsSequence(java.util.Collection<Object> source) defined by the constraint
     * <code>context Class1
     *       def: testCollectionAsSequence(source: Collection(OclAny)): 
      Sequence(OclAny) =
    source ->asSequence()</code></p>
     */
    public java.util.List<Object> testpackage.Class1.testCollectionAsSequence(java.util.Collection<Object> source) {
        return tudresden.ocl20.pivot.tools.codegen.ocl2java.types.util.OclCollections.asSequence(source);
    }
}