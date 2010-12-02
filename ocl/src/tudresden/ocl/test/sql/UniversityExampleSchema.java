/*
 * UniversityExampleSchema.java
 *
 * Created on 31. Mai 2001, 14:21
 */

package tudresden.ocl.test.sql;

import tudresden.ocl.codegen.decl.*;
import java.util.*;

/**
 * An example schema to test the ILSQLCodeGenerator. 
 * @author  Sten Loecher
 * @version 1.0
 */
public class UniversityExampleSchema extends java.lang.Object implements tudresden.ocl.sql.ORMapping {
    Map classifiersToTables;

    /** Creates new UniversityExampleSchema */
    public UniversityExampleSchema() {
        Table t;
        
        classifiersToTables = new HashMap();
        
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
     * @param classifier a classifier
     * @return a Map that maps the names of all association ends to the
     *         according class name
     */
    public Map associationEnds(String classifier) {
        Map result = new HashMap();
        
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
     * @param classifier a classifier
     * @return a Set containing all names of attributes that are properties of the classifier
     */
    public Set attributes(String classifier) {
        Set result = new HashSet();
        Table t = (Table)classifiersToTables.get(classifier);
        
        if (t != null) {
            result.addAll(t.attributes());
        }
       
        return result; 
    }
    
    /**
     * @return a Set containing the names of all classifiers
     */
    public Set classifiers() {
        return Collections.unmodifiableSet(classifiersToTables.keySet()); 
    }
    
    /**
     * @param classifier a classifier
     * @return a Set containing all the names of direct supertypes of the classifier
     */
    public Set directSupertypeNames(String classifier) {
        Set result = new HashSet();
        
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
     * @param classifier a classifier
     * @return a List with all table objects the classifier was mapped to
     * @see tudresden.ocl.codegen.decl.Table
     */
    public List getClassTables(String classifier) {
        List result = new ArrayList();
        
        result.add(classifiersToTables.get(classifier));
        
        return result; 
    }
    
    /**
     * @param classifier a classifier
     * @param assEnd the name of the association end
     * @return a List that contains guides to to the specified association end from the classifier
     * @see tudresden.ocl.codegen.decl.Guide
     */
    public List guidesToAssociationEnds(String classifier,String assEnd) {
        List result = new ArrayList();
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
     * @param classifier a classifier
     * @return a Set containing all names of operations that are properties of the classifier
     */
    public Set operations(String classifier) {
        Set result = new HashSet();
        
        return result; 
    }
    
    /**
     * @return a List with all table objects created during the object relational mapping
     * @see tudresden.ocl.codegen.decl.Table
     */
    public List tables() {
        List result = new ArrayList();
        
        result.addAll(classifiersToTables.values());
        
        return result; 
    }
    
}
