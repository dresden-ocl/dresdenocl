package testpackage.constraints;

/**
 * <p>Generated Aspect to enforce OCL constraint.</p>
 *
 * @author OCL22Java of Dresden OCL2 for Eclipse
 * @Generated
 */
public privileged aspect Class1_DefAspect_testBagUnion01 {

    /**
     * <p>Defines the method testBagUnion01(java.util.List<Object> source, java.util.Set<Object> arg01) defined by the constraint
     * <code>context Class1
     *       def: testBagUnion01(source: Bag(OclAny), arg01: Set(OclAny)): Bag(OclAny) = source ->union(arg01)</code></p>
     */
    public java.util.List<Object> testpackage.Class1.testBagUnion01(java.util.List<Object> source, java.util.Set<Object> arg01) {
        return org.dresdenocl.tools.codegen.ocl2java.types.util.OclBags.union(source, arg01);
    }
}