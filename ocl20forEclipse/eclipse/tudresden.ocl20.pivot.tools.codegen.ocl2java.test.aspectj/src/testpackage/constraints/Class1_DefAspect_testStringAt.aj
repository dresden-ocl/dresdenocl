package testpackage.constraints;

/**
 * <p>Generated Aspect to enforce OCL constraint.</p>
 *
 * @author OCL22Java of Dresden OCL2 for Eclipse
 * @Generated
 */
public privileged aspect Class1_DefAspect_testStringAt {

    /**
     * <p>Defines the method testStringAt(String source, Integer arg01) defined by the constraint
     * <code>context Class1
     *       def: testStringAt(source: String, arg01: Integer): String =
    source.at(arg01)</code></p>
     */
    public String testpackage.Class1.testStringAt(String source, Integer arg01) {
        return Character.toString(source.charAt(arg01 - 1));
    }
}