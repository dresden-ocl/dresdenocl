package testpackage.constraints;

/**
 * <p>Generated Aspect to enforce OCL constraint.</p>
 *
 * @author OCL22Java of Dresden OCL2 for Eclipse
 * @Generated
 */
public privileged aspect Class1_DefAspect_testBagIncluding01 {

    /**
     * <p>Defines the method testBagIncluding01(java.util.List<Object> source, Object arg01) defined by the constraint
     * <code>context Class1
     *       def: testBagIncluding01(source: Bag(OclAny), arg01: OclAny): Bag(OclAny) = source ->including(arg01)</code></p>
     */
    public java.util.List<Object> testpackage.Class1.testBagIncluding01(java.util.List<Object> source, Object arg01) {
        return org.dresdenocl.tools.codegen.ocl2java.types.util.OclBags.including(source, arg01);
    }
}