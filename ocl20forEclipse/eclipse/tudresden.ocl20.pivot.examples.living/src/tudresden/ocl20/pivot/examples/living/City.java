package tudresden.ocl20.pivot.examples.living;

import java.util.Vector;

public class City {

  private String name = "";

  private long area;

    /**
   * 
   * @element-type Accomodation
   */
  public Vector<Accomodation> accomodation;

  public City(String name) {
  	this.name = name;
  }

  public String getName() {
  	return name;
  }

  public String getSomeString(String like) {
  	return "some String like " + like;
  }

  public City(String name, long area) {
  	this.name = name;
  	this.area = area;
  	accomodation = new Vector<Accomodation>();
  }
  
  public void addAcc(Accomodation acc) {
	  if (accomodation == null)
		  accomodation = new Vector<Accomodation>();
	  if (!accomodation.contains(acc))
		  accomodation.add(acc);
  }

  public void removeAcc(Accomodation acc) {
	  if (!accomodation.contains(acc))
		  accomodation.remove(acc);
  }
  
  @Override
  public String toString() {
	  return "City: " + getName();
  }
}