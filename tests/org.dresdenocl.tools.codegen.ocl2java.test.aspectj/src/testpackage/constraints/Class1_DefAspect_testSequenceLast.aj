package testpackage.constraints;

/**
 * <p>Generated Aspect to enforce OCL constraint.</p>
 *
 * @author OCL22Java of Dresden OCL2 for Eclipse
 * @Generated
 */
public privileged aspect Class1_DefAspect_testSequenceLast {

    /**
     * <p>Defines the method testSequenceLast(java.util.List<Object> source) defined by the constraint
     * <code>context Class1
     *       def: testSequenceLast(source: Sequence(OclAny)): OclAny = source ->last()</code></p>
     */
    public Object testpackage.Class1.testSequenceLast(java.util.List<Object> source) {
        return org.dresdenocl.tools.codegen.ocl2java.types.util.OclSequences.last(source);
    }
}