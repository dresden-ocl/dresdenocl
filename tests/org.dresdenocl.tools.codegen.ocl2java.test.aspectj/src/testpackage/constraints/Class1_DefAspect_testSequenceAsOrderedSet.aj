package testpackage.constraints;

/**
 * <p>Generated Aspect to enforce OCL constraint.</p>
 *
 * @author OCL22Java of Dresden OCL2 for Eclipse
 * @Generated
 */
public privileged aspect Class1_DefAspect_testSequenceAsOrderedSet {

    /**
     * <p>Defines the method testSequenceAsOrderedSet(java.util.List<Object> source) defined by the constraint
     * <code>context Class1
     *       def: testSequenceAsOrderedSet(source: Sequence(OclAny)):    OrderedSet(OclAny) = source ->asOrderedSet()</code></p>
     */
    public java.util.List<Object> testpackage.Class1.testSequenceAsOrderedSet(java.util.List<Object> source) {
        return org.dresdenocl.tools.codegen.ocl2java.types.util.OclSequences.asOrderedSet(source);
    }
}