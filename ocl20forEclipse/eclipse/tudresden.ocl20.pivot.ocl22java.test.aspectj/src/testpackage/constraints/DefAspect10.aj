package testpackage.constraints;

/**
 * <p>Generated Aspect to enforce OCL constraint.</p>
 *
 * @author OCL22Java of Dresden OCL2 for Eclipse
 * @Generated
 */
public privileged aspect DefAspect10 {

    /* Declares a new super class containing the new attribute or method. */
    declare parents : testpackage.Class1 extends testpackage.constraints.ExtendedClass1;
    
    /**
     * <p>Pointcut for all calls on {@link testpackage.Class1#testStringAt(String source, Integer arg01)}.</p>
     */
    protected pointcut testStringAtCaller(testpackage.Class1 aClass, String source, Integer arg01):
    	call(* testpackage.Class1.testStringAt(String, Integer))
    	&& target(aClass) && args(source, arg01);
    
    /**
     * <p>Defines the method testStringAt(String source, Integer arg01) defined by the constraint
     * <code>context Class1
     *       def: testStringAt = source[].at( arg01[])</code></p>
     */
    String around(testpackage.Class1 aClass, String source, Integer arg01): testStringAtCaller(aClass, source, arg01) {
        return Character.toString(source.charAt(arg01 - 1));
    }
}