package testpackage.constraints;

/**
 * <p>Generated Aspect to enforce OCL constraint.</p>
 *
 * @author OCL22Java of Dresden OCL2 for Eclipse
 * @Generated
 */
public privileged aspect DefAspect23 {

    /* Declares a new super class containing the new attribute or method. */
    declare parents : testpackage.Class1 extends testpackage.constraints.ExtendedClass1;
    
    /**
     * <p>Pointcut for all calls on {@link testpackage.Class1#testSetFlatten(java.util.Set<Object> source)}.</p>
     */
    protected pointcut testSetFlattenCaller(testpackage.Class1 aClass, java.util.Set<Object> source):
    	call(* testpackage.Class1.testSetFlatten(java.util.Set<Object>))
    	&& target(aClass) && args(source);
    
    /**
     * <p>Defines the method testSetFlatten(java.util.Set<Object> source) defined by the constraint
     * <code>context Class1
     *       def: testSetFlatten = source[].flatten()</code></p>
     */
    java.util.Set<Object> around(testpackage.Class1 aClass, java.util.Set<Object> source): testSetFlattenCaller(aClass, source) {
        return (java.util.Set<Object>) tudresden.ocl20.pivot.ocl22java.types.util.OclSets.flatten(source);
    }
}