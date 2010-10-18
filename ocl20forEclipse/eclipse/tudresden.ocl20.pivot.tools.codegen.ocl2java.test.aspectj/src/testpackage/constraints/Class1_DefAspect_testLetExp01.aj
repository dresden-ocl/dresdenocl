package testpackage.constraints;

/**
 * <p>Generated Aspect to enforce OCL constraint.</p>
 *
 * @author OCL22Java of Dresden OCL2 for Eclipse
 * @Generated
 */
public privileged aspect Class1_DefAspect_testLetExp01 {

    /**
     * <p>Defines the method testLetExp01() defined by the constraint
     * <code>context Class1
     *       def: testLetExp01(): Integer =
    let senseOfLife = 42
    in senseOfLife</code></p>
     */
    public Integer testpackage.Class1.testLetExp01( ) {
        Integer senseOfLife;
        senseOfLife = new Integer(42);
        
    
        return senseOfLife;
    }
}