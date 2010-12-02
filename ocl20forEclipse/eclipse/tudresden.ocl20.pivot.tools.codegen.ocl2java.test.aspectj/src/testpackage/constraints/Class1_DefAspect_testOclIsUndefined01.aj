package testpackage.constraints;

/**
 * <p>Generated Aspect to enforce OCL constraint.</p>
 *
 * @author OCL22Java of Dresden OCL2 for Eclipse
 * @Generated
 */
public privileged aspect Class1_DefAspect_testOclIsUndefined01 {

    /**
     * <p>Defines the method testOclIsUndefined01(testpackage.Class1 source) defined by the constraint
     * <code>context Class1
     *       def: testOclIsUndefined01(source: Class1): Boolean =
    source.oclIsUndefined()</code></p>
     */
    public Boolean testpackage.Class1.testOclIsUndefined01(testpackage.Class1 source) {
        return (source == null);
    }
}