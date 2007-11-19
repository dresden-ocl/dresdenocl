package tudresden.ocl20.pivot.examples.living;

import java.util.Calendar;

public class Worker extends Person {

  private float income;

  private float distanceToWork;

  private float averageSpeed;
  
  public Worker(String name, Calendar birthday, Accomodation accMain) {
  	super(name, birthday, accMain);
  }
}