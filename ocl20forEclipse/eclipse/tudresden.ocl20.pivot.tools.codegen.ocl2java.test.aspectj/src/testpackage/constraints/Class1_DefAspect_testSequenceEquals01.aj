package testpackage.constraints;

/**
 * <p>Generated Aspect to enforce OCL constraint.</p>
 *
 * @author OCL22Java of Dresden OCL2 for Eclipse
 * @Generated
 */
public privileged aspect Class1_DefAspect_testSequenceEquals01 {

    /**
     * <p>Defines the method testSequenceEquals01(java.util.List<Object> source, java.util.List<Object> arg01) defined by the constraint
     * <code>context Class1
     *       def: testSequenceEquals01(source: Sequence(OclAny), arg01: Sequence(OclAny)): Boolean =
    source = arg01</code></p>
     */
    public Boolean testpackage.Class1.testSequenceEquals01(java.util.List<Object> source, java.util.List<Object> arg01) {
        return tudresden.ocl20.pivot.tools.codegen.ocl2java.types.util.OclSequences.equals(source, arg01);
    }
}