package org.dresdenocl.examples.royalsandloyals.ocl22javacode.constraints;

/**
 * <p>Generated Aspect to enforce OCL constraint.</p>
 *
 * @author OCL22Java of Dresden OCL2 for Eclipse
 * @Generated
 */
public privileged aspect Date_DeriveAspect_nowString {

    /**
     * <p>Pointcut for all requests on {@link org.dresdenocl.examples.royalsandloyals.ocl22javacode.Date#nowString}.</p>
     */
    protected pointcut nowStringGetter() :
    	get(static * org.dresdenocl.examples.royalsandloyals.ocl22javacode.Date.nowString);

    /**
     * <p>Derives the attribute nowString defined by the constraint
     * <code>context Date
     *       derive: Date::nowAsString()</code></p>
     */
    before(): nowStringGetter() {
        org.dresdenocl.examples.royalsandloyals.ocl22javacode.Date.nowString = org.dresdenocl.examples.royalsandloyals.ocl22javacode.Date.nowAsString();
    }
}