package testpackage.constraints;

/**
 * <p>Generated Aspect to enforce OCL constraint.</p>
 *
 * @author OCL22Java of Dresden OCL2 for Eclipse
 * @Generated
 */
public privileged aspect Class1_DefAspect_testBagExcluding01 {

    /**
     * <p>Defines the method testBagExcluding01(java.util.List<Object> source, Object arg01) defined by the constraint
     * <code>context Class1
     *       def: testBagExcluding01(source: Bag(OclAny), arg01: OclAny): Bag(OclAny) = source ->excluding(arg01)</code></p>
     */
    public java.util.List<Object> testpackage.Class1.testBagExcluding01(java.util.List<Object> source, Object arg01) {
        return tudresden.ocl20.pivot.tools.codegen.ocl2java.types.util.OclBags.excluding(source, arg01);
    }
}