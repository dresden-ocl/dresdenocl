package testpackage.constraints;

/**
 * <p>Generated Aspect to enforce OCL constraint.</p>
 *
 * @author OCL22Java of Dresden OCL2 for Eclipse
 * @Generated
 */
public privileged aspect Class1_DeriveAspect_staticDeriveProperty01 {

    /**
     * <p>Pointcut for all requests on {@link testpackage.Class1#staticDeriveProperty01}.</p>
     */
    protected pointcut staticDeriveProperty01Getter() :
    	get(static * testpackage.Class1.staticDeriveProperty01);
    
    /**
     * <p>Derives the attribute staticDeriveProperty01 defined by the constraint
     * <code>context Class1
     *       derive: 42</code></p>
     */
    before(): staticDeriveProperty01Getter() {
        testpackage.Class1.staticDeriveProperty01 = new Integer(42);
    }
}