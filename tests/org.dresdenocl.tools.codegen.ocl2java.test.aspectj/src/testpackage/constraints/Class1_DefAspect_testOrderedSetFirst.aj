package testpackage.constraints;

/**
 * <p>Generated Aspect to enforce OCL constraint.</p>
 *
 * @author OCL22Java of Dresden OCL2 for Eclipse
 * @Generated
 */
public privileged aspect Class1_DefAspect_testOrderedSetFirst {

    /**
     * <p>Defines the method testOrderedSetFirst(java.util.List<Object> source) defined by the constraint
     * <code>context Class1
     *       def: testOrderedSetFirst(source: OrderedSet(OclAny)): OclAny = source ->first()</code></p>
     */
    public Object testpackage.Class1.testOrderedSetFirst(java.util.List<Object> source) {
        return tudresden.ocl20.pivot.tools.codegen.ocl2java.types.util.OclOrderedSets.first(source);
    }
}