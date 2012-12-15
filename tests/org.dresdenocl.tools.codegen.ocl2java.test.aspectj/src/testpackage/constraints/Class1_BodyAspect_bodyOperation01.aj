package testpackage.constraints;

/**
 * <p>Generated Aspect to enforce OCL constraint.</p>
 *
 * @author OCL22Java of Dresden OCL2 for Eclipse
 * @Generated
 */
public privileged aspect Class1_BodyAspect_bodyOperation01 {

    /**
     * <p>Pointcut for all calls on {@link testpackage.Class1#bodyOperation01()}.</p>
     */
    protected pointcut bodyOperation01Caller(testpackage.Class1 aClass):
    	call(* testpackage.Class1.bodyOperation01())
    	&& target(aClass);

    /**
     * <p>Defines the body of the method bodyOperation01() defined by the constraint
     * <code>context Class1::bodyOperation01(): Integer
     *       body testBody01: 42</code></p>
     */
    Integer around(testpackage.Class1 aClass): bodyOperation01Caller(aClass) {
        return new Integer(42);
    }
}