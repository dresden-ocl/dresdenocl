package tudresden.ocl20.pivot.examples.living;

import java.util.Calendar;
import java.util.Vector;

public class Student extends Person {

    public Examination examination;
    /**
   * 
   * @element-type Course
   */
  public Vector  visitedCourses;

  public boolean writeExamination(Examination ex) {
  	return true;
  }

  public void getAverageMark(int group) {
  }

  public void visitCourse(Course course) {
  	this.visitedCourses.add(course);
  }
  
  public Student(String name, Calendar birthday, Accomodation accMain) {
  	super(name, birthday, accMain);
  	visitedCourses = new Vector();
  }
  
  @Override
  public String toString() {
	  	return "Student: " + name + " " + getBirthday() + " " + accs;
	  }
}