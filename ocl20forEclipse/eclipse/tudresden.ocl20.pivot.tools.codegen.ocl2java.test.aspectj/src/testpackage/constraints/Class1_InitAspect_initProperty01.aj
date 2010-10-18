package testpackage.constraints;

/**
 * <p>Generated Aspect to enforce OCL constraint.</p>
 *
 * @author OCL22Java of Dresden OCL2 for Eclipse
 * @Generated
 */
public privileged aspect Class1_InitAspect_initProperty01 {

    /**
     * <p>Describes all Constructors of the class {@link testpackage.Class1}.</p>
     */
    protected pointcut allClass1Constructors(testpackage.Class1 aClass):
        execution(testpackage.Class1.new(..)) && this(aClass);
    
    /**
     * <p><code>Initializes the attribute initProperty01 defined by the constraint
     * <code>context Class1::initProperty01
     *       init: 42</code></p>
     */
    after(testpackage.Class1 aClass) : allClass1Constructors(aClass) {
        aClass.initProperty01 = new Integer(42);
    }
}