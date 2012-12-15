package testpackage.constraints;

/**
 * <p>Generated Aspect to enforce OCL constraint.</p>
 *
 * @author OCL22Java of Dresden OCL2 for Eclipse
 * @Generated
 */
public privileged aspect Class1_DefAspect_testSetIncluding01 {

    /**
     * <p>Defines the method testSetIncluding01(java.util.Set<Object> source, Object arg01) defined by the constraint
     * <code>context Class1
     *       def: testSetIncluding01(source: Set(OclAny), arg01: OclAny): Set(OclAny) = source ->including(arg01)</code></p>
     */
    public java.util.Set<Object> testpackage.Class1.testSetIncluding01(java.util.Set<Object> source, Object arg01) {
        return tudresden.ocl20.pivot.tools.codegen.ocl2java.types.util.OclSets.including(source, arg01);
    }
}