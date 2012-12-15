package testpackage.constraints;

/**
 * <p>Generated Aspect to enforce OCL constraint.</p>
 *
 * @author OCL22Java of Dresden OCL2 for Eclipse
 * @Generated
 */
public privileged aspect Class1_DefAspect_testOrderedSetPrepend {

    /**
     * <p>Defines the method testOrderedSetPrepend(java.util.List<Object> source, Object arg01) defined by the constraint
     * <code>context Class1
     *       def: testOrderedSetPrepend(source: OrderedSet(OclAny), arg01: OclAny): OrderedSet(OclAny) = source ->prepend(arg01)</code></p>
     */
    public java.util.List<Object> testpackage.Class1.testOrderedSetPrepend(java.util.List<Object> source, Object arg01) {
        return org.dresdenocl.tools.codegen.ocl2java.types.util.OclOrderedSets.prepend(source, arg01);
    }
}