package testpackage.constraints;

/**
 * <p>Generated Aspect to enforce OCL constraint.</p>
 *
 * @author OCL22Java of Dresden OCL2 for Eclipse
 * @Generated
 */
public privileged aspect Class1_DefAspect_testOclIsTypeOf01 {

    /**
     * <p>Defines the method testOclIsTypeOf01(testpackage.Class1 source, Class<?> arg01) defined by the constraint
     * <code>context Class1
     *       def: testOclIsTypeOf01(source: Class1, arg01: OclType): Boolean = source.oclIsTypeOf(arg01)</code></p>
     */
    public Boolean testpackage.Class1.testOclIsTypeOf01(testpackage.Class1 source, Class<?> arg01) {
        return source.getClass().getCanonicalName().equals(arg01.getCanonicalName());
    }
}