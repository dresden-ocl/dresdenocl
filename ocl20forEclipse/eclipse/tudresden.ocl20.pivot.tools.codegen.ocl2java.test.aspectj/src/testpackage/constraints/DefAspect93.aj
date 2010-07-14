package testpackage.constraints;

/**
 * <p>Generated Aspect to enforce OCL constraint.</p>
 *
 * @author OCL22Java of Dresden OCL2 for Eclipse
 * @Generated
 */
public privileged aspect DefAspect93 {

    /* Declares a new super class containing the new attribute or method. */
    declare parents : testpackage.Class1 extends testpackage.constraints.ExtendedClass1;
    
    /**
     * <p>Pointcut for all calls on {@link testpackage.Class1#testSequenceAt(java.util.List<Object> source, Integer arg01)}.</p>
     */
    protected pointcut testSequenceAtCaller(testpackage.Class1 aClass, java.util.List<Object> source, Integer arg01):
    	call(* testpackage.Class1.testSequenceAt(java.util.List<Object>, Integer))
    	&& target(aClass) && args(source, arg01);
    
    /**
     * <p>Defines the method testSequenceAt(java.util.List<Object> source, Integer arg01) defined by the constraint
     * <code>context Class1
     *       def: testSequenceAt = source[].at( arg01[])</code></p>
     */
    Object around(testpackage.Class1 aClass, java.util.List<Object> source, Integer arg01): testSequenceAtCaller(aClass, source, arg01) {
        return tudresden.ocl20.pivot.tools.codegen.ocl2java.types.util.OclSequences.at(source, arg01);
    }
}