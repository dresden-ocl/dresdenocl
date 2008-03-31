/*
 * UniversityExampleSchema.java
 * 
 * Copyright (c) 2005 Florian Heidenreich
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
package tudresden.ocl20.tests.codegen.sql.codegen;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import tudresden.ocl20.codegen.sql.codegen.Guide;
import tudresden.ocl20.codegen.sql.orm.ORMapping;
import tudresden.ocl20.codegen.sql.orm.Table;

/**
 * This class represents a SQL-Table implementation of the 
 * UML UniversityExample Schema
 * 
 * <b>Attention:</b> Some of the used methods and classes 
 * in this example are depricated by now.
 *
 *@author Florian Heidenreich
 *@see tudresden.ocl20.codegen.sql.codegen.SQLCodeGenerator 
 */
/*
 * JavaDoc and comments supervised and completed by Claas Wilke in 2007
 */
@SuppressWarnings("deprecation")
public class UniversityExampleSchema implements ORMapping {

	Map<String,Table> classifiersToTables;

    /** 
     * Creates a new UniversityExampleSchema
     */
    public UniversityExampleSchema() {
        Table t;
        
        classifiersToTables = new HashMap<String,Table>();
        
        // initialize tables for all class
        t = new Table("PERSON");
        t.addColumn("",             "int",      "PID",          true);
        t.addColumn("firstName",    "String",   "FIRSTNAME",    false);
        t.addColumn("lastName",     "String",   "LASTNAME",     false);
        t.addColumn("birthDate",    "Date",     "BIRTHDATE",    false);
        t.addColumn("age",          "int",      "AGE",          false);
        t.addColumn("sex",          "String",   "SEX",          false);
        t.addColumn("isUnemployed", "boolean",  "ISUNEMPLOYED", false);
        t.addColumn("isMarried",    "boolean",  "ISMARRIED",    false);
        
        t.addColumn("",             "int",      "SUPERVISOR",   false);
        t.setForeignKey("SUPERVISOR", "PERSON", "PID");
        t.addColumn("",             "int",      "GRADE",        false);
        t.setForeignKey("GRADE", "GRADE", "GID");
        t.addColumn("",             "int",      "THEFACILITY",false);
        t.setForeignKey("THEFACILITY", "FACILTIY", "FID");
        classifiersToTables.put("Person", t);
        
        t = new Table("STUDENT");
        t.addColumn("",             "int",      "PID",          true);
        t.setForeignKey("PID", "PERSON", "PID");
        t.addColumn("matNumber",    "int",      "MATNUMBER",    false);
        t.addColumn("matDate",      "Date",     "MATDATE",      false);
        classifiersToTables.put("Student", t);
        
        t = new Table("EMPLOYEE");
        t.addColumn("",             "int",      "PID",          true);
        t.setForeignKey("PID", "PERSON", "PID");
        t.addColumn("socSecNo",    "String",      "SOCSECNO",   false);
        t.addColumn("wage",        "float",       "WAGE",       false);
        t.addColumn("taxClass",    "String",      "TAXCLASS",   false);
        classifiersToTables.put("Employee", t);
        
        t = new Table("GRADE");
        t.addColumn("",             "int",      "GID",          true);
        t.addColumn("name",         "String",   "NAME",         false);
        t.addColumn("value",        "int",      "VALUE",        false);        
        classifiersToTables.put("Grade", t);
        
        t = new Table("PAPER");
        t.addColumn("",             "int",      "PAID",          true);
        t.addColumn("title",        "String",   "TITLE",         false);
        t.addColumn("edition",      "String",   "EDITION",       false);
        t.addColumn("purpose",      "String",   "PURPOSE",       false);
        t.addColumn("category",     "String",   "CATEGORY",      false);
        t.addColumn("inProgress",   "boolean",  "INPROGRESS",    false); 
        classifiersToTables.put("Paper", t);
        
        t = new Table("FACILITY");
        t.addColumn("",             "int",      "FID",          true);
        t.addColumn("name",         "String",   "NAME",         false);
        
        t.addColumn("",             "int",      "HEADOFFACILITY",   false);
        t.setForeignKey("HEADOFFACILITY", "PERSON", "PID");
        t.addColumn("",             "int",      "SUPERFACILITY",   false);
        t.setForeignKey("SUPERFACILITY", "FACILITY", "FID");
        classifiersToTables.put("Facility", t);
        
        t = new Table("FACULTY");
        t.addColumn("",             "int",      "FID",          true);
        t.setForeignKey("FID", "FACILITY", "FID");
        classifiersToTables.put("Faculty", t);
        
        t = new Table("INSTITUTE");
        t.addColumn("",             "int",      "FID",          true);
        t.setForeignKey("FID", "FACILITY", "FID");
        classifiersToTables.put("Institute", t);
        
        t = new Table("CHAIR");
        t.addColumn("",             "int",      "FID",          true);
        t.setForeignKey("FID", "FACILITY", "FID");
        classifiersToTables.put("Chair", t);        
    }

    /**
     * Returns a Map that maps the names of all association ends to the
     * according class name
     *         
     * @param classifier a classifier
     * @return a Map that maps the names of all association ends to the
     *         according class name
     */
    public Map<String, String> getAssociationEnds(String classifier) {
        Map<String,String> result = new HashMap<String,String>();
        
        if (classifier.equals("Person")) {
            result.put("supervised", "Person");
            result.put("supervisor", "Person");
            result.put("grade", "Grade");
            result.put("paper", "Paper");
            result.put("owner", "Facility");
            result.put("theFacility", "Facility");
        } else if (classifier.equals("Student")) {
        } else if (classifier.equals("Employee")) {
        } else if (classifier.equals("Grade")) {
        } else if (classifier.equals("Paper")) {
            result.put("author", "Person");
        } else if (classifier.equals("Facility")) {
            result.put("subFacility", "Facility");
            result.put("superFacility", "Facility");
            result.put("member", "Person");
            result.put("headOfFacility", "Person");
        } else if (classifier.equals("Faculty")) {
        } else if (classifier.equals("Institute")) {
        } else if (classifier.equals("Chair")) {
        }
        
        return result;            
    }
    
    /**
     * Returns a Set containing all names of attributes that are 
     * properties of the classifier.
     * 
     * @param classifier a classifier
     * @return a Set containing all names of attributes that are properties of the classifier
     */
    public Set<String> getAttributes(String classifier) {
        Set<String> result = new HashSet<String>();
        Table t = (Table)classifiersToTables.get(classifier);
        
        if (t != null) {
            result.addAll(t.attributes());
        }
       
        return result; 
    }
    
    /**
     * @return a Set containing the names of all classifiers
     */
    public Set<String> getClassifiers() {
        return Collections.unmodifiableSet(classifiersToTables.keySet()); 
    }
    
    /**
     * Returns a Set containing all the names of direct 
     * supertypes of the classifier.
     * 
     * @param classifier a classifier
     * @return a Set containing all the names of direct supertypes of the classifier
     */
    public Set<String> getDirectSupertypeNames(String classifier) {
        Set<String> result = new HashSet<String>();
        
        if (classifier.equals("Person")) {            
        } else if (classifier.equals("Student")) {
            result.add("Person");
        } else if (classifier.equals("Employee")) {
            result.add("Person");
        } else if (classifier.equals("Grade")) {
        } else if (classifier.equals("Paper")) {
        } else if (classifier.equals("Facility")) {
        } else if (classifier.equals("Faculty")) {
            result.add("Facility");
        } else if (classifier.equals("Institute")) {
            result.add("Facility");            
        } else if (classifier.equals("Chair")) {
            result.add("Facility");
        }
        
        return result; 
    }
    
    /**
     * Returns a List with all table objects the classifier was mapped to.
     * 
     * @param classifier a classifier
     * @return a List with all table objects the classifier was mapped to
     * @see tudresden.ocl20.codegen.sql.orm.Table
     */
    public List<Table> getClassTables(String classifier) {
        List<Table> result = new ArrayList<Table>();
        
        result.add(classifiersToTables.get(classifier));
        
        return result; 
    }
    
    /**
     * Returns a List that contains guides to to the specified 
     * association end from the classifier.
     * 
     * @param classifier a classifier
     * @param assEnd the name of the association end
     * @return a List that contains guides to to the specified association end from the classifier
     * @see tudresden.ocl20.codegen.sql.codegen.Guide
     */
    public List<Guide> guidesToAssociationEnds(String classifier,String assEnd) {
        List<Guide> result = new ArrayList<Guide>();
        Guide g = new Guide(true);
        
        if (classifier.equals("Person")) {
            if (assEnd.equals("supervised")) {
                g.add("PID","PERSON","SUPERVISOR");
                g.add("PID","PERSON","PID");
            } else if (assEnd.equals("supervisor")) {
                g.add("PID","PERSON","PID");
                g.add("SUPERVISOR","PERSON","PID");                
            } else if (assEnd.equals("grade")) {
                g.add("GID","GRADE","GID");
                g.add("GRADE","PERSON","PID");
            } else if (assEnd.equals("paper")) {
                g.add("PAID","PAPER","PAID");
                g.add("PAID","PAPERS","PID");
                g.add("PID","PERSON","PID");
            } else if (assEnd.equals("owner")) {
                g.add("FID","FACILITY","FID");
                g.add("FID","MEMBERSHIP","PID");
                g.add("PID","PERSON","PID");
            } else if (assEnd.equals("theFacility")) {
                g.add("FID","FACILITY","FID");
                g.add("FID","PERSON","PID");
            }
        }  else if (classifier.equals("Paper")) {
            if (assEnd.equals("author")) {
                g.add("PID","PERSON","PID");
                g.add("PID","PAPERS","PAID");
                g.add("PAID","PAPER","PAID");
            }
        } else if (classifier.equals("Facility")) {
            if (assEnd.equals("subFacility")) {
                g.add("FID","FACILITY","SUPERFACILITY");
                g.add("FID","FACILITY","FID");
            } else if (assEnd.equals("superFacility")) {
                g.add("FID","FACILITY","FID");
                g.add("SUPERFACILITY","FACILITY","FID");
            } else if (assEnd.equals("member")) {
                g.add("PID","PERSON","PID");
                g.add("PID","MEMBERSHIP","FID");
                g.add("FID","FACILITY","FID");
            } else if (assEnd.equals("headOfFacility")) {
                g.add("PID","PERSON","PID");
                g.add("HEADOFFACILITY","FACILITY","FID");
            }
        }
        
        if (g.numberOfSteps() > 0) result.add(g);
        
        return result; 
    }
    
    /**
     * Returns a Set containing all names of operations that 
     * are properties of the classifier.
     * 
     * @param classifier a classifier
     * @return a Set containing all names of operations that are properties of the classifier
     */
    public Set<String> getOperations(String classifier) {
        Set<String> result = new HashSet<String>();
        
        return result; 
    }
    
    /**
     * Returns a List with all table objects created 
     * during the object relational mapping.
     * 
     * @return a List with all table objects created during the object relational mapping
     * @see tudresden.ocl20.codegen.sql.orm.Table
     */
    public List<Table> getTables() {
        List<Table> result = new ArrayList<Table>();
        
        result.addAll(classifiersToTables.values());
        
        return result; 
    }

}
