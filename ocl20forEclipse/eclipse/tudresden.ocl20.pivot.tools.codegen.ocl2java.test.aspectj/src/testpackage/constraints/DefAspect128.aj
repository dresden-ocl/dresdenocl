package testpackage.constraints;

/**
 * <p>Generated Aspect to enforce OCL constraint.</p>
 *
 * @author OCL22Java of Dresden OCL2 for Eclipse
 * @Generated
 */
public privileged aspect DefAspect128 {

    /* Declares a new super class containing the new attribute or method. */
    declare parents : testpackage.Class1 extends testpackage.constraints.ExtendedClass1;
    
    /**
     * <p>Pointcut for all calls on {@link testpackage.Class1#testStringSize01(String source)}.</p>
     */
    protected pointcut testStringSize01Caller(testpackage.Class1 aClass, String source):
    	call(* testpackage.Class1.testStringSize01(String))
    	&& target(aClass) && args(source);
    
    /**
     * <p>Defines the method testStringSize01(String source) defined by the constraint
     * <code>context Class1
     *       def: testStringSize01 = source[].size()</code></p>
     */
    Integer around(testpackage.Class1 aClass, String source): testStringSize01Caller(aClass, source) {
        return source.length();
    }
}