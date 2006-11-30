
package living;

/**
 * 
 * 
 */
public class Flat extends living.Accomodation {

/**
 * <p>Represents ...</p>
 * 
 */
    private int number;
/**
 * 
 * 
 */
    public living.House house;

    public  Flat(living.House house, int number) {
    	super(house.getStreet(), house.getHousenumber(), house.getZip(), house.getCity());
    	this.number = number;
    	this.house = house;
    	house.addFlat(this);
    	
        // your code here
    } 
    
    public String toString() {
    	return "Wohnung " + number + "\n" + super.toString();
    }
}
