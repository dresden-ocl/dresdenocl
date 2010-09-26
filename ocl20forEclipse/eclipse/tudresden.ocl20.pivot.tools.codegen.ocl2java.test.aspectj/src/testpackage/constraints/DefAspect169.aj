package testpackage.constraints;

/**
 * <p>Generated Aspect to enforce OCL constraint.</p>
 *
 * @author OCL22Java of Dresden OCL2 for Eclipse
 * @Generated
 */
public privileged aspect DefAspect169 {

    /* Declares a new super class containing the new attribute or method. */
    declare parents : testpackage.Class1 extends testpackage.constraints.ExtendedClass1;
    
    /**
     * <p>Pointcut for all calls on {@link testpackage.Class1#testStringToUpperCase(String source)}.</p>
     */
    protected pointcut testStringToUpperCaseCaller(testpackage.Class1 aClass, String source):
    	call(* testpackage.Class1.testStringToUpperCase(String))
    	&& target(aClass) && args(source);
    
    /**
     * <p>Defines the method testStringToUpperCase(String source) defined by the constraint
     * <code>context Class1
     *       def: testStringToUpperCase(source: String): String =
    source.toUpperCase()</code></p>
     */
    String around(testpackage.Class1 aClass, String source): testStringToUpperCaseCaller(aClass, source) {
        return source.toUpperCase();
    }
}