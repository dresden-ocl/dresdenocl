package testpackage.constraints;

/**
 * <p>Generated Aspect to enforce OCL constraint.</p>
 *
 * @author OCL22Java of Dresden OCL2 for Eclipse
 * @Generated
 */
public privileged aspect Class1_DefAspect_testOclAsType01 {

    /**
     * <p>Defines the method testOclAsType01(testpackage.Class1 source) defined by the constraint
     * <code>context Class1
     *       def: testOclAsType01(source: Class1): Class1 = source.oclAsType(Class1)</code></p>
     */
    public testpackage.Class1 testpackage.Class1.testOclAsType01(testpackage.Class1 source) {
        return ((testpackage.Class1) source);
    }
}