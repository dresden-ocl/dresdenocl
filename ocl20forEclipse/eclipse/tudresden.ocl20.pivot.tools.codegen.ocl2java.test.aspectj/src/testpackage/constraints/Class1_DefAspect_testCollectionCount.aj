package testpackage.constraints;

/**
 * <p>Generated Aspect to enforce OCL constraint.</p>
 *
 * @author OCL22Java of Dresden OCL2 for Eclipse
 * @Generated
 */
public privileged aspect Class1_DefAspect_testCollectionCount {

    /**
     * <p>Defines the method testCollectionCount(java.util.Collection<Object> source, Object arg01) defined by the constraint
     * <code>context Class1
     *       def: testCollectionCount(source: Collection(OclAny), arg01: OclAny): 
      Integer =
    source ->count(arg01)</code></p>
     */
    public Integer testpackage.Class1.testCollectionCount(java.util.Collection<Object> source, Object arg01) {
        return tudresden.ocl20.pivot.tools.codegen.ocl2java.types.util.OclCollections.count(source, arg01);
    }
}