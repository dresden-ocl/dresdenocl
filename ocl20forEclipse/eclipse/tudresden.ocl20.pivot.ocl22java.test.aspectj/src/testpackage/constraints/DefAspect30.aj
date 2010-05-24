package testpackage.constraints;

/**
 * <p>Generated Aspect to enforce OCL constraint.</p>
 *
 * @author OCL22Java of Dresden OCL2 for Eclipse
 * @Generated
 */
public privileged aspect DefAspect30 {

    /* Declares a new super class containing the new attribute or method. */
    declare parents : testpackage.Class1 extends testpackage.constraints.ExtendedClass1;
    
    /**
     * <p>Pointcut for all calls on {@link testpackage.Class1#testCollectionMin(java.util.Collection<Object> source)}.</p>
     */
    protected pointcut testCollectionMinCaller(testpackage.Class1 aClass, java.util.Collection<Object> source):
    	call(* testpackage.Class1.testCollectionMin(java.util.Collection<Object>))
    	&& target(aClass) && args(source);
    
    /**
     * <p>Defines the method testCollectionMin(java.util.Collection<Object> source) defined by the constraint
     * <code>context Class1
     *       def: testCollectionMin = source[].min()</code></p>
     */
    Object around(testpackage.Class1 aClass, java.util.Collection<Object> source): testCollectionMinCaller(aClass, source) {
        return tudresden.ocl20.pivot.ocl22java.types.util.OclCollections.min(source);
    }
}