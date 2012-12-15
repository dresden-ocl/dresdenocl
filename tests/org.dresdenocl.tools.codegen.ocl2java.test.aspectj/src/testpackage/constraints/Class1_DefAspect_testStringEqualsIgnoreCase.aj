package testpackage.constraints;

/**
 * <p>Generated Aspect to enforce OCL constraint.</p>
 *
 * @author OCL22Java of Dresden OCL2 for Eclipse
 * @Generated
 */
public privileged aspect Class1_DefAspect_testStringEqualsIgnoreCase {

    /**
     * <p>Defines the method testStringEqualsIgnoreCase(String source, String arg01) defined by the constraint
     * <code>context Class1
     *       def: testStringEqualsIgnoreCase(source: String, arg01: String): Boolean = source.equalsIgnoreCase(arg01)</code></p>
     */
    public Boolean testpackage.Class1.testStringEqualsIgnoreCase(String source, String arg01) {
        return source.equalsIgnoreCase(arg01);
    }
}