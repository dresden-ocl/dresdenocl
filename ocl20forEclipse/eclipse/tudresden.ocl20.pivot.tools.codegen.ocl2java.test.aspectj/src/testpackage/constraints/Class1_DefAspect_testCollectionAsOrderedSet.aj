package testpackage.constraints;

/**
 * <p>Generated Aspect to enforce OCL constraint.</p>
 *
 * @author OCL22Java of Dresden OCL2 for Eclipse
 * @Generated
 */
public privileged aspect Class1_DefAspect_testCollectionAsOrderedSet {

    /**
     * <p>Defines the method testCollectionAsOrderedSet(java.util.Collection<Object> source) defined by the constraint
     * <code>context Class1
     *       def: testCollectionAsOrderedSet(source: Collection(OclAny)): 
      OrderedSet(OclAny) =
    source ->asOrderedSet()</code></p>
     */
    public java.util.List<Object> testpackage.Class1.testCollectionAsOrderedSet(java.util.Collection<Object> source) {
        return tudresden.ocl20.pivot.tools.codegen.ocl2java.types.util.OclCollections.asOrderedSet(source);
    }
}