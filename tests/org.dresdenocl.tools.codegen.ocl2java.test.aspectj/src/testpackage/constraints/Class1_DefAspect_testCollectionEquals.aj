package testpackage.constraints;

/**
 * <p>Generated Aspect to enforce OCL constraint.</p>
 *
 * @author OCL22Java of Dresden OCL2 for Eclipse
 * @Generated
 */
public privileged aspect Class1_DefAspect_testCollectionEquals {

    /**
     * <p>Defines the method testCollectionEquals(java.util.Collection<Object> source, java.util.Collection<Object> arg01) defined by the constraint
     * <code>context Class1
     *       def: testCollectionEquals(source: Collection(OclAny), arg01: Collection(OclAny)): Boolean = source = arg01</code></p>
     */
    public Boolean testpackage.Class1.testCollectionEquals(java.util.Collection<Object> source, java.util.Collection<Object> arg01) {
        return org.dresdenocl.tools.codegen.ocl2java.types.util.OclCollections.equals(source, arg01);
    }
}