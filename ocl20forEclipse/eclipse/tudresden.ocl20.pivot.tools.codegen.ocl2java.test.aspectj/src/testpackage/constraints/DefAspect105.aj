package testpackage.constraints;

/**
 * <p>Generated Aspect to enforce OCL constraint.</p>
 *
 * @author OCL22Java of Dresden OCL2 for Eclipse
 * @Generated
 */
public privileged aspect DefAspect105 {

    /* Declares a new super class containing the new attribute or method. */
    declare parents : testpackage.Class1 extends testpackage.constraints.ExtendedClass1;
    
    /**
     * <p>Pointcut for all calls on {@link testpackage.Class1#testOrderedSetInsertAt(java.util.List<Object> source, Integer arg01, Object arg02)}.</p>
     */
    protected pointcut testOrderedSetInsertAtCaller(testpackage.Class1 aClass, java.util.List<Object> source, Integer arg01, Object arg02):
    	call(* testpackage.Class1.testOrderedSetInsertAt(java.util.List<Object>, Integer, Object))
    	&& target(aClass) && args(source, arg01, arg02);
    
    /**
     * <p>Defines the method testOrderedSetInsertAt(java.util.List<Object> source, Integer arg01, Object arg02) defined by the constraint
     * <code>context Class1
     *       def: testOrderedSetInsertAt(source: OrderedSet(OclAny), arg01: Integer, arg02: OclAny): OrderedSet(OclAny) =
    source ->insertAt(arg01, arg02)</code></p>
     */
    java.util.List<Object> around(testpackage.Class1 aClass, java.util.List<Object> source, Integer arg01, Object arg02): testOrderedSetInsertAtCaller(aClass, source, arg01, arg02) {
        return tudresden.ocl20.pivot.tools.codegen.ocl2java.types.util.OclOrderedSets.insertAt(source, arg01, arg02);
    }
}