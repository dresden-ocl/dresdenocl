package testpackage.constraints;

/**
 * <p>Generated Aspect to enforce OCL constraint.</p>
 *
 * @author OCL22Java of Dresden OCL2 for Eclipse
 * @Generated
 */
public privileged aspect Class1_DefAspect_testSetIntersection01 {

    /**
     * <p>Defines the method testSetIntersection01(java.util.Set<Object> source, java.util.Set<Object> arg01) defined by the constraint
     * <code>context Class1
     *       def: testSetIntersection01(source: Set(OclAny), arg01: Set(OclAny)): Set(OclAny) = source ->intersection(arg01)</code></p>
     */
    public java.util.Set<Object> testpackage.Class1.testSetIntersection01(java.util.Set<Object> source, java.util.Set<Object> arg01) {
        return org.dresdenocl.tools.codegen.ocl2java.types.util.OclSets.intersection(source, arg01);
    }
}