package testpackage.constraints;

/**
 * <p>Generated Aspect to enforce OCL constraint.</p>
 *
 * @author OCL22Java of Dresden OCL2 for Eclipse
 * @Generated
 */
public privileged aspect Class1_DefAspect_testBooleanOr01 {

    /**
     * <p>Defines the method testBooleanOr01(Boolean source, Boolean arg01) defined by the constraint
     * <code>context Class1
     *       def: testBooleanOr01(source: Boolean, arg01: Boolean): Boolean =
    source or arg01</code></p>
     */
    public Boolean testpackage.Class1.testBooleanOr01(Boolean source, Boolean arg01) {
        return (source || arg01);
    }
}