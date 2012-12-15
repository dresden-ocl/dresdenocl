package testpackage.constraints;

/**
 * <p>Generated Aspect to enforce OCL constraint.</p>
 *
 * @author OCL22Java of Dresden OCL2 for Eclipse
 * @Generated
 */
public privileged aspect Class1_DefAspect_testSetAsOrderedSet {

    /**
     * <p>Defines the method testSetAsOrderedSet(java.util.Set<Object> source) defined by the constraint
     * <code>context Class1
     *       def: testSetAsOrderedSet(source: Set(OclAny)):    OrderedSet(OclAny) = source ->asOrderedSet()</code></p>
     */
    public java.util.List<Object> testpackage.Class1.testSetAsOrderedSet(java.util.Set<Object> source) {
        return org.dresdenocl.tools.codegen.ocl2java.types.util.OclSets.asOrderedSet(source);
    }
}