package testpackage.constraints;

/**
 * <p>Generated Aspect to enforce OCL constraint.</p>
 *
 * @author OCL22Java of Dresden OCL2 for Eclipse
 * @Generated
 */
public privileged aspect testIntegerMod01 {

    /* Declares a new super class containing the new attribute or method. */
    declare parents : testpackage.Class1 extends testpackage.constraints.ExtendedClass1;
    
    /**
     * <p>Pointcut for all calls on {@link testpackage.Class1#testIntegerMod01(Integer source, Integer arg01)}.</p>
     */
    protected pointcut testIntegerMod01Caller(testpackage.Class1 aClass, Integer source, Integer arg01):
    	call(* testpackage.Class1.testIntegerMod01(Integer, Integer))
    	&& target(aClass) && args(source, arg01);
    
    /**
     * <p>Defines the method testIntegerMod01(Integer source, Integer arg01) defined by the constraint
     * <code>context Class1
     *       def: testIntegerMod01(source: Integer, arg01: Integer): Integer =
    source.mod(arg01)</code></p>
     */
    Integer around(testpackage.Class1 aClass, Integer source, Integer arg01): testIntegerMod01Caller(aClass, source, arg01) {
        return (source % arg01);
    }
}