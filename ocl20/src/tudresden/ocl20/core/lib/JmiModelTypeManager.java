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
import javax.jmi.reflect.*;
import java.util.*;

/**
 * This class provides some caching for JMI model type descriptions.
 * @author  Stefan Ocke
 */
public class JmiModelTypeManager extends ModelTypeManager {
    
    private RefPackage model;
    private JmiOclFactory factory;
        
    private Map packageCache = new HashMap();
    private Map rcCache = new HashMap();
    
    
    /** Creates a new instance of JmiModelTypeManager */
    public JmiModelTypeManager(JmiOclFactory factory) {
        this.model=factory.getModel();
        this.factory = factory;
    }
    
    
    //seeks for a package in the model. pathname is like "package::subpackage"
    
    
    public RefPackage findPackage(String pathname) {
        try{
            StringTokenizer st = new StringTokenizer(pathname,"::");
            RefPackage rp;
            rp = (RefPackage) packageCache.get(pathname);
            if(rp!=null){return rp;}
            
            rp = model;
            String modelname = (String)model.refMetaObject().refGetValue("name");
            
            while(st.hasMoreTokens()){
                String name = st.nextToken();
                
                //consider both cases:
                //1st : one package clusters all others and is not mentioned in the pathname (like "Core::Class")
                //2nd : the outermost package is mentioned in the classpath (like "UML::Core::Class")
                if(!(rp==model && name.equals(modelname))){
                    rp=rp.refPackage(name);
                }
                
            }
            
            packageCache.put(pathname, rp);
            return rp;
        } catch (InvalidNameException e){
            return null;
        }
        
    }
    
    //seeks for a class in the model. pathname is like "package::subpackage::class"
    protected OclModelType findModelType(String pathname) {
        try{
            int pos = pathname.lastIndexOf("::");
            RefPackage rp = findPackage(pathname.substring(0,pos));
            RefClass rc = rp.refClass(pathname.substring(pos+2));
            
            JmiModelType result = (JmiModelType) rcCache.get(rc);
            if(result == null){
                result = new JmiModelType(pathname, rc, factory);
                rcCache.put(rc, result);
            }
            return result;
        } catch (InvalidNameException e){
            return null;
        }
        
    }
   
    //seeks for an enumeration type in the model. pathname is like "package::subpackage::enumtype"
    protected OclEnumType findEnumType(String pathname) {
        try{
            int pos = pathname.lastIndexOf("::");
            RefPackage rp = findPackage(pathname.substring(0,pos));
            
            JmiEnumType result = new JmiEnumType(pathname, rp);
             
            return result;
        } catch (InvalidNameException e){
            return null;
        }
        
    }
    
    private Map stCache = new HashMap();
    
    public JmiStructType findJmiStructType(String pathname, String [] fieldnames, JmiType [] fieldtypes){
        JmiStructType result;
        result = (JmiStructType) stCache.get(pathname);
        if(result != null){
            return result;
        }
        
        int pos = pathname.lastIndexOf("::");
        RefPackage rp = findPackage(pathname.substring(0,pos));
        String name = pathname.substring(pos+2);
             
        result = new JmiStructType(this.factory, rp, name, fieldnames, fieldtypes);
        
        stCache.put(pathname, result);
        return result;
    }
    
}
