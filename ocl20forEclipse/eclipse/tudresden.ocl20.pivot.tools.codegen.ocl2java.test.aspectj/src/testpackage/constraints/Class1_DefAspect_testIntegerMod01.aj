package testpackage.constraints;

/**
 * <p>Generated Aspect to enforce OCL constraint.</p>
 *
 * @author OCL22Java of Dresden OCL2 for Eclipse
 * @Generated
 */
public privileged aspect Class1_DefAspect_testIntegerMod01 {

    /**
     * <p>Defines the method testIntegerMod01(Integer source, Integer arg01) defined by the constraint
     * <code>context Class1
     *       def: testIntegerMod01(source: Integer, arg01: Integer): Integer =
    source.mod(arg01)</code></p>
     */
    public Integer testpackage.Class1.testIntegerMod01(Integer source, Integer arg01) {
        return (source % arg01);
    }
}