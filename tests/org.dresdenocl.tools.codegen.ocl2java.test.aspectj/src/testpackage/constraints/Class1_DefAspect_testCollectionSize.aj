package testpackage.constraints;

/**
 * <p>Generated Aspect to enforce OCL constraint.</p>
 *
 * @author OCL22Java of Dresden OCL2 for Eclipse
 * @Generated
 */
public privileged aspect Class1_DefAspect_testCollectionSize {

    /**
     * <p>Defines the method testCollectionSize(java.util.Collection<Object> source) defined by the constraint
     * <code>context Class1
     *       def: testCollectionSize(source: Collection(OclAny)):    Integer = source ->size()</code></p>
     */
    public Integer testpackage.Class1.testCollectionSize(java.util.Collection<Object> source) {
        return tudresden.ocl20.pivot.tools.codegen.ocl2java.types.util.OclCollections.size(source);
    }
}