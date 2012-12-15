package testpackage.constraints;

/**
 * <p>Generated Aspect to enforce OCL constraint.</p>
 *
 * @author OCL22Java of Dresden OCL2 for Eclipse
 * @Generated
 */
public privileged aspect Class1_DefAspect_testSequenceIndexOf01 {

    /**
     * <p>Defines the method testSequenceIndexOf01(java.util.List<Object> source, Object arg01) defined by the constraint
     * <code>context Class1
     *       def: testSequenceIndexOf01(source: Sequence(OclAny), arg01: OclAny): Integer = source ->indexOf(arg01)</code></p>
     */
    public Integer testpackage.Class1.testSequenceIndexOf01(java.util.List<Object> source, Object arg01) {
        return org.dresdenocl.tools.codegen.ocl2java.types.util.OclSequences.indexOf(source, arg01);
    }
}