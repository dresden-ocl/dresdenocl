
package living;

/**
 * 
 * 
 */
public class Student extends living.Person {
/**
 * 
 * 
 */
    public living.Examination examination;
/**
 * 
 * 
 * 
 * @poseidon-type living.Course
 */
    public java.util.Collection visitedCourses = new java.util.TreeSet();

    public  Student(String name, java.util.Calendar birthday, Accomodation accMain) {        
        super(name, birthday, accMain);
    }     
    
/**
 * <p>Does ...</p>
 * 
 * 
 * @param ex 
 * @return 
 */
    public boolean writeExamination(living.Examination ex) {        
    	ex.setResult((float)2.3);
        return true;
    } 

/**
 * <p>Does ...</p>
 * 
 * 
 * @param group 
 */
    public void getAverageMark(int group) {        
        // your code here
    } 

/**
 * <p>Does ...</p>
 * 
 * 
 * @param course 
 */
    public void visitCourse(living.Course course) {        
    	visitedCourses.add(course);
    } 
 }
