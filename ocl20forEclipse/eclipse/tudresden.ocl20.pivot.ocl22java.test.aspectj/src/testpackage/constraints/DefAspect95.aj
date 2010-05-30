package testpackage.constraints;

/**
 * <p>Generated Aspect to enforce OCL constraint.</p>
 *
 * @author OCL22Java of Dresden OCL2 for Eclipse
 * @Generated
 */
public privileged aspect DefAspect95 {

    /* Declares a new super class containing the new attribute or method. */
    declare parents : testpackage.Class1 extends testpackage.constraints.ExtendedClass1;
    
    /**
     * <p>Pointcut for all calls on {@link testpackage.Class1#testSequenceInsertAt(java.util.List<Object> source, Integer arg01, Object arg02)}.</p>
     */
    protected pointcut testSequenceInsertAtCaller(testpackage.Class1 aClass, java.util.List<Object> source, Integer arg01, Object arg02):
    	call(* testpackage.Class1.testSequenceInsertAt(java.util.List<Object>, Integer, Object))
    	&& target(aClass) && args(source, arg01, arg02);
    
    /**
     * <p>Defines the method testSequenceInsertAt(java.util.List<Object> source, Integer arg01, Object arg02) defined by the constraint
     * <code>context Class1
     *       def: testSequenceInsertAt = source[].insertAt( arg01[], arg02[])</code></p>
     */
    java.util.List<Object> around(testpackage.Class1 aClass, java.util.List<Object> source, Integer arg01, Object arg02): testSequenceInsertAtCaller(aClass, source, arg01, arg02) {
        return tudresden.ocl20.pivot.ocl22java.types.util.OclSequences.insertAt(source, arg01, arg02);
    }
}