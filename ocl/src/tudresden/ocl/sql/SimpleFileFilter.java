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
import java.io.*;

/**
 *  A lightweight implementation of a file filter.
 *  @author  Sten Loecher
 */
public class SimpleFileFilter extends javax.swing.filechooser.FileFilter {

    private Set extensions;
    private String description;
    
    /**
     *  Creates a new SimpleFileFilter.
     */
    public SimpleFileFilter(String description, String[] ext) {
        extensions = new HashSet();
        for (int i=0; i<ext.length; i++) extensions.add((ext[i]).toLowerCase()); 
        this.description = description;
    }
    
    public boolean accept(java.io.File file) {
        if(file != null) {
	    if(file.isDirectory()) return true;
            if((getExtension(file) != null) && (extensions.contains(getExtension(file)))) return true;
        } 
        return false;
    }
    
    public java.lang.String getDescription() {
        return new String(description);
    }
    
    public String getExtension(File file) {
	if(file != null) {
	    String filename = file.getName();
	    int i = filename.lastIndexOf('.');
	    if(i>0 && i<filename.length()-1) {
		return filename.substring(i+1).toLowerCase();
	    };
        } 
        return null;
    }
}
