package testpackage.constraints;

/**
 * <p>Generated Aspect to enforce OCL constraint.</p>
 *
 * @author OCL22Java of Dresden OCL2 for Eclipse
 * @Generated
 */
public privileged aspect testIntegerToString {

    /* Declares a new super class containing the new attribute or method. */
    declare parents : testpackage.Class1 extends testpackage.constraints.ExtendedClass1;
    
    /**
     * <p>Pointcut for all calls on {@link testpackage.Class1#testIntegerToString(Integer source)}.</p>
     */
    protected pointcut testIntegerToStringCaller(testpackage.Class1 aClass, Integer source):
    	call(* testpackage.Class1.testIntegerToString(Integer))
    	&& target(aClass) && args(source);
    
    /**
     * <p>Defines the method testIntegerToString(Integer source) defined by the constraint
     * <code>context Class1
     *       def: testIntegerToString(source: Integer): String =
    source.toString()</code></p>
     */
    String around(testpackage.Class1 aClass, Integer source): testIntegerToStringCaller(aClass, source) {
        return source.toString();
    }
}