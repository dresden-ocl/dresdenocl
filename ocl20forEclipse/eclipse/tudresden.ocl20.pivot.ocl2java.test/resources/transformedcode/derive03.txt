package tudresden.ocl20.pivot.examples.royalsandloyals.constraints;

@Generated
public privileged aspect DeriveAspect3 {

    /**
     * <p>Pointcut for all requests on {@link tudresden.ocl20.pivot.examples.royalsandloyals.Date#nowString}.</p>
     */
    protected pointcut nowStringGetter() :
    	get(static * tudresden.ocl20.pivot.examples.royalsandloyals.Date.nowString);
    
    /**
     * <p>Derives the attribute nowString defined by the constraint
     * <code>context Date
     *       def: nowString = nowAsString()</code></p>
     */
    before(): nowStringGetter() {
        tudresden.ocl20.pivot.examples.royalsandloyals.Date.nowString = tudresden.ocl20.pivot.examples.royalsandloyals.Date.nowAsString();
    }
}