package testpackage.constraints;

/**
 * <p>Generated Aspect to enforce OCL constraint.</p>
 *
 * @author OCL22Java of Dresden OCL2 for Eclipse
 * @Generated
 */
public privileged aspect Class1_DefAspect_testStringGreaterThanEqual01 {

    /**
     * <p>Defines the method testStringGreaterThanEqual01(String source, String arg01) defined by the constraint
     * <code>context Class1
     *       def: testStringGreaterThanEqual01(source: String, arg01: String): Boolean = source >= arg01</code></p>
     */
    public Boolean testpackage.Class1.testStringGreaterThanEqual01(String source, String arg01) {
        return (source.compareTo(arg01) >= 0);
    }
}