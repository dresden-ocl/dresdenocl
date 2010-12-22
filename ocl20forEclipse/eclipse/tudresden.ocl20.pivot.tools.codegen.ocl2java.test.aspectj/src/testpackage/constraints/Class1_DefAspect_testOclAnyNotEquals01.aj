package testpackage.constraints;

/**
 * <p>Generated Aspect to enforce OCL constraint.</p>
 *
 * @author OCL22Java of Dresden OCL2 for Eclipse
 * @Generated
 */
public privileged aspect Class1_DefAspect_testOclAnyNotEquals01 {

    /**
     * <p>Defines the method testOclAnyNotEquals01(testpackage.Class1 source, testpackage.Class1 arg01) defined by the constraint
     * <code>context Class1
     *       def: testOclAnyNotEquals01(source: Class1, arg01: Class1): Boolean = source <> arg01</code></p>
     */
    public Boolean testpackage.Class1.testOclAnyNotEquals01(testpackage.Class1 source, testpackage.Class1 arg01) {
        return !source.equals(arg01);
    }
}