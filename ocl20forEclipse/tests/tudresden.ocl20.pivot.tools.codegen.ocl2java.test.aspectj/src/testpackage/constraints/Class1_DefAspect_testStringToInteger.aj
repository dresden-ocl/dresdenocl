package testpackage.constraints;

/**
 * <p>Generated Aspect to enforce OCL constraint.</p>
 *
 * @author OCL22Java of Dresden OCL2 for Eclipse
 * @Generated
 */
public privileged aspect Class1_DefAspect_testStringToInteger {

    /**
     * <p>Defines the method testStringToInteger(String source) defined by the constraint
     * <code>context Class1
     *       def: testStringToInteger(source: String): Integer = source.toInteger()</code></p>
     */
    public Integer testpackage.Class1.testStringToInteger(String source) {
        return Integer.parseInt(source);
    }
}