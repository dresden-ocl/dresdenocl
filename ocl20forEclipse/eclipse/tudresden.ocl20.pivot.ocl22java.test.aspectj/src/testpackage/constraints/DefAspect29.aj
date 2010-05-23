package testpackage.constraints;

/**
 * <p>Generated Aspect to enforce OCL constraint.</p>
 *
 * @author OCL22Java of Dresden OCL2 for Eclipse
 * @Generated
 */
public privileged aspect DefAspect29 {

    /* Declares a new super class containing the new attribute or method. */
    declare parents : testpackage.Class1 extends testpackage.constraints.ExtendedClass1;
    
    /**
     * <p>Pointcut for all calls on {@link testpackage.Class1#testStringPlus(String source, String arg01)}.</p>
     */
    protected pointcut testStringPlusCaller(testpackage.Class1 aClass, String source, String arg01):
    	call(* testpackage.Class1.testStringPlus(String, String))
    	&& target(aClass) && args(source, arg01);
    
    /**
     * <p>Defines the method testStringPlus(String source, String arg01) defined by the constraint
     * <code>context Class1
     *       def: testStringPlus = source[].+( arg01[])</code></p>
     */
    String around(testpackage.Class1 aClass, String source, String arg01): testStringPlusCaller(aClass, source, arg01) {
        return source.concat(arg01);
    }
}