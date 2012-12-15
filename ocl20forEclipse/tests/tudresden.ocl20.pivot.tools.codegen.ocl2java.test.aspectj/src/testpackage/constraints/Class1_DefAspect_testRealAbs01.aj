package testpackage.constraints;

/**
 * <p>Generated Aspect to enforce OCL constraint.</p>
 *
 * @author OCL22Java of Dresden OCL2 for Eclipse
 * @Generated
 */
public privileged aspect Class1_DefAspect_testRealAbs01 {

    /**
     * <p>Defines the method testRealAbs01(Float source) defined by the constraint
     * <code>context Class1
     *       def: testRealAbs01(source: Real): Real = source.abs()</code></p>
     */
    public Float testpackage.Class1.testRealAbs01(Float source) {
        return java.lang.Math.abs(source);
    }
}