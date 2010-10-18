package tudresden.ocl20.pivot.examples.royalsandloyals.constraints;

/**
 * <p>Generated Aspect to enforce OCL constraint.</p>
 *
 * @author OCL22Java of Dresden OCL2 for Eclipse
 * @Generated
 */
public privileged aspect LoyaltyProgram_DefAspect_getServicesByLevel {

    /**
     * <p>Defines the method getServicesByLevel(String levelName) defined by the constraint
     * <code>context LoyaltyProgram
     *       def: getServicesByLevel(levelName: String): Set(Service)
         = levels->select(name = levelName).availableServices->flatten()->asSet()</code></p>
     */
    public java.util.Set<tudresden.ocl20.pivot.examples.royalsandloyals.Service> tudresden.ocl20.pivot.examples.royalsandloyals.LoyaltyProgram.getServicesByLevel(String levelName) {
    	/* Self variable probably used within the definition. */
    	tudresden.ocl20.pivot.examples.royalsandloyals.LoyaltyProgram aClass = this;
    	
        java.util.ArrayList<tudresden.ocl20.pivot.examples.royalsandloyals.ServiceLevel> result2;
        result2 = new java.util.ArrayList<tudresden.ocl20.pivot.examples.royalsandloyals.ServiceLevel>();
        
        /* Iterator Select: Select all elements which fulfill the condition. */
        for (tudresden.ocl20.pivot.examples.royalsandloyals.ServiceLevel $implicitVariable0$ : aClass.levels) {
            if ($implicitVariable0$.name.equals(levelName)) {
                result2.add($implicitVariable0$);
            }
            // no else
        }
        java.util.ArrayList<tudresden.ocl20.pivot.examples.royalsandloyals.Service> result1;
        result1 = new java.util.ArrayList<tudresden.ocl20.pivot.examples.royalsandloyals.Service>();
        
        /* Iterator Collect: Iterate through all elements and collect them. Elements which are collections are flattened. */
        for (tudresden.ocl20.pivot.examples.royalsandloyals.ServiceLevel $implicitCollect0$ : result2) {
            result1.addAll($implicitCollect0$.availableServices);
        }
    
        return tudresden.ocl20.pivot.tools.codegen.ocl2java.types.util.OclSequences.asSet((java.util.List<tudresden.ocl20.pivot.examples.royalsandloyals.Service>) tudresden.ocl20.pivot.tools.codegen.ocl2java.types.util.OclSequences.flatten(result1));
    }
}