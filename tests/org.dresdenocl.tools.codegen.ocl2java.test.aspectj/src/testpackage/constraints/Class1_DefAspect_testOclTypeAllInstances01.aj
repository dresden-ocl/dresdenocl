package testpackage.constraints;

/**
 * <p>Generated Aspect to enforce OCL constraint.</p>
 *
 * @author OCL22Java of Dresden OCL2 for Eclipse
 * @Generated
 */
public privileged aspect Class1_DefAspect_testOclTypeAllInstances01 {

    /**
     * <p>Defines the method testOclTypeAllInstances01() defined by the constraint
     * <code>context Class1
     *       def: testOclTypeAllInstances01(): Set(Boolean) = Boolean.allInstances()</code></p>
     */
    public java.util.Set<Boolean> testpackage.Class1.testOclTypeAllInstances01() {
        java.util.Set<Boolean> result1 = new java.util.HashSet<Boolean>();
        result1.add(Boolean.FALSE);
        result1.add(Boolean.TRUE);
        return result1;
    }
}