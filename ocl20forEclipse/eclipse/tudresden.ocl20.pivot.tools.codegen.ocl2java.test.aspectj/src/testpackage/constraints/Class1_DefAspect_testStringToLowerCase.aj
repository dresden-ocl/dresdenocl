package testpackage.constraints;

/**
 * <p>Generated Aspect to enforce OCL constraint.</p>
 *
 * @author OCL22Java of Dresden OCL2 for Eclipse
 * @Generated
 */
public privileged aspect Class1_DefAspect_testStringToLowerCase {

    /**
     * <p>Defines the method testStringToLowerCase(String source) defined by the constraint
     * <code>context Class1
     *       def: testStringToLowerCase(source: String): String = source.toLowerCase()</code></p>
     */
    public String testpackage.Class1.testStringToLowerCase(String source) {
        return source.toLowerCase();
    }
}