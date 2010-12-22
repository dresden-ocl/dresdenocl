package testpackage.constraints;

/**
 * <p>Generated Aspect to enforce OCL constraint.</p>
 *
 * @author OCL22Java of Dresden OCL2 for Eclipse
 * @Generated
 */
public privileged aspect Class1_DefAspect_testStringToUpperCase {

    /**
     * <p>Defines the method testStringToUpperCase(String source) defined by the constraint
     * <code>context Class1
     *       def: testStringToUpperCase(source: String): String = source.toUpperCase()</code></p>
     */
    public String testpackage.Class1.testStringToUpperCase(String source) {
        return source.toUpperCase();
    }
}