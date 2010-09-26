package testpackage.constraints;

/**
 * <p>Generated Aspect to enforce OCL constraint.</p>
 *
 * @author OCL22Java of Dresden OCL2 for Eclipse
 * @Generated
 */
public privileged aspect DefAspect158 {

    /* Declares a new super class containing the new attribute or method. */
    declare parents : testpackage.Class1 extends testpackage.constraints.ExtendedClass1;
    
    /**
     * <p>Pointcut for all calls on {@link testpackage.Class1#testSetUnion02(java.util.Set<Object> source, java.util.List<Object> arg01)}.</p>
     */
    protected pointcut testSetUnion02Caller(testpackage.Class1 aClass, java.util.Set<Object> source, java.util.List<Object> arg01):
    	call(* testpackage.Class1.testSetUnion02(java.util.Set<Object>, java.util.List<Object>))
    	&& target(aClass) && args(source, arg01);
    
    /**
     * <p>Defines the method testSetUnion02(java.util.Set<Object> source, java.util.List<Object> arg01) defined by the constraint
     * <code>context Class1
     *       def: testSetUnion02(source: Set(OclAny), arg01: Bag(OclAny)): Bag(OclAny) =
    source ->union(arg01)</code></p>
     */
    java.util.List<Object> around(testpackage.Class1 aClass, java.util.Set<Object> source, java.util.List<Object> arg01): testSetUnion02Caller(aClass, source, arg01) {
        return tudresden.ocl20.pivot.tools.codegen.ocl2java.types.util.OclSets.unionWithBag(source, arg01);
    }
}