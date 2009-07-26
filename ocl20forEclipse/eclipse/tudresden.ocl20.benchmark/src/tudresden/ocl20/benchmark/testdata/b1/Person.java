package tudresden.ocl20.benchmark.testdata.b1;

import java.lang.reflect.Field;

public class Person {

	public Person(String name, String civstat, String gender, boolean alive)
	{
		this.name = name;
		this.civstat = civstat;
		this.gender = gender;
		this.alive = alive;
	}
	
	public Person()
	{
	}
	
	public String name;
	
	public String civstat;
	
	public String gender;
	
	public boolean alive;
	
	public Person husband;
	
	public Person wife;
	
	public Person spouse()
	{
		if(this.gender.equals("female")){
			return this.husband;
		}else{
			return this.wife;
		}
	}
	
	public void marry(Person p)
	{		
		this.civstat = "married";
		p.civstat = "married";
		assert(!p.gender.equals(this.gender));
		this.setSpouse(p);
		p.setSpouse(this);
	}
	
	public void divorce(Person p)
	{
		assert(this.civstat.equals("married"));
		assert(p.civstat.equals("married"));
		assert(p.name.equals(this.spouse().name));
		
		this.civstat = "divorced";
		p.civstat = "divorced";
		
		this.husband = null;
		this.wife = null;
		
		p.wife = null;
		p.husband = null;
	}
	
	public void setSpouse(Person p)
	{
		if(this.gender == "female"){
			assert(p.gender == "male");
			this.husband = p;
		}else{
			assert(p.gender == "female");
			this.wife = p;
		}
	}
	
	public void removeSpouse()
	{
		this.setSpouse(null);
	}
	
	public void die()
	{
		this.spouse().removeSpouse();
		this.removeSpouse();
		this.alive = false;
	}
		
			
	
	public void print()
	{
		try{
			String buf = "";
			Field[] fields = this.getClass().getDeclaredFields();
			buf+= this.getClass().getName()+"(\n";
			for(Field field : fields){
				buf += "\t"+field.getName()+": "+field.get(this)+"\n";
			}
			buf += ")";
			
			System.out.println(buf);
		}catch(IllegalAccessException e){
			System.out.println("cannot print due to protected visiblity issues");
		}
	}
}
