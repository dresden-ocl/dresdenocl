package testpackage.constraints;

/**
 * <p>Generated Aspect to enforce OCL constraint.</p>
 *
 * @author OCL22Java of Dresden OCL2 for Eclipse
 * @Generated
 */
public privileged aspect Class1_DefAspect_testIntegerDivide01 {

    /**
     * <p>Defines the method testIntegerDivide01(Integer source, Integer arg01) defined by the constraint
     * <code>context Class1
     *       def: testIntegerDivide01(source: Integer, arg01: Integer): Real = source / arg01</code></p>
     */
    public Float testpackage.Class1.testIntegerDivide01(Integer source, Integer arg01) {
        return (new Float (source) / new Float (arg01));
    }
}