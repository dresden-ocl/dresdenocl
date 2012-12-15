package org.dresdenocl.tudresden.ocl20.pivot.examples.royalsandloyals.constraints;

/**
 * <p>Generated Aspect to enforce OCL constraint.</p>
 *
 * @author OCL22Java of Dresden OCL2 for Eclipse
 * @Generated
 */
public privileged aspect Date_DeriveAspect_nowString {

    /**
     * <p>Pointcut for all requests on {@link org.dresdenocl.tudresden.ocl20.pivot.examples.royalsandloyals.Date#nowString}.</p>
     */
    protected pointcut nowStringGetter() :
    	get(static * org.dresdenocl.tudresden.ocl20.pivot.examples.royalsandloyals.Date.nowString);

    /**
     * <p>Derives the attribute nowString defined by the constraint
     * <code>context Date
     *       derive: Date::nowAsString()</code></p>
     */
    before(): nowStringGetter() {
        org.dresdenocl.tudresden.ocl20.pivot.examples.royalsandloyals.Date.nowString = org.dresdenocl.tudresden.ocl20.pivot.examples.royalsandloyals.Date.nowAsString();
    }
}