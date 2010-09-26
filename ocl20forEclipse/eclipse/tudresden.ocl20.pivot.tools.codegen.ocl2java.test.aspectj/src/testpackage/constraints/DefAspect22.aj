package testpackage.constraints;

/**
 * <p>Generated Aspect to enforce OCL constraint.</p>
 *
 * @author OCL22Java of Dresden OCL2 for Eclipse
 * @Generated
 */
public privileged aspect DefAspect22 {

    /* Declares a new super class containing the new attribute or method. */
    declare parents : testpackage.Class1 extends testpackage.constraints.ExtendedClass1;
    
    /**
     * <p>Pointcut for all calls on {@link testpackage.Class1#testBooleanLiteralExp02()}.</p>
     */
    protected pointcut testBooleanLiteralExp02Caller(testpackage.Class1 aClass):
    	call(* testpackage.Class1.testBooleanLiteralExp02())
    	&& target(aClass);
    
    /**
     * <p>Defines the method testBooleanLiteralExp02() defined by the constraint
     * <code>context Class1
     *       def: testBooleanLiteralExp02(): 
      Boolean =
    false</code></p>
     */
    Boolean around(testpackage.Class1 aClass): testBooleanLiteralExp02Caller(aClass) {
        return new Boolean(false);
    }
}