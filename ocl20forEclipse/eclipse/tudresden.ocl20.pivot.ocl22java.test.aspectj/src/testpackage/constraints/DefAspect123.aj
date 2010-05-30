package testpackage.constraints;

/**
 * <p>Generated Aspect to enforce OCL constraint.</p>
 *
 * @author OCL22Java of Dresden OCL2 for Eclipse
 * @Generated
 */
public privileged aspect DefAspect123 {

    /* Declares a new super class containing the new attribute or method. */
    declare parents : testpackage.Class1 extends testpackage.constraints.ExtendedClass1;
    
    /**
     * <p>Pointcut for all calls on {@link testpackage.Class1#testStringSubstring01(String source, Integer arg01, Integer arg02)}.</p>
     */
    protected pointcut testStringSubstring01Caller(testpackage.Class1 aClass, String source, Integer arg01, Integer arg02):
    	call(* testpackage.Class1.testStringSubstring01(String, Integer, Integer))
    	&& target(aClass) && args(source, arg01, arg02);
    
    /**
     * <p>Defines the method testStringSubstring01(String source, Integer arg01, Integer arg02) defined by the constraint
     * <code>context Class1
     *       def: testStringSubstring01 = source[].substring( arg01[], arg02[])</code></p>
     */
    String around(testpackage.Class1 aClass, String source, Integer arg01, Integer arg02): testStringSubstring01Caller(aClass, source, arg01, arg02) {
        return source.substring(arg01 - 1, arg02);
    }
}