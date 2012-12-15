package testpackage.constraints;

/**
 * <p>Generated Aspect to enforce OCL constraint.</p>
 *
 * @author OCL22Java of Dresden OCL2 for Eclipse
 * @Generated
 */
public privileged aspect Class1_DefAspect_testRealMin01 {

    /**
     * <p>Defines the method testRealMin01(Float source, Float arg01) defined by the constraint
     * <code>context Class1
     *       def: testRealMin01(source: Real, arg01: Real): Real = source.min(arg01)</code></p>
     */
    public Float testpackage.Class1.testRealMin01(Float source, Float arg01) {
        return java.lang.Math.min(source, arg01);
    }
}