/*
Copyright (C) 2009 by Franz Eichhorn (franz.eichhorn@gmx.de)

This file is part of the OCL Interpreter Test Suite of Dresden OCL2 for
Eclipse.

Dresden OCL2 for Eclipse is free software: you can redistribute it and/or modify 
it under the terms of the GNU Lesser General Public License as published by the 
Free Software Foundation, either version 3 of the License, or (at your option)
any later version.

Dresden OCL2 for Eclipse is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY 
or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License 
for more details.

You should have received a copy of the GNU Lesser General Public License along 
with Dresden OCL2 for Eclipse. If not, see <http://www.gnu.org/licenses/>.
*/
package tudresden.ocl20.benchmark.testdata.b2;

import java.util.ArrayList;
import java.util.List;

import tudresden.ocl20.benchmark.testdata.b2.CivilStatus;
import tudresden.ocl20.benchmark.testdata.b2.Gender;

// TODO: Auto-generated Javadoc
/**
 * Represents a model instance object from Test Part 2
 */
public class Person {

		
	public String name;
	
	public CivilStatus civstat;
	
	public Gender gender;
	
	public Boolean alive;
	
	public Person husband;
	
	public Person wife;
	
	
	//@ list of all persons to emulate the allInstances feature.
	private static List<Person> persons = new ArrayList<Person>();
	
	/**
	 * Instantiates a new person.
	 * 
	 * @param name 
	 * @param civstat 
	 * @param gender 
	 * @param alive 
	 */
	public Person(String name, CivilStatus civstat, Gender gender, boolean alive)
	{
		this();
		
		this.name = name;
		this.civstat = civstat;
		this.gender = gender;
		this.alive = alive;
	}
	
	/**
	 * Instantiates a new person and saves
	 * in the list of persons.
	 */
	public Person()
	{
		persons.add(this);
	}
	
	/**
	 * Returns the marriage partner.
	 * 
	 * @return husband or wife, according to gender
	 */
	public Person spouse()
	{
		if(this.gender == Gender.female){
			return this.husband;
		}else{
			return this.wife;
		}
	}
	
	/**
	 * Marry the person p.
	 * 
	 * @param p Person to marry
	 */
	public void marry(Person p)
	{		
		this.civstat = CivilStatus.married;
		p.civstat = CivilStatus.married;
		assert(p.gender != this.gender);
		this.setSpouse(p);
		p.setSpouse(this);
	}
	
	/**
	 * Sometimes the marriage doesn't pan out very well.
	 */
	public void divorce()
	{
		Person p = this.spouse();
		
		assert(this.civstat == CivilStatus.married);
		assert(p.civstat == CivilStatus.married);
		assert(p.name.equals(this.spouse().name));
		
		this.civstat = CivilStatus.divorced;
		p.civstat = CivilStatus.divorced;
		
		this.husband = null;
		this.wife = null;
		
		p.wife = null;
		p.husband = null;
	}
	
	/**
	 * Set spouse.
	 * 
	 * @param p the new spouse
	 */
	public void setSpouse(Person p)
	{
		if(this.gender == Gender.female){
			assert(p==null || p.gender == Gender.male);
			this.husband = p;
		}else{
			assert(p==null || p.gender == Gender.female);
			this.wife = p;
		}
	}
	
	/**
	 * Removes the spouse.
	 */
	public void removeSpouse()
	{
		this.setSpouse(null);
	}
	
	
	/**
	 * Birth-Method as defined in Test B2 of Benchmark
	 * used for Pre and Post Tests
	 */
	public void birth(String aName, Gender aGender)
	{
		this.alive = true;
		this.name = aName;
		this.gender = aGender;
		this.civstat = CivilStatus.single;
		
	}
	
	/**
	 * Dying means: removing spouse and setting alive to false.
	 */
	public void death()
	{
		if(this.spouse() != null){
			this.spouse().removeSpouse();
			this.removeSpouse();
		}
		
		this.alive = false;
	}
}
