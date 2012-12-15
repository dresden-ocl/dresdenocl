package testpackage.constraints;

/**
 * <p>Generated Aspect to enforce OCL constraint.</p>
 *
 * @author OCL22Java of Dresden OCL2 for Eclipse
 * @Generated
 */
public privileged aspect Class1_DefAspect_testOperationCallExp01 {

    /**
     * <p>Defines the method testOperationCallExp01() defined by the constraint
     * <code>context Class1
     *       def: testOperationCallExp01(): Integer = self.getInteger()</code></p>
     */
    public Integer testpackage.Class1.testOperationCallExp01() {
    	/* Self variable probably used within the definition. */
    	testpackage.Class1 aClass = this;
    	
        return aClass.getInteger();
    }
}