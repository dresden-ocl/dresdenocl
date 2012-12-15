package testpackage.constraints;

/**
 * <p>Generated Aspect to enforce OCL constraint.</p>
 *
 * @author OCL22Java of Dresden OCL2 for Eclipse
 * @Generated
 */
public privileged aspect Class1_DefAspect_testRealToString {

    /**
     * <p>Defines the method testRealToString(Float source) defined by the constraint
     * <code>context Class1
     *       def: testRealToString(source: Real): String = source.toString()</code></p>
     */
    public String testpackage.Class1.testRealToString(Float source) {
        return source.toString();
    }
}