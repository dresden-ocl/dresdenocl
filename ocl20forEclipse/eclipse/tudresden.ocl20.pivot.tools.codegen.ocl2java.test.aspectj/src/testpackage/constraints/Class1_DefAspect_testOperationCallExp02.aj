package testpackage.constraints;

/**
 * <p>Generated Aspect to enforce OCL constraint.</p>
 *
 * @author OCL22Java of Dresden OCL2 for Eclipse
 * @Generated
 */
public privileged aspect Class1_DefAspect_testOperationCallExp02 {

    /**
     * <p>Defines the method testOperationCallExp02() defined by the constraint
     * <code>context Class1
     *       def: testOperationCallExp02(): Integer = Class1::getStaticInteger()</code></p>
     */
    public Integer testpackage.Class1.testOperationCallExp02( ) {
        return testpackage.Class1.getStaticInteger();
    }
}