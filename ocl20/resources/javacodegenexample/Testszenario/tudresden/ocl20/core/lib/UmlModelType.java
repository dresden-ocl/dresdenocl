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
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class UmlModelType extends OclModelType {
	
	private Class jClass;
	
	UmlModelType(String name, Class jClass, OclFactory factory) {
		super(name, factory);
		this.jClass = jClass;
	}
	
	public static final UmlModelType ANY = new UmlModelType("ANY", null, null);

	public Class getJClass() {
		return jClass;
	}
	
	@Override
	Object getFeatureImpl(String name) {
		Class c = jClass;
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
			field.setAccessible(true);
			try {
				//System.out.println("Feld: " + field.getName());
				return field.get(null);
			} catch (Exception e) {
				throw new OclException("getFeature("+name+") for "+this+" failed: "+e.getMessage());
			}
		}
	}

	@Override
	//TODO: Find method if parameter-types have to be casted
	Object getFeatureImpl(String name, Object[] parameters) {
		Method method = null;
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
			//method = jClass.getDeclaredMethod(name, params_class);
			method = new BetterMethodFinder(jClass).findMethod(name, params_class);
			method.setAccessible(true);
			return method.invoke(null, parameters);
		} catch (Exception e) {
			throw new OclException("getFeature("+name+", ...) for "+this.getName()+"failed: "+e.getMessage());
		}
	}
	
	public boolean equals(Object o) {
		if (!(o instanceof UmlModelType))
			return false;
		else
			return ((UmlModelType)o).jClass.equals(jClass);
	}
	
	boolean isOfType(OclRoot or) {
		if (or.isUndefined())
			return false;
		
		if (!(or instanceof UmlModelObject))
			return false;
		else {
			if (this == ANY)
				return true;
			
			Object mo = ((UmlModelObject)or).getObject();
			return jClass.isInstance(mo);
		}
	}
	
	boolean isOfKind(OclRoot or) {
		if (or.isUndefined()) {
			return true;
		}
		
		if (!(or instanceof UmlModelObject)) {
			return false;
		} 
		else 
		{
			if (this==ANY) {
				return true;
			}
			Object o = ((UmlModelObject)or).getObject();
			return jClass.isInstance(o);
		}
	}
	
	
	//TODO: implement allInstances() for UmlModelType
	public OclSet allInstances() {
		throw new OclException("allInstances() not yet supported for UmlModelType");
	}

}
