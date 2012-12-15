package testpackage.constraints;

/**
 * <p>Generated Aspect to enforce OCL constraint.</p>
 *
 * @author OCL22Java of Dresden OCL2 for Eclipse
 * @Generated
 */
public privileged aspect Class1_DefAspect_testSetExcluding01 {

    /**
     * <p>Defines the method testSetExcluding01(java.util.Set<Object> source, Object arg01) defined by the constraint
     * <code>context Class1
     *       def: testSetExcluding01(source: Set(OclAny), arg01: OclAny): Set(OclAny) = source ->excluding(arg01)</code></p>
     */
    public java.util.Set<Object> testpackage.Class1.testSetExcluding01(java.util.Set<Object> source, Object arg01) {
        return org.dresdenocl.tools.codegen.ocl2java.types.util.OclSets.excluding(source, arg01);
    }
}