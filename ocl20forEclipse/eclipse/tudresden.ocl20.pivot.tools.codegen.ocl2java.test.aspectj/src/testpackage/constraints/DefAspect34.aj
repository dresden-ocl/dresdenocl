package testpackage.constraints;

/**
 * <p>Generated Aspect to enforce OCL constraint.</p>
 *
 * @author OCL22Java of Dresden OCL2 for Eclipse
 * @Generated
 */
public privileged aspect DefAspect34 {

    /* Declares a new super class containing the new attribute or method. */
    declare parents : testpackage.Class1 extends testpackage.constraints.ExtendedClass1;
    
    /**
     * <p>Pointcut for all calls on {@link testpackage.Class1#testBagFlatten(java.util.List<Object> source)}.</p>
     */
    protected pointcut testBagFlattenCaller(testpackage.Class1 aClass, java.util.List<Object> source):
    	call(* testpackage.Class1.testBagFlatten(java.util.List<Object>))
    	&& target(aClass) && args(source);
    
    /**
     * <p>Defines the method testBagFlatten(java.util.List<Object> source) defined by the constraint
     * <code>context Class1
     *       def: testBagFlatten(source: Bag(OclAny)): Bag(OclAny) =
    source ->flatten()</code></p>
     */
    java.util.List<Object> around(testpackage.Class1 aClass, java.util.List<Object> source): testBagFlattenCaller(aClass, source) {
        return (java.util.List<Object>) tudresden.ocl20.pivot.tools.codegen.ocl2java.types.util.OclBags.flatten(source);
    }
}