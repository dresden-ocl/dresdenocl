package testpackage.constraints;

/**
 * <p>Generated Aspect to enforce OCL constraint.</p>
 *
 * @author OCL22Java of Dresden OCL2 for Eclipse
 * @Generated
 */
public privileged aspect Class1_DefAspect_testOrderedSetIndexOf01 {

    /**
     * <p>Defines the method testOrderedSetIndexOf01(java.util.List<Object> source, Object arg01) defined by the constraint
     * <code>context Class1
     *       def: testOrderedSetIndexOf01(source: OrderedSet(OclAny), arg01: OclAny): Integer = source ->indexOf(arg01)</code></p>
     */
    public Integer testpackage.Class1.testOrderedSetIndexOf01(java.util.List<Object> source, Object arg01) {
        return tudresden.ocl20.pivot.tools.codegen.ocl2java.types.util.OclOrderedSets.indexOf(source, arg01);
    }
}