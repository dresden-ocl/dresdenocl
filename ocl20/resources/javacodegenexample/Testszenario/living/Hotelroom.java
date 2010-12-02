
package living;

/**
 * 
 * 
 */
public class Hotelroom extends living.Accomodation {

/**
 * <p>Represents ...</p>
 * 
 */
    private int number;

/**
 * <p>Does ...</p>
 * 
 * 
 * @param street 
 * @param housenumber 
 * @param zip 
 * @param city 
 * @param number 
 */
    public  Hotelroom(String street, int housenumber, String zip, living.City city, int number) {        
    	super(street, housenumber, zip, city);
    	this.number = number;
    } 
 }
