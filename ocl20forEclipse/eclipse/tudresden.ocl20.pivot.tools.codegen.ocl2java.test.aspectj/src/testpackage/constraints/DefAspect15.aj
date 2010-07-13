package testpackage.constraints;

/**
 * <p>Generated Aspect to enforce OCL constraint.</p>
 *
 * @author OCL22Java of Dresden OCL2 for Eclipse
 * @Generated
 */
public privileged aspect DefAspect15 {

    /* Declares a new super class containing the new attribute or method. */
    declare parents : testpackage.Class1 extends testpackage.constraints.ExtendedClass1;
    
    /**
     * <p>Pointcut for all calls on {@link testpackage.Class1#testBagUnion01(java.util.List<Object> source, java.util.Set<Object> arg01)}.</p>
     */
    protected pointcut testBagUnion01Caller(testpackage.Class1 aClass, java.util.List<Object> source, java.util.Set<Object> arg01):
    	call(* testpackage.Class1.testBagUnion01(java.util.List<Object>, java.util.Set<Object>))
    	&& target(aClass) && args(source, arg01);
    
    /**
     * <p>Defines the method testBagUnion01(java.util.List<Object> source, java.util.Set<Object> arg01) defined by the constraint
     * <code>context Class1
     *       def: testBagUnion01 = source[].union( arg01[])</code></p>
     */
    java.util.List<Object> around(testpackage.Class1 aClass, java.util.List<Object> source, java.util.Set<Object> arg01): testBagUnion01Caller(aClass, source, arg01) {
        return tudresden.ocl20.pivot.ocl22java.types.util.OclBags.union(source, arg01);
    }
}