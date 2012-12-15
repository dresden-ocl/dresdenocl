package testpackage.constraints;

/**
 * <p>Generated Aspect to enforce OCL constraint.</p>
 *
 * @author OCL22Java of Dresden OCL2 for Eclipse
 * @Generated
 */
public privileged aspect Class1_DefAspect_testBooleanXor01 {

    /**
     * <p>Defines the method testBooleanXor01(Boolean source, Boolean arg01) defined by the constraint
     * <code>context Class1
     *       def: testBooleanXor01(source: Boolean, arg01: Boolean): Boolean = source xor arg01</code></p>
     */
    public Boolean testpackage.Class1.testBooleanXor01(Boolean source, Boolean arg01) {
        return (source ^ arg01);
    }
}