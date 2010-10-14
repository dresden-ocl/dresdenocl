package testpackage.constraints;

/**
 * <p>Generated Aspect to enforce OCL constraint.</p>
 *
 * @author OCL22Java of Dresden OCL2 for Eclipse
 * @Generated
 */
public privileged aspect testOclAsType01 {

    /* Declares a new super class containing the new attribute or method. */
    declare parents : testpackage.Class1 extends testpackage.constraints.ExtendedClass1;
    
    /**
     * <p>Pointcut for all calls on {@link testpackage.Class1#testOclAsType01(testpackage.Class1 source)}.</p>
     */
    protected pointcut testOclAsType01Caller(testpackage.Class1 aClass, testpackage.Class1 source):
    	call(* testpackage.Class1.testOclAsType01(testpackage.Class1))
    	&& target(aClass) && args(source);
    
    /**
     * <p>Defines the method testOclAsType01(testpackage.Class1 source) defined by the constraint
     * <code>context Class1
     *       def: testOclAsType01(source: Class1): Class1 =
    source.oclAsType(Class1)</code></p>
     */
    testpackage.Class1 around(testpackage.Class1 aClass, testpackage.Class1 source): testOclAsType01Caller(aClass, source) {
        return ((testpackage.Class1) source);
    }
}