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
 *  definitions which serve as SQL92 assertion replacements and ECA trigger templates.
 *  The trigger templates must be further edited by the application developer.
 *  The trigger generator takes care for multiple tables which can be involved in one
 *  integrity view evaluation. Hence, the information about the involved tables must be
 *  provided to the generator. The generator then generates one trigger per involved table.
 *  @author  Sten Loecher
 */
public class TriggerGenerator extends java.lang.Object {
    
    SQLBuilder theSQLBuilder;

    /** 
     *  Creates a new TriggerGenerator
     *  @param a SQLBuilder object 
     */
    public TriggerGenerator(SQLBuilder sqlBuilder) {
        theSQLBuilder = sqlBuilder;
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
        String result[] = new String[involvedTables.length];
        
        for (int i=0; i<involvedTables.length; i++) {    
            theSQLBuilder.reset();
            theSQLBuilder.createAssertionReplacement(triggerName + viewName + "_on_" + involvedTables[i], involvedTables[i], viewName, errorMsg);
            result[i] = theSQLBuilder.getCode();
        }
        
        return result;
    }
    
    /**
     *  @return a number of trigger definitions that can serve as ECA trigger templates
     *          (the number of trigger definitions is equal to the number of involved tables)
     *  @param triggerName the prefix of each trigger name, the actual trigger name will get further information appended
     *  @param viewName the name of the integrity view
     *  @param involvedTables a list of involved tables
     */
    public String[] getECATriggerTemplate(String triggerName, String viewName, String[] involvedTables) {
        String result[] = new String[involvedTables.length];
        
        for (int i=0; i<involvedTables.length; i++) {    
            theSQLBuilder.reset();
            theSQLBuilder.createECATriggerTemplate(triggerName + viewName + "_on_" + involvedTables[i], involvedTables[i], viewName);
            result[i] = theSQLBuilder.getCode();
        }
        
        return result;
    }

}
