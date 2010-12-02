package testpackage.constraints;

/**
 * <p>Generated Aspect to enforce OCL constraint.</p>
 *
 * @author OCL22Java of Dresden OCL2 for Eclipse
 * @Generated
 */
public privileged aspect Class1_DefAspect_testOrderedSetSubOrderedSet {

    /**
     * <p>Defines the method testOrderedSetSubOrderedSet(java.util.List<Object> source, Integer arg01, Integer arg02) defined by the constraint
     * <code>context Class1
     *       def: testOrderedSetSubOrderedSet(source: OrderedSet(OclAny), arg01: Integer, arg02: Integer): OrderedSet(OclAny) =
    source ->subOrderedSet(arg01, arg02)</code></p>
     */
    public java.util.List<Object> testpackage.Class1.testOrderedSetSubOrderedSet(java.util.List<Object> source, Integer arg01, Integer arg02) {
        return tudresden.ocl20.pivot.tools.codegen.ocl2java.types.util.OclOrderedSets.subOrderedSet(source, arg01, arg02);
    }
}