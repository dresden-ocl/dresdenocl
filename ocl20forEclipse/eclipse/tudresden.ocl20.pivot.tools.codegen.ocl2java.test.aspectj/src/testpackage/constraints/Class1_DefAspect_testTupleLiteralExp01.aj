package testpackage.constraints;

/**
 * <p>Generated Aspect to enforce OCL constraint.</p>
 *
 * @author OCL22Java of Dresden OCL2 for Eclipse
 * @Generated
 */
public privileged aspect Class1_DefAspect_testTupleLiteralExp01 {

    /**
     * <p>Defines the method testTupleLiteralExp01() defined by the constraint
     * <code>context Class1
     *       def: testTupleLiteralExp01() =
    Tuple { a : Integer = 42, b : String = 'some'}</code></p>
     */
    public java.util.HashMap<String, Object> testpackage.Class1.testTupleLiteralExp01( ) {
        java.util.HashMap<String, Object> tuple1;
        tuple1 = new java.util.HashMap<String, Object>();
        
        tuple1.put("a", new Integer(42));
        tuple1.put("b", "some");
    
        return tuple1;
    }
}