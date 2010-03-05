package testpackage.constraints;

/**
 * <p>Generated Aspect to enforce OCL constraint.</p>
 *
 * @author OCL22Java of Dresden OCL2 for Eclipse
 * @Generated
 */
public privileged aspect DefAspect12 {

    /* Declares a new super class containing the new attribute or method. */
    declare parents : testpackage.Class1 extends testpackage.constraints.ExtendedClass1;
    
    /**
     * <p>Pointcut for all calls on {@link testpackage.Class1#testSetFlatten(tudresden.ocl20.pivot.ocl2java.types.OclSet<Object> source)}.</p>
     */
    protected pointcut testSetFlattenCaller(testpackage.Class1 aClass, tudresden.ocl20.pivot.ocl2java.types.OclSet<Object> source):
    	call(* testpackage.Class1.testSetFlatten(tudresden.ocl20.pivot.ocl2java.types.OclSet<Object>))
    	&& target(aClass) && args(source);
    
    /**
     * <p>Defines the method testSetFlatten(tudresden.ocl20.pivot.ocl2java.types.OclSet<Object> source) defined by the constraint
     * <code>context Class1
     *       def: testSetFlatten = source[].flatten()</code></p>
     */
    tudresden.ocl20.pivot.ocl2java.types.OclSet<Object> around(testpackage.Class1 aClass, tudresden.ocl20.pivot.ocl2java.types.OclSet<Object> source): testSetFlattenCaller(aClass, source) {
        return ((tudresden.ocl20.pivot.ocl2java.types.OclSet<Object>) source.flatten());
    }
}