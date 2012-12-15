package testpackage.constraints;

/**
 * <p>Generated Aspect to enforce OCL constraint.</p>
 *
 * @author OCL22Java of Dresden OCL2 for Eclipse
 * @Generated
 */
public privileged aspect Class1_DefAspect_testCollectionFlatten {

    /**
     * <p>Defines the method testCollectionFlatten(java.util.Collection<Object> source) defined by the constraint
     * <code>context Class1
     *       def: testCollectionFlatten(source: Collection(OclAny)): Collection(OclAny) = source ->flatten()</code></p>
     */
    public java.util.Collection<Object> testpackage.Class1.testCollectionFlatten(java.util.Collection<Object> source) {
        return (java.util.Collection<Object>) tudresden.ocl20.pivot.tools.codegen.ocl2java.types.util.OclCollections.flatten(source);
    }
}