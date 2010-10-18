package testpackage.constraints;

/**
 * <p>Generated Aspect to enforce OCL constraint.</p>
 *
 * @author OCL22Java of Dresden OCL2 for Eclipse
 * @Generated
 */
public privileged aspect Class1_DefAspect_testIntegerDiv01 {

    /**
     * <p>Defines the method testIntegerDiv01(Integer source, Integer arg01) defined by the constraint
     * <code>context Class1
     *       def: testIntegerDiv01(source: Integer, arg01: Integer): Integer =
    source.div(arg01)</code></p>
     */
    public Integer testpackage.Class1.testIntegerDiv01(Integer source, Integer arg01) {
        return (source / arg01);
    }
}