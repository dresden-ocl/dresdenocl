package testpackage.constraints;

/**
 * <p>Generated Aspect to enforce OCL constraint.</p>
 *
 * @author OCL22Java of Dresden OCL2 for Eclipse
 * @Generated
 */
public privileged aspect Class1_DefAspect_testStringSubstring01 {

    /**
     * <p>Defines the method testStringSubstring01(String source, Integer arg01, Integer arg02) defined by the constraint
     * <code>context Class1
     *       def: testStringSubstring01(source: String, arg01: Integer, arg02: Integer): String = source.substring(arg01, arg02)</code></p>
     */
    public String testpackage.Class1.testStringSubstring01(String source, Integer arg01, Integer arg02) {
        return source.substring(arg01 - 1, arg02);
    }
}