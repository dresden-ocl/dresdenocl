package tudresden.ocl20.pivot.examples.royalsandloyals.constraints;

/**
 * <p>Generated Aspect to enforce OCL constraint.</p>
 *
 * @author OCL22Java of Dresden OCL2 for Eclipse
 * @Generated
 */
public privileged aspect InitAspect4 {

    /**
     * <p>The initialization of the class {@link tudresden.ocl20.pivot.examples.royalsandloyals.Date}.</p>
     */
    protected pointcut staticDateInit():
        staticinitialization(tudresden.ocl20.pivot.examples.royalsandloyals.Date);
    
    /**
     * <p><code>Initializes the static attribute nowString defined by the constraint
     * <code>context Date::nowString
     *       init: now().toString()</code></p>
     */
    after(): staticDateInit() {
        tudresden.ocl20.pivot.examples.royalsandloyals.Date.nowString = tudresden.ocl20.pivot.examples.royalsandloyals.Date.now().toString();
    }
}