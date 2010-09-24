package testpackage.constraints;

/**
 * <p>Generated Aspect to enforce OCL constraint.</p>
 *
 * @author OCL22Java of Dresden OCL2 for Eclipse
 * @Generated
 */
public privileged aspect DefAspect134 {

    /* Declares a new super class containing the new attribute or method. */
    declare parents : testpackage.Class1 extends testpackage.constraints.ExtendedClass1;
    
    /**
     * <p>Pointcut for all calls on {@link testpackage.Class1#testSetAsSequence(java.util.Set<Object> source)}.</p>
     */
    protected pointcut testSetAsSequenceCaller(testpackage.Class1 aClass, java.util.Set<Object> source):
    	call(* testpackage.Class1.testSetAsSequence(java.util.Set<Object>))
    	&& target(aClass) && args(source);
    
    /**
     * <p>Defines the method testSetAsSequence(java.util.Set<Object> source) defined by the constraint
     * <code>context Class1
     *       def: testSetAsSequence(source: Set(OclAny)): 
      Sequence(OclAny) =
    source ->asSequence()</code></p>
     */
    java.util.List<Object> around(testpackage.Class1 aClass, java.util.Set<Object> source): testSetAsSequenceCaller(aClass, source) {
        return tudresden.ocl20.pivot.tools.codegen.ocl2java.types.util.OclSets.asSequence(source);
    }
}