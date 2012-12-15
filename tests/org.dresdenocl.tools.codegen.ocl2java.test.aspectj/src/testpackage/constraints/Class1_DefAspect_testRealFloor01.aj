package testpackage.constraints;

/**
 * <p>Generated Aspect to enforce OCL constraint.</p>
 *
 * @author OCL22Java of Dresden OCL2 for Eclipse
 * @Generated
 */
public privileged aspect Class1_DefAspect_testRealFloor01 {

    /**
     * <p>Defines the method testRealFloor01(Float source) defined by the constraint
     * <code>context Class1
     *       def: testRealFloor01(source: Real): Integer = source.floor()</code></p>
     */
    public Integer testpackage.Class1.testRealFloor01(Float source) {
        return (new Integer(new Double(java.lang.Math.floor(source)).intValue()));
    }
}