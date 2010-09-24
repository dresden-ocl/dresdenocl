package testpackage.constraints;

/**
 * <p>Generated Aspect to enforce OCL constraint.</p>
 *
 * @author OCL22Java of Dresden OCL2 for Eclipse
 * @Generated
 */
public privileged aspect DefAspect130 {

    /* Declares a new super class containing the new attribute or method. */
    declare parents : testpackage.Class1 extends testpackage.constraints.ExtendedClass1;
    
    /**
     * <p>Pointcut for all calls on {@link testpackage.Class1#testSequenceSubSequence(java.util.List<Object> source, Integer arg01, Integer arg02)}.</p>
     */
    protected pointcut testSequenceSubSequenceCaller(testpackage.Class1 aClass, java.util.List<Object> source, Integer arg01, Integer arg02):
    	call(* testpackage.Class1.testSequenceSubSequence(java.util.List<Object>, Integer, Integer))
    	&& target(aClass) && args(source, arg01, arg02);
    
    /**
     * <p>Defines the method testSequenceSubSequence(java.util.List<Object> source, Integer arg01, Integer arg02) defined by the constraint
     * <code>context Class1
     *       def: testSequenceSubSequence(source: Sequence(OclAny), arg01: Integer, arg02: Integer): Sequence(OclAny) =
    source ->subSequence(arg01, arg02)</code></p>
     */
    java.util.List<Object> around(testpackage.Class1 aClass, java.util.List<Object> source, Integer arg01, Integer arg02): testSequenceSubSequenceCaller(aClass, source, arg01, arg02) {
        return tudresden.ocl20.pivot.tools.codegen.ocl2java.types.util.OclSequences.subSequence(source, arg01, arg02);
    }
}