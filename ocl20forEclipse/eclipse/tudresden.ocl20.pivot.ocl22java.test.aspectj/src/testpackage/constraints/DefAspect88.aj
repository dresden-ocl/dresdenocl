package testpackage.constraints;

/**
 * <p>Generated Aspect to enforce OCL constraint.</p>
 *
 * @author OCL22Java of Dresden OCL2 for Eclipse
 * @Generated
 */
public privileged aspect DefAspect88 {

    /* Declares a new super class containing the new attribute or method. */
    declare parents : testpackage.Class1 extends testpackage.constraints.ExtendedClass1;
    
    /**
     * <p>Pointcut for all calls on {@link testpackage.Class1#testSequenceCount01(java.util.List<Object> source, Object arg01)}.</p>
     */
    protected pointcut testSequenceCount01Caller(testpackage.Class1 aClass, java.util.List<Object> source, Object arg01):
    	call(* testpackage.Class1.testSequenceCount01(java.util.List<Object>, Object))
    	&& target(aClass) && args(source, arg01);
    
    /**
     * <p>Defines the method testSequenceCount01(java.util.List<Object> source, Object arg01) defined by the constraint
     * <code>context Class1
     *       def: testSequenceCount01 = source[].count( arg01[])</code></p>
     */
    Integer around(testpackage.Class1 aClass, java.util.List<Object> source, Object arg01): testSequenceCount01Caller(aClass, source, arg01) {
        return tudresden.ocl20.pivot.ocl22java.types.util.OclSequences.count(source, arg01);
    }
}