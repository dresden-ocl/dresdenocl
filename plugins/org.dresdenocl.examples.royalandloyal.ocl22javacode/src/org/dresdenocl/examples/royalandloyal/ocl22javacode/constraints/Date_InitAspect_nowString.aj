package org.dresdenocl.examples.royalandloyal.ocl22javacode.constraints;

/**
 * <p>Generated Aspect to enforce OCL constraint.</p>
 *
 * @author OCL22Java of Dresden OCL2 for Eclipse
 * @Generated
 */
public privileged aspect Date_InitAspect_nowString {

    /**
     * <p>The initialization of the class {@link org.dresdenocl.examples.royalandloyal.ocl22javacode.Date}.</p>
     */
    protected pointcut staticDateInit():
        staticinitialization(org.dresdenocl.examples.royalandloyal.ocl22javacode.Date);

    /**
     * <p><code>Initializes the static attribute nowString defined by the constraint
     * <code>context Date::nowString
     *       init: Date::now().toString()</code></p>
     */
    after(): staticDateInit() {
        org.dresdenocl.examples.royalandloyal.ocl22javacode.Date.nowString = org.dresdenocl.examples.royalandloyal.ocl22javacode.Date.now().toString();
    }
}