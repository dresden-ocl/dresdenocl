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

package tudresden.ocl20.core.util;

import java.lang.reflect.*;
import java.util.*;


/**
 * A visitor implementation that determines the "right" visit-method by using java reflection.
 * @author  Stefan Ocke
 */
public abstract class ReflectiveVisitor {
    
    public static String pathname = "tudresden.ocl20.util.ReflectiveVisitor"; 
    
    String methodName;
    
    /** Creates a new instance of ReflectiveVisitor */
    public ReflectiveVisitor(String methodName) {
        this.methodName=methodName;
    }
      
    public Object visit(Object v) throws NoSuchMethodException, InvocationTargetException{
        Method m = findMethod(v);
        try {       
            return m.invoke(this, new Object[] { v });
        }
        catch ( IllegalAccessException e1 ) { e1.printStackTrace();}
        return null;
    }
    private Method findMethod(Object v) throws NoSuchMethodException{
        Class visitable = v.getClass();
        while (visitable != null) {

            Class visitor = getClass();
            while ( visitor != null && isAncestorOf(pathname, visitor)) {
                Method m; 
                try {
                    m = visitor.getDeclaredMethod(methodName, new Class[]{visitable});
                    return m;         
                } catch ( NoSuchMethodException e ) { }
                
                    List allInterfaces = new ArrayList();
                    allInterfaces.addAll(Arrays.asList(visitable.getInterfaces()));
                    int size=allInterfaces.size();
                    
                    try{
                        for(int i=0; i<size; i++){
                            try{
                                //System.out.println("Interface: "+((Class)allInterfaces.get(i)).getName());
                                List superinterfaces = Arrays.asList(((Class)allInterfaces.get(i)).getInterfaces()); 
                                //System.out.println("Superinterfaces: "+superinterfaces.size());
                                allInterfaces.addAll(superinterfaces);
                                size=allInterfaces.size();
                                //System.out.println("size:"+size);
                                m = visitor.getDeclaredMethod(methodName, new Class[]{(Class)allInterfaces.get(i)});
                                return m;
                            } catch ( NoSuchMethodException e ) { }
                        }
                    }catch(Exception e){
                        e.printStackTrace();
                    }
                
                visitor = visitor.getSuperclass();
            }
            visitable = visitable.getSuperclass();
        }
        throw new NoSuchMethodException(methodName+" ("+ v.getClass().getName()+")");
    }
    
    private boolean isAncestorOf(String ancestorName, Class descendant) {
        try {
            return Class.forName(ancestorName).isAssignableFrom(descendant);
        }
        catch ( ClassNotFoundException e ) { e.printStackTrace(); }
        return false;
    }
    
}
