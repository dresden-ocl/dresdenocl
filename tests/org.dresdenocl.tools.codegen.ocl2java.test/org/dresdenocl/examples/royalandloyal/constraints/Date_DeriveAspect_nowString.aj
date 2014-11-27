package org.dresdenocl.examples.royalandloyal.constraints;

/**
 * <p>Generated Aspect to enforce OCL constraint.</p>
 *
 * @author OCL22Java of Dresden OCL2 for Eclipse
 * @Generated
 */
public privileged aspect Date_DeriveAspect_nowString {

    /**
     * <p>Pointcut for all requests on {@link org.dresdenocl.examples.royalandloyal.Date#nowString}.</p>
     */
    protected pointcut nowStringGetter() :
    	get(static * org.dresdenocl.examples.royalandloyal.Date.nowString);

    /**
     * <p>Derives the attribute nowString defined by the constraint
     * <code>context Date
     *       derive: Date::nowAsString()</code></p>
     */
    before(): nowStringGetter() {
        org.dresdenocl.examples.royalandloyal.Date.nowString = org.dresdenocl.examples.royalandloyal.Date.nowAsString();
    }
}