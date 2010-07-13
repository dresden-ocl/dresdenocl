package testpackage.constraints;

/**
 * <p>Generated Aspect to enforce OCL constraint.</p>
 *
 * @author OCL22Java of Dresden OCL2 for Eclipse
 * @Generated
 */
public privileged aspect DefAspect39 {

    /* Declares a new super class containing the new attribute or method. */
    declare parents : testpackage.Class1 extends testpackage.constraints.ExtendedClass1;
    
    /**
     * <p>Pointcut for all calls on {@link testpackage.Class1#testCollectionProduct(java.util.Collection<Object> source, java.util.Collection<Object> arg01)}.</p>
     */
    protected pointcut testCollectionProductCaller(testpackage.Class1 aClass, java.util.Collection<Object> source, java.util.Collection<Object> arg01):
    	call(* testpackage.Class1.testCollectionProduct(java.util.Collection<Object>, java.util.Collection<Object>))
    	&& target(aClass) && args(source, arg01);
    
    /**
     * <p>Defines the method testCollectionProduct(java.util.Collection<Object> source, java.util.Collection<Object> arg01) defined by the constraint
     * <code>context Class1
     *       def: testCollectionProduct = source[].product( arg01[]).size()</code></p>
     */
    Integer around(testpackage.Class1 aClass, java.util.Collection<Object> source, java.util.Collection<Object> arg01): testCollectionProductCaller(aClass, source, arg01) {
        return tudresden.ocl20.pivot.ocl22java.types.util.OclCollections.size(tudresden.ocl20.pivot.ocl22java.types.util.OclCollections.product(source, arg01));
    }
}