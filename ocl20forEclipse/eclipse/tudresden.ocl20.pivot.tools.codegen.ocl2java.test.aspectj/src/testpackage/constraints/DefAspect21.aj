package testpackage.constraints;

/**
 * <p>Generated Aspect to enforce OCL constraint.</p>
 *
 * @author OCL22Java of Dresden OCL2 for Eclipse
 * @Generated
 */
public privileged aspect DefAspect21 {

    /* Declares a new super class containing the new attribute or method. */
    declare parents : testpackage.Class1 extends testpackage.constraints.ExtendedClass1;
    
    /**
     * <p>Pointcut for all calls on {@link testpackage.Class1#testBooleanToString(Boolean source)}.</p>
     */
    protected pointcut testBooleanToStringCaller(testpackage.Class1 aClass, Boolean source):
    	call(* testpackage.Class1.testBooleanToString(Boolean))
    	&& target(aClass) && args(source);
    
    /**
     * <p>Defines the method testBooleanToString(Boolean source) defined by the constraint
     * <code>context Class1
     *       def: testBooleanToString = source[].toString()</code></p>
     */
    String around(testpackage.Class1 aClass, Boolean source): testBooleanToStringCaller(aClass, source) {
        return source.toString();
    }
}