package testpackage.constraints;

/**
 * <p>Generated Aspect to enforce OCL constraint.</p>
 *
 * @author OCL22Java of Dresden OCL2 for Eclipse
 * @Generated
 */
public privileged aspect Class1_DefAspect_testOrderedSetReverse {

    /**
     * <p>Defines the method testOrderedSetReverse(java.util.List<Object> source) defined by the constraint
     * <code>context Class1
     *       def: testOrderedSetReverse(source: OrderedSet(OclAny)): OrderedSet(OclAny) = source ->reverse()</code></p>
     */
    public java.util.List<Object> testpackage.Class1.testOrderedSetReverse(java.util.List<Object> source) {
        return tudresden.ocl20.pivot.tools.codegen.ocl2java.types.util.OclOrderedSets.reverse(source);
    }
}