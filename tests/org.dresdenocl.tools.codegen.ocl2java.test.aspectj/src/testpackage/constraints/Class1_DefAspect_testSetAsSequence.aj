package testpackage.constraints;

/**
 * <p>Generated Aspect to enforce OCL constraint.</p>
 *
 * @author OCL22Java of Dresden OCL2 for Eclipse
 * @Generated
 */
public privileged aspect Class1_DefAspect_testSetAsSequence {

    /**
     * <p>Defines the method testSetAsSequence(java.util.Set<Object> source) defined by the constraint
     * <code>context Class1
     *       def: testSetAsSequence(source: Set(OclAny)):    Sequence(OclAny) = source ->asSequence()</code></p>
     */
    public java.util.List<Object> testpackage.Class1.testSetAsSequence(java.util.Set<Object> source) {
        return org.dresdenocl.tools.codegen.ocl2java.types.util.OclSets.asSequence(source);
    }
}