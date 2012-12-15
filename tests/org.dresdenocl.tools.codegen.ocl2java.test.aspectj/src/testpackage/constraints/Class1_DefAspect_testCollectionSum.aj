package testpackage.constraints;

/**
 * <p>Generated Aspect to enforce OCL constraint.</p>
 *
 * @author OCL22Java of Dresden OCL2 for Eclipse
 * @Generated
 */
public privileged aspect Class1_DefAspect_testCollectionSum {

    /**
     * <p>Defines the method testCollectionSum(java.util.Collection<Integer> source) defined by the constraint
     * <code>context Class1
     *       def: testCollectionSum(source: Collection(Integer)): Integer = source ->sum()</code></p>
     */
    public Integer testpackage.Class1.testCollectionSum(java.util.Collection<Integer> source) {
        return new Integer(tudresden.ocl20.pivot.tools.codegen.ocl2java.types.util.OclCollections.sum(source).intValue());
    }
}