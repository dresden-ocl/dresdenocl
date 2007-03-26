/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * OCL Compiler                                                      *
 * Copyright (C) 2005, 2006 Ronny Brandt (Ronny_Brandt@gmx.de).      *
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
package tudresden.ocl20.core.lib;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;

import tudresden.ocl20.core.lib.BetterMethodFinder;

public class UmlModelObject extends OclModelObject {
	
	private Object object;
	
	UmlModelObject(Object object, UmlOclFactory factory) {
		super(factory);
		this.object = object;
	}
	
	public OclBoolean isEqualTo(Object o) {
		if (!(o instanceof UmlModelObject))
		{
			System.out.println("JmiModelObject isEqualTo() is called with a non-UmlModelObject parameter");
			return OclBoolean.FALSE;
		}
		
		UmlModelObject other = (UmlModelObject)o;
		
		if (isUndefined())
			return new OclBoolean(0, getUndefinedReason());
		else if (other.isUndefined())
			return new OclBoolean(0, other.getUndefinedReason());
		if (this.object.equals(other.object))
			return OclBoolean.TRUE;
		else
			return OclBoolean.FALSE;
	}
	
	protected Object getFeatureImpl(String name) {
		Class c = object.getClass();
		Field field = null;
		while (c!=null && field==null)
		{
			try {
				field = c.getDeclaredField(name);
			} catch (SecurityException e1) {
				// TODO Auto-generated catch block
				throw new OclException("getFeature("+name+") for "+this+" failed: "+e1.getMessage());
			} catch (NoSuchFieldException e1) {
				// TODO Auto-generated catch block
				// Do nothing
			} finally {
				c = c.getSuperclass();
			}
		}
		if (field == null)
			throw new OclException("getFeature("+name+") for "+this+" failed");
		else
		{
			if (featurelistener!=null)
				featurelistener.onField(field, object);
			field.setAccessible(true);
			try {
				//System.out.println("Feld: " + field.getName());
				return field.get(object);
			} catch (Exception e) {
				throw new OclException("getFeature("+name+") for "+this+" failed: "+e.getMessage());
			}
		}
	}
	
	//TODO: Find method if parameter-types have to be casted
	protected Object getFeatureImpl(String name, Object[] parameters) {
		Class c = object.getClass();
		Method method = null;
		//Class parm_class[];
		List params = Arrays.asList(parameters);
		Class params_class[];
		if (params.isEmpty()) {
			params_class = null;
		} else {
			params_class = new Class[params.size()];
		}
		Iterator it = params.iterator();
		int i = 0;
		while (it.hasNext()) {
			params_class[i] = it.next().getClass();
		}
		try {
			//method = c.getDeclaredMethod(name, params_class);
			method = new BetterMethodFinder(c).findMethod(name, params_class);
			if (featurelistener!=null)
				featurelistener.onMethod(method, object);
			method.setAccessible(true);
			return method.invoke(object, parameters);
		} catch (Exception e) {
			throw new OclException("getFeature("+name+", ...) for "+this+" failed: "+e.getMessage());
		}
	}
	
	public OclBoolean oclInState(OclState state) {
		return OclBoolean.FALSE;
	}
	
	public String toString() {
		return this.object.toString();
	}
	
	Object getObject() {
		return object;
	}

	private static FeatureListener featurelistener=null;

	public static void setFeatureListener(FeatureListener f)
    {
      if(f==null)
        throw new RuntimeException();
      if(featurelistener!=null)
        throw new RuntimeException();
      featurelistener=f;
    }
  
    public static void clearFeatureListener()
    {
      if(featurelistener==null)
        throw new RuntimeException();
      featurelistener=null;
    }

}
