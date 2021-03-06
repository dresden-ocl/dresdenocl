package testpackage.constraints;

/**
 * <p>Generated Aspect to enforce OCL constraint.</p>
 *
 * @author OCL22Java of Dresden OCL2 for Eclipse
 * @Generated
 */
public privileged aspect Class1_DefAspect_testCollectionMin {

    /**
     * <p>Defines the method testCollectionMin(java.util.Collection<Object> source) defined by the constraint
     * <code>context Class1
     *       def: testCollectionMin(source: Collection(OclAny)): OclAny = source ->min()</code></p>
     */
    public Object testpackage.Class1.testCollectionMin(java.util.Collection<Object> source) {
        return org.dresdenocl.tools.codegen.ocl2java.types.util.OclCollections.min(source);
    }
}