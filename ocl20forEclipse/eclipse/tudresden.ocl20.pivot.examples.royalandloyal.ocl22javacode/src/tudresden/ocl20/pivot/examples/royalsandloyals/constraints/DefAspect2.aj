package tudresden.ocl20.pivot.examples.royalsandloyals.constraints;

/**
 * <p>Generated Aspect to enforce OCL constraint.</p>
 *
 * @author OCL22Java of Dresden OCL2 for Eclipse
 * @Generated
 */
public privileged aspect DefAspect2 {

    /* Declares a new super class containing the new attribute or method. */
    declare parents : tudresden.ocl20.pivot.examples.royalsandloyals.LoyaltyProgram extends tudresden.ocl20.pivot.examples.royalsandloyals.constraints.ExtendedLoyaltyProgram;
    
    /**
     * <p>Pointcut for all calls on {@link tudresden.ocl20.pivot.examples.royalsandloyals.LoyaltyProgram#getServicesByLevel(String levelName)}.</p>
     */
    protected pointcut getServicesByLevelCaller(tudresden.ocl20.pivot.examples.royalsandloyals.LoyaltyProgram aClass, String levelName):
    	call(* tudresden.ocl20.pivot.examples.royalsandloyals.LoyaltyProgram.getServicesByLevel(String))
    	&& target(aClass) && args(levelName);
    
    /**
     * <p>Defines the method getServicesByLevel(String levelName) defined by the constraint
     * <code>context LoyaltyProgram
     *       def: getServicesByLevel = levels[] -> select (  | name[].=( levelName[])).availableServices[].flatten().asSet()</code></p>
     */
    tudresden.ocl20.pivot.ocl2java.types.OclSet<tudresden.ocl20.pivot.examples.royalsandloyals.Service> around(tudresden.ocl20.pivot.examples.royalsandloyals.LoyaltyProgram aClass, String levelName): getServicesByLevelCaller(aClass, levelName) {
        tudresden.ocl20.pivot.ocl2java.types.OclOrderedSet<tudresden.ocl20.pivot.examples.royalsandloyals.ServiceLevel> result2;
        result2 = new tudresden.ocl20.pivot.ocl2java.types.OclOrderedSet<tudresden.ocl20.pivot.examples.royalsandloyals.ServiceLevel>();
        
        /* Iterator Select: Select all elements which fulfill the condition. */
        for (tudresden.ocl20.pivot.examples.royalsandloyals.ServiceLevel anElement2 : aClass.levels) {
            if (anElement2.name.equals(levelName)) {
                result2.add(anElement2);
            }
            // no else
        }
        tudresden.ocl20.pivot.ocl2java.types.OclSequence<tudresden.ocl20.pivot.examples.royalsandloyals.Service> result1;
        result1 = new tudresden.ocl20.pivot.ocl2java.types.OclSequence<tudresden.ocl20.pivot.examples.royalsandloyals.Service>();
        
        /* Iterator Collect: Iterate through all elements and collect them. Elements which are collections are flattened. */
        for (tudresden.ocl20.pivot.examples.royalsandloyals.ServiceLevel anElement1 : result2) {
            result1.addAll(anElement1.availableServices);
        }
    
        return ((tudresden.ocl20.pivot.ocl2java.types.OclSequence<tudresden.ocl20.pivot.examples.royalsandloyals.Service>) result1.flatten()).asSet();
    }
}