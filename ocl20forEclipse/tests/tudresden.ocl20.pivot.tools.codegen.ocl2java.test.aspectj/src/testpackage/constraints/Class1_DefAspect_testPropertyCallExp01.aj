package testpackage.constraints;

/**
 * <p>Generated Aspect to enforce OCL constraint.</p>
 *
 * @author OCL22Java of Dresden OCL2 for Eclipse
 * @Generated
 */
public privileged aspect Class1_DefAspect_testPropertyCallExp01 {

    /**
     * <p>Defines the method testPropertyCallExp01() defined by the constraint
     * <code>context Class1
     *       def: testPropertyCallExp01(): Integer = self.anInteger01</code></p>
     */
    public Integer testpackage.Class1.testPropertyCallExp01() {
    	/* Self variable probably used within the definition. */
    	testpackage.Class1 aClass = this;
    	
        return aClass.anInteger01;
    }
}