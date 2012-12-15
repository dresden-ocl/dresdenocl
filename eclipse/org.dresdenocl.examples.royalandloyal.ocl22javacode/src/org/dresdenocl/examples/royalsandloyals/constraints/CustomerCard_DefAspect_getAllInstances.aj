package org.dresdenocl.tudresden.ocl20.pivot.examples.royalsandloyals.constraints;

/**
 * <p>Generated Aspect to enforce OCL constraint.</p>
 *
 * @author OCL22Java of Dresden OCL2 for Eclipse
 * @Generated
 */
public privileged aspect CustomerCard_DefAspect_getAllInstances {

    protected static java.util.Map<String, java.util.Map> allInstances = new java.util.HashMap<String, java.util.Map>();

    /**
     * <p>Adds all instances of the class {@link org.dresdenocl.tudresden.ocl20.pivot.examples.royalsandloyals.CustomerCard} to the {@link java.util.Map} allInstances.</p>
     */
    after(org.dresdenocl.tudresden.ocl20.pivot.examples.royalsandloyals.CustomerCard aClass) : execution(org.dresdenocl.tudresden.ocl20.pivot.examples.royalsandloyals.CustomerCard.new(..)) && this(aClass) {

        java.util.Map<org.dresdenocl.tudresden.ocl20.pivot.examples.royalsandloyals.CustomerCard, Object> instanceMap;

        instanceMap = (java.util.Map<org.dresdenocl.tudresden.ocl20.pivot.examples.royalsandloyals.CustomerCard, Object>) allInstances.get(aClass.getClass().getCanonicalName());

        if (instanceMap == null) {
            instanceMap = new java.util.WeakHashMap<org.dresdenocl.tudresden.ocl20.pivot.examples.royalsandloyals.CustomerCard, Object>();
        }
        // no else.

        instanceMap.put(aClass, null);

        allInstances.put(aClass.getClass().getCanonicalName(), instanceMap);
    }

    /**
     * <p>Defines the method getAllInstances() defined by the constraint
     * <code>context CustomerCard
     *       def: getAllInstances() : Set(CustomerCard) = CustomerCard.allInstances()</code></p>
     */
    public java.util.Set<org.dresdenocl.tudresden.ocl20.pivot.examples.royalsandloyals.CustomerCard> org.dresdenocl.tudresden.ocl20.pivot.examples.royalsandloyals.CustomerCard.getAllInstances() {
        return (new java.util.HashSet<org.dresdenocl.tudresden.ocl20.pivot.examples.royalsandloyals.CustomerCard>((java.util.Set<org.dresdenocl.tudresden.ocl20.pivot.examples.royalsandloyals.CustomerCard>) allInstances.get(org.dresdenocl.tudresden.ocl20.pivot.examples.royalsandloyals.CustomerCard.class.getCanonicalName()).keySet()));
    }
}