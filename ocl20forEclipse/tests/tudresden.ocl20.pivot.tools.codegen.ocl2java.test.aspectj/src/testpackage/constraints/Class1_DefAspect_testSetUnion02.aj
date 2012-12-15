package testpackage.constraints;

/**
 * <p>Generated Aspect to enforce OCL constraint.</p>
 *
 * @author OCL22Java of Dresden OCL2 for Eclipse
 * @Generated
 */
public privileged aspect Class1_DefAspect_testSetUnion02 {

    /**
     * <p>Defines the method testSetUnion02(java.util.Set<Object> source, java.util.List<Object> arg01) defined by the constraint
     * <code>context Class1
     *       def: testSetUnion02(source: Set(OclAny), arg01: Bag(OclAny)): Bag(OclAny) = source ->union(arg01)</code></p>
     */
    public java.util.List<Object> testpackage.Class1.testSetUnion02(java.util.Set<Object> source, java.util.List<Object> arg01) {
        return tudresden.ocl20.pivot.tools.codegen.ocl2java.types.util.OclSets.unionWithBag(source, arg01);
    }
}