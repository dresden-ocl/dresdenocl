package tudresden.ocl20.pivot.examples.living;

public class Insurance {

  private static float currentPercentage = 5;

  private static int averageYears = 12;

    
  static public float calculateOffer(float insuranceSum) {
  	return (insuranceSum / averageYears / (1 + currentPercentage / 100));
  }

  static public void setPercentage(float percentage) {
  	currentPercentage = percentage;
  }

  static public float getPercentage() {
  	return currentPercentage;
  }

  static public void setYears(int years) {
  	averageYears = years;
  }

  static public int getYears() {
  	return averageYears;
  }
}