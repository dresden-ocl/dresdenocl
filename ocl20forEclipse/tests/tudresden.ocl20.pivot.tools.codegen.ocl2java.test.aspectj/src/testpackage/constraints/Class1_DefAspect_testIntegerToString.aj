package testpackage.constraints;

/**
 * <p>Generated Aspect to enforce OCL constraint.</p>
 *
 * @author OCL22Java of Dresden OCL2 for Eclipse
 * @Generated
 */
public privileged aspect Class1_DefAspect_testIntegerToString {

    /**
     * <p>Defines the method testIntegerToString(Integer source) defined by the constraint
     * <code>context Class1
     *       def: testIntegerToString(source: Integer): String = source.toString()</code></p>
     */
    public String testpackage.Class1.testIntegerToString(Integer source) {
        return source.toString();
    }
}