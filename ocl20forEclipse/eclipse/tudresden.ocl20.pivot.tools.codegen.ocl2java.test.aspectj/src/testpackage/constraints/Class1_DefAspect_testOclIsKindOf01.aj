package testpackage.constraints;

/**
 * <p>Generated Aspect to enforce OCL constraint.</p>
 *
 * @author OCL22Java of Dresden OCL2 for Eclipse
 * @Generated
 */
public privileged aspect Class1_DefAspect_testOclIsKindOf01 {

    /**
     * <p>Defines the method testOclIsKindOf01(testpackage.Class1 source, Class<?> arg01) defined by the constraint
     * <code>context Class1
     *       def: testOclIsKindOf01(source: Class1, arg01: OclType): Boolean =
    source.oclIsKindOf(arg01)</code></p>
     */
    public Boolean testpackage.Class1.testOclIsKindOf01(testpackage.Class1 source, Class<?> arg01) {
        return (arg01.isAssignableFrom(source.getClass()));
    }
}