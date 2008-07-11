package tudresden.ocl20.pivot.examples.living;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Vector;

public class Person {

  protected String name;

  protected Calendar birthday;

  private int age = -1;

    /**
   * 
   * @element-type Accomodation
   */
  List<Accomodation> accs;
  
    /**
   * 
   * @element-type Insurance
   */
  public Vector  insurance;

  public Person(String name, Calendar birthday, Accomodation accMain) {
  	this.name = name;
  	this.birthday = birthday;
  	accs = new ArrayList<Accomodation>();
  	accs.add(accMain);
  	insurance = new Vector();
  }

  protected void calculateAge() {
  	age = (Calendar.getInstance().get(Calendar.YEAR) - birthday.get(Calendar.YEAR));
  }

  public int getAge() {
  	if (age == -1)
  		calculateAge();
  	return age;
  }

  public void addAcc(Accomodation acc) {
  	this.accs.add(acc);
  }
  
  public String getBirthday() {
	  return birthday.get(Calendar.DAY_OF_MONTH) + "." + (birthday.get(Calendar.MONTH)+1) + "." + birthday.get(Calendar.YEAR); 
  }

  @Override
  public String toString() {
  	return "Person: " + name + " " + getBirthday() + " " + accs;
  }

  public void removeAcc(Accomodation acc) {
  	if (accs.contains(acc))
  		accs.remove(acc);
  }
}