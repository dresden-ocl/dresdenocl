package testpackage.constraints;

/**
 * <p>Generated Aspect to enforce OCL constraint.</p>
 *
 * @author OCL22Java of Dresden OCL2 for Eclipse
 * @Generated
 */
public privileged aspect Class1_DefAspect_testOclAnyEquals01 {

    /**
     * <p>Defines the method testOclAnyEquals01(testpackage.Class1 source, testpackage.Class1 arg01) defined by the constraint
     * <code>context Class1
     *       def: testOclAnyEquals01(source: Class1, arg01: Class1): Boolean =
    source = arg01</code></p>
     */
    public Boolean testpackage.Class1.testOclAnyEquals01(testpackage.Class1 source, testpackage.Class1 arg01) {
        return source.equals(arg01);
    }
}