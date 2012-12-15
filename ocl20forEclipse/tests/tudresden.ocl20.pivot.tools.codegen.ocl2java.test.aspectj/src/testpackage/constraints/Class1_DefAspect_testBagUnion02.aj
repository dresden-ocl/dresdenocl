package testpackage.constraints;

/**
 * <p>Generated Aspect to enforce OCL constraint.</p>
 *
 * @author OCL22Java of Dresden OCL2 for Eclipse
 * @Generated
 */
public privileged aspect Class1_DefAspect_testBagUnion02 {

    /**
     * <p>Defines the method testBagUnion02(java.util.List<Object> source, java.util.List<Object> arg01) defined by the constraint
     * <code>context Class1
     *       def: testBagUnion02(source: Bag(OclAny), arg01: Bag(OclAny)): Bag(OclAny) = source ->union(arg01)</code></p>
     */
    public java.util.List<Object> testpackage.Class1.testBagUnion02(java.util.List<Object> source, java.util.List<Object> arg01) {
        return tudresden.ocl20.pivot.tools.codegen.ocl2java.types.util.OclBags.union(source, arg01);
    }
}