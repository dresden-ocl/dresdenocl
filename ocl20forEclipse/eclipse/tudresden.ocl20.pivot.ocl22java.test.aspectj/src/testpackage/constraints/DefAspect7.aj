package testpackage.constraints;

/**
 * <p>Generated Aspect to enforce OCL constraint.</p>
 *
 * @author OCL22Java of Dresden OCL2 for Eclipse
 * @Generated
 */
public privileged aspect DefAspect7 {

    /* Declares a new super class containing the new attribute or method. */
    declare parents : testpackage.Class1 extends testpackage.constraints.ExtendedClass1;
    
    /**
     * <p>Pointcut for all calls on {@link testpackage.Class1#testCollectionProduct(tudresden.ocl20.pivot.ocl2java.types.OclCollection<Object> source, tudresden.ocl20.pivot.ocl2java.types.OclCollection<Object> arg01)}.</p>
     */
    protected pointcut testCollectionProductCaller(testpackage.Class1 aClass, tudresden.ocl20.pivot.ocl2java.types.OclCollection<Object> source, tudresden.ocl20.pivot.ocl2java.types.OclCollection<Object> arg01):
    	call(* testpackage.Class1.testCollectionProduct(tudresden.ocl20.pivot.ocl2java.types.OclCollection<Object>, tudresden.ocl20.pivot.ocl2java.types.OclCollection<Object>))
    	&& target(aClass) && args(source, arg01);
    
    /**
     * <p>Defines the method testCollectionProduct(tudresden.ocl20.pivot.ocl2java.types.OclCollection<Object> source, tudresden.ocl20.pivot.ocl2java.types.OclCollection<Object> arg01) defined by the constraint
     * <code>context Class1
     *       def: testCollectionProduct = source[].product( arg01[]).size()</code></p>
     */
    Integer around(testpackage.Class1 aClass, tudresden.ocl20.pivot.ocl2java.types.OclCollection<Object> source, tudresden.ocl20.pivot.ocl2java.types.OclCollection<Object> arg01): testCollectionProductCaller(aClass, source, arg01) {
        return source.product(arg01).size();
    }
}