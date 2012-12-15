package testpackage.constraints;

/**
 * <p>Generated Aspect to enforce OCL constraint.</p>
 *
 * @author OCL22Java of Dresden OCL2 for Eclipse
 * @Generated
 */
public privileged aspect Class1_DefAspect_testCollectionProduct {

    /**
     * <p>Defines the method testCollectionProduct(java.util.Collection<Object> source, java.util.Collection<Object> arg01) defined by the constraint
     * <code>context Class1
     *       def: testCollectionProduct(source: Collection(OclAny), arg01: Collection(OclAny)):    Integer = source ->product(arg01) ->size()</code></p>
     */
    public Integer testpackage.Class1.testCollectionProduct(java.util.Collection<Object> source, java.util.Collection<Object> arg01) {
        return org.dresdenocl.tools.codegen.ocl2java.types.util.OclCollections.size(org.dresdenocl.tools.codegen.ocl2java.types.util.OclCollections.product(source, arg01));
    }
}