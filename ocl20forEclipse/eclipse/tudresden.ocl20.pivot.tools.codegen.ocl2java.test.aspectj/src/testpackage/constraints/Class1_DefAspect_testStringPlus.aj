package testpackage.constraints;

/**
 * <p>Generated Aspect to enforce OCL constraint.</p>
 *
 * @author OCL22Java of Dresden OCL2 for Eclipse
 * @Generated
 */
public privileged aspect Class1_DefAspect_testStringPlus {

    /**
     * <p>Defines the method testStringPlus(String source, String arg01) defined by the constraint
     * <code>context Class1
     *       def: testStringPlus(source: String, arg01: String): String =
    source + arg01</code></p>
     */
    public String testpackage.Class1.testStringPlus(String source, String arg01) {
        return source.concat(arg01);
    }
}