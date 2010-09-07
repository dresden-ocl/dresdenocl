package testpackage.constraints;

/**
 * <p>Generated Aspect to enforce OCL constraint.</p>
 *
 * @author OCL22Java of Dresden OCL2 for Eclipse
 * @Generated
 */
public privileged aspect DefAspect58 {

    /* Declares a new super class containing the new attribute or method. */
    declare parents : testpackage.Class1 extends testpackage.constraints.ExtendedClass1;
    
    /**
     * <p>Pointcut for all calls on {@link testpackage.Class1#testOclIsKindOf01(testpackage.Class1 source, Class<?> arg01)}.</p>
     */
    protected pointcut testOclIsKindOf01Caller(testpackage.Class1 aClass, testpackage.Class1 source, Class<?> arg01):
    	call(* testpackage.Class1.testOclIsKindOf01(testpackage.Class1, Class<?>))
    	&& target(aClass) && args(source, arg01);
    
    /**
     * <p>Defines the method testOclIsKindOf01(testpackage.Class1 source, Class<?> arg01) defined by the constraint
     * <code>context Class1
     *       def: testOclIsKindOf01(source: Class1, arg01: OclType): Boolean =
    source.oclIsKindOf(arg01)</code></p>
     */
    Boolean around(testpackage.Class1 aClass, testpackage.Class1 source, Class<?> arg01): testOclIsKindOf01Caller(aClass, source, arg01) {
        return (arg01.isAssignableFrom(source.getClass()));
    }
}