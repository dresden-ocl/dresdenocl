package testpackage.constraints;

/**
 * <p>Generated Aspect to enforce OCL constraint.</p>
 *
 * @author OCL22Java of Dresden OCL2 for Eclipse
 * @Generated
 */
public privileged aspect Class1_DefAspect_testCollectionAsSet {

    /**
     * <p>Defines the method testCollectionAsSet(java.util.Collection<Object> source) defined by the constraint
     * <code>context Class1
     *       def: testCollectionAsSet(source: Collection(OclAny)):    Set(OclAny) = source ->asSet()</code></p>
     */
    public java.util.Set<Object> testpackage.Class1.testCollectionAsSet(java.util.Collection<Object> source) {
        return tudresden.ocl20.pivot.tools.codegen.ocl2java.types.util.OclCollections.asSet(source);
    }
}