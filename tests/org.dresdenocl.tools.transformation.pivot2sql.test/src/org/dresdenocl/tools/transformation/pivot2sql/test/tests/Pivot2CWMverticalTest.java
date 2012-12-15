package org.dresdenocl.tools.transformation.pivot2sql.test.tests;

import java.util.ArrayList;
import java.util.List;

import org.junit.BeforeClass;

import org.dresdenocl.tools.transformation.impl.Tuple;
import org.dresdenocl.tools.transformation.pivot2sql.test.tests.util.CWMTest;

public class Pivot2CWMverticalTest extends CWMTest {

	@BeforeClass
	public static void setUp_class() {

		modus = MODUS_VERTICAL;
	}

	/**
	 * 
	 * <p>
	 * Checks if a class mapped correctly.
	 * </p>
	 */
	public void testClass() {

		super.testClass();
		String view;
		tables.add("T_Person");
		views.add("OV_Person");
		view = "SELECT T_Person.PK_Person\nFROM T_Person\n";
		table2PrimaryKey.put(tables.get(0), "PK_Person");
		view2queryexpression.put(views.get(0), view);
		checkCWM();
	}

	/**
	 * <p>
	 * Checks if a property mapped correctly.
	 * </p>
	 */
	public void testProperty() {

		super.testProperty();
		List<Tuple<String, String>> attributes =
				new ArrayList<Tuple<String, String>>();
		String view;
		tables.add("T_Person");
		views.add("OV_Person");
		table2PrimaryKey.put(tables.get(0), "PK_Person");
		attributes.add(new Tuple<String, String>("firstName", "String"));
		attributes.add(new Tuple<String, String>("lastName", "String"));
		attributes.add(new Tuple<String, String>("birthDate", "Integer"));
		attributes.add(new Tuple<String, String>("age", "Integer"));
		attributes.add(new Tuple<String, String>("isMarried", "Boolean"));
		attributes.add(new Tuple<String, String>("salaries", "String"));
		view = "SELECT T_Person.PK_Person,T_Person.age AS age,";
		view += "T_Person.birthDate AS birthDate,T_Person.firstName AS firstName,";
		view += "T_Person.isMarried AS isMarried,T_Person.lastName AS lastName,";
		view += "T_Person.salaries AS salaries\nFROM T_Person\n";
		table2properties.put(tables.get(0), attributes);
		view2queryexpression.put(views.get(0), view);
		checkCWM();
	}

	/**
	 * <p>
	 * Checks if a inheritance mapped correctly.
	 * </p>
	 */
	public void testInheritance() {

		super.testInheritance();
		String view;
		List<Tuple<String, String>> attributes =
				new ArrayList<Tuple<String, String>>();
		List<String> foreignKeys = new ArrayList<String>();
		tables.add("T_Person");
		views.add("OV_Person");
		table2PrimaryKey.put(tables.get(0), "PK_Person");
		attributes.add(new Tuple<String, String>("lastName", "String"));
		view = "SELECT T_Person.PK_Person,T_Person.lastName AS lastName";
		view += "\nFROM T_Person\n";
		table2properties.put(tables.get(0), attributes);
		view2queryexpression.put(views.get(0), view);

		attributes = new ArrayList<Tuple<String, String>>();
		tables.add("T_Student");
		views.add("OV_Student");
		table2PrimaryKey.put(tables.get(1), "PK_Person");
		foreignKeys.add("PK_Person");
		attributes.add(new Tuple<String, String>("matNr", "Integer"));
		view = "SELECT T_Student.PK_Person,T_Person.lastName AS lastName,";
		view += "T_Student.matNr AS matNr";
		view += "\nFROM T_Person,T_Student\n";
		view += "WHERE T_Student.PK_Person=T_Person.PK_Person";
		table2properties.put(tables.get(1), attributes);
		table2ForeignKey.put(tables.get(1), foreignKeys);
		view2queryexpression.put(views.get(1), view);
		checkCWM();
	}

	/**
	 * 
	 * <p>
	 * Checks if a 1to1 relation mapped correctly.
	 * </p>
	 */
	public void testRelation1to1() {

		super.testRelation1to1();
		String view;
		List<String> foreignKeys1 = new ArrayList<String>();
		tables.add("T_Person");
		views.add("OV_Person");
		foreignKeys1.add("FK_currentPaper");
		view =
				"SELECT T_Person.PK_Person,T_Person.FK_currentPaper AS FK_currentPaper";
		view += "\nFROM T_Person\n";
		table2PrimaryKey.put(tables.get(0), "PK_Person");
		table2ForeignKey.put(tables.get(0), foreignKeys1);
		view2queryexpression.put(views.get(0), view);
		tables.add("T_Paper");
		views.add("OV_Paper");
		view = "SELECT T_Paper.PK_Paper";
		view += "\nFROM T_Paper\n";
		table2PrimaryKey.put(tables.get(1), "PK_Paper");
		view2queryexpression.put(views.get(1), view);
		checkCWM();
	}

	/**
	 * 
	 * <p>
	 * Checks if a 1toN relation mapped correctly.
	 * </p>
	 */
	public void testRelation1toN() {

		super.testRelation1toN();
		List<String> foreignKeys = new ArrayList<String>();
		String view;
		tables.add("T_Facility");
		views.add("OV_Facility");
		foreignKeys.add("FK_superFacility");
		view =
				"SELECT T_Facility.PK_Facility,T_Facility.FK_superFacility AS FK_superFacility";
		view += "\nFROM T_Facility\n";
		table2PrimaryKey.put(tables.get(0), "PK_Facility");
		view2queryexpression.put(views.get(0), view);
		table2ForeignKey.put(tables.get(0), foreignKeys);
		checkCWM();
	}

	/**
	 * 
	 * <p>
	 * Checks if a Nto1 relation mapped correctly.
	 * </p>
	 */
	public void testRelationNto1() {

		super.testRelationNto1();
		List<String> foreignKeys = new ArrayList<String>();
		String view;
		tables.add("T_Facility");
		views.add("OV_Facility");
		foreignKeys.add("FK_superFacility");
		view =
				"SELECT T_Facility.PK_Facility,T_Facility.FK_superFacility AS FK_superFacility";
		view += "\nFROM T_Facility\n";
		table2PrimaryKey.put(tables.get(0), "PK_Facility");
		table2ForeignKey.put(tables.get(0), foreignKeys);
		view2queryexpression.put(views.get(0), view);
		checkCWM();
	}

	/**
	 * 
	 * <p>
	 * Checks if a MtoN relation mapped correctly.
	 * </p>
	 */
	public void testRelationMtoN() {

		super.testRelationMtoN();
		List<String> foreignKeys = new ArrayList<String>();
		String view;
		tables.add("T_Person");
		views.add("OV_Person");
		view = "SELECT T_Person.PK_Person\nFROM T_Person\n";
		table2PrimaryKey.put(tables.get(0), "PK_Person");
		view2queryexpression.put(views.get(0), view);
		tables.add("T_Paper");
		views.add("OV_Paper");
		view = "SELECT T_Paper.PK_Paper\nFROM T_Paper\n";
		table2PrimaryKey.put(tables.get(1), "PK_Paper");
		view2queryexpression.put(views.get(1), view);
		tables.add("ASS_author_papers");
		foreignKeys.add("FK_papers");
		foreignKeys.add("FK_author");
		table2ForeignKey.put(tables.get(2), foreignKeys);
		checkCWM();
	}

	/**
	 * <p>
	 * Checks if a complex university example mapped correctly.
	 * </p>
	 */
	public void testComplexUniversity() {

		super.testComplexUniversity();
		List<Tuple<String, String>> attributes =
				new ArrayList<Tuple<String, String>>();
		List<String> foreignKeys = new ArrayList<String>();
		String view;
		// Create Person:
		tables.add("T_Person");
		views.add("OV_Person");
		attributes.add(new Tuple<String, String>("firstName", "String"));
		attributes.add(new Tuple<String, String>("lastName", "String"));
		attributes.add(new Tuple<String, String>("birthDate", "Date"));
		attributes.add(new Tuple<String, String>("age", "Integer"));
		attributes.add(new Tuple<String, String>("isMarried", "Boolean"));
		attributes.add(new Tuple<String, String>("salaries", "String"));
		foreignKeys.add("FK_supervisor");
		foreignKeys.add("FK_currentPaper");
		foreignKeys.add("FK_grade");
		foreignKeys.add("FK_theFacility");
		view = "SELECT T_Person.PK_Person,T_Person.age AS age,";
		view += "T_Person.birthDate AS birthDate,T_Person.firstName AS firstName,";
		view += "T_Person.isMarried AS isMarried,T_Person.lastName AS lastName,";
		view +=
				"T_Person.salaries AS salaries,T_Person.FK_currentPaper AS FK_currentPaper,";
		view +=
				"T_Person.FK_grade AS FK_grade,T_Person.FK_supervisor AS FK_supervisor,";
		view += "T_Person.FK_theFacility AS FK_theFacility";
		view += "\nFROM T_Person\n";
		table2properties.put(tables.get(0), attributes);
		table2ForeignKey.put(tables.get(0), foreignKeys);
		table2PrimaryKey.put(tables.get(0), "PK_Person");
		view2queryexpression.put(views.get(0), view);

		attributes = new ArrayList<Tuple<String, String>>();
		foreignKeys = new ArrayList<String>();
		foreignKeys.add("PK_Person");

		// Create Student
		tables.add("T_Student");
		views.add("OV_Student");
		attributes.add(new Tuple<String, String>("matNr", "Integer"));
		attributes.add(new Tuple<String, String>("matDate", "Date"));
		view =
				"SELECT T_Student.PK_Person,T_Person.age AS age,T_Person.birthDate AS birthDate,";
		view += "T_Person.firstName AS firstName,T_Person.isMarried AS isMarried,";
		view += "T_Person.lastName AS lastName,T_Person.salaries AS salaries,";
		view += "T_Student.matDate AS matDate,T_Student.matNr AS matNr,";
		view +=
				"T_Person.FK_currentPaper AS FK_currentPaper,T_Person.FK_grade AS FK_grade,";
		view +=
				"T_Person.FK_supervisor AS FK_supervisor,T_Person.FK_theFacility AS FK_theFacility";
		view += "\nFROM T_Person,T_Student\n";
		view += "WHERE T_Student.PK_Person=T_Person.PK_Person";
		table2properties.put(tables.get(1), attributes);
		table2ForeignKey.put(tables.get(1), foreignKeys);
		table2PrimaryKey.put(tables.get(1), "PK_Person");
		view2queryexpression.put(views.get(1), view);

		attributes = new ArrayList<Tuple<String, String>>();

		// Create Employee
		tables.add("T_Employee");
		views.add("OV_Employee");
		attributes.add(new Tuple<String, String>("soSecNr", "String"));
		attributes.add(new Tuple<String, String>("taxClass", "String"));
		attributes.add(new Tuple<String, String>("wage", "Integer"));
		view = "SELECT T_Employee.PK_Person,T_Employee.soSecNr AS soSecNr,";
		view += "T_Employee.taxClass AS taxClass,T_Employee.wage AS wage,";
		view += "T_Person.age AS age,T_Person.birthDate AS birthDate,";
		view += "T_Person.firstName AS firstName,T_Person.isMarried AS isMarried,";
		view += "T_Person.lastName AS lastName,T_Person.salaries AS salaries,";
		view += "T_Person.FK_currentPaper AS FK_currentPaper,";
		view +=
				"T_Person.FK_grade AS FK_grade,T_Person.FK_supervisor AS FK_supervisor,";
		view += "T_Person.FK_theFacility AS FK_theFacility";
		view += "\nFROM T_Employee,T_Person\n";
		view += "WHERE T_Employee.PK_Person=T_Person.PK_Person";
		table2properties.put(tables.get(2), attributes);
		table2ForeignKey.put(tables.get(2), foreignKeys);
		table2PrimaryKey.put(tables.get(2), "PK_Person");
		view2queryexpression.put(views.get(2), view);

		attributes = new ArrayList<Tuple<String, String>>();

		// Create PhDStudent
		tables.add("T_PhDStudent");
		views.add("OV_PhDStudent");
		attributes.add(new Tuple<String, String>("dissSubject", "String"));
		view = "SELECT T_PhDStudent.PK_Person,T_Employee.soSecNr AS soSecNr,";
		view += "T_Employee.taxClass AS taxClass,T_Employee.wage AS wage,";
		view += "T_Person.age AS age,T_Person.birthDate AS birthDate,";
		view += "T_Person.firstName AS firstName,T_Person.isMarried AS isMarried,";
		view += "T_Person.lastName AS lastName,T_Person.salaries AS salaries,";
		view += "T_PhDStudent.dissSubject AS dissSubject,";
		view += "T_Person.FK_currentPaper AS FK_currentPaper,";
		view +=
				"T_Person.FK_grade AS FK_grade,T_Person.FK_supervisor AS FK_supervisor,";
		view += "T_Person.FK_theFacility AS FK_theFacility";
		view += "\nFROM T_Employee,T_Person,T_PhDStudent\n";
		view +=
				"WHERE T_PhDStudent.PK_Person=T_Employee.PK_Person AND T_Employee.PK_Person=T_Person.PK_Person";
		table2properties.put(tables.get(3), attributes);
		table2ForeignKey.put(tables.get(3), foreignKeys);
		table2PrimaryKey.put(tables.get(3), "PK_Person");
		view2queryexpression.put(views.get(3), view);

		attributes = new ArrayList<Tuple<String, String>>();
		foreignKeys = new ArrayList<String>();

		// Create Grade:
		tables.add("T_Grade");
		views.add("OV_Grade");
		attributes.add(new Tuple<String, String>("name", "String"));
		attributes.add(new Tuple<String, String>("value", "Integer"));
		view =
				"SELECT T_Grade.PK_Grade,T_Grade.name AS name,T_Grade.value AS value";
		view += "\nFROM T_Grade\n";
		view2queryexpression.put(views.get(4), view);
		table2properties.put(tables.get(4), attributes);
		table2PrimaryKey.put(tables.get(4), "PK_Grade");
		table2ForeignKey.put(tables.get(4), foreignKeys);

		attributes = new ArrayList<Tuple<String, String>>();
		foreignKeys = new ArrayList<String>();

		// Create Paper
		tables.add("T_Paper");
		views.add("OV_Paper");
		attributes.add(new Tuple<String, String>("title", "String"));
		attributes.add(new Tuple<String, String>("edition", "String"));
		attributes.add(new Tuple<String, String>("purpose", "String"));
		attributes.add(new Tuple<String, String>("category", "String"));
		attributes.add(new Tuple<String, String>("inProgress", "Boolean"));
		view = "SELECT T_Paper.PK_Paper,T_Paper.category AS category,";
		view += "T_Paper.edition AS edition,T_Paper.inProgress AS inProgress,";
		view += "T_Paper.purpose AS purpose,T_Paper.title AS title";
		view += "\nFROM T_Paper\n";
		view2queryexpression.put(views.get(5), view);
		table2properties.put(tables.get(5), attributes);
		table2PrimaryKey.put(tables.get(5), "PK_Paper");
		table2ForeignKey.put(tables.get(5), foreignKeys);

		attributes = new ArrayList<Tuple<String, String>>();
		foreignKeys = new ArrayList<String>();

		// Create Facility
		tables.add("T_Facility");
		views.add("OV_Facility");
		foreignKeys.add("FK_headOfFacility");
		foreignKeys.add("FK_superFacility");
		attributes.add(new Tuple<String, String>("name", "String"));
		view = "SELECT T_Facility.PK_Facility,T_Facility.name AS name,";
		view += "T_Facility.FK_headOfFacility AS FK_headOfFacility,";
		view += "T_Facility.FK_superFacility AS FK_superFacility";
		view += "\nFROM T_Facility\n";
		view2queryexpression.put(views.get(6), view);
		table2properties.put(tables.get(6), attributes);
		table2ForeignKey.put(tables.get(6), foreignKeys);
		table2PrimaryKey.put(tables.get(6), "PK_Facility");

		foreignKeys = new ArrayList<String>();
		foreignKeys.add("PK_Facility");

		// Create Faculty
		tables.add("T_Faculty");
		views.add("OV_Faculty");
		view = "SELECT T_Faculty.PK_Facility,T_Facility.name AS name,";
		view += "T_Facility.FK_headOfFacility AS FK_headOfFacility,";
		view += "T_Facility.FK_superFacility AS FK_superFacility";
		view += "\nFROM T_Facility,T_Faculty\n";
		view += "WHERE T_Faculty.PK_Facility=T_Facility.PK_Facility";
		view2queryexpression.put(views.get(7), view);
		table2ForeignKey.put(tables.get(7), foreignKeys);
		table2PrimaryKey.put(tables.get(7), "PK_Facility");

		// Create Chair
		tables.add("T_Chair");
		views.add("OV_Chair");
		view = "SELECT T_Chair.PK_Facility,T_Facility.name AS name,";
		view += "T_Facility.FK_headOfFacility AS FK_headOfFacility,";
		view += "T_Facility.FK_superFacility AS FK_superFacility";
		view += "\nFROM T_Chair,T_Facility\n";
		view += "WHERE T_Chair.PK_Facility=T_Facility.PK_Facility";
		view2queryexpression.put(views.get(8), view);
		table2ForeignKey.put(tables.get(8), foreignKeys);
		table2PrimaryKey.put(tables.get(8), "PK_Facility");

		// Create Institute
		tables.add("T_Institute");
		views.add("OV_Institute");
		view = "SELECT T_Institute.PK_Facility,T_Facility.name AS name,";
		view += "T_Facility.FK_headOfFacility AS FK_headOfFacility,";
		view += "T_Facility.FK_superFacility AS FK_superFacility";
		view += "\nFROM T_Facility,T_Institute\n";
		view += "WHERE T_Institute.PK_Facility=T_Facility.PK_Facility";
		view2queryexpression.put(views.get(9), view);
		table2ForeignKey.put(tables.get(9), foreignKeys);
		table2PrimaryKey.put(tables.get(9), "PK_Facility");

		foreignKeys = new ArrayList<String>();

		// Create Association person - paper
		tables.add("ASS_author_papers");
		foreignKeys.add("FK_author");
		foreignKeys.add("FK_papers");
		table2ForeignKey.put(tables.get(10), foreignKeys);

		foreignKeys = new ArrayList<String>();

		// Create Association member - owner
		tables.add("ASS_member_owner");
		foreignKeys.add("FK_member");
		foreignKeys.add("FK_owner");
		table2ForeignKey.put(tables.get(11), foreignKeys);

		checkCWM();
	}

}
