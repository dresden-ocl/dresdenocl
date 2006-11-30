
package living;

/**
 * 
 * 
 */
public class Examination {

/**
 * <p>Represents ...</p>
 * 
 */
    private float mark;

/**
 * <p>Represents ...</p>
 * 
 */
    private boolean passed;

/**
 * <p>Represents ...</p>
 * 
 */
    private int group;
/**
 * 
 * 
 */
    public living.Course course;

/**
 * <p>Does ...</p>
 * 
 * 
 * @param mark 
 */
    public void setResult(float mark) {        
        this.mark = mark;
    	if (mark<=4.0)
    		this.passed = true;
    	else
    		this.passed = false;
    } 

/**
 * <p>Does ...</p>
 * 
 * 
 * @param course 
 */
    public  Examination(living.Course course) {        
        this.course = course;
    } 
 }
