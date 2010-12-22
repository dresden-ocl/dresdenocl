package testpackage.constraints;

/**
 * <p>Generated Aspect to enforce OCL constraint.</p>
 *
 * @author OCL22Java of Dresden OCL2 for Eclipse
 * @Generated
 */
public privileged aspect Class1_DefAspect_testIntegerNegation01 {

    /**
     * <p>Defines the method testIntegerNegation01(Integer source) defined by the constraint
     * <code>context Class1
     *       def: testIntegerNegation01(source: Integer): Integer = - source</code></p>
     */
    public Integer testpackage.Class1.testIntegerNegation01(Integer source) {
        return -(source);
    }
}