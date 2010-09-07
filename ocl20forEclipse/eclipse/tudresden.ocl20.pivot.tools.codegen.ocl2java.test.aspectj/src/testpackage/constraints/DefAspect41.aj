package testpackage.constraints;

/**
 * <p>Generated Aspect to enforce OCL constraint.</p>
 *
 * @author OCL22Java of Dresden OCL2 for Eclipse
 * @Generated
 */
public privileged aspect DefAspect41 {

    /* Declares a new super class containing the new attribute or method. */
    declare parents : testpackage.Class1 extends testpackage.constraints.ExtendedClass1;
    
    /**
     * <p>Pointcut for all calls on {@link testpackage.Class1#testCollectionSum(java.util.Collection<Integer> source)}.</p>
     */
    protected pointcut testCollectionSumCaller(testpackage.Class1 aClass, java.util.Collection<Integer> source):
    	call(* testpackage.Class1.testCollectionSum(java.util.Collection<Integer>))
    	&& target(aClass) && args(source);
    
    /**
     * <p>Defines the method testCollectionSum(java.util.Collection<Integer> source) defined by the constraint
     * <code>context Class1
     *       def: testCollectionSum(source: Collection(Integer)): Integer =
    source ->sum()</code></p>
     */
    Integer around(testpackage.Class1 aClass, java.util.Collection<Integer> source): testCollectionSumCaller(aClass, source) {
        return new Integer(tudresden.ocl20.pivot.tools.codegen.ocl2java.types.util.OclCollections.sum(source).intValue());
    }
}