package testpackage.constraints;

/**
 * <p>Generated Aspect to enforce OCL constraint.</p>
 *
 * @author OCL22Java of Dresden OCL2 for Eclipse
 * @Generated
 */
public privileged aspect Class1_DefAspect_testRealNegation01 {

    /**
     * <p>Defines the method testRealNegation01(Float source) defined by the constraint
     * <code>context Class1
     *       def: testRealNegation01(source: Real): Real =
    - source</code></p>
     */
    public Float testpackage.Class1.testRealNegation01(Float source) {
        return -(source);
    }
}