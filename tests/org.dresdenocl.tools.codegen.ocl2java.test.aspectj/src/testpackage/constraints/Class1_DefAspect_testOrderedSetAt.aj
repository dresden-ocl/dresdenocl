package testpackage.constraints;

/**
 * <p>Generated Aspect to enforce OCL constraint.</p>
 *
 * @author OCL22Java of Dresden OCL2 for Eclipse
 * @Generated
 */
public privileged aspect Class1_DefAspect_testOrderedSetAt {

    /**
     * <p>Defines the method testOrderedSetAt(java.util.List<Object> source, Integer arg01) defined by the constraint
     * <code>context Class1
     *       def: testOrderedSetAt(source: OrderedSet(OclAny), arg01: Integer): OclAny = source ->at(arg01)</code></p>
     */
    public Object testpackage.Class1.testOrderedSetAt(java.util.List<Object> source, Integer arg01) {
        return org.dresdenocl.tools.codegen.ocl2java.types.util.OclOrderedSets.at(source, arg01);
    }
}