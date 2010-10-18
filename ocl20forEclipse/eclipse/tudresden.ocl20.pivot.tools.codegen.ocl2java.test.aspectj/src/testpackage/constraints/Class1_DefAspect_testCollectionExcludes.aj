package testpackage.constraints;

/**
 * <p>Generated Aspect to enforce OCL constraint.</p>
 *
 * @author OCL22Java of Dresden OCL2 for Eclipse
 * @Generated
 */
public privileged aspect Class1_DefAspect_testCollectionExcludes {

    /**
     * <p>Defines the method testCollectionExcludes(java.util.Collection<Object> source, Object arg01) defined by the constraint
     * <code>context Class1
     *       def: testCollectionExcludes(source: Collection(OclAny), arg01: OclAny): Boolean =
    source ->excludes(arg01)</code></p>
     */
    public Boolean testpackage.Class1.testCollectionExcludes(java.util.Collection<Object> source, Object arg01) {
        return tudresden.ocl20.pivot.tools.codegen.ocl2java.types.util.OclCollections.excludes(source, arg01);
    }
}