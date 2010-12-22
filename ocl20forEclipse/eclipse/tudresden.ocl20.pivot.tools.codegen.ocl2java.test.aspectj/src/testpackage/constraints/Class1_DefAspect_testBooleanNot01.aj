package testpackage.constraints;

/**
 * <p>Generated Aspect to enforce OCL constraint.</p>
 *
 * @author OCL22Java of Dresden OCL2 for Eclipse
 * @Generated
 */
public privileged aspect Class1_DefAspect_testBooleanNot01 {

    /**
     * <p>Defines the method testBooleanNot01(Boolean source) defined by the constraint
     * <code>context Class1
     *       def: testBooleanNot01(source: Boolean): Boolean = not source</code></p>
     */
    public Boolean testpackage.Class1.testBooleanNot01(Boolean source) {
        return !source;
    }
}