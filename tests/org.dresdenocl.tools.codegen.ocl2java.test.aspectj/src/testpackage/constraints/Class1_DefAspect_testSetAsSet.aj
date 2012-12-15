package testpackage.constraints;

/**
 * <p>Generated Aspect to enforce OCL constraint.</p>
 *
 * @author OCL22Java of Dresden OCL2 for Eclipse
 * @Generated
 */
public privileged aspect Class1_DefAspect_testSetAsSet {

    /**
     * <p>Defines the method testSetAsSet(java.util.Set<Object> source) defined by the constraint
     * <code>context Class1
     *       def: testSetAsSet(source: Set(OclAny)):    Set(OclAny) = source ->asSet()</code></p>
     */
    public java.util.Set<Object> testpackage.Class1.testSetAsSet(java.util.Set<Object> source) {
        return org.dresdenocl.tools.codegen.ocl2java.types.util.OclSets.asSet(source);
    }
}