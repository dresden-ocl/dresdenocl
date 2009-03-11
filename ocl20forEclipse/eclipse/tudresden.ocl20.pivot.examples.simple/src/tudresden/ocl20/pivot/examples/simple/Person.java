package tudresden.ocl20.pivot.examples.simple;

public class Person {
	
	protected int age;
	
	protected String name;

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		
		String result;
		
		result = "Person[Name='" + this.name;
		result += "', age=" + this.age + "]";
		
		return result;
	}

}
