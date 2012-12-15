package testpackage.constraints;

/**
 * <p>Generated Aspect to enforce OCL constraint.</p>
 *
 * @author OCL22Java of Dresden OCL2 for Eclipse
 * @Generated
 */
public privileged aspect Class1_DefAspect_testOrderedSetLast {

    /**
     * <p>Defines the method testOrderedSetLast(java.util.List<Object> source) defined by the constraint
     * <code>context Class1
     *       def: testOrderedSetLast(source: OrderedSet(OclAny)): OclAny = source ->last()</code></p>
     */
    public Object testpackage.Class1.testOrderedSetLast(java.util.List<Object> source) {
        return org.dresdenocl.tools.codegen.ocl2java.types.util.OclOrderedSets.last(source);
    }
}