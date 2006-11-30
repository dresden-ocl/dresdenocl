
package living;

/**
 * 
 * 
 */
public class House extends living.Accomodation {
/**
 * 
 * 
 * 
 * @poseidon-type living.Flat
 */
    public java.util.Collection flat = new java.util.TreeSet();

/**
 * <p>Does ...</p>
 * 
 * 
 * @param flat 
 */
    public void addFlat(living.Flat flat) {        
   		this.flat.add(flat);
    } 

    public  House(String street, int housenumber, String zip, living.City city) {        
        super(street, housenumber, zip, city);
    } 
 }
