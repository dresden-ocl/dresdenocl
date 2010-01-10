package tudresden.ocl20.pivot.examples.royalsandloyals.constraints;

/**
 * <p>
 * The class {@link tudresden.ocl20.pivot.examples.royalsandloyals.constraints.ExtendedCustomer} provides some newly defined attributes and methods class {@link Customer}.
 * </p>
 */
public class ExtendedCustomer extends Object {

    protected String initial;    
    
    /**
     * @return The attribute initial of the type {@link String}.
     */
    public String getInitial() {
        return this.initial;
    }

    /**
     *<p>
     *A method signature implemented by an aspect.
     *</p>
     */
    public void checkInvariants() {
    }
        
}