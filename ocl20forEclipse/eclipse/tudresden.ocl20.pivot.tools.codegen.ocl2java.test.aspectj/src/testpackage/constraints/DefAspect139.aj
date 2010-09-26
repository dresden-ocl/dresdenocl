package testpackage.constraints;

/**
 * <p>Generated Aspect to enforce OCL constraint.</p>
 *
 * @author OCL22Java of Dresden OCL2 for Eclipse
 * @Generated
 */
public privileged aspect DefAspect139 {

    /* Declares a new super class containing the new attribute or method. */
    declare parents : testpackage.Class1 extends testpackage.constraints.ExtendedClass1;
    
    /**
     * <p>Pointcut for all calls on {@link testpackage.Class1#testSequenceLast(java.util.List<Object> source)}.</p>
     */
    protected pointcut testSequenceLastCaller(testpackage.Class1 aClass, java.util.List<Object> source):
    	call(* testpackage.Class1.testSequenceLast(java.util.List<Object>))
    	&& target(aClass) && args(source);
    
    /**
     * <p>Defines the method testSequenceLast(java.util.List<Object> source) defined by the constraint
     * <code>context Class1
     *       def: testSequenceLast(source: Sequence(OclAny)): OclAny =
    source ->last()</code></p>
     */
    Object around(testpackage.Class1 aClass, java.util.List<Object> source): testSequenceLastCaller(aClass, source) {
        return tudresden.ocl20.pivot.tools.codegen.ocl2java.types.util.OclSequences.last(source);
    }
}