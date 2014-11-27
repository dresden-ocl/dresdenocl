package testpackage.constraints;

/**
 * <p>Generated Aspect to enforce OCL constraint.</p>
 *
 * @author OCL22Java of Dresden OCL2 for Eclipse
 * @Generated
 */
public privileged aspect Class1_InitAspect_staticInitProperty01 {

    /**
     * <p>The initialization of the class {@link testpackage.Class1}.</p>
     */
    protected pointcut staticClass1Init():
        staticinitialization(testpackage.Class1);

    /**
     * <p><code>Initializes the static attribute staticInitProperty01 defined by the constraint
     * <code>context Class1::staticInitProperty01
     *       init: 42</code></p>
     */
    after(): staticClass1Init() {
        testpackage.Class1.staticInitProperty01 = new Integer(42);
    }
}