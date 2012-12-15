package testpackage.constraints;

/**
 * <p>Generated Aspect to enforce OCL constraint.</p>
 *
 * @author OCL22Java of Dresden OCL2 for Eclipse
 * @Generated
 */
public privileged aspect Class1_DefAspect_testStringIndexOf {

    /**
     * <p>Defines the method testStringIndexOf(String source, String arg01) defined by the constraint
     * <code>context Class1
     *       def: testStringIndexOf(source: String, arg01: String): Integer = source.indexOf(arg01)</code></p>
     */
    public Integer testpackage.Class1.testStringIndexOf(String source, String arg01) {
        return source.indexOf(arg01) + 1;
    }
}