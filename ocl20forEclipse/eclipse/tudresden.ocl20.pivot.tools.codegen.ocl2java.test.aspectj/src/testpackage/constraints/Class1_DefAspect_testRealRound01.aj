package testpackage.constraints;

/**
 * <p>Generated Aspect to enforce OCL constraint.</p>
 *
 * @author OCL22Java of Dresden OCL2 for Eclipse
 * @Generated
 */
public privileged aspect Class1_DefAspect_testRealRound01 {

    /**
     * <p>Defines the method testRealRound01(Float source) defined by the constraint
     * <code>context Class1
     *       def: testRealRound01(source: Real): Integer =
    source.round()</code></p>
     */
    public Integer testpackage.Class1.testRealRound01(Float source) {
        return java.lang.Math.round(source);
    }
}