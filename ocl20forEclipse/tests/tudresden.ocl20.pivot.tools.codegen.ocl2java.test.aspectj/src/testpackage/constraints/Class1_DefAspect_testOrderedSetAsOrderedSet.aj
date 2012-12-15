package testpackage.constraints;

/**
 * <p>Generated Aspect to enforce OCL constraint.</p>
 *
 * @author OCL22Java of Dresden OCL2 for Eclipse
 * @Generated
 */
public privileged aspect Class1_DefAspect_testOrderedSetAsOrderedSet {

    /**
     * <p>Defines the method testOrderedSetAsOrderedSet(java.util.List<Object> source) defined by the constraint
     * <code>context Class1
     *       def: testOrderedSetAsOrderedSet(source: OrderedSet(OclAny)):    OrderedSet(OclAny) = source ->asOrderedSet()</code></p>
     */
    public java.util.List<Object> testpackage.Class1.testOrderedSetAsOrderedSet(java.util.List<Object> source) {
        return tudresden.ocl20.pivot.tools.codegen.ocl2java.types.util.OclOrderedSets.asOrderedSet(source);
    }
}