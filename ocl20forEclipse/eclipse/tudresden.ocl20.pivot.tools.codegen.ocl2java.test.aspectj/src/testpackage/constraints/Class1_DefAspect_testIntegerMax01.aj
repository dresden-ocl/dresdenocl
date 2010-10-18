package testpackage.constraints;

/**
 * <p>Generated Aspect to enforce OCL constraint.</p>
 *
 * @author OCL22Java of Dresden OCL2 for Eclipse
 * @Generated
 */
public privileged aspect Class1_DefAspect_testIntegerMax01 {

    /**
     * <p>Defines the method testIntegerMax01(Integer source, Integer arg01) defined by the constraint
     * <code>context Class1
     *       def: testIntegerMax01(source: Integer, arg01: Integer): Integer =
    source.max(arg01)</code></p>
     */
    public Integer testpackage.Class1.testIntegerMax01(Integer source, Integer arg01) {
        return java.lang.Math.max(source, arg01);
    }
}