package testpackage.constraints;

/**
 * <p>Generated Aspect to enforce OCL constraint.</p>
 *
 * @author OCL22Java of Dresden OCL2 for Eclipse
 * @Generated
 */
public privileged aspect DefAspect124 {

    /* Declares a new super class containing the new attribute or method. */
    declare parents : testpackage.Class1 extends testpackage.constraints.ExtendedClass1;
    
    /**
     * <p>Pointcut for all calls on {@link testpackage.Class1#testStringConcat01(String source, String arg01)}.</p>
     */
    protected pointcut testStringConcat01Caller(testpackage.Class1 aClass, String source, String arg01):
    	call(* testpackage.Class1.testStringConcat01(String, String))
    	&& target(aClass) && args(source, arg01);
    
    /**
     * <p>Defines the method testStringConcat01(String source, String arg01) defined by the constraint
     * <code>context Class1
     *       def: testStringConcat01 = source[].concat( arg01[])</code></p>
     */
    String around(testpackage.Class1 aClass, String source, String arg01): testStringConcat01Caller(aClass, source, arg01) {
        return source.concat(arg01);
    }
}