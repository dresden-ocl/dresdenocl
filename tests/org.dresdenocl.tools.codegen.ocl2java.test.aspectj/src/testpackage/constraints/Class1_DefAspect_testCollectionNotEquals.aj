package testpackage.constraints;

/**
 * <p>Generated Aspect to enforce OCL constraint.</p>
 *
 * @author OCL22Java of Dresden OCL2 for Eclipse
 * @Generated
 */
public privileged aspect Class1_DefAspect_testCollectionNotEquals {

    /**
     * <p>Defines the method testCollectionNotEquals(java.util.Collection<Object> source, java.util.Collection<Object> arg01) defined by the constraint
     * <code>context Class1
     *       def: testCollectionNotEquals(source: Collection(OclAny), arg01: Collection(OclAny)): Boolean = source <> arg01</code></p>
     */
    public Boolean testpackage.Class1.testCollectionNotEquals(java.util.Collection<Object> source, java.util.Collection<Object> arg01) {
        return org.dresdenocl.tools.codegen.ocl2java.types.util.OclCollections.notEquals(source, arg01);
    }
}