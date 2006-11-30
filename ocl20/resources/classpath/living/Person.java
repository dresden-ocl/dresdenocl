
package living;

import java.util.Calendar;

/**
 * 
 * 
 */
public class Person {

/**
 * <p>Represents ...</p>
 * 
 */
    private String name;

/**
 * <p>Represents ...</p>
 * 
 */
    private java.util.Calendar birthday;

/**
 * <p>Represents ...</p>
 * 
 */
    private int age;
/**
 * 
 * 
 * 
 * @poseidon-type living.Insurance
 */
    public java.util.Collection insurance = new java.util.TreeSet();

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
 * @param birthday 
 * @param accMain 
 */
    public  Person(String name, java.util.Calendar birthday, living.Accomodation accMain) {        
    	this.name = name;
    	this.birthday = birthday;
    	if (accMain instanceof Accomodation)
    		accomodation.add(accMain);
    	calculateAge();
    } 

/**
 * <p>Does ...</p>
 * 
 */
    protected void calculateAge() {        
    	Calendar age = Calendar.getInstance();    	
    	age.setTimeInMillis(System.currentTimeMillis()-birthday.getTimeInMillis());
    	this.age = age.get(Calendar.YEAR)-1970;
    } 

/**
 * <p>Does ...</p>
 * 
 * 
 * @return 
 */
    public int getAge() {        
    	calculateAge();
    	return age;
    } 

/**
 * <p>Does ...</p>
 * 
 * 
 * @param acc 
 */
    public void addAcc(living.Accomodation acc) {        
    	if (acc instanceof Accomodation)
    		accomodation.add(acc);
    } 

/**
 * <p>Does ...</p>
 * 
 * 
 * @return 
 */
    public String toString() {
    	return "Name: " + name + "\nBirthday: " + birthday.getTime() + "\nAge: " + getAge() + "\nAddress:" + (accomodation.size() > 0 ? "\n" + ((java.util.TreeSet)accomodation).first() + "\n":"\n");
    }
    

/**
 * <p>Does ...</p>
 * 
 * 
 * @param acc 
 */
    public void removeAcc(living.Accomodation acc) {        
    	if (acc instanceof Accomodation)
    		accomodation.remove(acc);
    } 
 }
