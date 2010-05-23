package testpackage.constraints;

/**
 * <p>Generated Aspect to enforce OCL constraint.</p>
 *
 * @author OCL22Java of Dresden OCL2 for Eclipse
 * @Generated
 */
public privileged aspect DefAspect14 {

    /* Declares a new super class containing the new attribute or method. */
    declare parents : testpackage.Class1 extends testpackage.constraints.ExtendedClass1;
    
    /**
     * <p>Pointcut for all calls on {@link testpackage.Class1#testCollectionNotEmpty(java.util.Collection<Object> source)}.</p>
     */
    protected pointcut testCollectionNotEmptyCaller(testpackage.Class1 aClass, java.util.Collection<Object> source):
    	call(* testpackage.Class1.testCollectionNotEmpty(java.util.Collection<Object>))
    	&& target(aClass) && args(source);
    
    /**
     * <p>Defines the method testCollectionNotEmpty(java.util.Collection<Object> source) defined by the constraint
     * <code>context Class1
     *       def: testCollectionNotEmpty = source[].notEmpty()</code></p>
     */
    Boolean around(testpackage.Class1 aClass, java.util.Collection<Object> source): testCollectionNotEmptyCaller(aClass, source) {
        return tudresden.ocl20.pivot.ocl22java.types.util.OclCollections.notEmpty(source);
    }
}