/*
Copyright (C) 2001  Sten Loecher

This library is free software; you can redistribute it and/or
modify it under the terms of the GNU Lesser General Public
License as published by the Free Software Foundation; either
version 2.1 of the License, or (at your option) any later version.

This library is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
Lesser General Public License for more details.

You should have received a copy of the GNU Lesser General Public
License along with this library; if not, write to the Free Software
Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
*/

package tudresden.ocl.sql;

import java.util.*;

/**
 *  A trigger generator that can generate trigger definitions, which can be used to
 *  evaluate integrity views. Two kinds of trigger definitions are possible. Trigger 
 *  definitions which serve as SQL92-assertion-replacements and ECA-trigger-templates.
 *  The trigger templates must be further edited by the application developer.
 *  The trigger generator takes care for multiple tables which can be involved in one
 *  integrity view evaluation. Hence, the information about the involved tables must be
 *  provided to the generator. The generator then generates one trigger per involved table.
 *  @author  Sten Loecher
 *  @see SQLBuilder
 */
public class TriggerGenerator extends java.lang.Object implements SQLDirector {
    
    String theTriggerName;
    String theViewName;
    String theErrorMessage;
    String theInvolvedTables[];
    String theResult[];
    SQLBuilder theSQLBuilder;      
    int kindOfTrigger = 0;
    
    public static int ASSERTION_REPLACEMENT = 0;
    public static int ECA_TRIGGER_TEMPLATE = 1;

    /** 
     *  Creates a new TriggerGenerator
     *  @param a SQLBuilder object 
     */
    public TriggerGenerator(SQLBuilder sqlBuilder) {
        theSQLBuilder = sqlBuilder;
    }
    
    private String[] getAssertionReplacement() {
         String result[] = new String[theInvolvedTables.length];
        
        for (int i=0; i<theInvolvedTables.length; i++) {    
            theSQLBuilder.reset();
            theSQLBuilder.createAssertionReplacement(theTriggerName + theViewName + "_on_" + theInvolvedTables[i], theInvolvedTables[i], theViewName, theErrorMessage);
            result[i] = theSQLBuilder.getCode();
        }
        
        return result;
    }
    
    private String[] getECATriggerTemplate() {
        String result[] = new String[theInvolvedTables.length];
        
        for (int i=0; i<theInvolvedTables.length; i++) {    
            theSQLBuilder.reset();
            theSQLBuilder.createECATriggerTemplate(theTriggerName + theViewName + "_on_" + theInvolvedTables[i], theInvolvedTables[i], theViewName);
            result[i] = theSQLBuilder.getCode();
        }
        
        return result;
    }
    
    /**
     *  @return a number of trigger definitions that can serve as an SQL92 assertion replacement
     *          (the number of trigger definitions is equal to the number of involved tables)
     *  @param triggerName the prefix of each trigger name, the actual trigger name will get further information appended
     *  @param errorMsg the error message that will be thrown by the triggers
     *  @param viewName the name of the integrity view
     *  @param involvedTables a list of involved tables
     */
    public String[] getAssertionReplacement(String triggerName, String errorMsg, String viewName, String[] involvedTables) {
        theTriggerName = triggerName;
        theViewName = viewName;
        theErrorMessage = errorMsg;
        theInvolvedTables = involvedTables;      
        return getAssertionReplacement();
    }
    
    /**
     *  @return a number of trigger definitions that can serve as ECA trigger templates
     *          (the number of trigger definitions is equal to the number of involved tables)
     *  @param triggerName the prefix of each trigger name, the actual trigger name will get further information appended
     *  @param viewName the name of the integrity view
     *  @param involvedTables a list of involved tables
     */
    public String[] getECATriggerTemplate(String triggerName, String viewName, String[] involvedTables) {
        theTriggerName = triggerName;
        theViewName = viewName;
        theInvolvedTables = involvedTables;      
        return getECATriggerTemplate();        
    }
    
    /**
     * The prefix of all trigger names.
     */
    public void setTriggerName(String name) {
        theTriggerName = name;
    }
    
    /**
     * The name of the integrity view.
     */
    public void setViewName(String name) {
        theViewName = name;
    }
    
    /**
     * An error message that will be thrown from assertion replacement triggers.
     */
    public void setErrorMsg(String msg) {
        theErrorMessage = msg;
    }
        
    /**
     * An array of table names that are involved in evaluating the related integrity view.
     */
    public void setInvolvedTables(String[] tables) {
        theInvolvedTables = tables;
    }
    
    /**
     * @param the kind of trigger that should be created 
     */
    public void setKindOfTrigger(int kot) {
        kindOfTrigger = kot;
    }

    /**
     * @param sqlb a builder used by the director to build database specific code
     */
    public void setBuilder(SQLBuilder sqlb) {
        theSQLBuilder = sqlb;
    }
       
    /**
     * Does the construction of the SQL code.
     */
    public void construct() 
    throws IllegalStateException {
        switch (kindOfTrigger) {
            case 0: theResult = getAssertionReplacement(); 
                    break;
            case 1: theResult = getECATriggerTemplate(); 
                    break;
            default: throw new IllegalStateException("Specified kind of Trigger not supported !");
        }
    }
    
    /**
     * @return the resulting SQL code from the construction process
     */
    public String getCode() {
        String result = "";
        
        for (int i=0; i<theResult.length; i++) {
            result += theResult[i] + "\n";
        }
        
        return result;
    }    
}
