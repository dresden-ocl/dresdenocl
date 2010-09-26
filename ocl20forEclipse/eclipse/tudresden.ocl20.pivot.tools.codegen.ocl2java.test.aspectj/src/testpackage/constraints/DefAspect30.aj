package testpackage.constraints;

/**
 * <p>Generated Aspect to enforce OCL constraint.</p>
 *
 * @author OCL22Java of Dresden OCL2 for Eclipse
 * @Generated
 */
public privileged aspect DefAspect30 {

    /* Declares a new super class containing the new attribute or method. */
    declare parents : testpackage.Class1 extends testpackage.constraints.ExtendedClass1;
    
    /**
     * <p>Pointcut for all calls on {@link testpackage.Class1#testInvalidLiteralExp01()}.</p>
     */
    protected pointcut testInvalidLiteralExp01Caller(testpackage.Class1 aClass):
    	call(* testpackage.Class1.testInvalidLiteralExp01())
    	&& target(aClass);
    
    /**
     * <p>Defines the method testInvalidLiteralExp01() defined by the constraint
     * <code>context Class1
     *       def: testInvalidLiteralExp01(): Integer =
    invalid</code></p>
     */
    Integer around(testpackage.Class1 aClass): testInvalidLiteralExp01Caller(aClass) {
        return (Integer) tudresden.ocl20.pivot.tools.codegen.ocl2java.types.OclInvalidException.getInvalidLiteral();
    }
}