package tudresden.ocl20.pivot.examples.living;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public class ModelProviderClass {

	public static List<Object> getModelObjects() {
		List<Object> example = new ArrayList<Object>();
		City dresden = new City("Dresden");
		example.add(dresden);
		House awp = new House("Somestreet", 2, "01239", dresden);
		example.add(awp);
		Flat f1 = new Flat(awp, 1205);
		f1.setCategory(Accomodation.PERMANENTRESIDENCE);
		example.add(f1);
		Flat f2 = new Flat(awp, 1204);
		example.add(f2);
		City goerlitz = new City("Görlitz");
		example.add(goerlitz);
		House js = new House("Anotherstreet", 8, "02828", goerlitz);
		example.add(js);
		Flat f3 = new Flat(js, 21);
		f3.setCategory(Accomodation.SECONDARYRESIDENCE);
		example.add(f3);
		Student s1 = new Student("Ronny Brandt", new GregorianCalendar(1981,
				Calendar.AUGUST, 10), f1);
		s1.addAcc(f3);
		example.add(s1);
		Accomodation acc = new Accomodation("Anystreet", 12, "02828", goerlitz);
		example.add(acc);
		Course c = new Course(null);
		example.add(c);
		return example;
	}
}
