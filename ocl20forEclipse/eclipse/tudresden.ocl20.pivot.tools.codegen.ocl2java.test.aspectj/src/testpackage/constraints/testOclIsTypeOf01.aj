package testpackage.constraints;

/**
 * <p>Generated Aspect to enforce OCL constraint.</p>
 *
 * @author OCL22Java of Dresden OCL2 for Eclipse
 * @Generated
 */
public privileged aspect testOclIsTypeOf01 {

    /* Declares a new super class containing the new attribute or method. */
    declare parents : testpackage.Class1 extends testpackage.constraints.ExtendedClass1;
    
    /**
     * <p>Pointcut for all calls on {@link testpackage.Class1#testOclIsTypeOf01(testpackage.Class1 source, Class<?> arg01)}.</p>
     */
    protected pointcut testOclIsTypeOf01Caller(testpackage.Class1 aClass, testpackage.Class1 source, Class<?> arg01):
    	call(* testpackage.Class1.testOclIsTypeOf01(testpackage.Class1, Class<?>))
    	&& target(aClass) && args(source, arg01);
    
    /**
     * <p>Defines the method testOclIsTypeOf01(testpackage.Class1 source, Class<?> arg01) defined by the constraint
     * <code>context Class1
     *       def: testOclIsTypeOf01(source: Class1, arg01: OclType): Boolean =
    source.oclIsTypeOf(arg01)</code></p>
     */
    Boolean around(testpackage.Class1 aClass, testpackage.Class1 source, Class<?> arg01): testOclIsTypeOf01Caller(aClass, source, arg01) {
        return source.getClass().getCanonicalName().equals(arg01.getCanonicalName());
    }
}