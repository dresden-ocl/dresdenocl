package testpackage.constraints;

/**
 * <p>Generated Aspect to enforce OCL constraint.</p>
 *
 * @author OCL22Java of Dresden OCL2 for Eclipse
 * @Generated
 */
public privileged aspect Class1_DefAspect_testStringToReal {

    /**
     * <p>Defines the method testStringToReal(String source) defined by the constraint
     * <code>context Class1
     *       def: testStringToReal(source: String): Real =
    source.toReal()</code></p>
     */
    public Float testpackage.Class1.testStringToReal(String source) {
        return Float.parseFloat(source);
    }
}