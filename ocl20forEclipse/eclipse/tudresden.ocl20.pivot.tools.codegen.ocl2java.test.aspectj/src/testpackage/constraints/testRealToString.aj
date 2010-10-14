package testpackage.constraints;

/**
 * <p>Generated Aspect to enforce OCL constraint.</p>
 *
 * @author OCL22Java of Dresden OCL2 for Eclipse
 * @Generated
 */
public privileged aspect testRealToString {

    /* Declares a new super class containing the new attribute or method. */
    declare parents : testpackage.Class1 extends testpackage.constraints.ExtendedClass1;
    
    /**
     * <p>Pointcut for all calls on {@link testpackage.Class1#testRealToString(Float source)}.</p>
     */
    protected pointcut testRealToStringCaller(testpackage.Class1 aClass, Float source):
    	call(* testpackage.Class1.testRealToString(Float))
    	&& target(aClass) && args(source);
    
    /**
     * <p>Defines the method testRealToString(Float source) defined by the constraint
     * <code>context Class1
     *       def: testRealToString(source: Real): String =
    source.toString()</code></p>
     */
    String around(testpackage.Class1 aClass, Float source): testRealToStringCaller(aClass, source) {
        return source.toString();
    }
}