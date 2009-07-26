package tudresden.ocl20.benchmark.testdata.b1;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;



public class ModelFirstState {

	
	/**
	 * required method be a dummy --> try to provide models as requested by each test method
	 * This hack is done to reuse testperformer from interpreter test, hope it works :)
	 */
	public static List<Object> getModelObjects()
	{
		return getFinalStateModelInstance().getModelObjects();
	}
	/**
	 * @return A simple model instance of the simple UML model.
	 */
	protected static PersonMap getFinalStateModelInstance() {
		
		Person ada;
		Person bob;
		Person cyd;
		Person dan;
		Person eve;
		
		ada = new Person("Ada", "widowed", "female", true);

		bob = new Person("Bob", "divorced", "male", true);
	
		
		cyd = new Person("Cyd", "married", "male", true);
		
		dan = new Person("Dan", "married", "male", true);
		
		eve = new Person("Eve", "married", "female", true);
		
		eve.marry(dan);	
		
		PersonMap result = new PersonMap(5);
		
		result.add(ada);
		result.add(ada);
		result.add(bob);
		result.add(cyd);
		result.add(dan);
		result.add(eve);
		
		return result;
	}
	
	
	
	static protected class PersonMap extends HashMap<String, Person>
	{
		private final static long serialVersionUID=1L;
		
		public PersonMap(int size)
		{
			super(size);
		}
		
		public void add(Person p)
		{
			this.put(p.name, p);			
		}
		
		public List<Object> getModelObjects()
		{
			Collection<Person> values = this.values();
			
			return new ArrayList<Object>(values);
		}
	}
	
	
	
}
