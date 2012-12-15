package testpackage.constraints;

/**
 * <p>Generated Aspect to enforce OCL constraint.</p>
 *
 * @author OCL22Java of Dresden OCL2 for Eclipse
 * @Generated
 */
public privileged aspect Class1_DefAspect_testCollectionIsEmpty {

    /**
     * <p>Defines the method testCollectionIsEmpty(java.util.Collection<Object> source) defined by the constraint
     * <code>context Class1
     *       def: testCollectionIsEmpty(source: Collection(OclAny)): Boolean = source ->isEmpty()</code></p>
     */
    public Boolean testpackage.Class1.testCollectionIsEmpty(java.util.Collection<Object> source) {
        return tudresden.ocl20.pivot.tools.codegen.ocl2java.types.util.OclCollections.isEmpty(source);
    }
}