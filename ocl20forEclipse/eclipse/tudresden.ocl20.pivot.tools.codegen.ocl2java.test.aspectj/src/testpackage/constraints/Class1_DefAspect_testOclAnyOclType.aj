package testpackage.constraints;

/**
 * <p>Generated Aspect to enforce OCL constraint.</p>
 *
 * @author OCL22Java of Dresden OCL2 for Eclipse
 * @Generated
 */
public privileged aspect Class1_DefAspect_testOclAnyOclType {

    /**
     * <p>Defines the method testOclAnyOclType(Object source) defined by the constraint
     * <code>context Class1
     *       def: testOclAnyOclType(source: OclAny): OclType =
    source.oclType()</code></p>
     */
    public Class<?> testpackage.Class1.testOclAnyOclType(Object source) {
        return source.getClass();
    }
}