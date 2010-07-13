package testpackage.constraints;

/**
 * <p>Generated Aspect to enforce OCL constraint.</p>
 *
 * @author OCL22Java of Dresden OCL2 for Eclipse
 * @Generated
 */
public privileged aspect DefAspect59 {

    /* Declares a new super class containing the new attribute or method. */
    declare parents : testpackage.Class1 extends testpackage.constraints.ExtendedClass1;
    
    /**
     * <p>Pointcut for all calls on {@link testpackage.Class1#testOclAnyOclType(Object source)}.</p>
     */
    protected pointcut testOclAnyOclTypeCaller(testpackage.Class1 aClass, Object source):
    	call(* testpackage.Class1.testOclAnyOclType(Object))
    	&& target(aClass) && args(source);
    
    /**
     * <p>Defines the method testOclAnyOclType(Object source) defined by the constraint
     * <code>context Class1
     *       def: testOclAnyOclType = source[].oclType()</code></p>
     */
    Class around(testpackage.Class1 aClass, Object source): testOclAnyOclTypeCaller(aClass, source) {
        return source.getClass();
    }
}