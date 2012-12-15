package testpackage.constraints;

/**
 * <p>Generated Aspect to enforce OCL constraint.</p>
 *
 * @author OCL22Java of Dresden OCL2 for Eclipse
 * @Generated
 */
public privileged aspect Class1_DefAspect_testOclIsInvalid01 {

    /**
     * <p>Defines the method testOclIsInvalid01() defined by the constraint
     * <code>context Class1
     *       def: testOclIsInvalid01(): Boolean = self.oclIsInvalid()</code></p>
     */
    public Boolean testpackage.Class1.testOclIsInvalid01() {
    	/* Self variable probably used within the definition. */
    	testpackage.Class1 aClass = this;
    	
        Boolean result1;

        /* Check if the expression results in invalid. */
        try {
            /* DUMMY variable is necessary to form literals into a statement. */
            Object DUMMY = aClass; 
            result1 = false;
        }

        catch (Exception e) {
            result1 = true;
        }

        return result1;
    }
}