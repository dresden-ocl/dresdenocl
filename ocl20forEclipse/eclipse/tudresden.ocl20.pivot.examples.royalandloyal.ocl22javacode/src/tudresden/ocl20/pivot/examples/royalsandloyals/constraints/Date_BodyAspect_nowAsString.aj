package tudresden.ocl20.pivot.examples.royalsandloyals.constraints;

/**
 * <p>Generated Aspect to enforce OCL constraint.</p>
 *
 * @author OCL22Java of Dresden OCL2 for Eclipse
 * @Generated
 */
public privileged aspect Date_BodyAspect_nowAsString {

    /**
     * <p>Pointcut for all calls on {@link tudresden.ocl20.pivot.examples.royalsandloyals.Date#nowAsString()}.</p>
     */
    protected pointcut nowAsStringCaller():
    	execution(* tudresden.ocl20.pivot.examples.royalsandloyals.Date.nowAsString());
    
    /**
     * <p>Defines the body of the method nowAsString() defined by the constraint
     * <code>context Date::nowAsString(): String
     *       body: Date::now().toString()</code></p>
     */
    String around(): nowAsStringCaller() {
        return tudresden.ocl20.pivot.examples.royalsandloyals.Date.now().toString();
    }
}