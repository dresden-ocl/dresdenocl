package testpackage.constraints;

/**
 * <p>Generated Aspect to enforce OCL constraint.</p>
 *
 * @author OCL22Java of Dresden OCL2 for Eclipse
 * @Generated
 */
public privileged aspect testSequenceEquals01 {

    /* Declares a new super class containing the new attribute or method. */
    declare parents : testpackage.Class1 extends testpackage.constraints.ExtendedClass1;
    
    /**
     * <p>Pointcut for all calls on {@link testpackage.Class1#testSequenceEquals01(java.util.List<Object> source, java.util.List<Object> arg01)}.</p>
     */
    protected pointcut testSequenceEquals01Caller(testpackage.Class1 aClass, java.util.List<Object> source, java.util.List<Object> arg01):
    	call(* testpackage.Class1.testSequenceEquals01(java.util.List<Object>, java.util.List<Object>))
    	&& target(aClass) && args(source, arg01);
    
    /**
     * <p>Defines the method testSequenceEquals01(java.util.List<Object> source, java.util.List<Object> arg01) defined by the constraint
     * <code>context Class1
     *       def: testSequenceEquals01(source: Sequence(OclAny), arg01: Sequence(OclAny)): Boolean =
    source = arg01</code></p>
     */
    Boolean around(testpackage.Class1 aClass, java.util.List<Object> source, java.util.List<Object> arg01): testSequenceEquals01Caller(aClass, source, arg01) {
        return tudresden.ocl20.pivot.tools.codegen.ocl2java.types.util.OclSequences.equals(source, arg01);
    }
}