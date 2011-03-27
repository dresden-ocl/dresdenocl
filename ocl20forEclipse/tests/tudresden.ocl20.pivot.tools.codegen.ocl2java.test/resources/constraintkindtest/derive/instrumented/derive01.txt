package testpackage.constraints;

/**
 * <p>Generated Aspect to enforce OCL constraint.</p>
 *
 * @author OCL22Java of Dresden OCL2 for Eclipse
 * @Generated
 */
public privileged aspect Class1_DeriveAspect_deriveProperty01 {

    /**
     * <p>Pointcut for all requests on {@link testpackage.Class1#deriveProperty01}.</p>
     */
    protected pointcut deriveProperty01Getter(testpackage.Class1 aClass) :
    	get(* deriveProperty01) && target(aClass);
    
    /**
     * <p>Derives the attribute deriveProperty01 defined by the constraint
     * <code>context Class1
     *       derive: 42</code></p>
     */
    before(testpackage.Class1 aClass): deriveProperty01Getter(aClass) {
        aClass.deriveProperty01 = new Integer(42);
    }
}