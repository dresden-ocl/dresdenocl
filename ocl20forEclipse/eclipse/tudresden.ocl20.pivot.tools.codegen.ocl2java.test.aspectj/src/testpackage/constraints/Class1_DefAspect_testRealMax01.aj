package testpackage.constraints;

/**
 * <p>Generated Aspect to enforce OCL constraint.</p>
 *
 * @author OCL22Java of Dresden OCL2 for Eclipse
 * @Generated
 */
public privileged aspect Class1_DefAspect_testRealMax01 {

    /**
     * <p>Defines the method testRealMax01(Float source, Float arg01) defined by the constraint
     * <code>context Class1
     *       def: testRealMax01(source: Real, arg01: Real): Real = source.max(arg01)</code></p>
     */
    public Float testpackage.Class1.testRealMax01(Float source, Float arg01) {
        return java.lang.Math.max(source, arg01);
    }
}