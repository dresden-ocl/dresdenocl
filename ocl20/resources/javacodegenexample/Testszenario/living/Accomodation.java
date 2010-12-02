
package living;

/**
 * 
 * 
 */
public class Accomodation implements Comparable {

/**
 * <p>Represents ...</p>
 * 
 */
    private int category;

/**
 * <p>Represents ...</p>
 * 
 */
    private int price;

/**
 * <p>Represents ...</p>
 * 
 */
    private String street;

/**
 * <p>Represents ...</p>
 * 
 */
    private int housenumber;

/**
 * <p>Represents ...</p>
 * 
 */
    private String zip;

/**
 * <p>Represents ...</p>
 * 
 */
    public static int PERMANENTRESIDENCE = 1;

/**
 * <p>Represents ...</p>
 * 
 */
    public static int SECONDARYRESIDENCE = 2;
/**
 * 
 * 
 * 
 * @poseidon-type living.Insurance
 */
    public java.util.Collection insurance = new java.util.TreeSet();

    
    public living.City city;
    
/**
 * <p>Does ...</p>
 * 
 * 
 * @param street 
 * @param housenumber 
 * @param zip 
 * @param city 
 */
    public  Accomodation(String street, int housenumber, String zip, living.City city) {        
    	this.street = street;
    	this.housenumber = housenumber;
    	this.zip = zip;
    	this.city = city;
    } 

/**
 * <p>Does ...</p>
 * 
 * 
 * @param date 
 * @return 
 */
    public boolean isAvailable(java.util.Calendar date) {        
        // your code here
        return false;
    } 

/**
 * <p>Does ...</p>
 * 
 * 
 * @return 
 */
    public int getCategory() {        
    	return category;
    } 

/**
 * <p>Does ...</p>
 * 
 * 
 * @return 
 */
    public living.City getCity() {        
        // your code here
        return city;
    } 

/**
 * <p>Does ...</p>
 * 
 * 
 * @return 
 */
    public int getHousenumber() {        
        // your code here
        return housenumber;
    } 

/**
 * <p>Does ...</p>
 * 
 * 
 * @return 
 */
    public String getStreet() {        
        // your code here
        return street;
    } 

/**
 * <p>Does ...</p>
 * 
 * 
 * @return 
 */
    public String getZip() {        
        // your code here
        return zip;
    } 

/**
 * <p>Does ...</p>
 * 
 * 
 * @param category 
 * @return 
 */
    public boolean setCategory(int category) {        
    	this.category = category;
    	return false;
    } 

/**
 * <p>Does ...</p>
 * 
 * 
 * @return 
 */
    public String toString() {        
        // your code here
        return getStreet() + " " + getHousenumber() + "\n" + getZip() + " " + getCity().getName();
    } 

/**
 * <p>Does ...</p>
 * 
 * 
 * @param o 
 * @return 
 */
    public int compareTo(Object o) {        
    	Accomodation acc = (Accomodation)o;
    	if (acc.getCategory() > category)
    		return -1;
    	else if (acc.getCategory() < category)
    		return 1;
    	else
    		return 1;
    } 
 }
