package testpackage.constraints;

/**
 * <p>Generated Aspect to enforce OCL constraint.</p>
 *
 * @author OCL22Java of Dresden OCL2 for Eclipse
 * @Generated
 */
public privileged aspect Class1_DefAspect_testStringToBoolean {

    /**
     * <p>Defines the method testStringToBoolean(String source) defined by the constraint
     * <code>context Class1
     *       def: testStringToBoolean(source: String): Boolean = source.toBoolean()</code></p>
     */
    public Boolean testpackage.Class1.testStringToBoolean(String source) {
        return Boolean.parseBoolean(source);
    }
}