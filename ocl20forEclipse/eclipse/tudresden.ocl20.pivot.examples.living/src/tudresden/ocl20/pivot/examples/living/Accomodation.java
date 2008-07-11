package tudresden.ocl20.pivot.examples.living;

import java.util.Calendar;
import java.util.Vector;

public class Accomodation implements Comparable {

  protected int category;

  protected int price;

  protected String street;

  protected int housenumber;

  protected String zip;
  
  protected City city;
  
  public static int PERMANENTRESIDENCE = 1;

  public static int SECONDARYRESIDENCE = 2;

    /**
   * 
   * @element-type Person
   */
    /**
   * 
   * @element-type Insurance
   */
  public Vector  insurance;
  
  public Accomodation(String street, int housenumber, String zip, City city) {
  	this.street = street;
  	this.housenumber = housenumber;
  	this.zip = zip;
  	this.city = city;
  	insurance = new Vector();
  	this.city.addAcc(this);
  }

  public boolean isAvailable(Calendar date) {
  	return false;
  }

  public int getCategory() {
  	return category;
  }

  public City getCity() {
  	return city;
  }

  public int getHousenumber() {
  	return housenumber;
  }

  public String getStreet() {
  	return street;
  }

  public String getZip() {
  	return zip;
  }

  public boolean setCategory(int category) {
  	if (this.category == category)
  		return true;
  	this.category = category;
  	return false;
  }

  @Override
  public String toString() {
  	return "Acco: " + street + " " + housenumber + ", " + zip + " " + city;
  }

  public int compareTo(Object o) {
  	if (o instanceof Accomodation)
  	{
  		Accomodation acc = (Accomodation)o;
  		if (acc.price < price)
  			return -1;
  		if (acc.price > price)
  			return 1;
  		return 0;
  	}
  	return -1;
  }
}