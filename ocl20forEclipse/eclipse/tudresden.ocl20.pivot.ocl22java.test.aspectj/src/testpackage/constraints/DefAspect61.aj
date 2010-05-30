package testpackage.constraints;

/**
 * <p>Generated Aspect to enforce OCL constraint.</p>
 *
 * @author OCL22Java of Dresden OCL2 for Eclipse
 * @Generated
 */
public privileged aspect DefAspect61 {

    /* Declares a new super class containing the new attribute or method. */
    declare parents : testpackage.Class1 extends testpackage.constraints.ExtendedClass1;
    
    /**
     * <p>Pointcut for all calls on {@link testpackage.Class1#testOrderedSetIndexOf01(java.util.List<Object> source, Object arg01)}.</p>
     */
    protected pointcut testOrderedSetIndexOf01Caller(testpackage.Class1 aClass, java.util.List<Object> source, Object arg01):
    	call(* testpackage.Class1.testOrderedSetIndexOf01(java.util.List<Object>, Object))
    	&& target(aClass) && args(source, arg01);
    
    /**
     * <p>Defines the method testOrderedSetIndexOf01(java.util.List<Object> source, Object arg01) defined by the constraint
     * <code>context Class1
     *       def: testOrderedSetIndexOf01 = source[].indexOf( arg01[])</code></p>
     */
    Integer around(testpackage.Class1 aClass, java.util.List<Object> source, Object arg01): testOrderedSetIndexOf01Caller(aClass, source, arg01) {
        return tudresden.ocl20.pivot.ocl22java.types.util.OclOrderedSets.indexOf(source, arg01);
    }
}