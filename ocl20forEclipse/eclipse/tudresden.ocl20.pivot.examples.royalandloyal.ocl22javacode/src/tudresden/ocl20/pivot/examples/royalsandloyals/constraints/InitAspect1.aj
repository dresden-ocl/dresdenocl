package tudresden.ocl20.pivot.examples.royalsandloyals.constraints;

/**
 * <p>Generated Aspect to enforce OCL constraint.</p>
 *
 * @author OCL22Java of Dresden OCL2 for Eclipse
 * @Generated
 */
public privileged aspect InitAspect1 {

    /**
     * <p>Describes all Constructors of the class {@link tudresden.ocl20.pivot.examples.royalsandloyals.LoyaltyAccount}.</p>
     */
    protected pointcut allLoyaltyAccountConstructors(tudresden.ocl20.pivot.examples.royalsandloyals.LoyaltyAccount aClass):
        execution(tudresden.ocl20.pivot.examples.royalsandloyals.LoyaltyAccount.new(..)) && this(aClass);
    
    /**
     * <p><code>Initializes the attribute points defined by the constraint
     * <code>context LoyaltyAccount::points
     *       init: 0</code></p>
     */
    after(tudresden.ocl20.pivot.examples.royalsandloyals.LoyaltyAccount aClass) : allLoyaltyAccountConstructors(aClass) {
        aClass.points = new Integer(0);
    }
}