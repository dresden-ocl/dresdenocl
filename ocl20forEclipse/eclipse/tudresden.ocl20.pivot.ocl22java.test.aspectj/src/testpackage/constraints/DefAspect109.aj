package testpackage.constraints;

/**
 * <p>Generated Aspect to enforce OCL constraint.</p>
 *
 * @author OCL22Java of Dresden OCL2 for Eclipse
 * @Generated
 */
public privileged aspect DefAspect109 {

    /* Declares a new super class containing the new attribute or method. */
    declare parents : testpackage.Class1 extends testpackage.constraints.ExtendedClass1;
    
    /**
     * <p>Pointcut for all calls on {@link testpackage.Class1#testSetIncluding01(java.util.Set<Object> source, Object arg01)}.</p>
     */
    protected pointcut testSetIncluding01Caller(testpackage.Class1 aClass, java.util.Set<Object> source, Object arg01):
    	call(* testpackage.Class1.testSetIncluding01(java.util.Set<Object>, Object))
    	&& target(aClass) && args(source, arg01);
    
    /**
     * <p>Defines the method testSetIncluding01(java.util.Set<Object> source, Object arg01) defined by the constraint
     * <code>context Class1
     *       def: testSetIncluding01 = source[].including( arg01[])</code></p>
     */
    java.util.Set<Object> around(testpackage.Class1 aClass, java.util.Set<Object> source, Object arg01): testSetIncluding01Caller(aClass, source, arg01) {
        return tudresden.ocl20.pivot.ocl22java.types.util.OclSets.including(source, arg01);
    }
}