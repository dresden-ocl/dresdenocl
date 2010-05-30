package testpackage.constraints;

/**
 * <p>Generated Aspect to enforce OCL constraint.</p>
 *
 * @author OCL22Java of Dresden OCL2 for Eclipse
 * @Generated
 */
public privileged aspect DefAspect64 {

    /* Declares a new super class containing the new attribute or method. */
    declare parents : testpackage.Class1 extends testpackage.constraints.ExtendedClass1;
    
    /**
     * <p>Pointcut for all calls on {@link testpackage.Class1#testOrderedSetPrepend(java.util.List<Object> source, Object arg01)}.</p>
     */
    protected pointcut testOrderedSetPrependCaller(testpackage.Class1 aClass, java.util.List<Object> source, Object arg01):
    	call(* testpackage.Class1.testOrderedSetPrepend(java.util.List<Object>, Object))
    	&& target(aClass) && args(source, arg01);
    
    /**
     * <p>Defines the method testOrderedSetPrepend(java.util.List<Object> source, Object arg01) defined by the constraint
     * <code>context Class1
     *       def: testOrderedSetPrepend = source[].prepend( arg01[])</code></p>
     */
    java.util.List<Object> around(testpackage.Class1 aClass, java.util.List<Object> source, Object arg01): testOrderedSetPrependCaller(aClass, source, arg01) {
        return tudresden.ocl20.pivot.ocl22java.types.util.OclOrderedSets.prepend(source, arg01);
    }
}