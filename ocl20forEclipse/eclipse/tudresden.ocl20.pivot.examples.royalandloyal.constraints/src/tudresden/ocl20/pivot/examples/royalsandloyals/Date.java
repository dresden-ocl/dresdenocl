package tudresden.ocl20.pivot.examples.royalsandloyals;

import java.util.GregorianCalendar;

/**
 * <p>
 * Represents an implementation of the {@link Date} of the Loyals and Royals
 * example.
 * </p>
 * 
 * @author Claas Wilke
 */
public class Date {

	protected GregorianCalendar myDate;
	
	public static Date now = now();

	public static String nowString = nowAsString();

	private Date() {

		this.myDate = new GregorianCalendar();
	}

	public Date(int year, int month, int day) {

		this.myDate = new GregorianCalendar();

		this.myDate.set(year, month, day);
	}

	public boolean isAfter(Date aDate) {
		return this.myDate.after(aDate.myDate);
	}

	public boolean isBefore(Date aDate) {

		boolean result;

		result = true;

		if (aDate != null) {
			result = this.myDate.before(aDate.myDate);
		}
		// no else.

		return result;
	}

	public static Date now() {
		return new Date();
	}
	
	public static String nowAsString() {
		return now().toString();
	}
	
	public String toString() {
		
		String result;
		
		result = "";
		result += this.myDate.get(GregorianCalendar.DAY_OF_MONTH);
		result += "-";
		result += this.myDate.get(GregorianCalendar.MONTH);
		result += "-";
		result += this.myDate.get(GregorianCalendar.YEAR);
		
		return result;
	}
}
