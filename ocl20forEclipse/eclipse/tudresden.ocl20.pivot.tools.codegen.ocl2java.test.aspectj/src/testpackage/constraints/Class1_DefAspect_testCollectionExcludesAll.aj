package testpackage.constraints;

/**
 * <p>Generated Aspect to enforce OCL constraint.</p>
 *
 * @author OCL22Java of Dresden OCL2 for Eclipse
 * @Generated
 */
public privileged aspect Class1_DefAspect_testCollectionExcludesAll {

    /**
     * <p>Defines the method testCollectionExcludesAll(java.util.Collection<Object> source, java.util.Collection<Object> arg01) defined by the constraint
     * <code>context Class1
     *       def: testCollectionExcludesAll(source: Collection(OclAny), arg01: Collection(OclAny)): Boolean =
    source ->excludesAll(arg01)</code></p>
     */
    public Boolean testpackage.Class1.testCollectionExcludesAll(java.util.Collection<Object> source, java.util.Collection<Object> arg01) {
        return tudresden.ocl20.pivot.tools.codegen.ocl2java.types.util.OclCollections.excludesAll(source, arg01);
    }
}