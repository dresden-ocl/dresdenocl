package testpackage.constraints;

/**
 * <p>Generated Aspect to enforce OCL constraint.</p>
 *
 * @author OCL22Java of Dresden OCL2 for Eclipse
 * @Generated
 */
public privileged aspect DefAspect10 {

    /* Declares a new super class containing the new attribute or method. */
    declare parents : testpackage.Class1 extends testpackage.constraints.ExtendedClass1;
    
    /**
     * <p>Pointcut for all calls on {@link testpackage.Class1#testBagExcluding01(java.util.List<Object> source, Object arg01)}.</p>
     */
    protected pointcut testBagExcluding01Caller(testpackage.Class1 aClass, java.util.List<Object> source, Object arg01):
    	call(* testpackage.Class1.testBagExcluding01(java.util.List<Object>, Object))
    	&& target(aClass) && args(source, arg01);
    
    /**
     * <p>Defines the method testBagExcluding01(java.util.List<Object> source, Object arg01) defined by the constraint
     * <code>context Class1
     *       def: testBagExcluding01(source: Bag(OclAny), arg01: OclAny): Bag(OclAny) =
    source ->excluding(arg01)</code></p>
     */
    java.util.List<Object> around(testpackage.Class1 aClass, java.util.List<Object> source, Object arg01): testBagExcluding01Caller(aClass, source, arg01) {
        return tudresden.ocl20.pivot.tools.codegen.ocl2java.types.util.OclBags.excluding(source, arg01);
    }
}