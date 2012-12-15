package testpackage.constraints;

/**
 * <p>Generated Aspect to enforce OCL constraint.</p>
 *
 * @author OCL22Java of Dresden OCL2 for Eclipse
 * @Generated
 */
public privileged aspect Class1_DefAspect_testStringConcat01 {

    /**
     * <p>Defines the method testStringConcat01(String source, String arg01) defined by the constraint
     * <code>context Class1
     *       def: testStringConcat01(source: String, arg01: String): String = source.concat(arg01)</code></p>
     */
    public String testpackage.Class1.testStringConcat01(String source, String arg01) {
        return source.concat(arg01);
    }
}