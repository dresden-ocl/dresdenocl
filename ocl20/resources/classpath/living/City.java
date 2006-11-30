
package living;

/**
 * 
 * 
 */
public class City {

/**
 * <p>Represents ...</p>
 * 
 */
    private String name = "";

/**
 * <p>Represents ...</p>
 * 
 */
    private long area;

/**
 * 
 * 
 * 
 * @poseidon-type Living.Accomodation
 */
    public java.util.Collection accomodation = new java.util.TreeSet();

 /**
 * <p>Does ...</p>
 * 
 * 
 * @param name 
 */
    public  City(String name) {        
    	this.name = name;
    	this.area = -1;
    } 

/**
 * <p>Does ...</p>
 * 
 * 
 * @return 
 */
    public String getName() {        
        // your code here
        return name;
    } 

/**
 * <p>Does ...</p>
 * 
 * 
 * @param name 
 * @param area 
 */
    public  City(String name, long area) {
        this.name = name;
        this.area = area;
    } 
    
/**
 * <p>Does ...</p>
 * 
 * 
 * @param like 
 * @return 
 */
    public String getSomeString(String like) {        
        // your code here
        return "";
    } 
 }
