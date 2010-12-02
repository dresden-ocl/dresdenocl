package testpackage.constraints;

/**
 * <p>Generated Aspect to enforce OCL constraint.</p>
 *
 * @author OCL22Java of Dresden OCL2 for Eclipse
 * @Generated
 */
public privileged aspect Class1_DefAspect_testCollectionNotEmpty {

    /**
     * <p>Defines the method testCollectionNotEmpty(java.util.Collection<Object> source) defined by the constraint
     * <code>context Class1
     *       def: testCollectionNotEmpty(source: Collection(OclAny)): Boolean =
    source ->notEmpty()</code></p>
     */
    public Boolean testpackage.Class1.testCollectionNotEmpty(java.util.Collection<Object> source) {
        return tudresden.ocl20.pivot.tools.codegen.ocl2java.types.util.OclCollections.notEmpty(source);
    }
}