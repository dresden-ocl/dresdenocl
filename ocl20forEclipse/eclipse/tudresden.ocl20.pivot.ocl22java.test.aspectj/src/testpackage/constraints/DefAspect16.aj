package testpackage.constraints;

/**
 * <p>Generated Aspect to enforce OCL constraint.</p>
 *
 * @author OCL22Java of Dresden OCL2 for Eclipse
 * @Generated
 */
public privileged aspect DefAspect16 {

    /* Declares a new super class containing the new attribute or method. */
    declare parents : testpackage.Class1 extends testpackage.constraints.ExtendedClass1;
    
    /**
     * <p>Pointcut for all calls on {@link testpackage.Class1#testStringEqualsIgnoreCase(String source, String arg01)}.</p>
     */
    protected pointcut testStringEqualsIgnoreCaseCaller(testpackage.Class1 aClass, String source, String arg01):
    	call(* testpackage.Class1.testStringEqualsIgnoreCase(String, String))
    	&& target(aClass) && args(source, arg01);
    
    /**
     * <p>Defines the method testStringEqualsIgnoreCase(String source, String arg01) defined by the constraint
     * <code>context Class1
     *       def: testStringEqualsIgnoreCase = source[].equalsIgnoreCase( arg01[])</code></p>
     */
    Boolean around(testpackage.Class1 aClass, String source, String arg01): testStringEqualsIgnoreCaseCaller(aClass, source, arg01) {
        return source.equalsIgnoreCase(arg01);
    }
}