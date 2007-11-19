package tudresden.ocl20.pivot.examples.living;

public class Flat extends Accomodation {

  private int number;

  public House myHouse;
  
  public void setHouse(House house) {
  	this.myHouse = house;
  }
  
  public Flat(House house, int number) {
  	super(house.getStreet(), house.getHousenumber(), house.getZip(), house.getCity());
  	this.number = number;
  	house.addFlat(this);
  }
  
  @Override
  public String toString() {
	  return "Flat: " + street + " " + housenumber + ", " + zip + " " + city + ", Whg: " + number;
  }

  public int compareTo(Object o) {
  	if (o instanceof Flat)
  	{
  		Flat f = (Flat)o;
  		if (f.number < number)
  			return -1;
  		if (f.number > number)
  			return 1;
  		return 0;
  	}
  	return -1;
  }
}