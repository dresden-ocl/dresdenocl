package testpackage.constraints;

/**
 * <p>Generated Aspect to enforce OCL constraint.</p>
 *
 * @author OCL22Java of Dresden OCL2 for Eclipse
 * @Generated
 */
public privileged aspect Class1_DefAspect_testIntegerMin01 {

    /**
     * <p>Defines the method testIntegerMin01(Integer source, Integer arg01) defined by the constraint
     * <code>context Class1
     *       def: testIntegerMin01(source: Integer, arg01: Integer): Integer =
    source.min(arg01)</code></p>
     */
    public Integer testpackage.Class1.testIntegerMin01(Integer source, Integer arg01) {
        return java.lang.Math.min(source, arg01);
    }
}