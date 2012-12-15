package testpackage.constraints;

/**
 * <p>Generated Aspect to enforce OCL constraint.</p>
 *
 * @author OCL22Java of Dresden OCL2 for Eclipse
 * @Generated
 */
public privileged aspect Class1_DefAspect_testSetCount01 {

    /**
     * <p>Defines the method testSetCount01(java.util.Set<Object> source, Object arg01) defined by the constraint
     * <code>context Class1
     *       def: testSetCount01(source: Set(OclAny), arg01: OclAny): Integer = source ->count(arg01)</code></p>
     */
    public Integer testpackage.Class1.testSetCount01(java.util.Set<Object> source, Object arg01) {
        return tudresden.ocl20.pivot.tools.codegen.ocl2java.types.util.OclSets.count(source, arg01);
    }
}