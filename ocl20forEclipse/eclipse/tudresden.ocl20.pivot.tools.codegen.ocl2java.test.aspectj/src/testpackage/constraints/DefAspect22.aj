package testpackage.constraints;

/**
 * <p>Generated Aspect to enforce OCL constraint.</p>
 *
 * @author OCL22Java of Dresden OCL2 for Eclipse
 * @Generated
 */
public privileged aspect DefAspect22 {

    /* Declares a new super class containing the new attribute or method. */
    declare parents : testpackage.Class1 extends testpackage.constraints.ExtendedClass1;
    
    /**
     * <p>Pointcut for all calls on {@link testpackage.Class1#testTupleLiteralExp01()}.</p>
     */
    protected pointcut testTupleLiteralExp01Caller(testpackage.Class1 aClass):
    	call(* testpackage.Class1.testTupleLiteralExp01())
    	&& target(aClass);
    
    /**
     * <p>Defines the method testTupleLiteralExp01() defined by the constraint
     * <code>context Class1
     *       def: testTupleLiteralExp01() =
    Tuple { a : Integer = 42, b : String = 'some'}</code></p>
     */
    java.util.HashMap<String, Object> around(testpackage.Class1 aClass): testTupleLiteralExp01Caller(aClass) {
        java.util.HashMap<String, Object> tuple1;
        tuple1 = new java.util.HashMap<String, Object>();
        
        tuple1.put("a", new Integer(42));
        tuple1.put("b", "some");
    
        return tuple1;
    }
}