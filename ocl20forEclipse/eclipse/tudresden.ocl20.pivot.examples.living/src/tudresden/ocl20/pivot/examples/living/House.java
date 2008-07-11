package tudresden.ocl20.pivot.examples.living;

import java.util.Vector;

public class House extends Accomodation {

    /**
   * 
   * @element-type Flat
   */
  public Vector  flat;

  public void addFlat(Flat flat) {
  	this.flat.add(flat);
  	flat.setHouse(this);
  }
  
  public House(String street, int housenumber, String zip, City city) {
  	super(street, housenumber, zip, city);
  	flat = new Vector();
  }

  @Override
  public String toString() {
  	return "House: " + street + " " + housenumber + ", " + zip + " " + city;
  }
}