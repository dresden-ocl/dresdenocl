package testpackage.constraints;

/**
 * <p>Generated Aspect to enforce OCL constraint.</p>
 *
 * @author OCL22Java of Dresden OCL2 for Eclipse
 * @Generated
 */
public privileged aspect Class1_BodyAspect_staticBodyOperation01 {

    /**
     * <p>Pointcut for all calls on {@link testpackage.Class1#staticBodyOperation01()}.</p>
     */
    protected pointcut staticBodyOperation01Caller():
    	execution(* testpackage.Class1.staticBodyOperation01());
    
    /**
     * <p>Defines the body of the method staticBodyOperation01() defined by the constraint
     * <code>context Class1::staticBodyOperation01(): Integer
     *       body testStaticBody01: 42</code></p>
     */
    Integer around(): staticBodyOperation01Caller() {
        return new Integer(42);
    }
}