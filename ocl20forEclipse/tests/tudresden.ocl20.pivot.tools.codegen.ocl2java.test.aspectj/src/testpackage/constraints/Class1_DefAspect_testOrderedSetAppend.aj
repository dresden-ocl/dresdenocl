package testpackage.constraints;

/**
 * <p>Generated Aspect to enforce OCL constraint.</p>
 *
 * @author OCL22Java of Dresden OCL2 for Eclipse
 * @Generated
 */
public privileged aspect Class1_DefAspect_testOrderedSetAppend {

    /**
     * <p>Defines the method testOrderedSetAppend(java.util.List<Object> source, Object arg01) defined by the constraint
     * <code>context Class1
     *       def: testOrderedSetAppend(source: OrderedSet(OclAny), arg01: OclAny): OrderedSet(OclAny) = source ->append(arg01)</code></p>
     */
    public java.util.List<Object> testpackage.Class1.testOrderedSetAppend(java.util.List<Object> source, Object arg01) {
        return tudresden.ocl20.pivot.tools.codegen.ocl2java.types.util.OclOrderedSets.append(source, arg01);
    }
}