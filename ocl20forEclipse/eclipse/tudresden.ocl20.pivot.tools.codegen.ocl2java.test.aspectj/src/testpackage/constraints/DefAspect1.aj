package testpackage.constraints;

/**
 * <p>Generated Aspect to enforce OCL constraint.</p>
 *
 * @author OCL22Java of Dresden OCL2 for Eclipse
 * @Generated
 */
public privileged aspect DefAspect1 {

    /* Declares a new super class containing the new attribute or method. */
    declare parents : testpackage.Class1 extends testpackage.constraints.ExtendedClass1;
    
    /**
     * <p>Pointcut for all requests on {@link testpackage.Class1#defProperty01}.</p>
     */
    protected pointcut defProperty01Getter(testpackage.Class1 aClass) :
    	get(* defProperty01) && this(aClass);
    
    /**
     * <p>Initializes the attribute defProperty01 defined by the constraint
     * <code>context Class1
     *       def: defProperty01 = 42</code></p>
     */
    before(testpackage.Class1 aClass): defProperty01Getter(aClass) {
        aClass.defProperty01 = new Integer(42);
    }
}