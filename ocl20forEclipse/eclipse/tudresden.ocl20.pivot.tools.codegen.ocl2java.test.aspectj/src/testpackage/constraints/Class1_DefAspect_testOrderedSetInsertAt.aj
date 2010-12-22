package testpackage.constraints;

/**
 * <p>Generated Aspect to enforce OCL constraint.</p>
 *
 * @author OCL22Java of Dresden OCL2 for Eclipse
 * @Generated
 */
public privileged aspect Class1_DefAspect_testOrderedSetInsertAt {

    /**
     * <p>Defines the method testOrderedSetInsertAt(java.util.List<Object> source, Integer arg01, Object arg02) defined by the constraint
     * <code>context Class1
     *       def: testOrderedSetInsertAt(source: OrderedSet(OclAny), arg01: Integer, arg02: OclAny): OrderedSet(OclAny) = source ->insertAt(arg01, arg02)</code></p>
     */
    public java.util.List<Object> testpackage.Class1.testOrderedSetInsertAt(java.util.List<Object> source, Integer arg01, Object arg02) {
        return tudresden.ocl20.pivot.tools.codegen.ocl2java.types.util.OclOrderedSets.insertAt(source, arg01, arg02);
    }
}