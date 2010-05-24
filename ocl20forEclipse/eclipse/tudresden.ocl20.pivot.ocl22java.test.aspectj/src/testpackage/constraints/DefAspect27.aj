package testpackage.constraints;

/**
 * <p>Generated Aspect to enforce OCL constraint.</p>
 *
 * @author OCL22Java of Dresden OCL2 for Eclipse
 * @Generated
 */
public privileged aspect DefAspect27 {

    /* Declares a new super class containing the new attribute or method. */
    declare parents : testpackage.Class1 extends testpackage.constraints.ExtendedClass1;
    
    /**
     * <p>Pointcut for all calls on {@link testpackage.Class1#testCollectionIncludes(java.util.Collection<Object> source, Object arg01)}.</p>
     */
    protected pointcut testCollectionIncludesCaller(testpackage.Class1 aClass, java.util.Collection<Object> source, Object arg01):
    	call(* testpackage.Class1.testCollectionIncludes(java.util.Collection<Object>, Object))
    	&& target(aClass) && args(source, arg01);
    
    /**
     * <p>Defines the method testCollectionIncludes(java.util.Collection<Object> source, Object arg01) defined by the constraint
     * <code>context Class1
     *       def: testCollectionIncludes = source[].includes( arg01[])</code></p>
     */
    Boolean around(testpackage.Class1 aClass, java.util.Collection<Object> source, Object arg01): testCollectionIncludesCaller(aClass, source, arg01) {
        return tudresden.ocl20.pivot.ocl22java.types.util.OclCollections.includes(source, arg01);
    }
}