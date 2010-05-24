package testpackage.constraints;

/**
 * <p>Generated Aspect to enforce OCL constraint.</p>
 *
 * @author OCL22Java of Dresden OCL2 for Eclipse
 * @Generated
 */
public privileged aspect DefAspect91 {

    /* Declares a new super class containing the new attribute or method. */
    declare parents : testpackage.Class1 extends testpackage.constraints.ExtendedClass1;
    
    /**
     * <p>Pointcut for all calls on {@link testpackage.Class1#testStringIndexOf(String source, String arg01)}.</p>
     */
    protected pointcut testStringIndexOfCaller(testpackage.Class1 aClass, String source, String arg01):
    	call(* testpackage.Class1.testStringIndexOf(String, String))
    	&& target(aClass) && args(source, arg01);
    
    /**
     * <p>Defines the method testStringIndexOf(String source, String arg01) defined by the constraint
     * <code>context Class1
     *       def: testStringIndexOf = source[].indexOf( arg01[])</code></p>
     */
    Integer around(testpackage.Class1 aClass, String source, String arg01): testStringIndexOfCaller(aClass, source, arg01) {
        return source.indexOf(arg01) + 1;
    }
}