package living;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Creating cities 'Dresden' and 'Goerlitz'");
		City dresden = new City("Dresden");
		City goerlitz = new City("Goerlitz");
		
		System.out.println("Creating houses and adding flats");
		House jahn = new House("JS", 8, "02828", goerlitz);	
		Accomodation accJahnMain4 = new Flat(jahn, 4);
		accJahnMain4.setCategory(Accomodation.PERMANENTRESIDENCE);
		Accomodation accJahnSecond4 = new Flat(jahn, 4);
		accJahnSecond4.setCategory(Accomodation.SECONDARYRESIDENCE);
		Accomodation accJahnMain7 = new Flat(jahn, 7);
		accJahnMain7.setCategory(Accomodation.PERMANENTRESIDENCE);
		House awp = new House("AWP", 2, "01239", dresden);
		Accomodation accAWPMain = new Flat(awp, 125);
		accAWPMain.setCategory(Accomodation.PERMANENTRESIDENCE);		
		Accomodation accTGSSecond = new SingleRoom("TGS", 94, "01219", dresden, 311);
		accTGSSecond.setCategory(Accomodation.SECONDARYRESIDENCE);

		System.out.println("Creating persons and setting residence");
		Student ronny = new Student("Ronny Brandt", new java.util.GregorianCalendar(1981,Calendar.AUGUST,10), accAWPMain);
		ronny.addAcc(accJahnSecond4);
		Person henry = new ResidentWorker("H B", new java.util.GregorianCalendar(1982,Calendar.NOVEMBER,16), accJahnMain7);		
		henry.addAcc(accJahnMain7);
		Person martin = new Student("M B", new GregorianCalendar(1984, Calendar.DECEMBER, 18), accJahnMain7);
		martin.addAcc(accTGSSecond);
		
		henry.removeAcc(accJahnMain7);
		
		System.out.println("Creating courses and let student visit them");
		Course swt = new Course("Softwaretechnik");
		Course et = new Course("Elektrotechnik");
		ronny.visitCourse(swt);
		
		System.out.println("Creating examinations and let student write them");
		Examination exSwt = new Examination(swt);
		Examination exEt = new Examination(et);
		ronny.writeExamination(exSwt);
		ronny.writeExamination(exEt);
		
		System.out.println();
		System.out.println(ronny);
		System.out.println(henry);
		System.out.println(martin);
	}
}
