package testpackage.constraints;

/**
 * <p>Generated Aspect to enforce OCL constraint.</p>
 *
 * @author OCL22Java of Dresden OCL2 for Eclipse
 * @Generated
 */
public privileged aspect Class1_DefAspect_testIfExp01 {

    /**
     * <p>Defines the method testIfExp01() defined by the constraint
     * <code>context Class1
     *       def: testIfExp01(): Integer = if (true)   then 1   else -1 endif</code></p>
     */
    public Integer testpackage.Class1.testIfExp01() {
        Integer ifExpResult1;

        if (new Boolean(true)) {
            ifExpResult1 = new Integer(1);
        } else {
            ifExpResult1 = -(new Integer(1));
        }

        return ifExpResult1;
    }
}