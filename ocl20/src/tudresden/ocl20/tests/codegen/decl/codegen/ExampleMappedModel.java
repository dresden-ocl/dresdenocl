/*
 * ExampleMappedModel.java
 * 
 * Copyright (c) 2006 Florian Heidenreich
 * Contact: <mail@fheidenreich.de>
 *
 * This file is part of the Dresden OCL2.0 Toolkit
 * created at Technische Universitaet Dresden (TUD), Germany.
 * Visit http://dresden-ocl.sourceforge.net/ for details.
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston,
 * MA  02111-1307  USA
 *
 */

package tudresden.ocl20.tests.codegen.decl.codegen;

import java.util.HashMap;
import java.util.Map;

import tudresden.ocl20.codegen.decl.mapping.Guide;
import tudresden.ocl20.codegen.decl.mapping.MappedClass;
import tudresden.ocl20.codegen.decl.mapping.MappedModel;

/**
 * An example implementation of <code>MappedModel</code> used by the test case 
 * <code>TestDeclarativeCodeGenerator</code>.
 * 
 * @author Florian Heidenreich
 * 
 * @see tudresden.ocl2.tests.codegen.decl.codegen.TestDeclarativeCodeGenerator.java
 */
/*
 * Javadoc added by Claas Wilke in 2007.
 */
public class ExampleMappedModel implements MappedModel {
	
	private static int id = 0;
	private Map<String,MappedClass> classes;

	public ExampleMappedModel() {
		classes = new HashMap<String, MappedClass>();
		
		createChair();
		createEmployee();
		createFacility();
		createFaculty();
		createGrade();
		createInstitute();
		createPaper();
		createPerson();
		createStudent();
	}
	
	public MappedClass getClass(String name) {
		return classes.get(name);
	}

	public String getUniqueAlias() {
		id ++;
		if (id == 1) {
			return "SELF";
		}
		return "ALIAS" + id;
	}
	
	private void createChair() {
		ExampleMappedClass chair = new ExampleMappedClass("Chair");
		
		Guide guide = new Guide(false);
		guide.add("name", "OV_Chair", "PK_Facility");
		chair.addAttributeGuide("name", guide);
		
		guide = new Guide(true);
		guide.add("FK_headOfFacility", "OV_Chair", "PK_Facility");
		chair.addAssociationEndGuide("headOfFacility", guide);

		guide = new Guide(true);
		guide.add("FK_member", "OV_Person", "PK_Person");
		guide.add("FK_member", "ASS_ASSTAB3", "FK_owner");
		guide.add("PK_Facility", "OV_Chair", "PK_Facility");
		chair.addAssociationEndGuide("owner", guide);

		guide = new Guide(true);
		guide.add("PK_Facility", "OV_Chair", "FK_superFacility");
		chair.addAssociationEndGuide("subFacility", guide);

		guide = new Guide(true);
		guide.add("FK_superFacility", "OV_Chair", "PK_Facility");
		chair.addAssociationEndGuide("superFacility", guide);
		
		guide = new Guide(false);
		guide.add("PK_Facility", "OV_Chair", "PK_Facility");
		chair.setClassGuide(guide);
		
		classes.put("Chair", chair);		
	}

	private void createEmployee() {
		ExampleMappedClass employee = new ExampleMappedClass("Employee");
		
		Guide guide = new Guide(false);
		guide.add("isMarried", "OV_Employee", "PK_Person");
		employee.addAttributeGuide("isMarried", guide);

		guide = new Guide(false);
		guide.add("age", "OV_Employee", "PK_Person");
		employee.addAttributeGuide("age", guide);

		guide = new Guide(false);
		guide.add("taxClass", "OV_Employee", "PK_Person");
		employee.addAttributeGuide("taxClass", guide);

		guide = new Guide(false);
		guide.add("wage", "OV_Employee", "PK_Person");
		employee.addAttributeGuide("wage", guide);

		guide = new Guide(false);
		guide.add("firstName", "OV_Employee", "PK_Person");
		employee.addAttributeGuide("firstName", guide);

		guide = new Guide(false);
		guide.add("birthDate", "OV_Employee", "PK_Person");
		employee.addAttributeGuide("birthDate", guide);

		guide = new Guide(false);
		guide.add("soSecNr", "OV_Employee", "PK_Person");
		employee.addAttributeGuide("soSecNr", guide);

		guide = new Guide(false);
		guide.add("lastName", "OV_Employee", "PK_Person");
		employee.addAttributeGuide("lastName", guide);

		guide = new Guide(true);
		guide.add("FK_theFacility", "OV_Employee", "PK_Person");
		employee.addAssociationEndGuide("theFacility", guide);

		guide = new Guide(true);
		guide.add("PK_Facility", "OV_Facility", "PK_Facility");
		guide.add("FK_owner", "ASS_ASSTAB3", "FK_member");
		guide.add("PK_Person", "OV_Employee", "PK_Person");
		employee.addAssociationEndGuide("owner", guide);

		guide = new Guide(true);
		guide.add("FK_supervisor", "OV_Employee", "PK_Person");
		employee.addAssociationEndGuide("supervisor", guide);

		guide = new Guide(true);
		guide.add("PK_Person", "OV_Employee", "FK_supervisor");
		employee.addAssociationEndGuide("supervised", guide);

		guide = new Guide(true);
		guide.add("FK_currentPaper", "OV_Employee", "PK_Person");
		employee.addAssociationEndGuide("currentPaper", guide);

		guide = new Guide(true);
		guide.add("FK_grade", "OV_Employee", "PK_Person");
		employee.addAssociationEndGuide("grade", guide);

		guide = new Guide(true);
		guide.add("PK_Paper", "OV_Paper", "PK_Paper");
		guide.add("FK_papers", "ASS_ASSTAB2", "FK_author");
		guide.add("PK_Person", "OV_Employee", "PK_Person");
		employee.addAssociationEndGuide("papers", guide);
		
		guide = new Guide(false);
		guide.add("PK_Person", "OV_Employee", "PK_Person");
		employee.setClassGuide(guide);

		classes.put("Employee", employee);
	}

	private void createFacility() {
		ExampleMappedClass facility = new ExampleMappedClass("Facility");
		
		Guide guide = new Guide(false);
		guide.add("name", "OV_Facility", "PK_Facility");
		facility.addAttributeGuide("name", guide);
		
		guide = new Guide(true);
		guide.add("FK_headOfFacility", "OV_Facility", "PK_Facility");
		facility.addAssociationEndGuide("headOfFacility", guide);

		guide = new Guide(true);
		guide.add("PK_Facility", "OV_Facility", "FK_superFacility");
		facility.addAssociationEndGuide("subFacility", guide);

		guide = new Guide(true);
		guide.add("PK_Person", "OV_Person", "PK_Person");
		guide.add("FK_member", "ASS_ASSTAB3", "FK_owner");
		guide.add("PK_Facility", "OV_Facility", "PK_Facility");
		facility.addAssociationEndGuide("member", guide);

		guide = new Guide(true);
		guide.add("FK_superFacility", "OV_Facility", "PK_Facility");
		facility.addAssociationEndGuide("superFacility", guide);
		
		guide = new Guide(false);
		guide.add("PK_Facility", "OV_Facility", "PK_Facility");
		facility.setClassGuide(guide);
		
		classes.put("Facility", facility);
	}

	private void createFaculty() {
		ExampleMappedClass faculty = new ExampleMappedClass("Faculty");
		
		Guide guide = new Guide(false);
		guide.add("name", "OV_Faculty", "PK_Facility");
		faculty.addAttributeGuide("name", guide);
		
		guide = new Guide(true);
		guide.add("FK_headOfFacility", "OV_Faculty", "PK_Facility");
		faculty.addAssociationEndGuide("headOfFacility", guide);

		guide = new Guide(true);
		guide.add("FK_member", "OV_Person", "PK_Person");
		guide.add("FK_member", "ASS_ASSTAB3", "FK_owner");
		guide.add("PK_Facility", "OV_Faculty", "PK_Facility");
		faculty.addAssociationEndGuide("owner", guide);

		guide = new Guide(true);
		guide.add("PK_Facility", "OV_Faculty", "FK_superFacility");
		faculty.addAssociationEndGuide("subFacility", guide);

		guide = new Guide(true);
		guide.add("FK_superFacility", "OV_Faculty", "PK_Facility");
		faculty.addAssociationEndGuide("superFacility", guide);

		guide = new Guide(false);
		guide.add("PK_Facility", "OV_Faculty", "PK_Facility");
		faculty.setClassGuide(guide);
		
		classes.put("Faculty", faculty);
	}

	private void createGrade() {
		ExampleMappedClass grade = new ExampleMappedClass("Grade");
		
		Guide guide = new Guide(false);
		guide.add("value", "OV_Grade", "PK_Grade");
		grade.addAttributeGuide("value", guide);

		guide = new Guide(false);
		guide.add("name", "OV_Grade", "PK_Grade");
		grade.addAttributeGuide("name", guide);

		guide = new Guide(true);
		guide.add("FK_person", "OV_Grade", "PK_Grade");
		grade.addAssociationEndGuide("person", guide);
		
		guide = new Guide(false);
		guide.add("PK_Grade", "OV_Grade", "PK_Grade");
		grade.setClassGuide(guide);

		classes.put("Grade", grade);
	}

	private void createInstitute() {
		ExampleMappedClass institute = new ExampleMappedClass("Institute");
		
		Guide guide = new Guide(false);
		guide.add("name", "OV_Institute", "PK_Facility");
		institute.addAttributeGuide("name", guide);
		
		guide = new Guide(true);
		guide.add("FK_headOfFacility", "OV_Institute", "PK_Facility");
		institute.addAssociationEndGuide("headOfFacility", guide);

		guide = new Guide(true);
		guide.add("FK_member", "OV_Person", "PK_Person");
		guide.add("FK_member", "ASS_ASSTAB3", "FK_owner");
		guide.add("PK_Facility", "OV_Institute", "PK_Facility");
		institute.addAssociationEndGuide("owner", guide);

		guide = new Guide(true);
		guide.add("PK_Facility", "OV_Institute", "FK_superFacility");
		institute.addAssociationEndGuide("subFacility", guide);

		guide = new Guide(true);
		guide.add("FK_superFacility", "OV_Institute", "PK_Facility");
		institute.addAssociationEndGuide("superFacility", guide);
		
		guide = new Guide(false);
		guide.add("PK_Facility", "OV_Institute", "PK_Facility");
		institute.setClassGuide(guide);
		
		classes.put("Institute", institute);
	}

	private void createPaper() {
		ExampleMappedClass paper = new ExampleMappedClass("Paper");
		
		Guide guide = new Guide(false);
		guide.add("purpose", "OV_Paper", "PK_Paper");
		paper.addAttributeGuide("purpose", guide);
		
		guide = new Guide(false);
		guide.add("edition", "OV_Paper", "PK_Paper");
		paper.addAttributeGuide("edition", guide);

		guide = new Guide(false);
		guide.add("inProgress", "OV_Paper", "PK_Paper");
		paper.addAttributeGuide("inProgress", guide);

		guide = new Guide(false);
		guide.add("title", "OV_Paper", "PK_Paper");
		paper.addAttributeGuide("title", guide);

		guide = new Guide(false);
		guide.add("category", "OV_Paper", "PK_Paper");
		paper.addAttributeGuide("category", guide);
		
		guide = new Guide(true);
		guide.add("FK_person", "OV_Paper", "PK_Paper");
		paper.addAssociationEndGuide("person", guide);

		guide = new Guide(true);
		guide.add("PK_Person", "OV_Person", "PK_Person");
		guide.add("FK_author", "ASS_ASSTAB2", "FK_papers");
		guide.add("PK_Paper", "OV_Paper", "PK_Paper");
		paper.addAssociationEndGuide("author", guide);

		guide = new Guide(false);
		guide.add("PK_Paper", "OV_Paper", "PK_Paper");
		paper.setClassGuide(guide);
		
		classes.put("Paper", paper);
	}

	private void createPerson() {
		ExampleMappedClass person = new ExampleMappedClass("Person");
		
		Guide guide = new Guide(false);
		guide.add("isMarried", "OV_Person", "PK_Person");
		person.addAttributeGuide("isMarried", guide);

		guide = new Guide(false);
		guide.add("age", "OV_Person", "PK_Person");
		person.addAttributeGuide("age", guide);

		guide = new Guide(false);
		guide.add("firstName", "OV_Person", "PK_Person");
		person.addAttributeGuide("firstName", guide);

		guide = new Guide(false);
		guide.add("birthDate", "OV_Person", "PK_Person");
		person.addAttributeGuide("birthDate", guide);
		
		guide = new Guide(false);
		guide.add("lastName", "OV_Person", "PK_Person");
		person.addAttributeGuide("lastName", guide);
		
		guide = new Guide(true);
		guide.add("FK_theFacility", "OV_Person", "PK_Person");
		person.addAssociationEndGuide("theFacility", guide);

		guide = new Guide(true);
		guide.add("PK_Facility", "OV_Facility", "PK_Facility");
		guide.add("FK_owner", "ASS_ASSTAB3", "FK_member");
		guide.add("PK_Person", "OV_Person", "PK_Person");
		person.addAssociationEndGuide("owner", guide);

		guide = new Guide(true);
		guide.add("FK_supervisor", "OV_Person", "PK_Person");
		person.addAssociationEndGuide("supervisor", guide);

		guide = new Guide(true);
		guide.add("PK_Person", "OV_Person", "FK_supervisor");
		person.addAssociationEndGuide("supervised", guide);

		guide = new Guide(true);
		guide.add("FK_currentPaper", "OV_Person", "PK_Person");
		person.addAssociationEndGuide("currentPaper", guide);

		guide = new Guide(true);
		guide.add("FK_grade", "OV_Person", "PK_Person");
		person.addAssociationEndGuide("grade", guide);

		guide = new Guide(true);
		guide.add("PK_Paper", "OV_Paper", "PK_Paper");
		guide.add("FK_papers", "ASS_ASSTAB2", "FK_author");
		guide.add("PK_Person", "OV_Person", "PK_Person");
		person.addAssociationEndGuide("papers", guide);
		
		guide = new Guide(false);
		guide.add("PK_Person", "OV_Person", "PK_Person");
		person.setClassGuide(guide);
		
		classes.put("Person", person);
	}

	private void createStudent() {
		ExampleMappedClass student = new ExampleMappedClass("Student");
		
		Guide guide = new Guide(false);
		guide.add("isMarried", "OV_Student", "PK_Person");
		student.addAttributeGuide("isMarried", guide);

		guide = new Guide(false);
		guide.add("age", "OV_Student", "PK_Person");
		student.addAttributeGuide("age", guide);

		guide = new Guide(false);
		guide.add("matNr", "OV_Student", "PK_Person");
		student.addAttributeGuide("matNr", guide);

		guide = new Guide(false);
		guide.add("matDate", "OV_Student", "PK_Person");
		student.addAttributeGuide("matDate", guide);

		guide = new Guide(false);
		guide.add("firstName", "OV_Student", "PK_Person");
		student.addAttributeGuide("firstName", guide);

		guide = new Guide(false);
		guide.add("birthDate", "OV_Student", "PK_Person");
		student.addAttributeGuide("birthDate", guide);

		guide = new Guide(false);
		guide.add("lastName", "OV_Student", "PK_Person");
		student.addAttributeGuide("lastName", guide);
		
		guide = new Guide(true);
		guide.add("FK_theFacility", "OV_Person", "PK_Person");
		student.addAssociationEndGuide("theFacility", guide);

		guide = new Guide(true);
		guide.add("PK_Facility", "OV_Facility", "PK_Facility");
		guide.add("FK_owner", "ASS_ASSTAB3", "FK_member");
		guide.add("PK_Person", "OV_Student", "PK_Person");
		student.addAssociationEndGuide("owner", guide);

		guide = new Guide(true);
		guide.add("FK_supervisor", "OV_Student", "PK_Person");
		student.addAssociationEndGuide("supervisor", guide);

		guide = new Guide(true);
		guide.add("PK_Person", "OV_Person", "FK_supervisor");
		student.addAssociationEndGuide("supervised", guide);

		guide = new Guide(true);
		guide.add("FK_currentPaper", "OV_Person", "PK_Person");
		student.addAssociationEndGuide("currentPaper", guide);

		guide = new Guide(true);
		guide.add("FK_grade", "OV_Person", "PK_Person");
		student.addAssociationEndGuide("grade", guide);

		guide = new Guide(true);
		guide.add("PK_Paper", "OV_Paper", "PK_Paper");
		guide.add("FK_papers", "ASS_ASSTAB2", "FK_author");
		guide.add("PK_Person", "OV_Student", "PK_Person");
		student.addAssociationEndGuide("papers", guide);
		
		guide = new Guide(false);
		guide.add("PK_Person", "OV_Student", "PK_Person");
		student.setClassGuide(guide);
		
		classes.put("Student", student);
	}
}
