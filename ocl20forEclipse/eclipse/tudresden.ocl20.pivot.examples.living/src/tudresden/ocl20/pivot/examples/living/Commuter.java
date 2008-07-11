package tudresden.ocl20.pivot.examples.living;

import java.util.Calendar;

public class Commuter extends Worker {

  private int travelsPerMonth;
  
  
  public Commuter(String name, Calendar birthday, Accomodation accMain) {
  	super(name, birthday, accMain);
  }
}