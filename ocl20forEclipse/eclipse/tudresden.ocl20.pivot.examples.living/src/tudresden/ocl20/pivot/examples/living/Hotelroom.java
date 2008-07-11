package tudresden.ocl20.pivot.examples.living;

public class Hotelroom extends Accomodation {

  private int number;

  public Hotelroom(String street, int housenumber, String zip, City city, int number) {
  	super(street, housenumber, zip, city);
  	this.number = number;
  }
}