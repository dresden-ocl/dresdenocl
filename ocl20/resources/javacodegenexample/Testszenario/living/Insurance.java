
package living;

/**
 * 
 * 
 */
public class Insurance {

/**
 * <p>Represents ...</p>
 * 
 */
    private static float currentPercentage = 5;

/**
 * <p>Represents ...</p>
 * 
 */
    private static int averageYears = 12;

/**
 * <p>Does ...</p>
 * 
 * 
 * @param insuranceSum 
 * @return 
 */
    public static float calculateOffer(float insuranceSum) {        
        float result = ((insuranceSum / averageYears) / 12) / (currentPercentage / 100 + 1);
        return result;
    } 

/**
 * <p>Does ...</p>
 * 
 * 
 * @param percentage 
 */
    public static void setPercentage(float percentage) {        
    	currentPercentage = percentage;
    } 

/**
 * <p>Does ...</p>
 * 
 * 
 * @return 
 */
    public static float getPercentage() {        
        return currentPercentage;
    } 

/**
 * <p>Does ...</p>
 * 
 * 
 * @param years 
 */
    public static void setYears(int years) {        
        averageYears = years;
    } 

/**
 * <p>Does ...</p>
 * 
 * 
 * @return 
 */
    public static int getYears() {        
        return averageYears;
    } 
 }
