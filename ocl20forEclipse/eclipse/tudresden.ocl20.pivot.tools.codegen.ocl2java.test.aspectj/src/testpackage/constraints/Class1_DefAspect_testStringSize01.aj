package testpackage.constraints;

/**
 * <p>Generated Aspect to enforce OCL constraint.</p>
 *
 * @author OCL22Java of Dresden OCL2 for Eclipse
 * @Generated
 */
public privileged aspect Class1_DefAspect_testStringSize01 {

    /**
     * <p>Defines the method testStringSize01(String source) defined by the constraint
     * <code>context Class1
     *       def: testStringSize01(source: String): Integer =
    source.size()</code></p>
     */
    public Integer testpackage.Class1.testStringSize01(String source) {
        return source.length();
    }
}