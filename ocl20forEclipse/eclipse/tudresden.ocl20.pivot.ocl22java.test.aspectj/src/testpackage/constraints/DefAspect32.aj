package testpackage.constraints;

/**
 * <p>Generated Aspect to enforce OCL constraint.</p>
 *
 * @author OCL22Java of Dresden OCL2 for Eclipse
 * @Generated
 */
public privileged aspect DefAspect32 {

    /* Declares a new super class containing the new attribute or method. */
    declare parents : testpackage.Class1 extends testpackage.constraints.ExtendedClass1;
    
    /**
     * <p>Pointcut for all calls on {@link testpackage.Class1#testStringToLowerCase(String source)}.</p>
     */
    protected pointcut testStringToLowerCaseCaller(testpackage.Class1 aClass, String source):
    	call(* testpackage.Class1.testStringToLowerCase(String))
    	&& target(aClass) && args(source);
    
    /**
     * <p>Defines the method testStringToLowerCase(String source) defined by the constraint
     * <code>context Class1
     *       def: testStringToLowerCase = source[].toLowerCase()</code></p>
     */
    String around(testpackage.Class1 aClass, String source): testStringToLowerCaseCaller(aClass, source) {
        return source.toLowerCase();
    }
}