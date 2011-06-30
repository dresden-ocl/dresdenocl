package testpackage.constraints;

/**
 * <p>Generated Aspect to enforce OCL constraint.</p>
 *
 * @author OCL22Java of Dresden OCL2 for Eclipse
 * @Generated
 */
public privileged aspect Class1_DefAspect_defProperty01 {

    /**
     * <p>Defines the field defProperty01 defined by the constraint
     * <code>context Class1
     *       def: defProperty01: Integer =  42</code></p>
     */
    public Integer testpackage.Class1.defProperty01;

    /**
     * <p>Getter method for the attribute defProperty01.</p>
     */
    public Integer testpackage.Class1.getDefProperty01() {
        return this.defProperty01;    
    }
    /**
     * <p>Pointcut for all requests on {@link testpackage.Class1#defProperty01}.</p>
     */
    protected pointcut defProperty01Getter(testpackage.Class1 aClass) :
    	get(* defProperty01) && target(aClass);

    /**
     * <p>Initializes the attribute defProperty01 defined by the constraint
     * <code>context Class1
     *       def: defProperty01: Integer =  42</code></p>
     */
    before(testpackage.Class1 aClass): defProperty01Getter(aClass) {
        aClass.defProperty01 = new Integer(42);
    }
}