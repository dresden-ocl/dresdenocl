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

package tudresden.ocl20.lib;

/**
 * Represents an argument for a model operation call. 
 * Includes a description of the model type of the parameter and of its kind (in, out, inout)
 * @author  Stefan Ocke
 */
public class OclParameter {
    
    public static final int DIR_IN = 0;
    public static final int DIR_OUT = 1;
    public static final int DIR_INOUT = 2;
    
    private NonOclType nonOclType;   
    private OclRoot value;
    private int direction;
    private String name; //only needed for out and inout parameters
    
    /** Creates a new in parameter */
    public OclParameter(NonOclType nonOclType, OclRoot value) {
        this.nonOclType=nonOclType;
        this.value = value;
        direction = DIR_IN;
        name = null;
    }
       
    /** Creates an inout parameter */
    public OclParameter(String name, NonOclType nonOclType, OclRoot value) {
        this.nonOclType=nonOclType;
        this.value = value;
        direction = DIR_INOUT;
        this.name=name;
    }
    
    /** Creates a new out parameter */
    public OclParameter(String name, NonOclType nonOclType) {
        this.nonOclType=nonOclType;
        this.value = null;
        direction = DIR_OUT;
        this.name=name;
    }
    
    public NonOclType getNonOclType() {
        return this.nonOclType;
    }

    public OclRoot getValue() {
        return this.value;
    }

    public int getDirection() {
        return this.direction;
    }
    
    public String getName() {
        return this.name;
    }
}
