package org.dresdenocl.examples.royalandloyal.constraints;

/**
 * <p>Generated Aspect to enforce OCL constraint.</p>
 *
 * @author OCL22Java of Dresden OCL2 for Eclipse
 * @Generated
 */
public privileged aspect CustomerCard_DefAspect_getAllInstances {

    protected static java.util.Map<String, java.util.Map> allInstances = new java.util.HashMap<String, java.util.Map>();

    /**
     * <p>Adds all instances of the class {@link org.dresdenocl.examples.royalandloyal.CustomerCard} to the {@link java.util.Map} allInstances.</p>
     */
    after(org.dresdenocl.examples.royalandloyal.CustomerCard aClass) : execution(org.dresdenocl.examples.royalandloyal.CustomerCard.new(..)) && this(aClass) {

        java.util.Map<org.dresdenocl.examples.royalandloyal.CustomerCard, Object> instanceMap;

        instanceMap = (java.util.Map<org.dresdenocl.examples.royalandloyal.CustomerCard, Object>) allInstances.get(aClass.getClass().getCanonicalName());

        if (instanceMap == null) {
            instanceMap = new java.util.WeakHashMap<org.dresdenocl.examples.royalandloyal.CustomerCard, Object>();
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
    public java.util.Set<org.dresdenocl.examples.royalandloyal.CustomerCard> org.dresdenocl.examples.royalandloyal.CustomerCard.getAllInstances() {
        return (new java.util.HashSet<org.dresdenocl.examples.royalandloyal.CustomerCard>((java.util.Set<org.dresdenocl.examples.royalandloyal.CustomerCard>) allInstances.get(org.dresdenocl.examples.royalandloyal.CustomerCard.class.getCanonicalName()).keySet()));
    }
}