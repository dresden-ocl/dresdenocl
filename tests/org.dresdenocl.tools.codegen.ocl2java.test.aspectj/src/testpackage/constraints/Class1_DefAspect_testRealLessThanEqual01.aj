package testpackage.constraints;

/**
 * <p>Generated Aspect to enforce OCL constraint.</p>
 *
 * @author OCL22Java of Dresden OCL2 for Eclipse
 * @Generated
 */
public privileged aspect Class1_DefAspect_testRealLessThanEqual01 {

    /**
     * <p>Defines the method testRealLessThanEqual01(Float source, Float arg01) defined by the constraint
     * <code>context Class1
     *       def: testRealLessThanEqual01(source: Real, arg01: Real): Boolean = source <= arg01</code></p>
     */
    public Boolean testpackage.Class1.testRealLessThanEqual01(Float source, Float arg01) {
        return (source <= arg01);
    }
}