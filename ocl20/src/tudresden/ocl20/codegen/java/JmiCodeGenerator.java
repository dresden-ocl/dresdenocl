/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * OCL 2 Compiler                                                    *
 * Copyright (C) 2002, 2003 Stefan Ocke (stefan.ocke@gmx.de).        *
 * All rights reserved.                                              *
 *                                                                   *
 * This work was done as a diploma project at the Chair for Software *
 * Technology, Dresden University Of Technology, Germany             *
 * (http://www-st.inf.tu-dresden.de).  It is understood that any     *
 * modification not identified as such is not covered by the         *
 * preceding statement.                                              *
 *                                                                   *
 * This work is free software; you can redistribute it and/or        *
 * modify it under the terms of the GNU Library General Public       *
 * License as published by the Free Software Foundation; either      *
 * version 2 of the License, or (at your option) any later version.  *
 *                                                                   *
 * This work is distributed in the hope that it will be useful,      *
 * but WITHOUT ANY WARRANTY; without even the implied warranty of    *
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU *
 * Library General Public License for more details.                  *
 *                                                                   *
 * You should have received a copy of the GNU Library General Public *
 * License along with this library; if not, write to the             *
 * Free Software Foundation, Inc., 59 Temple Place - Suite 330,      *
 * Boston, MA  02111-1307, USA.                                      *
 *                                                                   *
 * To submit a bug report, send a comment, or get the latest news on *
 * this project and other projects, please visit the web site:       *
 * http://www-st.inf.tu-dresden.de/ (Chair home page) or             *
 * http://www-st.inf.tu-dresden.de/ocl/ (project home page)          *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package tudresden.ocl20.codegen;

import tudresden.ocl20.*;

/**
 * This CodeGenerator creates Java-Code that uses the Jmi-Implementation of the OCL basis library.
 * That is, the used factory is JmiOclFactory and the used NonOclType is JmiType
 *
 * @author Stefan Ocke
 */
public class JmiCodeGenerator extends CodeGenerator {
    
    JmiTypeMapping tm = new JmiTypeMapping(this);
    
    /** Creates a new instance of JmiCodeGenerator */
    public JmiCodeGenerator(OclModel model, boolean usePkgPrefix) {
        super(model, usePkgPrefix);
    }
    
    public JmiCodeGenerator(OclModel model) {
        super(model, true);
    }
    
    protected String getFactoryCode(String id)
    {
            return "final "+getPkgPrefix()+"JmiOclFactory "+id+" = "+getPkgPrefix()+"JmiOclFactory.getInstance(model);\n";
    }
    
    protected String getNonOclType(tudresden.ocl20.jmi.ocl.commonmodel.Parameter parameter) {
        return tm.getNonOclType(parameter);
    }
    
}
